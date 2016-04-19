import authoringEnvironment.ServerUtility;
import com.jcraft.jsch.SftpException;

/**
 * Created by davidyan on 4/18/16.
 */
public class ServerUtilityTester {

    public static void main(String[] args){
        ServerUtility myUtility = new ServerUtility();
        try {
            System.out.println(myUtility.getFileNames());
        }  catch (SftpException e) {
            e.printStackTrace();
        }

    }
}
