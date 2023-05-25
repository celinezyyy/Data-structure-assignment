package Q6_Assignment;

import assignmenT_Q3.*;
import java.util.LinkedList;

public class Q6MyQueue {
    
    private LinkedList list = new LinkedList<>();
    
    public Q6MyQueue()
    {
        
    }
    
    public void enqueue(char e)
    {
        list.addLast(e);
    }
    
    public char dequeue()
    {
        return (char) list.removeFirst();
    }
    
    public char peek()
    {
        return (char) list.peek();
    }
    
    public int getSize()
    {
        return list.size();
    }
    
    public String toString()
    {
        String text="";
        for(int i=0; i<list.size(); i++)
            text += list.get(i);
        return text;
    }
}




