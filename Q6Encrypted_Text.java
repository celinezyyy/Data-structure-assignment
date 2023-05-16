
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
        
//        String encryptedText = "^hkcpzl$^jhv$^jhv$av$bzl$^aol$^johpu$^zayhalnlt,$(ojpod)$pz$av$johpu$opz$(zwpozlsaahi)$dpao$zayvun$pyvu$johpuz.";
//        int shiftPosition = 7;

        // Decrypt the text
        String decryptedText = decryptEncrypt(encryptedText, shift);
        System.out.println(decryptedText);
    }
    
    public static String decryptEncrypt(String encryptedText, int shift) 
    {
        String decryptedText = "";
        
        // Iterate over each character in the encrypted text
        for (int i = 0; i < encryptedText.length(); i++) {
            char c = encryptedText.charAt(i);   
            c = Character.toLowerCase(c);   //change the character to lower case
            
            // Decrypt lowercase letters using Caesar cipher
            
            if(c=='^')
            {
                i++;
                c = encryptedText.charAt(i);
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
                int endIndex = encryptedText.indexOf(')', i);
                String invertedText = encryptedText.substring(i + 1, endIndex);
                String invertedTextDecrypt="";
                for(int j=invertedText.length()-1; j>=0;j--)
                {
                    c = invertedText.charAt(j);
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




