package views;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import models.Company;
import models.Portfolio;
import models.Stock;

/**
 * View class that handle all the print statements.
 */
public class View {
  static Appendable out = null;

  /**
   * Constructor for view class.
   * @param out system.out.
   */
  public View(Appendable out) {
    View.out = out;
  }
    public static void userPortfolioEmpty () throws IOException {
      View.out.append("\nNo portfolio had been created");
    }

  /**
   * print the stock value by given date.
   * @param stock the stock needed to print.
   * @param date the date that parsing by stock.
   * @throws IOException IO exception to catch unexpected error.
   */
  public static void printStockValueByGivenDate(Stock stock, String date) throws IOException {
    View.out.append("\nEach ").append(stock.getCompanyName()).append(" share worth following on: ")
        .append(date);
    View.out.append("\nYou have ").append(String.valueOf(stock.getUserShared()))
        .append(" shares on this company");

  }

  /**
   *  print the maximum value of the stock.
   * @param high maximum value.
   * @throws IOException IO exception to catch unexpected error.
   */
  public static void printMaxValue(double high) throws IOException {
    View.out.append("\nMaximum value: ").append(String.valueOf(high));
  }

  /**
   * print the minimul value of the stock.
   * @param low minimunl value.
   * @throws IOException IO exception to catch unexpected error.
   */
  public static void printMinValue(double low) throws IOException {
    View.out.append("\nMaximum value: ").append(String.valueOf(low));
  }

  /**
   * print some new lines.
   */
  public static void newLines() throws IOException {
    View.out.append("\n");
  }

  /**
   * print invalid date.
   * @throws IOException
   */
  public static void printDateInValid() throws IOException {
    View.out.append("\nInvalid date or no data for this date. Please enter another date (YYYY-MM-DD):");
  }

  /**
   * print the maximum total value.
   * @param totalHighValue the value needed to be print.
   * @throws IOException IO exception to catch unexpected error.
   */
  public static void printMaxTotalValue(double totalHighValue) throws IOException {
    View.out.append("\nTotal portfolio value based on highest stock prices: ")
        .append(String.valueOf(totalHighValue));
  }

  /**
   * print min total value.
   * @param totalLowValue min value that needed to be print.
   * @throws IOException IO exception to catch unexpected error.
   */
  public static void printMinTotalValue(double totalLowValue) throws IOException {
    View.out.append("\nTotal portfolio value based on lowest stock prices: ")
        .append(String.valueOf(totalLowValue));
  }

  /**
   * end of portfolio.
   * @throws IOException IO exception to catch unexpected error.
   */
  public static void endOfYourPortfolio() throws IOException {
    View.out.append("\n\nEND OF YOUR PORTFOLIOS");
  }

  /**
   *  print the high and low vlaue by given date.
   * @param date the date.
   * @param company the company.
   * @throws IOException IO exception to catch unexpected error.
   */
  public static void printHighLowOnGivenDate(String date,Company company) throws IOException {
    View.out.append("\nDate: ").append(date).append("\nHigh: ").append(company.getHigh())
        .append("\nLow: ").append(company.getLow());
  }

  /**
   * diasplay new welcmone message for new user.
   * @param username username.
   * @throws IOException IO exception to catch unexpected error.
   */
  public void displayNewWelcomeMessage(String username) throws IOException {
    View.out.append("\nHello, new user: ").append(username).append(", Welcome To Money For US");
  }

  /**
   * sho welecome message for old value.
   * @param username username.
   * @throws IOException IO exception to catch unexpected error.
   */
  public void displayWelcomeMessage(String username) throws IOException {
    View.out.append("\nHello ").append(username).append(", Welcome To Money For US");
  }

  /**
   * main menu.
   * @throws IOException IO exception to catch unexpected error.
   */
  public void mainMenu() throws IOException {
    View.out.append("\nMain menu");
    View.out.append("\n1. View Created Portfolio");
    View.out.append("\n2. Create new Portfolio");
    View.out.append("\n3. Exit Program");
    View.out.append("\nPlease enter the number corresponding to your choice: ");
  }

  /**
   * prompt for creating portfolio.
   * @throws IOException IO exception to catch unexpected error.
   */
  public void createPortfolio() throws IOException {
    View.out.append("\nThere are two ways to create a new Portfolio: ");
    View.out.append("\n1. Import new portfolio");
    View.out.append("\n2. Filled out the form");
    View.out.append("\n3. Go back to main menu");
    View.out.append("\n4. Exit Program");
    View.out.append("\nPlease enter the number corresponding to your choice: ");
  }

  /**
   * prompt to see if portfolio or done.
   * @throws IOException IO exception to catch unexpected error.
   */
  public void addMorePortfolioOrDone() throws IOException {
    View.out.append("\n1. Add more stock");
    View.out.append("\n2. Done");

  }

  /**
   * prompt that user want to fill out the form.
   * @throws IOException IO exception to catch unexpected error.
   */
  public void fillFormIntro() throws IOException {
    View.out.append("\nPlease enter a company's symbol ");
    View.out.append("\neg,GooG for google");
  }

  /**
   * portfolio name fill out.
   * @throws IOException IO exception to catch unexpected error.
   */
  public void fillFormPortfolioName() throws IOException {
    View.out.append("\nPlease enter a portfolio name: ");
  }

  /**
   * prompt user's name.
   * @throws IOException IO exception to catch unexpected error.
   */
  public void promptUserName() throws IOException {
    View.out.append("\nPlease enter a username: ");
  }

  /**
   * number is invalid.
   * @throws IOException IO exception to catch unexpected error.
   */
  public void NumberInvalidInput() throws IOException {
    View.out.append("\nInvalid input. Please enter a number.");
  }

  /**
   * selection of a menu is invalid.
   * @param range the range.
   * @throws IOException IO exception to catch unexpected error.
   */
  public void menuSelectInvalid(int range) throws IOException {
    View.out.append("\nInvalid input. Please enter a number between 1 and ")
        .append(String.valueOf(range));
  }

  /**
   * prompt the quantity of purhcase.
   * @throws IOException IO exception to catch unexpected error.
   */
  public void promptQuantityOfPurchase() throws IOException {
    View.out.append("\nPlease enter the quantity of purchase, the number must be larger than 0:");
  }

  /**
   * in valid input that great than 0.
   * @throws IOException IO exception to catch unexpected error.
   */
  public void InvalidInputGreaterThanZero() throws IOException {
    View.out.append("\nThe number must be larger than 0. Please try again:");
  }


  /**
   * prompt for portfolio.
   * @throws IOException IO exception to catch unexpected error.
   */
  public void promptForPortfolio() throws IOException {
    View.out.append("\nPlease enter the name of the portfolio you would like to access:");
  }

  /**
   * prompt for a file name.
   * @throws IOException IO exception to catch unexpected error.
   */
  public void promptForFileName() throws IOException {
    View.out.append("\nPlease enter the name of the file you would like to access (no .xml is necessary):");
  }

  /**
   * prompt user for date input.
   * @throws IOException IO exception to catch unexpected error.
   */
  public void promptDate() throws IOException {
    View.out.append("\nPlease enter in a date to view "
        + "the stock's profit on that date (Ex.2024-03-05):");
  }

  /**
   * invalid portfolio.
   * @throws IOException IO exception to catch unexpected error.
   */
  public void invalidPortfolio() throws IOException {
    View.out.append("\nDuplicate portfolio name please try again: ");
  }

  /**
   * folder is invalid.
   * @throws IOException IO exception to catch unexpected error.
   */
  public void invalidfile() throws IOException {
    View.out.append("\nFolder is empty or does not exist.");
  }

  /**
   * not successfully import xml.
   * @throws IOException IO exception to catch unexpected error.
   */
  public void invalidImportPortfolio() throws IOException {
    View.out.append("\nPortfolio did not import correctly, please go back to import interface and try again");
  }


  /**
   * successfully imported a xml file.
   * @throws IOException IO exception to catch unexpected error.
   */
  public void addedImportfile()throws IOException {
    View.out.append("\nImport file success!");
  }

  /**
   * invalid date.
   * @throws IOException IO exception to catch unexpected error.
   */
  public void invalidDate() throws IOException {
    View.out.append("\nDate must be in the format of yyyy-MM-dd, please try again:");
  }

  /**
   * name is invalid.
   * @throws IOException IO exception to catch unexpected error.
   */
  public void invalidPortfolioUsernameInput() throws IOException {
    View.out.append("\nThe Portfolio name is not found, please try again or exit to main menu to add portfolio: ");
  }

  /**
   * tell user has successfuly purchase.
   * @param quantity quantity of purchase.
   * @param companySymbol company symbol.
   * @throws IOException IO exception to catch unexpected error.
   */
  public void successPurchase(int quantity, String companySymbol) throws IOException {
    View.out.append("\nYou have chosen to purchase ").append(String.valueOf(quantity))
        .append(" shares of ").append(companySymbol).append(".");
  }

  /**
   * The company symbol is not valid.
   * @throws IOException IO exception to catch unexpected error.
   */
  public void invalidCompanySymbol() throws IOException {
    View.out.append("\nPlease enter a valid company symbol.");
  }

  /**
   * print for add or done.
   * @throws IOException IO exception to catch unexpected error.
   */
  public void addCompanyOrDone() throws IOException {
    View.out.append("\nAdd another company with shares or select done for done creating this port");
  }


  /**
   * print all the porfolio infomation inside the portfolio.
   * @param portfolioList the portfolio list that needed to be print.
   * @throws IOException IO exception to catch unexpected error.
   */
  public void donePortfolioInfo(ArrayList<Portfolio> portfolioList) throws IOException {
    for (int p = 0; p < portfolioList.size(); p++) {
      Portfolio portfolio = portfolioList.get(p);
      // Print the portfolio index and size of its stock list

      View.out.append("\nPortfolio ").append(String.valueOf(p + 1))
          .append(" - The shares of your portfolio in total: ")
          .append(String.valueOf(portfolio.getTotalShares()));

      // Iterate through the stockArrayList of the current portfolio
      for (int i = 0; i < portfolio.stockArrayList.size(); i++) {
        // Print the company name of each stock
        View.out.append("\nStock ").append(String.valueOf(i + 1)).append(": ")
            .append(portfolio.stockArrayList.get(i).getCompanyName());
      }
      // Add a new line for better readability between portfolios
      View.out.append("\n");
    }
  }

  /**
   * Display the stocks info inside the portfolio.
   *
   */
  public void displayPortfolios(List<Portfolio> portfolios) throws IOException {
    if (portfolios.isEmpty()) {
      View.out.append("\nNo portfolio had been created");
    } else {
      for (Portfolio portfolio : portfolios) {
        View.out.append("\nPortfolio Name: ").append(portfolio.name);
        View.out.append("\n  Company quantity: ").append(String.valueOf(portfolio.getQuantity()));
      }
    }
  }

  /**
   *  display stock's infomation.
   * @param portfolios portfolios.
   * @param inputPortfolios the input portfolios.
   * @throws IOException if there is an empty list.
   */
  public void displayStocks(List<Portfolio> portfolios, String inputPortfolios) throws IOException {
    for (Portfolio portfolio : portfolios) {
      if (portfolio.name.equals(inputPortfolios)) {
        View.out.append("\nPortfolio Name: ").append(portfolio.name);
        for (Stock stock : portfolio.getStocks()) {
          View.out.append("\n  Stock Name: ").append(stock.getCompanyName());
          View.out.append("\n    Shared Value: ").append(String.valueOf(stock.getUserShared()));
        }
      }
    }
  }

  /**
   * stock menu.
   * @throws IOException
   */
  public void stockMenu() throws IOException {
    View.out.append("\n1, Exit to Main menu");
    View.out.append("\n2, Exit the program");
    View.out.append("\n3, Change dates to view the stock's profit on that date");
    View.out.append("\nPlease enter the number corresponding to your choice: ");
  }

  /**
   * portfolio's menu.
   * @throws IOException
   */
  public void portfolioMenu() throws IOException {
    View.out.append("\n1, Exit to Main menu");
    View.out.append("\n2, Exit the program");
    View.out.append("\n3, View more details on one portfolio");
    View.out.append("\nPlease enter the number corresponding to your choice: ");
  }

  /**
   * Saying good bey to user.
   */
    public void goodBey() {
    System.out.println("Good Bye");
    }
}
