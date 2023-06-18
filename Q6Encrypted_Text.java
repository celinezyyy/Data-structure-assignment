package Q6_Assignment;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
import java.util.Queue;

public class Q6Encrypted_Text {

    public static void main(String[] args) {
        
        Scanner read = new Scanner(System.in);
        Queue<Character> encryptTextQueue = new LinkedList<>();
        
        // Input encrypted text and shift position
        System.out.print("Text: ");
        String encryptedText = read.nextLine();
        System.out.print("Shift: ");
        String shiftPosition = read.next();
        int shift = Integer.parseInt(shiftPosition);
        
        for(int i=0; i<encryptedText.length(); i++)     
            encryptTextQueue.add(encryptedText.charAt(i));    //add the text into queue (easy to access, FIFO)
        
//        String encryptedText = "^hkcpzl$^jhv$^jhv$av$bzl$^aol$^johpu$^zayhalnlt,$(ojpod)$pz$av$johpu$opz$(zwpozlsaahi)$dpao$zayvun$pyvu$johpuz.";
//        int shiftPosition = 7;

        // Decrypt the text
        Queue<Character> decryptedText = decrypt(encryptTextQueue, shift);
        
        //print out the result
        int length = decryptedText.size();
        for(int i=0; i<length; i++)
            System.out.print(decryptedText.poll());
        System.out.println();
    }
    
    public static Queue decrypt(Queue<Character> encryptTextQueue, int shift) 
    {
        Queue<Character> decryptedText = new LinkedList<>();
        final int SIZE = encryptTextQueue.size();
        // Iterate over each character in the encrypted text
        for (int i = 0; i < SIZE; i++) 
        {
            char c =  encryptTextQueue.poll();
            c = Character.toLowerCase(c);   //change the character to lower case
            
            // Decrypt lowercase letters using Caesar cipher
            if(c=='^')
            {
                i++;
                c = encryptTextQueue.poll();
                
                c = (char) ((c - shift - 'a' + 26) % 26 + 'a');
                //substract 'a' is to normalize the range within 0 to 25(inclusive)
                // plus 26 is to ensures that the result of the calculation is a positive value within the range of 0 to 25.
                // % 26 is for handling cases where the value exceeds the range of 0 to 25
                // Adding 'a' to the result brings the value back to the ASCII range of lowercase letters. 
                c = Character.toUpperCase(c);
                decryptedText.offer(c);
            }
            // Decrypt space characters
            else if (c == '$') 
            {
                c = ' ';
                decryptedText.offer(c);
            }
            // Decrypt inverted text
            else if (c == '(') 
            {
                char find=')';
                Stack<Character> temp = new Stack();       //using stack to keep the text in bracket
                char keep = encryptTextQueue.poll();
                i++;
                while(keep!=find)
                {
                    temp.push(keep);            
                    keep = encryptTextQueue.poll();
                    i++;
                }
                int tempSize = temp.size();
                for(int j=0; j<tempSize;j++)
                {
                    char reverseInvertedText = temp.pop();      
                    //pop out according to LIFO rule, so that the text can be reversed to original text
                    c = (char) ((reverseInvertedText - shift - 'a' + 26) % 26 + 'a');
                    decryptedText.offer(c);
                }
            }
            //skip for ',' and '.'
            else if(c == ',' || c == '.')
            {
                decryptedText.offer(c);
            }
            else
            {
                c = (char) ((c - shift - 'a' + 26) % 26 + 'a');
                decryptedText.offer(c);
            }
        }
        return decryptedText;
    }
}