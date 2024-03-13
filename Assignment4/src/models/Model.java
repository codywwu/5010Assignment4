package models;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Model {
  private XMLDatabase xmlDatabase;
  private List<Portfolio> userPortfolios;
  private User user;

  private Portfolio portfolio;

  // Constructor
  public Model() {
    xmlDatabase = new XMLDatabase();
  }

  public static boolean checkIfPortfolioEmpty(List<Portfolio> userPortfolios) {
    return userPortfolios.isEmpty();
  }

  public Boolean checkInputName(String name){
    user = new User(name,0);
    return XMLDatabase.checkName(name);
  }
  public User creatUser(String username,float buyingPower){
    User user = new User(username,buyingPower);
    xmlDatabase.addUser(username);
    return user;
  }

  public void addPortfolioUser(){
    user.addPortfolio(portfolio);
  }

  public String getUserName(){
    return user.getUserName();
  }

  public ArrayList<Portfolio> getPList(){
    return user.getPortfolioList();
  }

  public List<Portfolio> getUserPortfolios() {
    userPortfolios=xmlDatabase.getPortfoliosByUsername(user.userName);
    return userPortfolios;
  }

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

  public Portfolio getPortfolio(){
    return portfolio;
  }

  public Stock createStock(String companySymbol,long userShared) {
    return new Stock(companySymbol,userShared);
  }

  public void newXML(){
    xmlDatabase=new XMLDatabase();
  }

  public Boolean dataCheckExistInXML(Stock stock,String date){
    return xmlDatabase.isDateExistInXML(stock.getCompanyName(), date);
  }

  public String getDatahigh(){
    return XMLDatabase.highStock.trim();
  }

  public String getDataLow(){
    return XMLDatabase.lowStock.trim();
  }

  public void createPortfolio(String name){
    portfolio= new Portfolio(name);
  }

  public void addStockPort(Stock stock){
    portfolio.addStock(stock);
  }

  public void addPToXML(){
    xmlDatabase.addPortfolioXML(getUserName(),portfolio.name,portfolio);
  }

  public void addCompanyXML(String c){
    xmlDatabase.createXMLbyCompanyInfo(c);
  }

  public void readImport(String fileName){
    portfolio=xmlDatabase.readImportedFile(fileName);
  }

  public Boolean checkFileExists(String inFile){
    File folder = new File("InputData/");
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




  public static void displayPortfolioValueByGivenDate(List<Portfolio> portfolios, String givenDate,String portfolioName){
    XMLDatabase database = new XMLDatabase();
    double totalHighValue = 0;
    double totalLowValue = 0;
    Scanner scanner = new Scanner(System.in); // Create a scanner for user input
    boolean validDate = false;
    while (!validDate) {
      if (portfolios.isEmpty()) {
        System.out.println("No portfolio had been created");
        return; // Exit if no portfolios
      } else {
        for (Portfolio portfolio : portfolios) {
          if (portfolio.name.equals(portfolioName)) {
            System.out.println("Portfolio Name: " + portfolio.name);
            for (Stock stock : portfolio.getStocks()) {
              if (database.isDateExistInXML(stock.getCompanyName(), givenDate)) {
                validDate = true; // Set validDate to true if at least one stock has the given date
                System.out.println("Each " + stock.getCompanyName() + " share worth following on: " + givenDate);
                System.out.println("You have " + stock.getUserShared() + " shares on this company");
                XMLDatabase.stockValueByGivenDate(givenDate, stock.getCompanyName());

                double high = Double.parseDouble(XMLDatabase.highStock.trim()) * stock.getUserShared();
                System.out.println("Maximum value: " + high);
                totalHighValue += high; // Add to total portfolio high value

                double low = Double.parseDouble(XMLDatabase.lowStock.trim()) * stock.getUserShared();
                System.out.println("Minimum value: " + low);
                totalLowValue += low; // Add to total portfolio low value

                System.out.println("\n\n");
              }
            }
          }
        }
      }

      // If we get here and validDate is false, then the date was not found for any stock
      if (!validDate) {
        System.out.println("Invalid date or no data for this date. Please enter another date (YYYY-MM-DD):");
        givenDate = scanner.nextLine(); // Read a new date from the user
      } else {
        // After validating date and calculating values, print total portfolio values
        System.out.println("Total portfolio value based on highest stock prices: " + totalHighValue);
        System.out.println("Total portfolio value based on lowest stock prices: " + totalLowValue);
      }
    }

    System.out.println("\nEND OF YOUR PORTFOLIOS");
  }
}
