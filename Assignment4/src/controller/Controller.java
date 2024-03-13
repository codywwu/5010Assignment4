package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import models.*;

import views.View;


public class Controller {
    private Scanner input = new Scanner(System.in);
    private int menuSelection = 0;
    private final Model model;
    private static View view;


    public Controller(Model model, View view) {
        this.model = model;
        Controller.view = view;
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
        if (model.checkInputName(username)) {
            view.displayWelcomeMessage(username);
        } else {
            view.displayNewWelcomeMessage(username);
            model.creatUser(username, 1000);
        }

        mainMenu();
    }

    public void mainMenu() {
        menuSelection = 0;
        whileTrue();

    }

    private void showUserPortfolio() {
        int portfolioAction;

        while (true) {
            view.displayPortfolios(model.getUserPortfolios());
            view.portfolioMenu();

            try {
                portfolioAction = input.nextInt();
                if (validMenuSelection(portfolioAction, 3)) {
                    input.nextLine(); // Consume the invalid input

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
                enterDateViewStock(portfolioName);
                break;
        }
        mainMenu();
    }

    public void enterDateViewStock(String portfolioName){
        input = new Scanner(System.in);
        view.promptDate();
        String date = input.nextLine();
        while (!isValidDateFormat(date)) {
            date = input.nextLine();
        }
        //Model.displayPortfolioValueByGivenDate(model.getUserPortfolios(), date,portfolioName );
        boolean validDate = false;
        double totalHighValue = 0;
        Scanner scanner = new Scanner(System.in);
        double totalLowValue = 0;
        while (!validDate) {

            if (Model.checkIfPortfolioEmpty(model.getUserPortfolios())) {
                View.userPortfolioEmpty();
            } else {
                for (Portfolio portfolio : model.getUserPortfolios()) {
                    if (portfolio.name.equals(portfolioName)) {
                        //View.printPortfolioName(portfolio.name);
                        for (Stock stock : portfolio.getStocks()) {
                            if (model.dataCheckExistInXML(stock, date)) {
                                validDate = true;
                                View.printStockValueByGivenDate(stock, date);
                                Company company = XMLDatabase.stockValueByGivenDate(date, stock.getCompanyName());
                                if(company.getHasValidDate()){
                                    View.printHighLowOnGivenDate(date,company);
//System.out.println("Date: " + date + "\nHigh: " + company.getHigh() + "\nLow: " + company.getLow());
                                }

                                double high = Double.parseDouble(model.getDatahigh()) * stock.getUserShared();
                                View.printMaxValue(high);
                                totalHighValue += high; // Add to total portfolio high value

                                double low = Double.parseDouble(model.getDataLow()) * stock.getUserShared();
                                View.printMinValue(low);
                                totalLowValue += low; // Add to total portfolio low value

                                View.newLines();
                            }
                        }
                    }
                }
            }
            if (!validDate) {
                View.printDateInValid();
                date = scanner.nextLine(); // Read a new date from the user
            } else {
                // After validating date and calculating values, print total portfolio values
                View.printMaxTotalValue(totalHighValue);
                View.printMinTotalValue(totalLowValue);

            }
        }
        View.endOfYourPortfolio();
        viewStocks();
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
            String portfolioName = "Portfolio" + portfolioNumber;
            model.createPortfolio(portfolioName);
            view.createPortfolio();
            try {
                menuSelection = input.nextInt();
                if (validMenuSelection(menuSelection, 4)) {
                    input.nextLine(); // Consume the invalid input
                    continue; // Restart the loop to prompt for input again
                }
                break; // Break out of the loop if input is valid
            } catch (InputMismatchException e) {
                view.NumberInvalidInput();
                input.nextLine(); // Consume the invalid input
            }
        }
        switch (menuSelection) {
            case 1:
                importFile();
                break;
            case 2:
                setPortfolioName();
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

    private void importFile() {
        input = new Scanner(System.in);
        view.promptForFileName();
        String fileName = input.nextLine();
        if (!model.checkFileExists(fileName)) {
            view.invalidfile();
            view.promptForFileName();
            fileName = input.nextLine();
        }
        model.readImport(fileName);

        if (model.getPortfolio() != null) {
            model.newXML();
            model.addPortfolioUser();
            model.addPToXML();
            view.addedImportfile();
            mainMenu();
        } else {
            view.invalidImportPortfolio();
            setPortfolio();
        }
    }

    private void setPortfolioName() {
        input = new Scanner(System.in);
        view.fillFormPortfolioName();
        String portfolioName = input.nextLine();
        while (model.checkPortfolioName(portfolioName)) {
            view.invalidPortfolio();
            portfolioName = input.nextLine();
        }
        model.createPortfolio(portfolioName);
    }

    private void FillForm() {

        String companySymbol = null;
        view.fillFormIntro();
        int quantity = -1; // Initialize to an invalid value to enter the loop
        while (companySymbol == null) {
            companySymbol = input.next();
            if (CheckValidCompanySymbol(companySymbol)) {
                // Creating the company file.
                model.addCompanyXML(companySymbol);
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
                view.successPurchase(quantity, companySymbol);
                Stock stock = model.createStock(companySymbol, quantity);
                model.addStockPort(stock);
            } else {
                // if not valid, prompt again for a valid company symbol.
                view.invalidCompanySymbol();
                companySymbol = null; // Reset for re-validation
            }
        }
        view.addCompanyOrDone();
        menuSelection = 0;
        while (validMenuSelection(menuSelection, 2)) {
            view.addMorePortfolioOrDone();
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
        model.addPortfolioUser();
        model.addPToXML();
        view.donePortfolioInfo(model.getPList());
        whileTrue();
    }

    private void whileTrue() {
        while (true) {
            view.mainMenu();
            try {
                menuSelection = input.nextInt();
                if (validMenuSelection(menuSelection, 3)) {
                    input.nextLine(); // Consume the invalid input
                    continue; // Restart the loop to prompt for input again
                }
                break; // Break out of the loop if input is valid
            } catch (InputMismatchException e) {
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

    private boolean CheckValidCompanySymbol(String companySymbol) {
        return XMLDatabase.companySymbolExists(companySymbol);
    }

}
