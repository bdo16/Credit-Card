// Credit Card verification driver

public class CreditCardLabDriver
{

  public static void main(String[] args){
    CreditCard test = new CreditCard();
    for (int i = 0; i < 50; i++) {
      String num = test.generateCard(1); //1 for Master, 2 for Visa, etc
      System.out.println("is "+num+" a valid credit card number? "+ test.verify(num));
      int typeInt = test.findType(num);
      String[] types = {"none", "Master Card", "Visa", "American Express", "Discover", "Diners Club/ Carte Blanche"};
      System.out.println("Credit card type is: "+ types[typeInt]);
    }
    }
  }