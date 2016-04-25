import authoringEnvironment.ServerUtility;

/**
 * Created by davidyan on 4/18/16.
 */
public class ServerUtilityTester {

    public static void main(String[] args){
        ServerUtility myUtility = new ServerUtility();
        System.out.println(myUtility.getFileNames());
        myUtility.getFile("davidstest.xml");
        myUtility.endSession();

    }
}
