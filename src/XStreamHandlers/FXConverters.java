package XStreamHandlers;

import java.util.List;
import java.util.Map;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;

import javafx.beans.property.ListProperty;
import javafx.beans.property.MapProperty;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

/**
 * Utility to configure a xStream with JavaFX property converters.<br>
 * <br>
 * Created at 17/09/11 11:18.<br>
 *
 * @author Antoine Mischler <antoine@dooapp.com>
 */
public class FXConverters {

    public static void configure(XStream xStream) {
        xStream.registerConverter(new StringPropertyConverter(xStream.getMapper()));
        xStream.registerConverter(new BooleanPropertyConverter(xStream.getMapper()));
        xStream.registerConverter(new ObjectPropertyConverter(xStream.getMapper()));
        xStream.registerConverter(new DoublePropertyConverter(xStream.getMapper()));
        xStream.registerConverter(new LongPropertyConverter(xStream.getMapper()));
        xStream.registerConverter(new IntegerPropertyConverter(xStream.getMapper()));
        //xStream.registerConverter(new ObservableListConverter(xStream.getMapper()));
        //xStream.registerConverter(new ObservableMapConverter(xStream.getMapper()));
        xStream.registerConverter(new ListPropertyConverter(xStream.getMapper()));
        xStream.registerConverter(new MapPropertyConverter(xStream.getMapper()));
        xStream.registerConverter(new ConverterWrapper(lookupTypeConverter(xStream, Map.class), ObservableMap.class));
        xStream.registerConverter(new ConverterWrapper(lookupTypeConverter(xStream, List.class), ObservableList.class));
        xStream.registerConverter(new ConverterWrapper(lookupTypeConverter(xStream, Map.class), MapProperty.class));
        xStream.registerConverter(new ConverterWrapper(lookupTypeConverter(xStream, List.class), ListProperty.class));
        xStream.registerConverter(new ViewSpriteConverter());
    }
    
    private static Converter lookupTypeConverter(XStream xStream, Class clazz) {
    	          return xStream.getConverterLookup().lookupConverterForType(clazz);
    	    }

}