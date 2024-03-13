package models;

import java.util.ArrayList;


public class User implements UserInterface {
  String userName;

  String passWord;

  double buyingPower;

  ArrayList<Portfolio> portfolioList = new ArrayList<>();

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

  public void setBuyingPower(double buyingPower){
    this.buyingPower = buyingPower;
  }
  public void setPassWord(String passWord){
    this.passWord = passWord;
  }

  /**
   * @return
   */
  @Override
  public String getPassWord() {
    return passWord;
  }

  /**
   * @return
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

  public ArrayList<Portfolio> getPortfolioList(){
    return portfolioList;
  }
}

