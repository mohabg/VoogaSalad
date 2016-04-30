package XStreamHandlers;

import com.sun.javafx.collections.ObservableListWrapper;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.collections.CollectionConverter;
import com.thoughtworks.xstream.mapper.Mapper;

import javafx.beans.binding.ListExpression;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ListPropertyConverter extends CollectionConverter implements Converter {

    public ListPropertyConverter(Mapper mapper) {
        super(mapper);
    }

    @Override
    public boolean canConvert(Class type) {
        return ListProperty.class.isAssignableFrom(type);
    }
    
    @Override
    protected Object createCollection(Class type) {
        if (type == ListExpression.class) {
            return new SimpleListProperty(FXCollections.observableArrayList());
        }
        return super.createCollection(type);
    }
}


