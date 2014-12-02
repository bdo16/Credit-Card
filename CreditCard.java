import java.util.Random;
public class CreditCard
{
  public String generateCard () {
    Random q = new Random (); // needed
    int numdigits = 13 + (int)(Math.random()*4); //number of digits in the credit card, 13-16
    int sum = 0; // the sum to be checked to see if divisible by 10
    String result = ""; //the string that will be returned by piecing together the array below
    int [] card = new int[numdigits]; //makes the array that holds the digits of the credit card
    for (int i = 0; i < numdigits; i ++) { 
      card[i] = (int)(Math.random()*10); //sets all numbers to 0-9 randomly 
    }
    
    //valid starting digits
    int[] firstdigit = {3, 4, 5, 6}; //all possible beginning numbers
    card[0] = firstdigit[q.nextInt(firstdigit.length)]; //makes the first digit
    if (card[0] == 5) //if Master Card
      card[1] = (int)(Math.random()*5) + 1; // makes the second digit randomly from 1-5
    if (card[0] == 6) { //if Discover
      card[1] = 0;
      card[2] = 1;
      card[3] = 1; //since first four digits are 6011
    }
    if (card[0] == 3) { // if starting with 5
      int[] seconddigit = {0, 4, 6, 7, 8}; //all possible second numbers if first digit is 3
      card[1] = seconddigit[q.nextInt(seconddigit.length)]; //makes the second digit
      if (card[1] == 0)
        card[2] = (int)(Math.random()*5) + 1; // makes the third digit randomly from 1-5
    }
    
    boolean isdouble = true; // starting from second to last digit, which is doubled
    for (int i = numdigits - 2; i >= 0; i--) { // will go through every number besides the last one
      if (isdouble) { // if even
        int temp = 2 * card[i]; // doubles the number
        if (temp >= 10) // if it is has two digits
          temp = 1 + temp % 10; // adds the digits together
        sum += temp; // adds the number to the result
        isdouble = false; // the next number won't be doubled
      }
      else { // if odd 
        sum += card[i]; // adds the number
        isdouble = true; // the next number will be doubled
      } 
    }
    
    int remainder = sum % 10; // the remainder of the sum (not counting last number);
    if (remainder == 0)
      card[numdigits - 1] = 0; // the last number will be set so that the sum will be a multiple of 10
    else
      card[numdigits - 1] = 10 - remainder;
    
    for (int i = numdigits - 1; i >= 0; i--) {
      int index = numdigits - 1 - i;
      result += card[index]; //pieces together the array into the string result
    }
    return result;
  }
  
  public int findType (String num) {
    
    // if first 2 numbers are 51-55, then return 1 for MasterCard
    if (num.substring(0, 2).equals("51") ||
        num.substring(0, 2).equals("52") ||
        num.substring(0, 2).equals("53") ||
        num.substring(0, 2).equals("54") ||
        num.substring(0, 2).equals("55"))
      return 1;
    
    // else if first number is 4, then return 2 for Visa
    else if (num.substring(0, 1).equals("4"))
      return 2;
    
    // else if first two numbers are 34 or 37, return 3 for American Express
    else if (num.substring(0, 2).equals("34") ||
             num.substring(0, 2).equals("37"))
      return 3;
    
    // else if first four numbers are 6011, return 4 for Discover
    else if (num.substring(0, 4).equals("6011"))
      return 4;
    
    // else if the first two numbes are 36 or 38, or first three numbers are 300-305
    else if (num.substring(0, 2).equals("36") ||
             num.substring(0, 2).equals("38") ||
             num.substring(0, 3).equals("300") ||
             num.substring(0, 3).equals("301") ||
             num.substring(0, 3).equals("302") ||
             num.substring(0, 3).equals("303") ||
             num.substring(0, 3).equals("304") ||
             num.substring(0, 3).equals("305"))
      return 5; // return 5 for Diners Club/ Carte Blanche
    
    else return 0; // else return 0 for "none"
  }
  
  public boolean verify (String num) {
    int result = 0; // the sum to be checked
    boolean isdouble = false; // the first number is not doubled
    for (int i = num.length() - 1; i >= 0; i--) { // will go through every number
      if (isdouble) { // if even
        int temp = 2 * Integer.valueOf(num.substring(i, i + 1)); // doubles the number
        if (temp >= 10) // if it is has two digits
          temp = 1 + temp % 10; // adds the digits together
        result += temp; // adds the number to the result
        isdouble = false; // the next number won't be doubled
      }
      else { // if odd 
        result += Integer.valueOf(num.substring(i, i + 1)); // adds the number
        isdouble = true; // the next number will be doubled
      } 
    }
    return result % 10 == 0; // returns true if multiple of 10
  }
  
}