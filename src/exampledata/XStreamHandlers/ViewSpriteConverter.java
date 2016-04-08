package exampledata.XStreamHandlers;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import authoringEnvironment.ViewSprite;

public class ViewSpriteConverter implements Converter{

	@Override
	public boolean canConvert(Class object) {
		return object.equals(ViewSprite.class);
	}

	@Override
	public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {
		ViewSprite vs = (ViewSprite) value;
		writer.startNode("imagePath");
		writer.setValue(vs.getMyImage());
		writer.endNode();
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		reader.moveDown();
		String imagePath = reader.getValue();
		ViewSprite vs = new ViewSprite(imagePath);
		reader.moveUp();
		return vs;
	}
	
}
