package models;
import java.util.List;
import java.util.Scanner;


public class Model {
  private final XMLDatabase xmlDatabase;
  private List<Portfolio> userPortfolios;
  private User user;

  // Constructor
  public Model() {

    xmlDatabase = new XMLDatabase();
  }

  public Boolean checkInputName(String name) {
    user = new User(name, 0);
    return XMLDatabase.checkName(name);
  }


  public Stock createStock(String companySymbol, long userShared, String currentDate) {
    return new Stock(companySymbol, userShared, currentDate);
  }

  public User creatUser(String username, float buyingPower) {
    User user = new User(username, buyingPower);
    xmlDatabase.addUser(username);
    return user;
  }

  public List<Portfolio> getUserPortfolios() {
    userPortfolios = xmlDatabase.getPortfoliosByUsername(user.userName);
    return userPortfolios;
  }

  public boolean checkPortfolioName(String input) {
    for (Portfolio portfolio : userPortfolios) {
      if (portfolio.name.equalsIgnoreCase(input)) {
        return true; // Portfolio name found
      }
    }
    return false; // Portfolio name not found
  }




  public void readImport(String fileName){
    Portfolio importP = xmlDatabase.readImportedFile(fileName);
    if (checkPortfolioName(importP.name)){
      System.out.println("Portfolio name is Duplicated");
    }
  }

  public Portfolio createPortfolio(String name,int shares){
    return new Portfolio(name);
  }


  public static void displayPortfolioValueByGivenDate(List<Portfolio> portfolios, String givenDate) {
    Scanner scanner = new Scanner(System.in); // Create a scanner for user input
    boolean validDate = false;
    XMLDatabase database = new XMLDatabase();
    double totalHighValue = 0;
    double totalLowValue = 0;

    while (!validDate) {
      if (portfolios.isEmpty()) {
        System.out.println("No portfolio had been created");
        return; // Exit if no portfolios
      } else {
        for (Portfolio portfolio : portfolios) {
          System.out.println("Portfolio Name: " + portfolio.name);
          for (Stock stock : portfolio.getStocks()) {
            if (database.isDateExistInXML(stock.getCompanyName(), givenDate)) {
              validDate = true; // Set validDate to true if at least one stock has the given date
              System.out.println("Each " + stock.getCompanyName() + " share worth following on: " + givenDate);
              System.out.println("You have " + stock.getUserShared() + " shares on this company");
              database.stockValueByGivenDate(givenDate, stock.getCompanyName());

              Double high = Double.parseDouble(database.highStock.trim()) * stock.getUserShared();
              System.out.println("Maximum value: " + high);
              totalHighValue += high; // Add to total portfolio high value

              Double low = Double.parseDouble(database.lowStock.trim()) * stock.getUserShared();
              System.out.println("Minimum value: " + low);
              totalLowValue += low; // Add to total portfolio low value

              System.out.println("\n\n");
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
