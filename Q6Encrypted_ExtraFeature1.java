// type 1 - using extra number
package Q6_Assignment;

import java.util.Scanner;

public class Q6Encrypted_ExtraFeature1 {

    public static void main(String[] args) {
        
        // Input encrypted text, shift position and a number
        Scanner read = new Scanner(System.in);
        System.out.print("Text: ");
        String encryptedText = read.next();
        System.out.print("Shift: ");
        String shiftPosition = read.next();
        System.out.print("Enter a number(for more secure encryption): ");
        String numSub = read.next();
        
        int shift = Integer.parseInt(shiftPosition);
        int numToSub = Integer.parseInt(numSub);
//        String encryptedText = "^hkcpzl$^jhv$^jhv$av$bzl$^aol$^johpu$^zayhalnlt,$(ojpod)$pz$av$johpu$opz$(zwpozlsaahi)$dpao$zayvun$pyvu$johpuz.";
//        int shiftPosition = 7;

        // encrypt the text
        String extraEncrypt = encrypt(encryptedText, numToSub);
        // Decrypt the text
        String decryptedText = decryptEncrypt(extraEncrypt, shift, numToSub);
        System.out.println(decryptedText);
    }
    public static String encrypt(String encryptedText, int numToSub)
    {
        String extraEncryption="";
        for(int i=0; i<encryptedText.length(); i++)
        {
            char c = encryptedText.charAt(i);  
            c-=numToSub;
            extraEncryption+=c;
        }
        return extraEncryption;
    }
    
    public static String decryptEncrypt(String extraEncrypt, int shift, int numToSub) 
    {
        String decryptedText = "";
        
        // Iterate over each character in the encrypted text
        for (int i = 0; i < extraEncrypt.length(); i++) {
            char c = extraEncrypt.charAt(i);   
            c += numToSub;
            c = Character.toLowerCase(c);   //change the character to lower case
            
            // Decrypt lowercase letters using Caesar cipher
            
            if(c=='^')
            {
                i++;
                c = extraEncrypt.charAt(i);
                c += numToSub;
                c = (char) ((c - shift - 'a' + 26) % 26 + 'a');
                //  26 ensures that the result of the calculation is a positive value within the range of 0 to 25.
                c = Character.toUpperCase(c);
                decryptedText += c;
            }
            // Decrypt space characters
            else if (c == '$') {
                c = ' ';
                decryptedText += c;
            }
            // Decrypt inverted text
            else if (c == '(') {
                
                int  find = ')' - numToSub;
                int endIndex = extraEncrypt.indexOf(find, i);
                String invertedText = extraEncrypt.substring(i + 1, endIndex);
                String invertedTextDecrypt="";
                for(int j=invertedText.length()-1; j>=0;j--)
                {
                    c = invertedText.charAt(j);
                    c += numToSub;
                    c = (char) ((c - shift - 'a' + 26) % 26 + 'a');
                    invertedTextDecrypt+=c;
                }
                decryptedText += invertedTextDecrypt;
                i = endIndex;
            }
            //skip for ',' and '.'
            else if(c == ',' || c == '.'){
                decryptedText += c;
            }
            // Ignore other characters
            else{
                c = (char) ((c - shift - 'a' + 26) % 26 + 'a');
                decryptedText += c;
            }
        }
        return decryptedText;
    }
}