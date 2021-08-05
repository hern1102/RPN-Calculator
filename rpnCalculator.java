/*
Description: In reverse Polish notation, the operators follow their operands; for 
instance, to add 3 and 4, one would write 3 4 + rather than 3 + 4. If there are multiple 
operations, operators are given immediately after their second operands; so the 
expression written 3 − 4 + 5 in conventional notation would be written 3 4 − 5 + 
in reverse Polish notation: 4 is first subtracted from 3, then 5 is added to it. 
An advantage of reverse Polish notation is that it removes the need for parentheses 
that are required by infix notation. This class uses a Stack via composition that 
is derived from a Doubly Linked List. That stack will is used to pop operands off 
and perform some arithmetic depending on the operator, and push the result back 
on to the stack. It will continue to do this until the input, which will be a String
formatted for RPN calculations, no longer has any tokens, and there is only one value
on the stack remaining.
 */ 

package project.pkg3;

import java.util.Scanner;
import static jdk.nashorn.internal.objects.NativeString.toLowerCase;

/**
 *
 * @author antho
 */
public class rpnCalculator {
    
    /*
    An instance of Stack is declared in order to push and pop integers on and off 
    the stack as required. (Composition)
    */
    private Stack<Double> stack;
   

    public rpnCalculator(){
        stack = new Stack<>(); 
    }
    
    /*
    Function: double evaluate (String expr)
    Description: This method conducts the calculations while parsing the string. 
    Essentially it parses each token and if the token is a operand, it pushes the 
    token on to the stack. Once the token is a operator, it will pop two operands 
    off of the stack, perform the calculations, and then push the result back on
    to the stack. Of course it does this with out private methods. if cos(), sin(), or tan()
    is one of the operators, then it will only pop one operand off of the stack, 
    perform the calculation, and then push the result back on to the stack. 
    Inputs: The String expression that you wish to parse and calculate. 
    (IT HAS TO FOLLOW RPN (POSTFIX NOTATION) FORMAT).
    Outputs: A double will be the output, the double will be the answer to the calculation.
    */

    public double evaluate(String expr)throws NotationFormatException{
            double op1, op2, result = 0;
            String token;
            Scanner parser = new Scanner(expr);     

            while(parser.hasNext()){
                token = toLowerCase(parser.next());
     
                if (isOperator(token)){
                    
                    if(token.equals("sin") || token.equals("cos") || token.equals("tan")){
                        
                        op1 = (stack.pop());
                        
                        result = evaluateSingleOperator(token, op1, 0);
                        
                        stack.push(result);
                    }else{
                       
                        
                     if(stack.at(1) == null){
                       throw new NotationFormatException();
                     }
                    
                   
                    op2 = (stack.pop());
                    op1 = (stack.pop());
                    result = evaluateSingleOperator(token, op1, op2);     //
                    stack.push(result);
                   }
                    
                    
                }else{
                    
                    if(!(token.charAt(0) < 58 && token.charAt(0) > 47)){
                        throw new NotationFormatException();
                    }
                    
                    stack.push(Double.parseDouble(token));
                }
                    
            }

            return result;
            
    }
    
    /*
    Function: private boolean isOperator(String token)
    Description: This method returns a boolean expression which really helps with 
    the flow of the evaluate method since it essentially checks to see if the token
    that was parsed is equal an operator. If it is, then it will return true, and 
    trigger the arithmetic in evaluate.
    Inputs: This is a private method, the input will come from the evaluate method,
    however, the input will be a token.
    Outputs: The output will be a boolean, either true (it is an operator) or false,
    (it is not an operator).
    */

    private boolean isOperator(String token){
        return ( token.equals("+") || token.equals("-") ||
                  token.equals("*") || token.equals("/") || token.equals("cos") 
                    || token.equals("tan") || token.equals("sin") );

    }
     
    /*
    Function: private double evaluateSingleOperator(String operation, double op1, double op2)
    Description: This method also feeds off of the evaluate method and is triggered
    by whether or not the token is an operator. If isOperator is true, then two operands
    are popped off the stack, and the token with the operator and the operands are
    passed as parameters to this method for calculation using a switch depending 
    on the operator. Once the calculation is complete, the result is returned in order
    to be push back on the stack by evaluate. 
    Inputs: The operator, and two operands on the stack.
    Outputs: The double that will be pushed back on the stack by the evaluate method.
    */

    private double evaluateSingleOperator(String operation, double op1, double op2){
        double result = 0;

        switch (operation){
            case "+":
                result = op1 + op2;
                break;
            case "-":
                result = op1 - op2;
                break;
            case "*":
                result = op1 * op2;
                break;
            case "/":
                result = op1 / op2;
                break;
            case "cos":
                result = Math.cos(op1);
                break;
            case "sin":
                result = Math.sin(op1);
                break;
            case "tan":
                result = Math.tan(op1);
                break;
        }

        return result;
    }   
    
}
