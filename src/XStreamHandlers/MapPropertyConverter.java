package XStreamHandlers;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.collections.CollectionConverter;
import com.thoughtworks.xstream.converters.collections.MapConverter;
import com.thoughtworks.xstream.mapper.Mapper;

import javafx.beans.binding.ListExpression;
import javafx.beans.binding.MapExpression;
import javafx.beans.property.ListProperty;
import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.collections.FXCollections;

public class MapPropertyConverter extends MapConverter implements Converter {

    public MapPropertyConverter(Mapper mapper) {
        super(mapper);
    }

    @Override
    public boolean canConvert(Class type) {
        return MapProperty.class.isAssignableFrom(type);
    }
    
    @Override
    protected Object createCollection(Class type) {
        if (type == MapExpression.class) {
            return new SimpleMapProperty(FXCollections.observableHashMap());
        }
        return super.createCollection(type);
    }
}