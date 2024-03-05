package controller;
import java.util.Scanner;
import models.Model;
import views.View;

public class Controller {
  private Scanner input = new Scanner(System.in);
  private int menuSelection = 0;
  private Model model;
  private View view;

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
      case 3:
        mainMenu();
        break;
      case 4:
        exitProgram();
        break;
    }

  }

}
