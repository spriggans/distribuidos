/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author Keyma
 */
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import javax.ws.rs.core.MediaType;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class ClienteGeo {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

        
        ClienteGeo cg = new ClienteGeo();
        ArrayList<String> geoIp = cg.getGeoIp("190.203.253.76");
        System.out.println("Esta es la lista resultado"+geoIp.get(0)+"/"+geoIp.get(1));
    }

    public static String getCharacterDataFromElement(Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return cd.getData();
        }
        return "";
    }

    public ArrayList<String> getGeoIp(String ip) throws ParserConfigurationException, SAXException, IOException {
        String URI = "http://www.webservicex.net/geoipservice.asmx/GetGeoIP?IPAddress="+ip;
        Client cliente = Client.create();
        ArrayList<String> respuesta = new ArrayList<String>();


        WebResource recurso = cliente.resource(URI);
        String result = recurso.accept(MediaType.APPLICATION_XML).get(String.class);

        System.out.println("---RESULTADOS:%s---\n");
        System.out.println(result);



        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(result));

        Document doc = db.parse(is);
        NodeList nodes = doc.getElementsByTagName("GeoIP");

        for (int i = 0; i < nodes.getLength(); i++) {
            Element element = (Element) nodes.item(i);

            NodeList name = element.getElementsByTagName("CountryName");
            Element line = (Element) name.item(0);
            System.out.println("Name: " + getCharacterDataFromElement(line));
            respuesta.add(getCharacterDataFromElement(line));
            NodeList title = element.getElementsByTagName("CountryCode");
            line = (Element) title.item(0);
            System.out.println("Code: " + getCharacterDataFromElement(line));
            respuesta.add(getCharacterDataFromElement(line));
        }
        return respuesta;
    }
}
