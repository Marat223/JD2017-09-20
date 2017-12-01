package by.it.akhmelev.jd02_08.classwork;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SAX extends DefaultHandler {

    private String tab = "";
    private StringBuilder content = new StringBuilder();

    final static private String XML_PATH =
            "src/by/it/akhmelev/jd02_07/04+XSD.xml";

    public static void main(String[] args) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            SAX sax = new SAX();
            saxParser.parse(XML_PATH, sax);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("startDocument");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("endDocument");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.print(tab + "<" + qName);
        for (int i=0; i<attributes.getLength(); i++){
            String attName=attributes.getLocalName(i);
            String attValue=attributes.getValue(i);
            System.out.print(" "+attName+"="+attValue);
        }

        tab = tab + "\t";
        System.out.println(">");
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        String str=content.toString().trim();
        if (str.length() > 0)
            System.out.println(tab + str);
        content.setLength(0);
        tab = tab.substring(1);
        System.out.println(tab + "</" + qName + ">");
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        content.append(new String(ch, start, length));
    }
}
