
package Q6_Assignment;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.util.Base64;
import java.util.Scanner;

public class Q6EncryptedText_ExtraFeature {

    private SecretKey key;
    private final int KEY_SIZE = 128;       // using 128 bits because if we use 192 and 256 bits, it will slower the encryption and decryption operations
                                            //as 128 bits is enough to strike a balance security and efficiency, provide strong encryption while minimizing the performance impact
    private final int DATA_LENGTH = 128;    // used to specify the length of the GCM authentication tag
                                            // (GCM) Galois/Counter Mode -  a mode of operation for symmetric key block ciphers, the authentication tag is generated during the encryption process and appended to the ciphertext
    private Cipher encryptionCipher;

    public void generateKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");    //advanced encryption standard
        keyGenerator.init(KEY_SIZE);
        key = keyGenerator.generateKey();       // generate the random AES secret key 
    }
    
    public String encrypt(String message) throws Exception {
        byte[] dataInBytes = message.getBytes();       // change the message in byte format
        encryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
        encryptionCipher.init(Cipher.ENCRYPT_MODE, key);     // prepare the cipher to encrypt message using the key generated   
        byte[] encryptedBytes = encryptionCipher.doFinal(dataInBytes);  // perform the encryption operation
        return encode(encryptedBytes);
    }

    public String decrypt(String encryptedData) throws Exception {
        byte[] dataInBytes = decode(encryptedData);
        Cipher decryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(DATA_LENGTH, encryptionCipher.getIV());
        decryptionCipher.init(Cipher.DECRYPT_MODE, key, spec);
        byte[] decryptedBytes = decryptionCipher.doFinal(dataInBytes);  //// perform the decryption operation
        return new String(decryptedBytes);
    }
    
    private String encode(byte[] data) {        //convert the encrypted bytes into a string representation
        return Base64.getEncoder().encodeToString(data);    
    }

    private byte[] decode(String data) {        // convert the encoded String into a byte array
        return Base64.getDecoder().decode(data);
    }
    
    public static void main(String[] args) {
        try {
             Scanner read = new Scanner(System.in);
            System.out.print("Text: ");
            String encryptedText = read.nextLine(); //Advise Cao Cao to use The Chain Strategem, which is to chain his battleships with strong iron chains.
            Q6EncryptedText_ExtraFeature aes_encryption = new Q6EncryptedText_ExtraFeature();
            aes_encryption.generateKey();
            String encryptedData = aes_encryption.encrypt(encryptedText);
            String decryptedData = aes_encryption.decrypt(encryptedData);

            System.out.println("Encrypted message : " + encryptedData);
            System.out.println("Decrypted message : " + decryptedData);
        } catch (Exception ignored) {
        }
    }
}
