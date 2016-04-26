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
        signInDialog("dwy3","Davidspassword");
        ssh = new JSch();
        myConfig = new Properties();
        myConfig.put("StrictHostKeyChecking","no");
        try {
            mySession = ssh.getSession(myUsername, HOSTNAME, 22);
        } catch (JSchException e) {
        }
        mySession.setConfig(myConfig);
        mySession.setPassword(myPassword);
        try {
            mySession.connect();
            myChannel = mySession.openChannel("sftp");
            myChannel.connect();
        } catch (JSchException e) {
        }
        mySftp = (ChannelSftp) myChannel;
        try {
            mySftp.cd(DIRECTORY);
        }catch (SftpException e) {
        }
    }

    public void createDirectory(String directoryName){
        try {
            mySftp.mkdir(directoryName);
            mySftp.cd(directoryName);
        } catch (SftpException e) {
        }
    }

    public List<String> getFileNames(){
        Vector files = null;
        try {
            files = mySftp.ls("*");
        } catch (SftpException e) {
            e.printStackTrace();
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
            e.printStackTrace();
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
        }
    }




    public void endSession(){
        myChannel.disconnect();
        mySession.disconnect();

    }


    public static void main(String[] args) throws JSchException, SftpException, IOException {

//
//        java.util.Properties config = new java.util.Properties();
//        config.put("StrictHostKeyChecking", "no");
//
//        JSch ssh = new JSch();
//        Session session = ssh.getSession(login, hostname, 22);
//        session.setConfig(config);
//        session.setPassword(password);
//        session.connect();
//        Channel channel = session.openChannel("sftp");
//        channel.connect();
//
//        ChannelSftp sftp = (ChannelSftp) channel;
//        sftp.mkdir("newfolder");
//        sftp.cd(directory);
//        Vector files = sftp.ls("*");
//        System.out.printf("Found %d files in dir %s%n", files.size(), directory);
//        ArrayList<ChannelSftp.LsEntry> list = new ArrayList<>(files);
//
//        for (ChannelSftp.LsEntry file : list) {
//            if (file.getAttrs().isDir()) {
//                continue;
//            }
//            System.out.printf("Reading file : %s%n", file.getFilename());
//            BufferedReader bis = new BufferedReader(new InputStreamReader(sftp.get(file.getFilename())));
//            String line = null;
//            while ((line = bis.readLine()) != null) {
//                System.out.println(line);
//            }
//            bis.close();
//        }


    }


    private void signInDialog(String user, String pass){
        Dialog<Pair<String, String>> dialog = new Dialog<Pair<String,String>>();
        dialog.setTitle("Login Dialog");
        dialog.setHeaderText("Look, a Custom Login Dialog");

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
            myUsername = user;
            myPassword = pass;
        });
    }
}

