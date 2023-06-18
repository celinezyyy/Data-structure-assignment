// using extra pin number
package Q6_Assignment;

import java.util.LinkedList;
import java.util.Random;
import java.util.Queue;
import java.util.Stack;
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
        Queue<Character> encryptTextQueue = new LinkedList<>();
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
            encryptTextQueue.offer(encryptedText.charAt(i));    //add the text into queue (easy to access, FIFO)
        
        for (int i = 0; i < pinStack.length; i++) 
            pinStack[i] = pin.charAt(i);          //array can easy access in sequence
        
//        String encryptedText = "^hkcpzl$^jhv$^jhv$av$bzl$^aol$^johpu$^zayhalnlt,$(ojpod)$pz$av$johpu$opz$(zwpozlsaahi)$dpao$zayvun$pyvu$johpuz.";
//        int shiftPosition = 7;

        // extra encrypt the text
        Queue extraEncrypt = encrypt(encryptTextQueue, pinStack, subPin);
        
        System.out.println("\nExtra encrypted text after performed the encryption:");
        for(Object list : extraEncrypt)
        {
            System.out.print(list);
        }
            
        System.out.println();
        
        // Decrypt the text
        Queue decryptedText = decrypt(extraEncrypt, shift, pinStack, subPin);
        System.out.println("\nDecrypted text:");
        while(!decryptedText.isEmpty())
            System.out.print(decryptedText.poll());
        System.out.println();
    }

    public static Queue encrypt(Queue<Character> encryptedText, char[] pinStack, int[] subPin) 
    {
        Queue<Character> extraEncryption = new LinkedList();
        final int ENCRYPTSIZE = encryptedText.size();
        
        for(int i=0; i<ENCRYPTSIZE; i++)
        {
            char c = encryptedText.poll();
            for(int p=0; p<subPin.length; p++)
                c  = (char) ((c + pinStack[subPin[p]] - ' ' + 94) % 94 +' ');        //encrypt from last character of pin more secure
            
            extraEncryption.offer(c);
        }
        return extraEncryption;
    }

    public static Queue decrypt(Queue<Character> extraEncrypt, int shift, char[] pinStack, int[] subPin) 
    {
        final int SIZE = pinStack.length;
        final int ENCRYPTSIZE = extraEncrypt.size();
        Queue<Character> decryptedText = new LinkedList<>();
        
        // Iterate over each character in the encrypted text
        for (int i = 0; i < ENCRYPTSIZE; i++) 
        {
            char c = extraEncrypt.poll();
            //c = Character.toLowerCase(c);   //change the character to lower case
            for (int p = subPin.length-1; p >=0; p--) 
                c  = (char) ((c - pinStack[subPin[p]] - ' ' + 94) % 94 +' ');      
            
            // Decrypt lowercase letters using Caesar cipher
            if (c == '^') 
            {
                i++;
                c = extraEncrypt.poll();
                c = Character.toLowerCase(c); 
                
                for (int p = subPin.length-1; p >=0; p--) 
                    c  = (char) ((c - pinStack[subPin[p]] - ' ' + 94) % 94 +' ');  
                
                c = (char) ((c - shift - 'a' + 26) % 26 + 'a');
                c = Character.toUpperCase(c);
                decryptedText.offer(c);
            } // Decrypt space characters
            else if (c == '$') 
            {
                c = ' ';
                decryptedText.offer(c);
            } // Decrypt inverted text
            else if (c == '(') 
            {
                char find = ')';
                for (int p = 0; p <subPin.length; p++) 
                    find  = (char) ((find + pinStack[subPin[p]] - ' ' + 94) % 94 +' ');        
                
                Stack<Character> temp = new Stack<>();       //using stack to keep the text in bracket
                char keep = extraEncrypt.poll();
                i++;
                while (keep != find) {
                    temp.push(keep);
                    keep = extraEncrypt.poll();
                    i++;
                }
                int tempSize = temp.size();

                for (int j = 0; j < tempSize; j++) 
                {
                    char reverseInvertedText = temp.pop();      //pop out according to LIFO rule, so that the text can be reversed to original text
                    for (int p = subPin.length-1; p >=0; p--) 
                        reverseInvertedText  = (char) ((reverseInvertedText - pinStack[subPin[p]]-' ' + 94) % 94 +' ');  
                    
                    c = (char) ((reverseInvertedText - shift - 'a' + 26) % 26 + 'a');
                    decryptedText.offer(c);
                }
            } //skip for ',' and '.'
            else if (c == ',' || c == '.') 
                decryptedText.offer(c);
            else //decrypt alphabet that didn't satisfied the 
            {     
                c = (char) ((c - shift - 'a' + 26) % 26 + 'a');
                decryptedText.offer(c);
            }
        }
        return decryptedText;
    }
}
