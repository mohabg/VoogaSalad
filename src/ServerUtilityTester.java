import authoringEnvironment.ServerUtility;
import com.jcraft.jsch.SftpException;

import java.io.IOException;

/**
 * Created by davidyan on 4/18/16.
 */
public class ServerUtilityTester {

    public static void main(String[] args){
        ServerUtility myUtility = new ServerUtility();
        try {
//            myUtility.addFile(new File("SavedGameData/SavedGames/jkjk.xml"));
            myUtility.getFile("jkjk");
        }  catch (SftpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
