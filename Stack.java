/*
Description: I used inheritance to derive the the Stack class from the
doublyLinkedList class in order to access the protected Node data since that was 
critical for accessing the data in each node. Another reason I used inheritance was
because doublyLinkedList was a generic class which is what I wanted to make my stack
in order to use Integer stacks or String stacks in my other classes. This Stack class
follows the rules that come with using a stack like Last In First Out (LIFO).
 */ 

package project.pkg3;

/**
 *
 * @author antho
 */
public class Stack <T> extends doublyLinkedList{
    
    
    //Default constructor, allows you to create an empty Stack
    public Stack(){
        
    }
    
    //Working constructor, allows you to create a Stack with a piece of data 
    //from the instantiation of the object
    public Stack(T data){
        
        push(data);
        
    }
    
    /*
    Function: isEmpty()
    Description: This method, which is incorporated in other method if else statements,
    allows my to check if the stack is empty by checking if the protected data (head) is 
    null. If this is the case, then the stack is empty.
    Inputs: No inputs required for this method.
    Outputs: This method will return a boolean whether or not there is anything on 
    the stack.
    */
    
    public boolean isEmpty(){
        return head == null;
    }
    
    
    /*
    Function: push(T data)
    Description: This method will at a node to the top of the stack, the top of the stack 
    using a doubly linked list will be the head. 
    Inputs: Push will create a new node with the data you pass it.
    Outputs: None
    */
    
    public void push(T data){
        
        if(isEmpty()){
            append(data);
        }else{
            prepend(data);
        }
    }
    
    /*
    Function: T pop()
    Description: Pop does 2 things, it will not only remove the top node in the stack,
    it will also return that node to you. If your stack is empty, the method will 
    return null implying that there is nothing to pop, since the stack is empty.
    Inputs: None
    Outputs: The data that is being taken off the top of the stack.
    */
    
    public T pop(){
        
        if(!isEmpty()){
            T temp = (T) at(0);
            delete(temp);
            return temp;
        }else{
            return null;
        }
        
    }
    
    /*
    Function: T peek()
    Description: This method returns the data that is at the top of the stack 
    without removing it. 
    Inputs: None
    Outputs: The data that is at the top of the stack. 
    */
    
    public T peek(){
        
        return (T) at(0);
        
    }
    
}
