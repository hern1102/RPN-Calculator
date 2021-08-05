/*
Description: A doubly-linked list is a linked data structure that consists of a set of 
sequentially linked records called nodes. Each node contains two fields, called links, 
that are references to the previous and to the next node in the sequence of nodes. The 
beginning and ending nodes previous and next links, respectively, point to some kind of 
node or null, to facilitate traversal of the list. This class is a generic doubly linked 
list class that uses uses Nodes via composition and stores data in these nodes, and uses 
the methods below to linked all nodes together.
 */ 

package project.pkg3;

/**
 *
 * @author antho
 */
public class doublyLinkedList <T> {
    
    //default constructor
    public doublyLinkedList(){  
    }
    
    //overloaded constructor
    public doublyLinkedList(T data){
        append(data);
    }
    
    /*
    Creating a protected instance of Node as head in the field allows us
    to create an anchor to the first element in the Linked List when it
    is created. This will enable a lot of the methods below, and enable
    the methods of other classes that derived from LinkedList that it why
    this instance is protected.
    */
    
    protected Node head;
    
    /*
    Function: append(T data)
    Description: This method adds a node with the data you pass it to the end
    of the linked list
    Inputs: The data you wish to add to the end of the linked list
    Outputs: None
    */
    
    public void append(T data){
        
        if(head == null){
            head = new Node(data);
            return;
        }
        
        Node current = head;
        
        while(current.next != null){
            current = current.next;
        }
        
        Node n = new Node();
        
        n.data = data;
        n.next = null;
        
        current.next = n;
        
        n.prev = current;
        
    }
    
    /*
    Function: prepend(T data)
    Description: This method will add the data that you pass to it to the 
    front of the LinkedList. 
    Inputs: The data you wish to add to the front of the linked list
    Outputs: None
    */
    
    public void prepend(T data){
        
        Node newHead = new Node(data);
        newHead.next = head;
        head = newHead;
        newHead.prev = head;
        newHead.next.prev = head;
        head.prev = null;
        
    }
    
    /*
    Function: insert(T search, T dataInserted)
    Description: This method will search through the data section of every node
    and once it finds that node you were searching for, it will add a new node 
    with the data that you passed it in front of the node you searched for. 
    In laymans terms, the method INSERTS a new node in front of whatever node you
    tell it to with the data that you pass to it.
    Inputs: The node you are searching for, and the data you wish to insert
    Outputs: None
    */
    
    public void insert(T search, T dataInserted){
        
        Node current = head;
        Node n;
        
        if(current.data == search){
            prepend(dataInserted);
        }
        
        while(current.next != null){
            if(current.next.data.equals(search)){
                n = new Node(dataInserted);
                n.next = current.next;
                n.prev = current;
                current.next.prev = n;
                current.next = n;
                return;
            }
            current = current.next;
        }
        
    }
    
    /*
    Function: delete(T data)
    Description: This method takes a piece of data that you no longer want in 
    your linked list and removes it from the linked list
    Inputs: The piece of data you want removed from the linked list
    Outputs: None
    */
    
    public void delete(T data){
        
        if(head == null){
            return;
        }
        if(head.data == data){
            if(head.next == null){
                head = null;
                return;
            }
            head = head.next;
            head.prev = null;
            return;
        }
        
        Node current = head;

        
        while(current.next != null){
            if(current.next.data == data){
                if(current.next.next == null){
                    current.next.prev = null;
                    current.next = null;
                    return;
                }
               
                current.next = current.next.next;
                current.next.prev = current;
                
                return;
            }
            current = current.next;
        }
        
    }
    
    /*
    Function: int find(T data)
    Description: This method is going to take a piece of data and search the
    linkedlist for a node that contains that piece of data. If it finds a node
    that contains that piece of data, the method will return the node index. 
    Inputs: The piece of data you wish to find
    Outputs: An int that represents the index of the node in the linked list where
    the data that you were searching for was found.
    */
    
    public int find(T data){
        
        int counter = 0;
        Node current = head;
        
        while(current != null){
            if(current.data.equals(data)){
                return counter;
            }
            counter++;
            current = current.next;
        }
        counter = -1;
        return counter;
        
    }
    
    /*
    Function: T at(int nodeIndex)
    Description: This method will return the node at the index of the linked
    list that you pass it. This method worked so well with the find()
    method, that it was incorporated in project 3 to find the index of the node
    and then return the Node at index (blank). 
    Inputs: The index you wish to return as an int
    Outputs: The object that was at the index that you were looking for
    */
    
    
    public T at(int nodeIndex){
        
        Node current = head;
        int counter = 0;
        
        while(current != null){
            if(counter == nodeIndex){
                return (T) current.data;
            }
            counter++;
            current = current.next;
        }
        return null;
    }
    
    /*
    Function: printList()
    Description: For testing purposes, printList is incorporated in order to ensure 
    that all of the next references were working correctly
    Inputs: None required
    Outputs: This method will print the entire linked list 
    */
    
    public void printList(){
        Node current = head;
        while(current != null){
            System.out.println(current.data);
            current = current.next;
        }
    }
    
    /*
    Function: printListReverse()
    Description: For testing purposes, printListReverse is incorporated in order
    to ensure that all of the previous references for the nodes are working correctly.
    Inputs: None required
    Outputs: This method will print the entire linked list in reverse order 
    */
    
    
    public void printListReverse(){
        Node current = head;
        while(current.next != null){
            current = current.next;
        }
        
        while(current != null){
            System.out.println(current.data);
            current = current.prev;
        }
    }
    
    
}
