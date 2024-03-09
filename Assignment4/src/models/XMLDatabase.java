package models;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
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

  public static void main(String[] args) throws TransformerConfigurationException {
    //TODO GetName from XML
//    XMLDatabase xmlDatabase = new XMLDatabase();
//    NodeList laptops = xmlDatabase.getUsersFromDocument();
//    System.out.println("UserNames:");
//    for (int i = 0; i < laptops.getLength(); i++) {
//      Node laptop = laptops.item(i);
//      if (laptop.getNodeType() == Node.ELEMENT_NODE) {
//        Element laptopElement = (Element) laptop;
//        String laptopName = laptopElement.getAttribute("name");
//        System.out.println(laptopName);
//      }
//    }
//    System.out.println(xmlDatabase.checkName("Ahri"));
    //TODO
//    XMLDatabase xmlDatabase = new XMLDatabase();
//    List<Portfolio> portfolios = xmlDatabase.setPortfoliosByUsername("aaa");
//    for (Portfolio portfolio : portfolios) {
//      System.out.println("Portfolio Name: " + portfolio.getName());
//      for (Stock stock : portfolio.getStocks()) {
//        System.out.println("  Stock Name: " + stock.getName());
//        System.out.println("  Stock Value: " + stock.getValue());
//        System.out.println("  Stock Time: " + stock.getTime());
//      }
    //TODO create new XML by company name.
    XMLDatabase xmlDatabase = new XMLDatabase();
    xmlDatabase.createXMLbyCompanyInfo("KO");
  }

  public void createXMLbyCompanyInfo(String companyName) {
    String apiKey = "W0M1JOKC82EZEQA8";
    String stockSymbol = companyName; // ticker symbol for Google
    URL url = null;
    String fileName = stockSymbol + "_StockData.xml";
    File file = new File(fileName);

    if (file.exists()) {
      System.out.println("XML file for " + stockSymbol + " already exists. No new file created.");
      return; // Exit the program if the file exists
    }

    if (companySymbolExists(apiKey, stockSymbol)) {
      System.out.println("Company symbol " + stockSymbol + " does not exist or no data available.");
      return;
    }


    try {
      url = new URL("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY"
              + "&outputsize=full" + "&symbol=" + stockSymbol + "&apikey=" + apiKey + "&datatype=csv");
    } catch (MalformedURLException e) {
      throw new RuntimeException("the alphavantage API has either changed or no longer works");
    }

    try (InputStream in = url.openStream();
         BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
      String line;
      reader.readLine(); // Skip the header line

      DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
      Document document = documentBuilder.newDocument();
      Element rootElement = document.createElement("StockData");
      document.appendChild(rootElement);

      while ((line = reader.readLine()) != null) {
        String[] data = line.split(",");

        // Assuming data follows the format: timestamp,open,high,low,close,volume
        if (data.length >= 6) {
          Element record = document.createElement("Record");
          rootElement.appendChild(record);

          Element date = document.createElement("Date");
          date.appendChild(document.createTextNode(data[0]));
          record.appendChild(date);

          Element open = document.createElement("Open");
          open.appendChild(document.createTextNode(data[1]));
          record.appendChild(open);

          Element high = document.createElement("High");
          high.appendChild(document.createTextNode(data[2]));
          record.appendChild(high);

          Element low = document.createElement("Low");
          low.appendChild(document.createTextNode(data[3]));
          record.appendChild(low);

          Element close = document.createElement("Close");
          close.appendChild(document.createTextNode(data[4]));
          record.appendChild(close);

          Element volume = document.createElement("Volume");
          volume.appendChild(document.createTextNode(data[5]));
          record.appendChild(volume);
        }
      }

      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      DOMSource domSource = new DOMSource(document);
      StreamResult streamResult = new StreamResult(new File(fileName));
      transformer.transform(domSource, streamResult);

      System.out.println("XML file created successfully for stock: " + stockSymbol);
    } catch (IOException | TransformerException | ParserConfigurationException e) {
      e.printStackTrace();
    }
  }

  private static boolean companySymbolExists(String apiKey, String stockSymbol) {
    String urlTemplate = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=%s&apikey=%s";
    String queryUrl = String.format(urlTemplate, stockSymbol, apiKey);

    try (InputStream in = new URL(queryUrl).openStream();
         BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
      String response = reader.readLine(); // Read the first line of the response
      // Simple check to see if response contains "Global Quote" which indicates valid data
      return response != null && response.contains("Global Quote");
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }

}
