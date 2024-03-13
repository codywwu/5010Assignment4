package models;

import java.util.ArrayList;

interface UserInterface {
  String getUserName();
  String getPassWord();
  double getBuyingPower();

  /**
   * add a portfolio into the user's portfolio list.
   * @return the added this portfolio list into user's current list.
   */
  void addPortfolio(Portfolio newPortfolio);

  /**
   * get the portfolio by index.
   * @return return the portfolio by requested index.
   */
  Portfolio getPortfolio(int i);
}
