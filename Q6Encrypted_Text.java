
package Q6_Assignment;

import java.util.Scanner;

public class Q6Encrypted_Text {

    public static void main(String[] args) {
        
        // Input encrypted text and shift position
        Scanner read = new Scanner(System.in);
        System.out.print("Text: ");
        String encryptedText = read.nextLine();
        System.out.print("Shift: ");
        String shiftPosition = read.next();
        int shift = Integer.parseInt(shiftPosition);
        
        Q6MyQueue encryptTextQueue = new Q6MyQueue();
        for(int i=0; i<encryptedText.length(); i++)     
            encryptTextQueue.enqueue(encryptedText.charAt(i));    //add the text into queue (easy to access, FIFO)
        
//        String encryptedText = "^hkcpzl$^jhv$^jhv$av$bzl$^aol$^johpu$^zayhalnlt,$(ojpod)$pz$av$johpu$opz$(zwpozlsaahi)$dpao$zayvun$pyvu$johpuz.";
//        int shiftPosition = 7;

        // Decrypt the text
        Q6MyQueue decryptedText = decrypt(encryptTextQueue, shift);
        System.out.println(decryptedText);
    }
    
    public static Q6MyQueue decrypt(Q6MyQueue encryptTextQueue, int shift) 
    {
        Q6MyQueue decryptedText = new Q6MyQueue();
        final int SIZE = encryptTextQueue.getSize();
        // Iterate over each character in the encrypted text
        for (int i = 0; i < SIZE; i++) {
            char c = encryptTextQueue.dequeue();   
            c = Character.toLowerCase(c);   //change the character to lower case
            
            // Decrypt lowercase letters using Caesar cipher
            if(c=='^')
            {
                i++;
                c = encryptTextQueue.dequeue();
                c = (char) ((c - shift - 'a' + 26) % 26 + 'a');
                //  26 ensures that the result of the calculation is a positive value within the range of 0 to 25.
                c = Character.toUpperCase(c);
                decryptedText.enqueue(c);
            }
            // Decrypt space characters
            else if (c == '$') 
            {
                c = ' ';
                decryptedText.enqueue(c);
            }
            // Decrypt inverted text
            else if (c == '(') 
            {
                char find=')';
                Q6MyStack temp = new Q6MyStack();       //using stack to keep the text in bracket
                char keep = encryptTextQueue.dequeue();
                i++;
                while(keep!=find)
                {
                    temp.push(keep);            
                    keep = encryptTextQueue.dequeue();
                    i++;
                }
                int tempSize = temp.getSize();
                for(int j=0; j<tempSize;j++)
                {
                    char reverseInvertedText = temp.pop();      //pop out according to LIFO rule, so that the text can be reversed to original text
                    c = (char) ((reverseInvertedText - shift - 'a' + 26) % 26 + 'a');
                    decryptedText.enqueue(c);
                }
            }
            //skip for ',' and '.'
            else if(c == ',' || c == '.')
            {
                decryptedText.enqueue(c);
            }
            // Ignore other characters
            else
            {
                c = (char) ((c - shift - 'a' + 26) % 26 + 'a');
                decryptedText.enqueue(c);
            }
        }
        return decryptedText;
    }
}