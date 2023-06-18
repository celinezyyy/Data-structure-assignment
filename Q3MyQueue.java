package assignmenT_Q3;

import java.util.LinkedList;

public class Q3MyQueue<E> {
    
    private LinkedList<E> list = new LinkedList<>();
    
    public Q3MyQueue(E[]e)
    {
        for(int i=0; i<e.length; i++)
            enqueue(e[i]);
    }
    
    public Q3MyQueue()
    {
        
    }
    
    public void enqueue(E e)
    {
        list.addLast(e);
    }
    
    public E dequeue()
    {
        if(list.isEmpty())
        {
            System.out.print("Queue is empty now.");
            return null;
        }
        
        return list.removeFirst();
    }
    
    public E getElement(int i)
    {
        return list.get(i);
    }
    
    public int getSize()
    {
        return list.size();
    }
    
    public boolean isEmpty()
    {
        return list.isEmpty();
    }
    
    public String toString()
    {
        return "Queue: " + list.toString();
    }
}




