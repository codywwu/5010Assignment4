package models;

import java.util.ArrayList;

/**
 * User class that implments the user interface class.
 */
public class User implements UserInterface {
  String userName;

  String passWord;

  double buyingPower;


  ArrayList<Portfolio> portfolioList = new ArrayList<>();

  /**
   * User class that contians name and buying power.
   * @param userName name.
   * @param buyingPower buying power.
   */
  public User(String userName, double buyingPower){
    this.userName = userName;
    //this.passWord = passWord;
    this.buyingPower = buyingPower;
  }
  /**
   * @return
   */
  @Override
  public String getUserName() {
    return userName;
  }

  /**
   * Set buying power.
   * @param buyingPower the buying power.
   */
  public void setBuyingPower(double buyingPower){
    this.buyingPower = buyingPower;
  }

  /**
   * Set the password
   * @param passWord set the password.
   */
  public void setPassWord(String passWord){
    this.passWord = passWord;
  }

  /**
   *  get the password.
   * @return the password.
   */
  @Override
  public String getPassWord() {
    return passWord;
  }

  /**
   * Get the buying power.
   * @return buying power.
   */
  @Override
  public double getBuyingPower() {
    return buyingPower;
  }

  /**
   * add a portfolio into the user's portfolio list.
   *
   * @return the added this portfolio list into user's current list.
   */
  @Override
  public void addPortfolio(Portfolio newPortfolio) {
    portfolioList.add(newPortfolio);
  }

  /**
   * get the portfolio by index.
   *
   * @param i
   * @return return the portfolio by requested index.
   */
  @Override
  public Portfolio getPortfolio(int i) {
    return portfolioList.get(i);
  }

  /**
   * Get the portfolio list.
   * @return a list of portfolio.
   */
  public ArrayList<Portfolio> getPortfolioList(){
    return portfolioList;
  }
}

