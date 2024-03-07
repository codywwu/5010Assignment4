package models;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import org.xml.sax.SAXException;

public class XMLDatabase {
  List<String> userlist=new ArrayList<String>();

  public List getUserNames(String[] args) {

    //Get the Document Builder

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    try {
      DocumentBuilder builder = factory.newDocumentBuilder();

      // Get Document
      Document document = builder.parse(new File("Assignment4/data.xml"));

      // Normalize the xml structure
      document.getDocumentElement().normalize();

      // Get all the element by the tag name
      NodeList userList = document.getElementsByTagName("user");
      for(int i = 0; i <userList.getLength(); i++) {
        Node laptop = userList.item(i);
        if(laptop.getNodeType() == Node.ELEMENT_NODE) {
          Element laptopElement = (Element) laptop;

//          System.out.println("Laptop Name: " + laptopElement.getAttribute("name"));

//          NodeList laptopDetails =  laptop.getChildNodes();
//          for(int j = 0; j < laptopDetails.getLength(); j++){
//            Node detail = laptopDetails.item(j);
//            if(detail.getNodeType() == Node.ELEMENT_NODE) {
//              Element detailElement = (Element) detail;
//              System.out.println("     " + detailElement.getTagName() + ": "
//                  + detailElement.getAttribute("value"));
//            }
//
//          }

        }
      }
    } catch (ParserConfigurationException | SAXException | IOException e) {
      e.printStackTrace();
    }
    return userlist;
  }

}
