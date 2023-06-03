// using extra pin number
package Q6_Assignment;

import java.util.Random;
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
        System.out.print("Enter a pin number between 9-12(in digit): ");
        String pin = read.next();

        int shift = Integer.parseInt(shiftPosition);

        if(pin.length()<9 || pin.length()>12)
        {
            System.out.print("Invalid pin number! Please enter again: ");
            pin = read.next();
        }
        Q6MyQueue encryptTextQueue = new Q6MyQueue();
        char[] pinStack = new char[pin.length()];

        Random generate = new Random(); 
        int[] subPin = new int[4];
        
        for (int i = 0; i < 4; i++) //randomly generate a 4 different index from pin number
            subPin[i] = generate.nextInt(pin.length());
        
        System.out.print("The random index of pin number that will be used to extra encrypted the text: " );
        for(int i=0; i<subPin.length; i++)
        {
            if(i!=subPin.length-1)
                System.out.print(subPin[i] + ", ");
            else
                System.out.print(subPin[i]);
        }
        
        System.out.println();
        
        for (int i = 0; i < encryptedText.length(); i++) 
            encryptTextQueue.enqueue(encryptedText.charAt(i));    //add the text into queue (easy to access, FIFO)
        
        for (int i = 0; i < pinStack.length; i++) 
            pinStack[i] = pin.charAt(i);          //array can easy access in sequence
        
//        String encryptedText = "^hkcpzl$^jhv$^jhv$av$bzl$^aol$^johpu$^zayhalnlt,$(ojpod)$pz$av$johpu$opz$(zwpozlsaahi)$dpao$zayvun$pyvu$johpuz.";
//        int shiftPosition = 7;

        // encrypt the text
        Q6MyQueue extraEncrypt = encrypt(encryptTextQueue, pinStack, subPin);
        // Decrypt the text
        System.out.println("Extra encrypted text after performed the encryption: " + extraEncrypt);
        Q6MyQueue decryptedText = decrypt(extraEncrypt, shift, pinStack, subPin);
        System.out.println("Decrypted text: " + decryptedText);
    }

    public static Q6MyQueue encrypt(Q6MyQueue encryptedText, char[] pinStack, int[] subPin) 
    {
        Q6MyQueue extraEncryption = new Q6MyQueue();
        final int ENCRYPTSIZE = encryptedText.getSize();
        
        for(int i=0; i<ENCRYPTSIZE; i++)
        {
            char c = encryptedText.dequeue();
            for(int p=0; p<subPin.length; p++)
                c  = (char) ((c + pinStack[subPin[p]]- ' ' + 94) % 94 +' ');        //encrypt from last character of pin more secure
            
            extraEncryption.enqueue(c);
        }
        return extraEncryption;
    }

    public static Q6MyQueue decrypt(Q6MyQueue extraEncrypt, int shift, char[] pinStack, int[] subPin) 
    {
        final int SIZE = pinStack.length;
        final int ENCRYPTSIZE = extraEncrypt.getSize();
        Q6MyQueue decryptedText = new Q6MyQueue();
        
        // Iterate over each character in the encrypted text
        for (int i = 0; i < ENCRYPTSIZE; i++) 
        {
            char c = extraEncrypt.dequeue();
            //c = Character.toLowerCase(c);   //change the character to lower case
            for (int p = subPin.length-1; p >=0; p--) 
                c  = (char) ((c - pinStack[subPin[p]]-' ' + 94) % 94 +' ');      
            
            // Decrypt lowercase letters using Caesar cipher
            if (c == '^') 
            {
                i++;
                c = extraEncrypt.dequeue();
                c = Character.toLowerCase(c); 
                
                for (int p = subPin.length-1; p >=0; p--) 
                    c  = (char) ((c - pinStack[subPin[p]]-' ' + 94) % 94 +' ');  
                
                c = (char) ((c - shift - 'a' + 26) % 26 + 'a');
                c = Character.toUpperCase(c);
                decryptedText.enqueue(c);
            } // Decrypt space characters
            else if (c == '$') 
            {
                c = ' ';
                decryptedText.enqueue(c);
            } // Decrypt inverted text
            else if (c == '(') 
            {
                char find = ')';
                for (int p = 0; p <subPin.length; p++) 
                    find  = (char) ((find + pinStack[subPin[p]]-' ' + 94) % 94 +' ');        
                
                Q6MyStack temp = new Q6MyStack();       //using stack to keep the text in bracket
                char keep = extraEncrypt.dequeue();
                i++;
                while (keep != find) {
                    temp.push(keep);
                    keep = extraEncrypt.dequeue();
                    i++;
                }
                int tempSize = temp.getSize();

                for (int j = 0; j < tempSize; j++) 
                {
                    char reverseInvertedText = temp.pop();      //pop out according to LIFO rule, so that the text can be reversed to original text
                    for (int p = subPin.length-1; p >=0; p--) 
                        reverseInvertedText  = (char) ((reverseInvertedText - pinStack[subPin[p]]-' ' + 94) % 94 +' ');  
                    
                    c = (char) ((reverseInvertedText - shift - 'a' + 26) % 26 + 'a');
                    decryptedText.enqueue(c);
                }
            } //skip for ',' and '.'
            else if (c == ',' || c == '.') 
                decryptedText.enqueue(c);
            else //decrypt alphabet that didn't satisfied the 
            {     
                c = (char) ((c - shift - 'a' + 26) % 26 + 'a');
                decryptedText.enqueue(c);
            }
        }
        return decryptedText;
    }
}
