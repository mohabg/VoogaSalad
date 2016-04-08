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
		writer.startNode("image");
		writer.setValue(vs.getMyImage() +  " " + vs.getTranslateX() + " " + vs.getTranslateY() + " " + vs.getFitWidth() + " " + vs.getFitHeight());
		writer.endNode();
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		reader.moveDown();
		String[] imageData = reader.getValue().split(" ");
		System.out.println(reader.getValue());
		ViewSprite vs = new ViewSprite(imageData[0]);
		vs.setTranslateX(Double.parseDouble(imageData[1]));
		vs.setTranslateY(Double.parseDouble(imageData[2]));
		vs.setFitHeight(Double.parseDouble(imageData[3]));
		vs.setFitHeight(Double.parseDouble(imageData[4]));
		
		reader.moveUp();
		return vs;
	}
	
}
