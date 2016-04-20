package XStreamHandlers;

import com.sun.javafx.collections.ObservableMapWrapper;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.collections.MapConverter;
import com.thoughtworks.xstream.mapper.Mapper;

import javafx.beans.binding.MapExpression;
import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

public class ObservableMapConverter extends MapConverter implements Converter {

    public ObservableMapConverter(Mapper mapper) {
        super(mapper);
    }

    @Override
    public boolean canConvert(Class type) {
        return ObservableMap.class.isAssignableFrom(type);
    }
    
    @Override
    protected Object createCollection(Class type) {
        if (type == ObservableMapWrapper.class) {
            return FXCollections.observableHashMap();
        }
        return super.createCollection(type);
    }
}