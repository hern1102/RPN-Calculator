/*
Description: THIS IS THE MAIN FILE. The infixCalculator class will take an input
from the user in infix notation format using the following operators (+,-,*,/)
Once the input is recieved, the program then parses each token and using the private
methods that return booleans to guide the flow of the program. Once the program converts 
the infix notation to postfix notation, it then solves the postfi notation expression
with the methods from rpnCalculator. I use composition to implement the Stack class in the 
infix calculator class, and I also derived infixCalculator from rpnCalculator. 
 */ 

package project.pkg3;


import java.util.Scanner;
import java.util.StringTokenizer;
import static jdk.nashorn.internal.objects.NativeString.toLowerCase;

/**
 *
 * @author antho
 */
public class infixCalculator extends rpnCalculator{


    public infixCalculator(){
        
    }
    
    /*
    Function: isOperator(String c)
    Description: This method essentially checks to see if the String in question is an 
    operator, it will control the flow of the convertToPostFix method since it will return
    a boolean depending on the result.
    Inputs: A String is required to test for equality 
    Outputs: A boolean will be returned depending on whether or not the String is 
    an operator.
    */

    private boolean isOperator(String c) { 

        return "+".equals(c)  ||  "-".equals(c)  ||  "*".equals(c)  ||  "/".equals(c)  || "(".equals(c) || ")".equals(c) 
                || "cos".equals(c) || "sin".equals(c) || "tan".equals(c);
   
    }

    /*
    Function: isSpace(String c)
    Description: This method will check if the String in question is a space or not, 
    which like the isOperator method, will control the flow of the program. This method
    has a little less significance compared to the flow of the program. 
    Inputs: A String is required to test for equality
    Outputs: A boolean will be returned depending on whether or not the String is 
    a space or not.
    */

    private boolean isSpace(String c) {  

        return (c.equals(" "));
   
    }
    
    
    /*
    Function: lowerPrecedence(String op1, String op2)
    Description: This method will check to see if operator 1 has a lesser precedence 
    than operator 2. Depending on the result, this method will alter the flow of the 
    convertToPostfix method. op1 and op2 are assumed to be operators (+,-,*,/).
    Inputs: 2 Strings that represent operators.
    Outputs: A boolean will be returned depending on the comparison.
    */


    private boolean lowerPrecedence(String op1, String op2) {
      
      switch (op1) {

         case "+":
         case "-":
            return !("+".equals(op2) || "-".equals(op2)) ;

         case "*":
         case "/":
            return "(".equals(op2);

         case "(":
            return true;
            
         case"sin":
         case"tan":
         case"cos":
            return true;

         default:  // Should not occur
            return false;
      }
 
    }


    /*
    Function: convertToPostfix(String infix)
    Description: This method will take the input of a string in and parse what should 
    be an expression in infix notation and return a string with the equivalent in Postfix
    notation or RPN.
    Inputs: This method requires a properly formatted infix notation String expression 
    Outputs: A String will be the output of this method, that string will be the equivalent
    of the infix notation, but in postfix notation. 
    */

    public String convertToPostfix(String infix) throws NotationFormatException{
        Stack operatorStack = new Stack(); 
        
        StringTokenizer parser = new StringTokenizer(infix,"+-*/^() ",true);
        StringBuffer postfix = new StringBuffer(infix.length());
        while (parser.hasMoreTokens()) {
           String token = toLowerCase(parser.nextToken());
           
           if (isOperator(token)){
              while (!operatorStack.isEmpty() &&
                  !lowerPrecedence(((String)operatorStack.peek()), token)){
                  postfix.append(" ").append((String)operatorStack.pop());
              }
          
              if (token.equals(")")) {
                    String operator = (String)operatorStack.pop();
                    
                    
                    if(operatorStack.isEmpty()){
                           throw new NotationFormatException();
                       }

                    while (!(operator.equals("("))) {
                       postfix.append(" ").append(operator);
                       
                       if(operatorStack.isEmpty()){
                           throw new NotationFormatException();
                       }
                       
                       
                       operator = (String)operatorStack.pop();  
                    }
              }
              else
                 operatorStack.push(token);
           } else if (isSpace(token)) {
               
           } else { 
             postfix.append(" ").append(token);  
           }
         }
        while (!operatorStack.isEmpty())
           postfix.append(" ").append((String)operatorStack.pop());
     
        return postfix.toString();
       
    }
   
    /*
    Function: Calculate(String str)
    Description: This method is basically the culminating method for this program.
    It takes in a String that is formatted as infix notaion as input, converts 
    that string to postfix notation, and then performs the calculation and returns
    the result as an double. 
    Inputs: A string formatted in infix notation will be the input
    Outputs: A double that represents the result of the expression passed will be 
    the output.
    */
   
   
    public Double Calculate(String str) throws NotationFormatException{
       String infixToPostfix = convertToPostfix(str);
       Double result = evaluate(infixToPostfix);
       return result;
     
    }
   
    
   public static void main(String[] args){
       
       /*
       The program below will provide the user with some instructions on how to proceed 
       with the program. Once the user enters an expression as a String in infix notation, an 
       example will be given as to what that looks like, that expression will be converted to 
       to a postfix notation using the infixCalculator, and then parsed and evaluated using the
       rpnCalculator. The conversion to postfix and the result of the expression will be printed
       to the console. The user will then be asked if he or she would like to evaluate another 
       expression, all they would simply have to do is type "y" for yes and "n" to exit the program.
       */

       
       try{
           String expression, again;
           Double result;

           Scanner keyboard = new Scanner(System.in);

        do{  
            infixCalculator evaluator = new infixCalculator();
            System.out.println("Enter a valid Infix expression one token " +
                                 "at a time with a space between each token (e.g. 2 * (2 + 2 * 4)/ 20 + 5 - 2 + sin(45))");
            System.out.println("Each token must be an integer or an operator (+,-,*,/,(),cos(),sin(),tan())");
            //sin(), cos(), and tan() when used multiple times in each expression can alter the precedence of an expression
            System.out.println("You can only use the following operators once in each expression: cos(),sin(),tan() (e.g. 3 * 2 + sin(45))");
            expression = keyboard.nextLine();

            result = evaluator.Calculate(expression);
            System.out.println();
            System.out.println("That expression in PostFix notation (RPN) is: " + evaluator.convertToPostfix(expression));
            System.out.println("That expression equates to: " + result);

            System.out.print("Would you like to evaluate another expression [Y/N]? ");
            again = keyboard.nextLine();
            System.out.println();
        
        } while (again.equalsIgnoreCase("y"));
        
       }catch(NotationFormatException e){
           System.out.println(e.getMessage());
       }
        

        /*
        There is plenty of test code for the other parts of this assignment. Each class
        and method was tested seperately to ensure that everything worked before
        moving on to the rest of the assignment.
        */  
            
            
            
        /*   
        //***********    TEST CODE FOR THE QUEUE   ************
        
        Queue<String> queue = new Queue<>("ZERO");
        
        queue.enqueue("One");
        queue.enqueue("Two");
        queue.enqueue("Three");
        queue.enqueue("Four");
        queue.enqueue("Five");
        queue.enqueue("Six");
        
        
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        
        System.out.println("This is you peeking: " + queue.peek());
        
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        */
        
        
        
        /*
        //***********    TEST CODE FOR THE STACK   ************
        
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        stack.push(5);
        stack.push(10);        
        stack.push(15);
        stack.push(20);
        stack.push(25);
        stack.push(30);
        stack.push(35);
        
        
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        
        System.out.println(stack.peek());
        
        
        stack.push(0);
        stack.push(5);
        stack.push(10);        
        stack.push(15);
        stack.push(20);
        stack.push(25);
        stack.push(30);
        stack.push(35);
        
        stack.printList();
        */
        
        
        
        
        /*
        //***********    TEST CODE FOR THE DOUBLY LINKED LIST   ************
       
        doublyLinkedList <String> list = new doublyLinkedList<>();
        
        list.append("Hey1");
        list.append("Hey2");
        list.append("Hey3");
        list.prepend("ZERO");
        list.prepend("NEG ONE");
 
        list.append("Last append");
        
        list.delete("Hey1");
        
        list.insert("NEG ONE", "INSERTION");

        
        list.printList();
        
        System.out.println("This is where you find the index of the node "
                + "that contains the data you are looking for INDEX #: " + list.find("Last append"));
        */ 
               
     } 
    
}
   
   
    
    
    
    


