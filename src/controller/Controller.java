package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import models.*;

import views.View;

/**
 * Controller class that would handle all the logic.
 */
public class Controller {
    private Scanner input;
    private int menuSelection = 0;
    private final Model model;
    private static View view;
    final Readable in;
    final Appendable out;

    /**
     * constructor for controller.
     * @param model the model.
     * @param view view.
     * @param in system in.
     * @param out system out.
     */
    public Controller(Model model, View view, Readable in, Appendable out) {
        this.model = model;
        this.view = view;
        this.in=in;
        this.out=out;
        this.input= new Scanner(this.in);
    }

    /**
     * introduction menu for user.
     * @throws IOException IO exception to catch unexpected error.
     */
    public void intro() throws IOException {
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

    /**
     * catch if main menu is correct.
     * @throws IOException
     */
    public void mainMenu() throws IOException {
        menuSelection = 0;
        whileTrue();

    }

    /**
     * show user's portfolio and their corresponding information.
     * @throws IOException IO exception to catch unexpected error.
     */
    private void showUserPortfolio() throws IOException {
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


    /**
     * view stocks information .
     * @throws IOException IO exception to catch unexpected error.
     */
    private void viewStocks() throws IOException {
        input = new Scanner(this.in);
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

    /**
     * enter the date the view the actual stock.
     * @param portfolioName name of the portfilio that will be check.
     * @throws IOException IO exception to catch unexpected error.
     */
    public void enterDateViewStock(String portfolioName) throws IOException {
        input = new Scanner(this.in);
        view.promptDate();
        String date = input.nextLine();
        while (!isValidDateFormat(date)) {
            date = input.nextLine();
        }
        //Model.displayPortfolioValueByGivenDate(model.getUserPortfolios(), date,portfolioName );
        boolean validDate = false;
        double totalHighValue = 0;
        Scanner scanner = new Scanner(this.in);
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
        showUserPortfolio();
    }

    /**
     * check if the date is valid.
     * @param dateStr string for date.
     * @return true is valid.
     * @throws IOException IO exception to catch unexpected error.
     */
    public static boolean isValidDateFormat(String dateStr) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate.parse(dateStr, formatter);
            return true;
        } catch (DateTimeParseException e) {
            view.invalidDate();
            return false;
        }
    }

    /**
     * menu selection dispenser.
     * @param input input of user.
     * @param range range of the selection.
     * @return true if the input is valid.
     * @throws IOException IO exception to catch unexpected error.
     */
    public boolean validMenuSelection(int input, int range) throws IOException {
        if ((input <= 0 || input > range)) {
            view.menuSelectInvalid(range);
        }
        return input <= 0 || input > range;
    }

    /**
     * end the program.
     */
    public void exitProgram() {
        view.goodBey();
        System.exit(0);
    }


    int portfolioNumber = 1;

    /**
     * Set the portfolio by 2 methods. Import or fill out form.
     * @throws IOException IO exception to catch unexpected error.
     */
    public void setPortfolio() throws IOException {
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

    /**
     * import the portfolio from file.
     * @throws IOException IO exception to catch unexpected error.
     */
    private void importFile() throws IOException {
        input = new Scanner(this.in);
        view.promptForFileName();
        String fileName = input.nextLine();

        while (!model.checkFileExists(fileName)) {
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

    /**
     * set the name of the portfolio.
     * @throws IOException IO exception to catch unexpected error.
     */
    private void setPortfolioName() throws IOException {
        input = new Scanner(this.in);
        view.fillFormPortfolioName();
        String portfolioName = input.nextLine();
        while (model.checkPortfolioName(portfolioName)) {
            view.invalidPortfolio();
            portfolioName = input.nextLine();
        }
        model.createPortfolio(portfolioName);
    }

    /**
     * ask user to fill out the form to create a portfolio.
     * @throws IOException IO exception to catch unexpected error.
     */
    private void FillForm() throws IOException {

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

    /**
     * handle when user click done .
     */
    private void doneCreatPortfolio() throws IOException {
        model.addPortfolioUser();
        model.addPToXML();
        view.donePortfolioInfo(model.getPList());
        whileTrue();
    }

    /**
     * while the selection is true, helper method.
     * @throws IOException IO exception to catch unexpected error.
     */
    private void whileTrue() throws IOException {
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

    /**
     * check if the company symbol is valid.
     * @param companySymbol
     * @return
     */
    private boolean CheckValidCompanySymbol(String companySymbol) {
        return XMLDatabase.companySymbolExists(companySymbol);
    }

}
