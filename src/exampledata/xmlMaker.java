package exampledata;

import com.thoughtworks.xstream.XStream;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;

import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.InputSource;

import com.thoughtworks.xstream.io.xml.StaxDriver;

import exampledata.XStreamHandlers.FXConverters;

import java.io.*;

public class xmlMaker {

	public static void main(String[] args) throws IOException {
		Level myLevel = new Level(1, false);
		XStream xstream = new XStream(new StaxDriver());
		xstream.setMode(XStream.ID_REFERENCES);
		FXConverters.configure(xstream);

		// Object to XML Conversion
		String xml = xstream.toXML(myLevel);
		FileWriter fw = new FileWriter("my-file.xml");
		try {
			fw.write(xml);
			fw.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// fos = new FileOutputStream("src/exampledata/myLevelTest"+ ".xml");
		// try{
		// fos.write("<?xml version=\"1.0\"?>".getBytes("UTF-8"));
		// byte[] bytes = xml.getBytes("UTF-8");
		// fos.write(bytes);
		// fos.close();
		// }catch(Exception e){
		// System.err.println("Error in XML Write: " + e.getMessage());
		//
		// }

		// File fi = new
		// File("voogasalad_TheDuballers/src/exampledata/myLevelTest.xml");
		Level readLevel = (Level) xstream.fromXML(xml);

	}

	public static String formatXml(String xml) {
		try {
			Transformer serializer = SAXTransformerFactory.newInstance().newTransformer();

			serializer.setOutputProperty(OutputKeys.INDENT, "yes");
			serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

			Source xmlSource = new SAXSource(new InputSource(new ByteArrayInputStream(xml.getBytes())));
			StreamResult res = new StreamResult(new ByteArrayOutputStream());

			serializer.transform(xmlSource, res);

			return new String(((ByteArrayOutputStream) res.getOutputStream()).toByteArray());

		} catch (Exception e) {
			return xml;
		}
	}
}
