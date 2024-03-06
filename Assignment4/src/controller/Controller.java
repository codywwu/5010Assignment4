package controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import models.Model;
import models.Portfolio;
import views.View;

public class Controller {
  private Scanner input = new Scanner(System.in);
  private int menuSelection = 0;
  private Model model;
  private View view;

  Portfolio portfolio;

  public Controller(Model model, View view) {
    this.model = model;
    this.view = view;
  }

  // Constructor
  public void intro() {
    String username = null;
    // Prompt user for username\
    while (username == null) {
      System.out.println("Please enter a username: ");
      username = input.nextLine();
    }
    view.displayWelcomeMessage(username);
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
      }catch (java.util.InputMismatchException e) {
        System.out.println("Invalid input. Please enter a number.");
        input.nextLine(); // Consume the invalid input
      }
    }
    switch (menuSelection) {
      case 2:
        setPortfolio();
        break;
      case 3:
        exitProgram();
        break;
    }
  }

  public boolean validMenuSelection(int input, int range) {
    if ((input <= 0 || input > range)) {
      System.out.println("Invalid input. Please enter a number between 1 and " + range);
    }
    return input <= 0 || input > range;
  }

  public void exitProgram() {
    System.exit(0);
  }

  public void setPortfolio() {
    menuSelection = 0;
    while (true) {
      view.createPortfolio();
      try {
        menuSelection = input.nextInt();
        if (validMenuSelection(menuSelection, 4)) {
          input.nextLine(); // Consume the invalid input
          continue; // Restart the loop to prompt for input again
        }
        break; // Break out of the loop if input is valid
      }catch (java.util.InputMismatchException e) {
        System.out.println("Invalid input. Please enter a number.");
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
    String portName = null;
    System.out.println("Please enter a company's symbol ");
    System.out.println("eg,GooG for google");
    int quantity = -1; // Initialize to an invalid value to enter the loop

    while (companySymbol == null) {
      System.out.println("Please enter the company symbol:");
      companySymbol = input.next();
      if (CheckValidCompanySymbol(companySymbol)) {
        // if valid, prompt for the quantity of purchase.
        System.out.println("Please enter the quantity of purchase, the number must be larger than 0:");
        while (quantity <= 0) {
          try {
            quantity = input.nextInt();
            if (quantity <= 0) {
              System.out.println("The number must be larger than 0. Please try again:");
            }
          } catch (InputMismatchException e) {
            System.out.println("That's not a number. Please enter a valid quantity:");
            input.next(); // Consume the invalid input and prompt again
          }
        }
        System.out.println("You have chosen to purchase " + quantity + " shares of " + companySymbol + ".");
        //TODO add stock and the corresponding shares into the port.
        // Proceed with further processing here

       // port.addStock();

      } else {
        // if not valid, prompt again for a valid company symbol.
        System.out.println("Please enter a valid company symbol.");
        companySymbol = null; // Reset for re-validation
      }
    }

    System.out.println("Add another company with shares or select done for done creating this port");

    menuSelection = 0;
    while (validMenuSelection(menuSelection, 2)) {
      view.addMoreProfoiloOrDone();
      menuSelection = input.nextInt();
    }

    switch (menuSelection) {
      case 1:
        FillForm();
        break;
      case 2:
        doneCreatPortfolio();
    }


  }

  private void doneCreatPortfolio() {
    //TODO Add port into the user's portList.
  }

  private boolean CheckValidCompanySymbol(String companySymbol) {
    //TODO Always return true for now.
    //TODO Where should this method at ?

    return true;
  }

}
