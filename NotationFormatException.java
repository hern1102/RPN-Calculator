/*
Name: Anthony Hernandez 
Description: This NotationFormatException class is derived from Exception class and incorporated 
in the rpnCalculator and infixCalculator to explain to the user that the expression that 
they have entered into the program does not follow the guidelines set forth in regards to 
the notation format. Of course this is only displayed if the user does not comply with 
the example given when the user runs the program. I have tried many variations of wrong input,
from parathesis missing {e.g.  1 * (4 + 5  }, to the inproper use of operators { e.g. 4 + 2 - }, and 
letters and numbers that are not allowed such as (e.g. 4 + 2 # 1).
 */ 

package project.pkg3;

/**
 *
 * @author antho
 */
public class NotationFormatException extends Exception{
    
    public NotationFormatException(){
        super("Invalid Notation Format");
    }
    
    public NotationFormatException(String errorMsg){
        super(errorMsg);
    }
    
}
