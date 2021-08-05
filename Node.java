/*
class: protected class Node
Description: This class allows for the creation of Node in which you can 
create a Node and access that Nodes next, prev, and data. It really is the base of
LinkedList
Inputs: When creating a Node, you can pass it the data that you wish to store 
in memory.
*/

package project.pkg3;

/**
 *
 * @author antho
 */
public class Node <T>{
   
    protected Node next;
    protected Node prev;
    protected T data;
        
        public Node(){ 
        }
        public Node(T data) {
            this.data = data;
        }
}
