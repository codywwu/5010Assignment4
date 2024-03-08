package controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import models.Model;
import models.Portfolio;
import models.Stock;

import views.View;

public class Controller {
  private Scanner input = new Scanner(System.in);
  private int menuSelection = 0;
  private Model model;
  private View view;

  //TODO currentDate depending on API.
  private String currentDate;
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
      user = new User(username,1000);
    }
    if (model.checkInputName(username)){
      view.displayWelcomeMessage(username);
    }else {
      view.displayNewWelcomeMessage(username);
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
    // Date ?
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
      //Creating new portforlio here.
      String portfolioName = "Portfolio"+portfolioNumber;
      portfolio = new Portfolio(portfolioName,0);
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

  private void FillForm() {

    String companySymbol = null;
    view.fillFormIntro();
    int quantity = -1; // Initialize to an invalid value to enter the loop

    while (companySymbol == null) {

      companySymbol = input.next();
      if (CheckValidCompanySymbol(companySymbol)) {
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

        //TODO add stock and the corresponding shares into the list.
        // Proceed with further processing here

        //
        Stock stock = new Stock(companySymbol,currentDate);
        portfolio.addStock(stock,quantity);
      } else {
        // if not valid, prompt again for a valid company symbol.
       view.invalidCompanySymbol();
        companySymbol = null; // Reset for re-validation
      }
    }

   view.addCompanyOrDone();
    menuSelection = 0;
    while (validMenuSelection(menuSelection, 2)) {
      view.addMoreProfoiloOrDone();
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
    //TODO Add port into the user's portList.
  user.addPortfolio(portfolio);
  view.donePortfolioInfo(user.getPortfolioList());
  }

  private boolean CheckValidCompanySymbol(String companySymbol) {
    //TODO Always return true for now.
    //TODO Where should this method at ?

    return true;
  }

}
