package javafxtest;

import java.util.LinkedList;

public class Q6MyQueuejavafx {
    
    private LinkedList list = new LinkedList<>();
    
    
    public Q6MyQueuejavafx()
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
    
    public char getElement(int i)
    {
        return (char) list.get(i);
    }
    
    public char peek()
    {
        return (char) list.peek();
    }
    
    public int getSize()
    {
        return list.size();
    }
    
    public boolean contains(char e)
    {
        return list.contains(e);
    }
    
    public boolean isEmpty()
    {
        return list.isEmpty();
    }
    
    public String toString()
    {
        String text="";
        for(int i=0; i<list.size(); i++)
            text += list.get(i);
        return text;
    }
}




