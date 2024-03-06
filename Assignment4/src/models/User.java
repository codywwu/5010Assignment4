package models;

import models.UserInterface;

public class User implements UserInterface {
  String userName;

  String passWord;

  double buyingPower;



  public User(String userName, String passWord, double buyingPower){
    this.userName = userName;
    this.passWord = passWord;
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

  public void setUserName(String userName){ this.userName = userName;}
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
}
