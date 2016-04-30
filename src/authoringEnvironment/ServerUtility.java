package authoringEnvironment;

import com.jcraft.jsch.*;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.io.*;
import java.util.*;

/**
 * Created by davidyan on 4/18/16.
 * Simple server utility that allows users to save to and pull files from a server
 */
public class ServerUtility {
    private static final String DIRECTORY = "VoogaSalad";
    private static final  String HOSTNAME = "login.cs.duke.edu";
    private String myUsername;
    private String myPassword;
    private JSch ssh;
    private Properties myConfig;
    private Session mySession;
    private Channel myChannel;
    private ChannelSftp mySftp;


    public ServerUtility(){
        signInDialog();
        ssh = new JSch();
        myConfig = new Properties();
        myConfig.put("StrictHostKeyChecking","no");
        try {
            mySession = ssh.getSession(myUsername, HOSTNAME, 22);
        } catch (JSchException e) {
            //TODO: Good error handling
        }
        mySession.setConfig(myConfig);
        mySession.setPassword(myPassword);
        try {
            mySession.connect();
            myChannel = mySession.openChannel("sftp");
            myChannel.connect();
        } catch (JSchException e) {
            //TODO: Good error handling
        }
        mySftp = (ChannelSftp) myChannel;
        try {
            mySftp.cd(DIRECTORY);
        }catch (SftpException e) {
            //TODO: Good error handling
        }
    }

    public void createDirectory(String teamDirectoryName){
        try {
            mySftp.mkdir(teamDirectoryName);
            mySftp.cd(teamDirectoryName);
        } catch (SftpException e) {
            //TODO: Good error handling
        }
    }

    public List<String> getFileNames(){
        Vector files = null;
        try {
            files = mySftp.ls("*");
        } catch (SftpException e) {
            //TODO: Good error handling
        }
        ArrayList<ChannelSftp.LsEntry> list = new ArrayList<>(files);
        List<String> myFileNames = new ArrayList<>();
        list.forEach(file->{
            myFileNames.add(file.getFilename());
        });
        return myFileNames;

    }

    private List<ChannelSftp.LsEntry> getListOfFiles(){
        Vector files = null;
        try {
            files = mySftp.ls("*");
        } catch (SftpException e) {
            //TODO: Good error handling
        }
        ArrayList<ChannelSftp.LsEntry> myList = new ArrayList<>(files);
        endSession();

        return myList;

    }

    public List<ChannelSftp.LsEntry> getAllFiles(){
        Vector files = null;
        try {
            files = mySftp.ls("*");
        } catch (SftpException e) {
            //TODO: Good error handling
        }
        ArrayList<ChannelSftp.LsEntry> myFilesList = new ArrayList<>(files);

        return myFilesList;
    }

    public void addFile(File myFile){
        List<String> myFileNames = getFileNames();
        if(myFileNames.contains(myFile.getName())){
            System.out.println("File Name Already Exists");

        }else {
            try {
                mySftp.put(new FileInputStream(myFile), myFile.getName());
            } catch (SftpException e) {
                //TODO: Throw up an error dialog

            } catch (FileNotFoundException e) {
                //TODO: Throw up an error dialog
            }
        }
    }

    public void getFile(String fileName) {

        try {
            File folder = new File("SavedGameData/SavedGames/");
            File[] listOfFiles = folder.listFiles();
            for(File file: listOfFiles){
                if(file.getName().equals(fileName)){
                    //System.out.println(file.getName().split(".xml")[0]);
                    mySftp.get(fileName,"SavedGameData/DefaultGames/"+file.getName());
                }
            }
            mySftp.get(fileName,"SavedGameData/DefaultGames/"+fileName);

        }
        catch (SftpException e) {
            //TODO: Good error handling
        }
    }

    public void endSession(){
        myChannel.disconnect();
        mySession.disconnect();

    }

    private void signInDialog(){
        Dialog<Pair<String, String>> dialog = new Dialog<Pair<String,String>>();
        dialog.setTitle("Login to Server");
        dialog.setHeaderText("Save to Server");

        ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField username = new TextField();
        username.setPromptText("Username");
        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        grid.add(new Label("Username:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(password, 1, 1);

        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        username.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        Platform.runLater(() -> username.requestFocus());

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(username.getText(), password.getText());
            }
            return null;
        });

        Optional<Pair<String,String>> result = dialog.showAndWait();

        result.ifPresent(usernamePassword -> {
            myUsername = usernamePassword.getKey();
            myPassword = usernamePassword.getValue();
        });
    }
}