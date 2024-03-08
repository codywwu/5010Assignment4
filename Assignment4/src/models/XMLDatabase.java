package models;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLDatabase {

  private static Document document;
  private String fileName;

  //Get the Document Builder
  public XMLDatabase() {
    readLocalFile();
  }

  private void readLocalFile() {
    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      document = builder.parse(new File("Assignment4/data.xml"));
      document.getDocumentElement().normalize();
    } catch (ParserConfigurationException | SAXException | IOException e) {
      e.printStackTrace();
    }
  }

  public static NodeList getUsersFromDocument() {
    return getUsersFromDocument(document);
  }

  private static NodeList getUsersFromDocument(Document newDocument) {
    return newDocument.getElementsByTagName("user");
  }

  public static Boolean checkName(String inputName) {
    NodeList usernames = getUsersFromDocument();
    for (int i = 0; i < usernames.getLength(); i++) {
      Node laptop = usernames.item(i);
      if (laptop.getNodeType() == Node.ELEMENT_NODE) {
        Element laptopElement = (Element) laptop;
        String name = laptopElement.getAttribute("name");
        if (name.equals(inputName)) {
          return true;
        }
      }
    }
    return false;
  }

  public void addUser(String username) {
    Element newUser = document.createElement("user");
    newUser.setAttribute("name", username);

    Element newPassword = document.createElement("password");
    newPassword.setAttribute("value", "");
    newUser.appendChild(newPassword);

    Element portfolios = document.createElement("portfolios");
    newUser.appendChild(portfolios);

    document.getDocumentElement().appendChild(newUser);

    saveChanges();
  }

  public List<Portfolio> setPortfoliosByUsername(String username) {
    List<Portfolio> portfoliosList = new ArrayList<>();

    NodeList userList = document.getElementsByTagName("user");
    for (int i = 0; i < userList.getLength(); i++) {
      Node userNode = userList.item(i);
      if (userNode.getNodeType() == Node.ELEMENT_NODE) {
        Element userElement = (Element) userNode;
        String name = userElement.getAttribute("name");
        if (name.equals(username)) {
          NodeList portfolios = userElement.getElementsByTagName("portfolio");
          for (int j = 0; j < portfolios.getLength(); j++) {
            Node portfolioNode = portfolios.item(j);
            if (portfolioNode.getNodeType() == Node.ELEMENT_NODE) {
              Element portfolioElement = (Element) portfolioNode;
              String portfolioName = portfolioElement.getAttribute("name");
              List<Stock> stocksList = new ArrayList<>();
              NodeList stocks = portfolioElement.getElementsByTagName("stock");
              for (int k = 0; k < stocks.getLength(); k++) {
                Node stockNode = stocks.item(k);
                if (stockNode.getNodeType() == Node.ELEMENT_NODE) {
                  Element stockElement = (Element) stockNode;
                  String stockName = stockElement.getAttribute("name");
                  int stockValue = Integer.parseInt(stockElement.getAttribute("value"));
                  String stockTime = stockElement.getElementsByTagName("time").item(0).getAttributes().getNamedItem("value").getNodeValue();
                  //new Stock
                  stocksList.add(new Stock(stockName /*stockValue*/, stockTime));
                }
              }
              //new portfolio
              portfoliosList.add(new Portfolio(portfolioName,0/*stocksList*/));
            }
          }
          break;
        }
      }
    }
    return portfoliosList;
  }

  private void saveChanges() {
    try {
      Transformer transformer = TransformerFactory.newInstance().newTransformer();
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

      DOMSource source = new DOMSource(document);
      StreamResult result = new StreamResult(new File("Assignment4/data.xml"));
      transformer.transform(source, result);
    } catch (TransformerException e) {
      e.printStackTrace();
    }
  }

  // add portfolio
  // implement each method to the program

  public static void main(String[] args) {
    XMLDatabase xmlDatabase = new XMLDatabase();
    NodeList laptops = xmlDatabase.getUsersFromDocument();
    System.out.println("UserNames:");
    for (int i = 0; i < laptops.getLength(); i++) {
      Node laptop = laptops.item(i);
      if (laptop.getNodeType() == Node.ELEMENT_NODE) {
        Element laptopElement = (Element) laptop;
        String laptopName = laptopElement.getAttribute("name");
        System.out.println(laptopName);
      }
    }
    System.out.println(xmlDatabase.checkName("Ahri"));

//    XMLDatabase xmlDatabase = new XMLDatabase();
//    List<Portfolio> portfolios = xmlDatabase.setPortfoliosByUsername("aaa");
//    for (Portfolio portfolio : portfolios) {
//      System.out.println("Portfolio Name: " + portfolio.getName());
//      for (Stock stock : portfolio.getStocks()) {
//        System.out.println("  Stock Name: " + stock.getName());
//        System.out.println("  Stock Value: " + stock.getValue());
//        System.out.println("  Stock Time: " + stock.getTime());
//      }
  }
}
