package authoringEnvironment.settingsWindow;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;

/**
 * Created by davidyan on 4/11/16.
 */
public class ExampleReflection {

    public static void main(String[] args){
        try {
            Class clazz = Class.forName("authoringEnvironment.ExampleAttack");
            for(Constructor constructor: clazz.getConstructors()){
                for(Parameter param: constructor.getParameters()){
                    if(param.getType().equals(Double.TYPE)){
                        System.out.println("yes");
                    }else{
                        System.out.println("NO");
                    }
                    System.out.println(param.getType());
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
