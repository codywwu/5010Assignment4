public class User implements UserInterface{
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
