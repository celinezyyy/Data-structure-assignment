// using extra pin number
package Q6_Assignment;

import java.util.Queue;
import java.util.Scanner;

public class Q6Encrypted_ExtraFeature {

    public static void main(String[] args) {
        
        // Input encrypted text, shift position and a pin number
        Scanner read = new Scanner(System.in);
        System.out.print("Text: ");
        String encryptedText = read.next();
        System.out.print("Shift: ");
        String shiftPosition = read.next();
        System.out.print("Enter a pin number with 12 value(can be digit or alphabet or mix): ");
        String pin = read.next();
        if(pin.length()!=12)
        {
            System.out.print("Your pin number is exceed 12 value! Please enter again:");
            pin = read.next();
        }
        int shift = Integer.parseInt(shiftPosition);
        
        Q6MyQueue encryptTextQueue = new Q6MyQueue();   
        char []pinStack = new char[12];
        
        for(int i=0; i<encryptedText.length(); i++)     
            encryptTextQueue.enqueue(encryptedText.charAt(i));    //add the text into queue (easy to access, FIFO)
        
        for(int i=0; i<pinStack.length; i++)
            pinStack[i]=pin.charAt(i);          //array can easy access in sequence
//        String encryptedText = "^hkcpzl$^jhv$^jhv$av$bzl$^aol$^johpu$^zayhalnlt,$(ojpod)$pz$av$johpu$opz$(zwpozlsaahi)$dpao$zayvun$pyvu$johpuz.";
//        int shiftPosition = 7;

        // encrypt the text
        Q6MyQueue extraEncrypt = encrypt(encryptTextQueue, pinStack);
        // Decrypt the text
        Q6MyQueue decryptedText = decrypt(extraEncrypt, shift, pinStack);
        System.out.println("Decrypted text: " + decryptedText);
    }
    public static Q6MyQueue encrypt(Q6MyQueue encryptedText, char[] pinStack)
    {
        Q6MyQueue extraEncryption = new Q6MyQueue();
        final int ENCRYPTSIZE = encryptedText.getSize();
        
        for(int i=0; i<ENCRYPTSIZE; i++)
        {
            char c = encryptedText.dequeue();
            for(int p=pinStack.length-1; p>=0; p--)
                c -= (pinStack[p]);        //encrypt from last character of pin more secure
            
            extraEncryption.enqueue(c);
        }
        return extraEncryption;
    }
    
    public static Q6MyQueue decrypt(Q6MyQueue extraEncrypt, int shift, char[] pinStack) 
    {
        final int SIZE = pinStack.length;
        final int ENCRYPTSIZE = extraEncrypt.getSize();
        Q6MyQueue decryptedText = new Q6MyQueue();
        
        // Iterate over each character in the encrypted text
        for (int i = 0; i < ENCRYPTSIZE; i++) 
        {
            char c = extraEncrypt.dequeue();  
            for(int p=SIZE-1; p>=0; p--)    //decrypt to original text
                c += (pinStack[p]);
            
            c = Character.toLowerCase(c);   //change the character to lower case
            
            // Decrypt lowercase letters using Caesar cipher
            if(c=='^')
            {
                i++;
                c = extraEncrypt.dequeue();
                for(int p=SIZE-1; p>=0; p--)
                    c += (pinStack[p]);
            
                c = (char) ((c - shift - 'a' + 26) % 26 + 'a');
                //  26 ensures that the result of the calculation is a positive value within the range of 0 to 25.
                c = Character.toUpperCase(c);
                decryptedText.enqueue(c);
            }
            // Decrypt space characters
            else if (c == '$') {
                c = ' ';
                decryptedText.enqueue(c);
            }
            // Decrypt inverted text
            else if (c == '(') 
            {
                char find=')';
                for(int p=SIZE-1; p>=0; p--)
                    find -= (pinStack[p]); 
            
                Q6MyStack temp = new Q6MyStack();       //using stack to keep the text in bracket
                char keep = extraEncrypt.dequeue();
                i++;
                while(keep!=find)
                {
                    temp.push(keep);            
                    keep = extraEncrypt.dequeue();
                    i++;
                }
                int tempSize = temp.getSize();
                
                for(int j=0; j<tempSize;j++)
                {
                    char reverseInvertedText = temp.pop();      //pop out according to LIFO rule, so that the text can be reversed to original text
                    for(int p=SIZE-1; p>=0; p--)
                        reverseInvertedText += (pinStack[p]);

                    c = (char) ((reverseInvertedText - shift - 'a' + 26) % 26 + 'a');
                    decryptedText.enqueue(c);
                }
            }
            //skip for ',' and '.'
            else if(c == ',' || c == '.')
            {
                decryptedText.enqueue(c);
            }
            else
            {
                c = (char) ((c - shift - 'a' + 26) % 26 + 'a');
                decryptedText.enqueue(c);
            }
        }
        return decryptedText;
    }
}