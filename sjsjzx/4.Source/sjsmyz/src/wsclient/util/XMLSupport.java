package wsclient.util;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

import org.apache.log4j.Logger;
import org.exolab.castor.xml.schema.Schema;
import org.exolab.castor.xml.schema.reader.SchemaReader;
import org.exolab.castor.xml.schema.writer.SchemaWriter;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XMLSupport {
	private static Logger log = Logger.getLogger(XMLSupport.class);

	private XMLSupport() {
	}

	public static String outputString(Document doc) {
		XMLOutputter xmlWriter = new XMLOutputter("    ", true);

		return xmlWriter.outputString(doc);
	}

	public static String outputString(Element elem) {
		XMLOutputter xmlWriter = new XMLOutputter("    ", true);
		return xmlWriter.outputString(elem);
	}

	public static Document readXML(String xml) throws JDOMException {
		return readXML(new StringReader(xml));
	}

	public static Document readXML(Reader reader) throws JDOMException {
		SAXBuilder xmlBuilder = new SAXBuilder(false);

		Document doc = xmlBuilder.build(reader);

		return doc;
	}

	public static Schema readSchema(Reader reader) throws IOException {

		InputSource inputSource = new InputSource(reader);

		SchemaReader schemaReader = new SchemaReader(inputSource);
		schemaReader.setValidation(false);

		Schema schema = schemaReader.read();

		return schema;
	}

	public static Element convertSchemaToElement(Schema schema)
			throws SAXException, IOException, JDOMException {

		String content = outputString(schema);

		if (content != null) {

			Document doc = readXML(new StringReader(content));

			return doc.getRootElement();
		}

		return null;
	}

	public static Schema convertElementToSchema(Element element)
			throws IOException {

		String content = outputString(element);
		log.debug("�ڷ���convertElementToSchema(Element element)��,"
				+ "�Ѿ���Dom�͵�<xsd:schema>Ԫ��ת����"
				+ "JDom����Element element,��ӡschema���νṹ:");
		log.debug(content);
		if (content != null) {
			return readSchema(new StringReader(content));
		}

		return null;
	}

	public static String outputString(Schema schema) throws IOException,
			SAXException {
		StringWriter writer = new StringWriter();
		SchemaWriter schemaWriter = new SchemaWriter(writer);
		schemaWriter.write(schema);
		return writer.toString();
	}
}
