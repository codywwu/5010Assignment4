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
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * XML Database class, that will process all the xml related.
 */
public class XMLDatabase {

  private static Document document;
  public static String highStock;
  public static String lowStock;

  /**
   * Constructor for XML database, read a file.
   */
  public XMLDatabase() {
    readLocalFile();
  }

  /**
   * read the local file of Userdata.
   */
  private void readLocalFile() {
    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      document = builder.parse(new File("../res/data.xml"));
      document.getDocumentElement().normalize();
    } catch (ParserConfigurationException | SAXException | IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * read from a XML file that would return a portfolio.
   * @param fileName name of the file to be import.
   * @return a portfolio imported.
   */
  public Portfolio readImportedFile(String fileName) {
    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      document = builder.parse(new File("../InputData/"+fileName+".xml"));
      document.getDocumentElement().normalize();
    } catch (ParserConfigurationException | SAXException | IOException e) {
      e.printStackTrace();
    } Element portfolioElement = (Element) document.getElementsByTagName("portfolio").item(0);
    String portfolioName = portfolioElement.getAttribute("name");
    //creat portfolio
    Portfolio portfolio = new Portfolio(portfolioName);
    NodeList stockList = portfolioElement.getElementsByTagName("stock");

    for (int i = 0; i < stockList.getLength(); i++) {
      Element stockElement = (Element) stockList.item(i);
      Stock stock = getStock(stockElement);
      if (!companySymbolExists(stock.getCompanyName()) && stock.getUserShared()<=0){
        return null;
      }
      createXMLbyCompanyInfo(stock.getCompanyName());
      portfolio.stockArrayList.add(stock);
    }
    return portfolio;
  }


  /**
   * help method for creating documents for users.
   * @return NodeList for XML data.
   */
  public static NodeList getUsersFromDocument() {
    return getUsersFromDocument(document);
  }

  /**
   * get the user element from the XML data.
   * @param newDocument the document that need to be parsed.
   * @return the NodeList
   */
  private static NodeList getUsersFromDocument(Document newDocument) {
    return newDocument.getElementsByTagName("user");
  }

  /**
   * check if the name is valid.
   * @param inputName company symbol
   * @return true if name is valid.
   */
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

  /**
   * ADd a user into the xml file.
   * @param username username.
   */
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

  /**
   * get the portfolio by a username
   * @param username username.
   * @return List of portfolio that this user holds.
   */
  public List<Portfolio> getPortfoliosByUsername(String username) {
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
              Portfolio portfolio = new Portfolio(portfolioName);

              NodeList stocks = portfolioElement.getElementsByTagName("stock");
              for (int k = 0; k < stocks.getLength(); k++) {
                Node stockNode = stocks.item(k);
                if (stockNode.getNodeType() == Node.ELEMENT_NODE) {
                  Stock stock = getStock((Element) stockNode);
                    portfolio.stockArrayList.add(stock); // Add to existing list
                }
              }
              portfoliosList.add(portfolio);
            }
          }
          break;
        }
      }
    }
    return portfoliosList;
  }

  /**
   * get the stock by username.
   * @param stockNode stockNode.
   * @return the stock.
   */
  private static Stock getStock(Element stockNode) {
    String stockName = stockNode.getAttribute("name");
    int stockValue;
    try {
      stockValue = Integer.parseInt(stockNode.getAttribute("value"));
    } catch (NumberFormatException e) {
      // Handle the case where the value attribute is not a valid integer
      stockValue = 0; // Or any other default value you want to use
    }
    return new Stock(stockName, stockValue);
  }

  /**
   *  Add a portfolio by XML.
   * @param username user name .
   * @param portfolioName porfolioName needed to be add.
   * @param portfolio porfolio that need to be add.
   */
  public void addPortfolioXML(String username, String portfolioName, Portfolio portfolio) {
    NodeList userList = document.getElementsByTagName("user");
    List<Stock> stocks= portfolio.stockArrayList;
    for (int i = 0; i < userList.getLength(); i++) {
      Node userNode = userList.item(i);
      if (userNode.getNodeType() == Node.ELEMENT_NODE) {
        Element userElement = (Element) userNode;
        String name = userElement.getAttribute("name");
        if (name.equals(username)) {
          Element portfolios = (Element) userElement.getElementsByTagName("portfolios").item(0);

          Element portfolioElement = document.createElement("portfolio");
          portfolioElement.setAttribute("name", portfolioName);

          for (Stock stock : stocks) {
            Element stockElement = document.createElement("stock");
            stockElement.setAttribute("name", stock.getCompanyName());
            stockElement.setAttribute("value", String.valueOf(stock.getUserShared()));
            portfolioElement.appendChild(stockElement);
          }
          portfolios.appendChild(portfolioElement);
          saveChanges();
          break;
        }
      }
    }
  }

  /**
   * Save all the changes to file.
   */
  private void saveChanges() {
    try {
      Transformer transformer = TransformerFactory.newInstance().newTransformer();
      transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
      transformer.setOutputProperty(OutputKeys.METHOD, "xml");
      transformer.setOutputProperty(OutputKeys.INDENT, "no");
      transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

      DOMSource source = new DOMSource(document);
      StreamResult result = new StreamResult(new File("../res/data.xml"));
      transformer.transform(source, result);
    } catch (TransformerException e) {
      e.printStackTrace();
    }
  }


  /**
   * creat a XML file for a company.
   * @param companyName
   */
  public void createXMLbyCompanyInfo(String companyName) {
    String apiKey = "W0M1JOKC82EZEQA8";
    URL url;
    String fileName = companyName + "_StockData.xml";

    try {
      url = new URL("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY"
              + "&outputsize=full" + "&symbol=" + companyName + "&apikey=" + apiKey + "&datatype=csv");
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

    } catch (IOException | TransformerException | ParserConfigurationException e) {
      e.printStackTrace();
    }
  }

  /**
   * check if the companySymbol exist.
   * @param stockSymbol
   * @return
   */
  public static boolean companySymbolExists( String stockSymbol) {
    String apiKey = "W0M1JOKC82EZEQA8";
    String urlTemplate = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=%s&apikey=%s";
    URL url = null;

    try {
      // Attempt to create the URL
      url = new URL("https://www.alphavantage"
              + ".co/query?function=TIME_SERIES_DAILY"
              + "&outputsize=full"
              + "&symbol"
              + "=" + stockSymbol + "&apikey="+apiKey+"&datatype=csv");
      // Assuming additional code here for HTTP request and processing the response
      // This is where you'd typically use the 'url' object

      // If everything above succeeds, return true
    } catch (MalformedURLException e) {
      // Log the exception or handle it as deemed appropriate
      System.err.println("the alphavantage API has either changed or no longer works");

      // Return false if an exception is caught
    }

    InputStream in;
    StringBuilder output = new StringBuilder();

    try {
      /*
      Execute this query. This returns an InputStream object.
      In the csv format, it returns several lines, each line being separated
      by commas. Each line contains the date, price at opening time, highest
      price for that date, lowest price for that date, price at closing time
      and the volume of trade (no. of shares bought/sold) on that date.

      This is printed below.
       */
      in = url.openStream();
      int b;

      while ((b=in.read())!=-1) {
        output.append((char)b);
      }
    }
    catch (IOException e) {
      throw new IllegalArgumentException("No price data found for "+stockSymbol);
    }

    return !output.toString().contains("Error Message");

  }

  /**
   * calculate the stock value by given date.
   * @param givenDate the date.
   * @param filePath file's path that from
   * @return a company that hold the infomation.
   */
  public static Company stockValueByGivenDate(String givenDate, String filePath) {
      Company company = null;
      try {
          filePath = "../" + filePath + "_StockData.xml";
          File xmlFile = new File(filePath);

          DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
          DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
          Document doc = dBuilder.parse(xmlFile);
          Boolean hasValidDate = false;
          company = new Company(givenDate, highStock, lowStock, hasValidDate);
          doc.getDocumentElement().normalize();

          NodeList nList = doc.getElementsByTagName("Record");

          for (int temp = 0; temp < nList.getLength(); temp++) {
              Node nNode = nList.item(temp);

              if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                  Element eElement = (Element) nNode;

                  String date = eElement.getElementsByTagName("Date").item(0).getTextContent();
                  if (date.equals(givenDate)) {
                      String high = eElement.getElementsByTagName("High").item(0).getTextContent();
                      String low = eElement.getElementsByTagName("Low").item(0).getTextContent();
                      company.hasValidDate = true;

                      company.high = high;
                      highStock = high;
                      company.low = low;
                      lowStock = low;
                      //System.out.println("Date: " + date + "\nHigh: " + high + "\nLow: " + low);
                      return company;
                  }
              }
          }

         // System.out.println("No data found for the given date: " + givenDate);

      } catch (Exception e) {
          e.printStackTrace();
      }

      return company;

  }

  /**
   * Checks if the given date exists in the specified XML file.
   *
   * @param givenDate The date to search for in the XML file.
   * @return true if the date exists, false otherwise.
   */
  /**
   * Checks if the given date exists in the specified XML file.
   *
   * @param filePath The path to the XML file.
   * @param givenDate The date to search for in the XML file.
   * @return true if the date exists, false otherwise.
   */
  public boolean isDateExistInXML(String filePath, String givenDate) {
    try {
      filePath = "../"+filePath+"_StockData.xml";
      File xmlFile = new File(filePath);
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(xmlFile);
      doc.getDocumentElement().normalize();

      // Assuming "Record" is the element name that contains date information
      NodeList nList = doc.getElementsByTagName("Record");

      for (int temp = 0; temp < nList.getLength(); temp++) {
        Node nNode = nList.item(temp);

        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
          Element eElement = (Element) nNode;
          // Assuming "Date" is the sub-element name under "Record" that contains the date
          String date = eElement.getElementsByTagName("Date").item(0).getTextContent();

          if (date.equals(givenDate)) {
            return true; // Given date found
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
   // System.out.print("The date your provided was not a business day");
    return false; // Given date not found
  }


}
