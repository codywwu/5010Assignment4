package views;

import java.util.ArrayList;
import java.util.List;
import models.Portfolio;
import models.Stock;

public class View {

  public void displayNewWelcomeMessage(String username) {
    System.out.println("Hello, new user: " + username + ", Welcome To Money For US");
  }

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

  public void createPortfolio() {
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
  public void addMorePortfoiloOrDone() {
    System.out.println("1. Add more stock");
    System.out.println("2. Done");

  }

  public void fillFormIntro() {
    System.out.println("Please enter a company's symbol ");
    System.out.println("eg,GooG for google");
  }

  public void promptUserName() {
    System.out.println("Please enter a username: ");
  }

  public void NumberInvalidInput() {
    System.out.println("Invalid input. Please enter a number.");
  }

  public void menuSelectInvalid(int range) {
    System.out.println("Invalid input. Please enter a number between 1 and " + range);
  }

  public void promptQuantityOfPurchase() {
    System.out.println("Please enter the quantity of purchase, the number must be larger than 0:");
  }

  public void InvalidInputGreaterThanZero() {
    System.out.println("The number must be larger than 0. Please try again:");
  }

  public void promptForPortfolio() {
    System.out.println("Please enter the name of the portfolio you would like to access:");
  }

  public void promptForFileName() {
    System.out.println("Please enter the name of the file you would like to access (no .xml is necessary):");
  }

  public void promptDate() {
    System.out.println("Please enter in a date to view "
        + "the stock's profit on that date (Ex.2024-03-05):");
  }

  public void invalidPortfolio() {
    System.out.println("Import file failed, please try again:");
  }

  public void addedImportfile(){
    System.out.println("Import file success!");
  }

  public void invalidDate() {
    System.out.println("Date must be in the format of yyyy-MM-dd, please try again:");
  }

  public void invalidPortfolioUsernameInput() {
    System.out.println(
        "The Portfolio name is not found, please try again or exit to main menu to add portfolio: ");
  }

  public void successPurchase(int quantity, String companySymbol) {
    System.out.println(
        "You have chosen to purchase " + quantity + " shares of " + companySymbol + ".");
  }

  public void invalidCompanySymbol() {
    System.out.println("Please enter a valid company symbol.");
  }

  public void addCompanyOrDone() {
    System.out.println(
        "Add another company with shares or select done for done creating this port");

  }

  public void donePortfolioInfo(ArrayList<Portfolio> portfolioList) {
    for (int p = 0; p < portfolioList.size(); p++) {
      Portfolio portfolio = portfolioList.get(p);
      // Print the portfolio index and size of its stock list
      System.out.println("Portfolio " + (p + 1) + " - The share of your portfolio in total: "
          + portfolio.stockArrayList.get(p).getUserShared());

      // Iterate through the stockArrayList of the current portfolio
      for (int i = 0; i < portfolio.stockArrayList.size(); i++) {
        // Print the company name of each stock
        System.out.println(
            "Stock " + (i + 1) + ": " + portfolio.stockArrayList.get(i).getCompanyName());
      }
      // Add a new line for better readability between portfolios
      System.out.println();
    }
  }

  /**
   * Display the stocks info inside the portfolio.
   *
   * @param portfolios
   */
  public void displayPortfolios(List<Portfolio> portfolios) {
    if (portfolios.isEmpty()) {
      System.out.println("No portfolio had been created");
    } else {
      for (Portfolio portfolio : portfolios) {
        System.out.println("Portfolio Name: " + portfolio.name);
        System.out.println("  Stock quantity: " + portfolio.getQuantity());
      }
    }
  }

  public void displayStocks(List<Portfolio> portfolios, String inputPortfolios) {
    for (Portfolio portfolio : portfolios) {
      if (portfolio.name.equals(inputPortfolios)) {
        System.out.println("Portfolio Name: " + portfolio.name);
        for (Stock stock : portfolio.getStocks()) {
          System.out.println("  Stock Name: " + stock.getCompanyName());
          System.out.println("    Shared Value: " + stock.getUserShared());
          System.out.println("    Create Time: " + stock.getTimeStamp());
        }
      }
    }
  }

  public void stockMenu() {
    System.out.println("1, Exit to Main menu");
    System.out.println("2, Exit the program");
    System.out.println("3, Change dates to view the stock's profit on that date");
    System.out.println("Please enter the number corresponding to your choice: ");
  }

  public void portfolioMenu() {
    System.out.println("1, Exit to Main menu");
    System.out.println("2, Exit the program");
    System.out.println("3, View more details on one portfolio");
    System.out.println("Please enter the number corresponding to your choice: ");
  }


  public void changeTimeStampOrMain() {
    System.out.println("End of your portfolios");
    System.out.println("1. Change another timestamp");
    System.out.println("2. Go back to main menu");
  }

  public void promptTimeStamp() {
    System.out.println("Please enter the time stamp you want to check");
  }
}
