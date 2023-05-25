
package Q6_Assignment;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class Q6MyStack {
    

    ArrayList list = new ArrayList<>();
    
    public void push(char o)
    {
        list.add(o);
    }
    
    public char pop()
    {
        if(isEmpty())
            throw new EmptyStackException();
        char o = (char) list.remove(getSize()-1);
        return o;
    }
    
    public char peek()
    {
        return (char) list.get(getSize()-1);
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
        return list.toString();
    }
    
}