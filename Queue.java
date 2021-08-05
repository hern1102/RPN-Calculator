/*
Name: Anthony Hernandez 
Description: I used inheritance to derive the the Queue class from the
doublyLinkedList class in order to access the protected Node data since that was 
critical for accessing the data in each node. Another reason I used inheritance was
because doublyLinkedList was a generic class which is what I wanted to make my Queue
in order to provide flexibility and use almost any data type in my other classes. This 
Queue class follows the rules that come with using a Queue like First In, First Out (FIFO).
 */ 

package project.pkg3;

/**
 *
 * @author antho
 */
public class Queue <T> extends doublyLinkedList{
    
    //Default constructor, allows you to create an empty Queue
    public Queue(){
        
    }
    
    //Working constructor, allows you to create a Queue with a piece of data 
    //from the instantiation of the object
    public Queue(T data){
        
        enqueue(data);
        
    }
    
    /*
    A private instance of node was created in order to more efficently add new
    nodes to the end of the Queue as discussed in the lecture material.
    */
    
    private Node tail;
    
    /*
    Function: isEmpty()
    Description: This method, which is incorporated in other method's if else statements,
    allows me to check if the queue is empty by checking if the protected data (head) is 
    null. If this is the case, then the queue is empty.
    Inputs: No inputs required for this method.
    Outputs: This method will return a boolean whether or not there is anything on 
    the queue. 
    */
    
    public boolean isEmpty(){
        return head == null;
    }
    
    /*
    Function: enqueue(T data)
    Description: For a more efficent program, we keep track of tail, and simply
    add to the end of the linked list using a reference vice traversing through
    the entire linked list. In layman terms, this adds the data that you wish 
    to add to the end of the queue.
    Inputs: The piece of data that you wish to add to the end of the queue
    Outputs: None
    */
    
    public void enqueue(T data){
        
        Node temp = new Node(data);
       
        //If queue is empty, then new node is both the head and tail
        if (isEmpty()){
           head = temp;
           tail = temp; 
           return;
        }
       
        // Add the new node at the end of queue and change tail in a doubly
        //linked list fashion
        tail.next = temp;
        tail.next.prev = tail;
        tail = tail.next;

    }
    
    /*
    Function: T dequeue()
    Description: This method simply returns the piece of data at the front of 
    the queue, and also removes that piece of data from the front of the queue.
    Inputs: None
    Outputs: The piece of data that was at the front of the queue is returned.
    */
    
    public T dequeue(){
        
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
    Description: This method returns the data that is at the top of the queuej
    without removing it. 
    Inputs: None
    Outputs: The data that is at the top of the queue
    */
    
    public T peek(){
        
        return (T) at(0);
        
    }
    
}
