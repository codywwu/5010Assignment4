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




  public Portfolio createPortfolio(String name,int shares){
   Portfolio portfolio = new Portfolio(name,0);
   return portfolio;
  }
}
