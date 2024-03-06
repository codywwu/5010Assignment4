package views;
import controller.StockController;
import models.Stock;

public class View {


  public void displayWelcomeMessage(String username) {
    System.out.println("Hello " + username + ", Welcome To Money For US");
  }

  public void mainMenu() {
    System.out.println("Main menu");
    System.out.println("1. View Created Portfolio");
    System.out.println("2. Create new Portfolio");
    System.out.println("3. Exit Program");
    System.out.println("Please enter the number corresponding to your choice: ");
  }

  public void createPortfolio(){
    System.out.println("There are two ways to create a new Portfolio: ");
    System.out.println("1. Import new portfolio");
    System.out.println("2. Filled out the form");
    System.out.println("3. Go back to main menu");
    System.out.println("4. Exit Program");
    System.out.println("Please enter the number corresponding to your choice: ");
  }

//    Stock stock = new Stock("TESLA",300,40,20,"2020");
//    StockController stockController = new StockController();
//
//    stockController.setStockLocalHigh(50);
//
//    System.out.println(stockController.getStockLocalHigh());
public void addMoreProfoiloOrDone(){
  System.out.println("1. Add more profoil");
  System.out.println("2. Done");

}




}
