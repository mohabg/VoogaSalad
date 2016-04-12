package resources;

/**
 * Created by davidyan on 4/11/16.
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class ResourcesReader {
    private static final String FOLDER = "resources/";
    private static final String SUFFIX = ".properties";
    private static final String LOAD_FAIL = "Could not load lang file: %s";
    Properties myProperties;
    private InputStream myFileStream;

    public ResourcesReader(String behaviorType){
        String filename = FOLDER + behaviorType + SUFFIX;
        try {
            myProperties = new Properties();
            myFileStream = getClass().getClassLoader().getResourceAsStream(filename);
            myProperties.load(myFileStream);

        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch(IOException e2){
            e2.printStackTrace();
        }

    }


    public List<String> getAllKeys() throws IOException{
        ResourcesReader myReader = new ResourcesReader("AttackBehaviors");
        List<String> behaviorList = new ArrayList<>();
        Set<Object> keys = myProperties.keySet();
        for(Object k:keys){
            String key = (String)k;
            behaviorList.add(myReader.getString(key));
        }
        return behaviorList;
    }

    public String getString (String key) {
        return myProperties.getProperty(key);
    }

}

