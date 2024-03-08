package models;

import java.util.ArrayList;

public interface UserInterface {
  public String getUserName();
  public String getPassWord();
  public double getBuyingPower();

  /**
   * add a portfolio into the user's portfolio list.
   * @return the added this portfolio list into user's current list.
   */
  public void addPortfolio(Portfolio newPortfolio);

  /**
   * get the portfolio by index.
   * @return return the portfolio by requested index.
   */
  public Portfolio getPortfolio(int i);
}
