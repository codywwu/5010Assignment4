package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import models.User;
import models.Model;
import models.Portfolio;
import models.Stock;

import models.XMLDatabase;
import views.View;


public class Controller {
  private Scanner input = new Scanner(System.in);
  private int menuSelection = 0;
  private Model model;
  private static View view;

  private XMLDatabase database = new XMLDatabase();
  //TODO currentDate depending on API.
  private String currentDate="";
  private User user;
  private Portfolio portfolio;



  public Controller(Model model, View view) {
    this.model = model;
    this.view = view;
  }

  // Constructor
  public void intro() {
    String username = null;
    // Prompt user for username\
    while (username == null) {
     view.promptUserName();
      username = input.nextLine();
      // User have 1000 buying power for now, indented for future use.
    }
    if (model.checkInputName(username)){
      view.displayWelcomeMessage(username);
      user = new User(username,1000);
    }else {
      view.displayNewWelcomeMessage(username);
      user = model.creatUser(username,1000);
    }

    mainMenu();
  }

  public void mainMenu() {
    menuSelection = 0;
    while (true) {
      view.mainMenu();
      try {
        menuSelection = input.nextInt();
        if (validMenuSelection(menuSelection, 3)) {
          input.nextLine(); // Consume the invalid input
          continue; // Restart the loop to prompt for input again
        }
        break; // Break out of the loop if input is valid
      }catch (InputMismatchException e) {
        view.NumberInvalidInput();
        input.nextLine(); // Consume the invalid input
      }
    }
    switch (menuSelection) {
      case 1:
        showUserPortfolio();
        break;
      case 2:
        setPortfolio();
        break;
      case 3:
        exitProgram();
        break;
    }
  }

  private void showUserPortfolio() {
    int portfolioAction = 0;

    while (true) {
      view.displayPortfolios(model.getUserPortfolios());
      view.portfolioMenu();

      try {
        portfolioAction = input.nextInt();
        if (validMenuSelection(portfolioAction, 3)) {
          input.nextLine(); // Consume the invalid input
          continue;
        } else {
          // Process the valid input
          switch (portfolioAction) {
            case 1:
              mainMenu();
              break;
            case 2:
              exitProgram();
              break;
            case 3:
              viewStocks();
              break;
            default:
              // Handle invalid menu options
              view.menuSelectInvalid(3);
              break;
          }
          break; // Exit the loop if the input is valid
        }
      } catch (InputMismatchException e) {
        view.menuSelectInvalid(3);
        // Clear the invalid input
        input.nextLine();
      }
    }
  }


  private void viewStocks() {
    input = new Scanner(System.in);
    view.promptForPortfolio();
    String portfolioName = input.nextLine();
    while (!model.checkPortfolioName(portfolioName)) {
      view.invalidPortfolioUsernameInput();
      portfolioName = input.nextLine();
    }
    view.displayStocks(model.getUserPortfolios(), portfolioName);
    view.stockMenu();
    int optionSelection;
    try {
      optionSelection = input.nextInt();
      while (validMenuSelection(optionSelection, 3)) {
        optionSelection = input.nextInt();
      }
    } catch (InputMismatchException e) {
      optionSelection = input.nextInt();
      view.menuSelectInvalid(3);
    }
    switch (optionSelection) {
      case 1:
        mainMenu();
        break;
      case 2:
        exitProgram();
        break;
      case 3:
        input = new Scanner(System.in);
        view.promptDate();
        String date = input.nextLine();
        while (!isValidDateFormat(date)) {
          date = input.nextLine();
        }
        Model.displayPortfolioValueByGivenDate(model.getUserPortfolios(), date);
        break;
    }
    mainMenu();
  }

  public static boolean isValidDateFormat(String dateStr) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    try {
      LocalDate.parse(dateStr, formatter);
      return true;
    } catch (DateTimeParseException e) {
      view.invalidDate();
      return false;
    }
  }

  public boolean validMenuSelection(int input, int range) {
    if ((input <= 0 || input > range)) {
      view.menuSelectInvalid(range);
    }
    return input <= 0 || input > range;
  }

  public void exitProgram() {
    System.exit(0);
  }


  int portfolioNumber = 1;
  public void setPortfolio() {
    menuSelection = 0;
    while (true) {
      //Creating new portfolio here.
      String portfolioName = "Portfolio"+portfolioNumber;
      portfolio=model.createPortfolio(portfolioName,0);
      view.createPortfolio();
      try {
        menuSelection = input.nextInt();
        if (validMenuSelection(menuSelection, 4)) {
          input.nextLine(); // Consume the invalid input
          continue; // Restart the loop to prompt for input again
        }
        break; // Break out of the loop if input is valid
      }catch (InputMismatchException e) {
       view.NumberInvalidInput();
        input.nextLine(); // Consume the invalid input
      }
    }
    switch (menuSelection) {
      case 2:
        FillForm();
        break;
      case 3:
        mainMenu();
        break;
      case 4:
        exitProgram();
        break;
    }

  }

  private void importFile(){
    input=new Scanner(System.in);
    view.promptForFileName();
    String fileName=input.nextLine();
    model.readImport(fileName);
  }

  private void FillForm() {

    String companySymbol = null;
    view.fillFormIntro();
    int quantity = -1; // Initialize to an invalid value to enter the loop

    while (companySymbol == null) {

      companySymbol = input.next();
      if (CheckValidCompanySymbol(companySymbol)) {
        // Creating the company file.
        database.createXMLbyCompanyInfo(companySymbol);
        // if valid, prompt for the quantity of purchase.
       view.promptQuantityOfPurchase();
         while (quantity <= 0) {
          try {
            quantity = input.nextInt();
            if (quantity <= 0) {
            view.InvalidInputGreaterThanZero();
            }
          } catch (InputMismatchException e) {
           view.NumberInvalidInput();
            input.next(); // Consume the invalid input and prompt again
          }
        }
        view.successPurchase(quantity,companySymbol);

        Stock stock = model.createStock(companySymbol,quantity,currentDate);

        portfolio.addStock(stock);
      } else {
        // if not valid, prompt again for a valid company symbol.
       view.invalidCompanySymbol();
        companySymbol = null; // Reset for re-validation
      }
    }

   view.addCompanyOrDone();
    menuSelection = 0;
    while (validMenuSelection(menuSelection, 2)) {
      view.addMorePorfoiloOrDone();
      menuSelection = input.nextInt();
    }

    switch (menuSelection) {
      case 1:
        portfolioNumber++;
        FillForm();
        break;
      case 2:
        doneCreatPortfolio();
    }
  }

  private void doneCreatPortfolio() {
  user.addPortfolio(portfolio);
  database.addPortfolioXML(user.getUserName(),portfolio.name,portfolio);

  view.donePortfolioInfo(user.getPortfolioList());

    while (true) {
      view.mainMenu();
      try {
        menuSelection = input.nextInt();
        if (validMenuSelection(menuSelection, 3)) {
          input.nextLine(); // Consume the invalid input
          continue; // Restart the loop to prompt for input again
        }
        break; // Break out of the loop if input is valid
      }catch (InputMismatchException e) {
        view.NumberInvalidInput();
        input.nextLine(); // Consume the invalid input
      }
    }
    switch (menuSelection) {
      case 1:
        showUserPortfolio();
        break;
      case 2:
        setPortfolio();
        break;
      case 3:
        mainMenu();
        break;
    }

  }

  private boolean CheckValidCompanySymbol(String companySymbol) {
    return database.companySymbolExists(companySymbol);
  }

}
