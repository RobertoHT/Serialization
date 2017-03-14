/**
 * Created by Robert on 10/03/17.
 */
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;

import org.w3c.dom.*;
import javax.xml.parsers.*;

public class CountriesDOMParser {
    public static void setLifeExpectancy(Countries countries, String xml) {
        //Removing the first blank space
        xml = xml.substring(1,xml.length());
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        Document document = null;
        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(new InputSource(new StringReader(xml)));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        NodeList nodeList = document.getDocumentElement().getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            // Check if the current node is an instance of element
            if (node instanceof Element) {
                String iso2code = null;
                String lifeExpectancy = null;

                //We get a list of the child nodes
                NodeList childNodes = node.getChildNodes();
                for (int j = 0; j < childNodes.getLength(); j++) {
                    Node cNode = childNodes.item(j);

                    //Identifying the child tag of wb:data encountered.
                    if (cNode instanceof Element) {
                        //We get the last child of the current tag,
                        //the last child will be the text node
                        if(cNode.getLastChild() != null){
                            String content = cNode.getLastChild().getNodeValue().trim();
                            switch (cNode.getNodeName()) {
                                case "wb:country":
                                    NamedNodeMap attr = cNode.getAttributes();
                                    iso2code = attr.getNamedItem("id").getNodeValue();;
                                    break;
                                case "wb:value":
                                    lifeExpectancy = content;
                                    break;
                            }
                        }
                    }
                }
                if(lifeExpectancy != null){
                    countries.setLifeExpectancy(iso2code, lifeExpectancy);
                }
            }
        }
    }
}
