package models;

import java.util.ArrayList;
import java.util.List;

public class Model {
  private List<String> data; // Example: Storing user input data
  XMLDatabase xmlDatabase = new XMLDatabase();

  // Constructor
  public Model() {
    this.data = new ArrayList<>();
  }

  public Boolean checkInputName(String name){
    return xmlDatabase.checkName(name);
  }

  public Stock createStock(String companySymbol, String currentDate) {
     Stock stock = new Stock(companySymbol,currentDate);
            return stock;
  }

  public User creatUser(String username,float buyingPower){
    User user = new User(username,buyingPower);

    return user;
  }

  public Portfolio createPortfolio(String name,int shares){

   Portfolio portfolio = new Portfolio(name,0);
   return portfolio;
  }
}
