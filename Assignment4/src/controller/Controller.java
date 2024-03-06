package controller;
import java.util.InputMismatchException;
import java.util.Scanner;
import models.Model;
import views.View;
import java.util.Scanner;
public class Controller {
  private Scanner input = new Scanner(System.in);
  private int menuSelection = 0;
  private Model model;
  private View view;

  private Scanner sc;
  public Controller(Model model, View view) {
    this.model = model;
    this.view = view;
  }

  // Constructor
  public void intro() {
    String username = null;
    // Prompt user for username\
    while (username ==null){
      System.out.println("Please enter a username: ");
      username = input.nextLine();
    }
    view.displayWelcomeMessage(username);
    mainMenu();
  }

  public void mainMenu(){
    menuSelection=0;
    while (validMenuSelection(menuSelection, 3)) {
      view.mainMenu();
      menuSelection = input.nextInt();
    }
    switch (menuSelection){
      case 2:
        setPortfolio();
        break;
      case 3:
        exitProgram();
        break;
    }
  }

  public boolean validMenuSelection(int input, int range){
    return input <= 0 || input > range;
  }

  public void exitProgram(){
    System.exit(0);
  }

  public void setPortfolio(){
    menuSelection=0;
    while (validMenuSelection(menuSelection, 4)) {
      view.createPortfolio();
      menuSelection = input.nextInt();
    }
    switch (menuSelection){
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
        
        // Proceed with further processing here
      } else {
        // if not valid, prompt again for a valid company symbol.
        System.out.println("Please enter a valid company symbol.");
        companySymbol = null; // Reset for re-validation
      }
    }

  }

  private boolean CheckValidCompanySymbol(String companySymbol) {
    //TODO Always return true for now.
    //TODO Where should this method at ?

    return true;
  }

}
