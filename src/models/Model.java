package models;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Model of the project that will be the bridge between the controller and view.
 */
public class Model {
  private XMLDatabase xmlDatabase;
  private List<Portfolio> userPortfolios;
  private User user;

  private Portfolio portfolio;

  /**
   * constructor for model, that would take a database.
   */
  public Model() {
    xmlDatabase = new XMLDatabase();
  }

  /**
   * check if porfolio is empty.
   * @param userPortfolios user portfolio that needed to check.
   * @return true if empty.
   */
  public static boolean checkIfPortfolioEmpty(List<Portfolio> userPortfolios) {
    return userPortfolios.isEmpty();
  }

  /**
   * check if the the input name is in xml databse.
   * @param name name needed to be check.
   * @return true if not.
   */
  public Boolean checkInputName(String name){
    user = new User(name,0);
    return XMLDatabase.checkName(name);
  }

  /**
   *  Add the user into the xml.
   * @param username name to be add.
   * @param buyingPower the bp, set to be 1000.
   */
  public void creatUser(String username,float buyingPower){
    user = new User(username,buyingPower);
    xmlDatabase.addUser(username);
  }

  /**
   * add the portfolio into the user.
   */
  public void addPortfolioUser(){
    user.addPortfolio(portfolio);
  }

  /**
   * get user name.
   * @return the user's name
   */
  public String getUserName(){
    return user.getUserName();
  }

  /**
   * get the portfolio from user.
   * @return portfolio list.
   */
  public ArrayList<Portfolio> getPList(){
    return user.getPortfolioList();
  }

  /**
   * get user's portfolios from list.
   * @return the user's portfolio.
   */
  public List<Portfolio> getUserPortfolios() {
    userPortfolios=xmlDatabase.getPortfoliosByUsername(user.userName);
    return userPortfolios;
  }

  /**
   * check if portfolio's name exist.
   * @param input input symbol.
   * @return true is exist.
   */
  public boolean checkPortfolioName (String input){
    userPortfolios=getUserPortfolios();
    if (userPortfolios==null){
      return false;
    }
    for (Portfolio portfolio : userPortfolios) {
      if (portfolio.name.equals(input)) {
        return true; // Portfolio name found
      }
    }
    return false; // Portfolio name not found
  }

  /**
   * get the portfolio.
   * @return portoflio.
   */
  public Portfolio getPortfolio(){
    return portfolio;
  }

  /**
   * create stock.
   * @param companySymbol company symbol.
   * @param userShared shares from user.
   * @return stock.
   */
  public Stock createStock(String companySymbol,long userShared) {
    return new Stock(companySymbol,userShared);
  }

  /**
   * a new xml.
   */
  public void newXML(){
    xmlDatabase=new XMLDatabase();
  }

  /**
   * check if any data in XML file.
   * @param stock stock of the file.
   * @param date the data.
   * @return true if data is in xml.
   */
  public Boolean dataCheckExistInXML(Stock stock,String date){
    return xmlDatabase.isDateExistInXML(stock.getCompanyName(), date);
  }

  /**
   * get high in data.
   * @return high stock in string.
   */
  public String getDatahigh(){
    return XMLDatabase.highStock.trim();
  }

  /**
   * get data low.
   * @return low data in string.
   */
  public String getDataLow(){
    return XMLDatabase.lowStock.trim();
  }

  /**
   * create a new portfolio.
   * @param name portfolio's name.
   */
  public void createPortfolio(String name){
    portfolio= new Portfolio(name);
  }

  /**
   * stock from portfolio.
   * @param stock the stock.
   */
  public void addStockPort(Stock stock){
    portfolio.addStock(stock);
  }

  /**
   * add portoflio into XML.
   */
  public void addPToXML(){
    xmlDatabase.addPortfolioXML(getUserName(),portfolio.name,portfolio);
  }


  /**
   * add the company into XML.
   * @param c company.
   */
  public void addCompanyXML(String c){
    xmlDatabase.createXMLbyCompanyInfo(c);
  }

  /**
   * read the file.
   * @param fileName file name.
   */
  public void readImport(String fileName){
    portfolio=xmlDatabase.readImportedFile(fileName);
  }

  /**
   *  check if the file exists.
   * @param inFile file name.
   * @return true if exist.
   */
  public Boolean checkFileExists(String inFile){
    File folder = new File("../InputData/");

//    return folder.exists();
    File[] files = folder.listFiles();
    if (files != null) {
      // Iterate over each file and print its name
      for (File file : files) {
        if (file.getName().equals(inFile+".xml")) {
          return true;
        }
      }
    } else {

      return false;
    }
    return false;
  }
}
