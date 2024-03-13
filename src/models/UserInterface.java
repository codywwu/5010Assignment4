package models;


/**
 * User Interface that offers various of methods for users.
 */
interface UserInterface {
  /**
   * Get the user name.
   * @return  username.
   */
  String getUserName();

  /**
   * Get the password.
   * @return the password.
   */
  String getPassWord();

  /**
   * get the buying power.
   * @return buying power.
   */
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
