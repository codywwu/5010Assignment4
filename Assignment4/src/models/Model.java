package models;
import java.util.List;


public class Model {
  private final XMLDatabase xmlDatabase;
  private List<Portfolio> userPortfolios;
  private User user;

  // Constructor
  public Model() {

    xmlDatabase = new XMLDatabase();
  }

  public Boolean checkInputName(String name){
    user = new User(name,0);
    return XMLDatabase.checkName(name);
  }


  public Stock createStock(String companySymbol,long userShared, String currentDate) {
    return new Stock(companySymbol,userShared,currentDate);
  }

  public User creatUser(String username,float buyingPower){
    User user = new User(username,buyingPower);
    xmlDatabase.addUser(username);
    return user;
  }

  public List<Portfolio> getUserPortfolios() {
    userPortfolios=xmlDatabase.getPortfoliosByUsername(user.userName);
    return userPortfolios;
  }

  public boolean checkPortfolioName (String input){
    for (Portfolio portfolio : userPortfolios) {
      if (portfolio.name.equalsIgnoreCase(input)) {
        return true; // Portfolio name found
      }
    }
    return false; // Portfolio name not found
  }




  public Portfolio createPortfolio(String name,int shares){
    return new Portfolio(name);
  }


  public static void displayPortfolioValueByGivenDate(List<Portfolio> portfolios, String givenDate, String portfolioAction){
    XMLDatabase database = new XMLDatabase();
    if (portfolios.isEmpty()){
      System.out.println("No portfolio had been created");
    } else {
      for (Portfolio portfolio : portfolios) {
        System.out.println("Portfolio Name: " + portfolio.name);
        //database.stockValueByGivenDate();
        //HOW TO GET STOCK NAME FROM A LIST
        for (Stock stock : portfolio.getStocks()) {
          System.out.println("Each "+stock.getCompanyName()+" share worth following on: "+givenDate);
          System.out.println("You have "+stock.getUserShared()+"shares on this company");
          database.stockValueByGivenDate(givenDate,stock.getCompanyName());

          Double high = Double.parseDouble(database.highStock.trim()) * stock.getUserShared();
          System.out.println("Maximum value: " +high);

          Double low =  Double.parseDouble(database.lowStock.trim()) * stock.getUserShared();
          System.out.println("Minimum value: " +low);
          System.out.println("\n\n");
        }
      }
    }
  }
}
