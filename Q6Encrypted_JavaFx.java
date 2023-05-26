// basic feature
package javafxtest;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Q6Encrypted_JavaFx extends Application 
{

    private TextArea outputTextArea;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Set up the user interface
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        
        // Top section - input fields and decrypt button
        VBox topBox = new VBox(5);
        topBox.setAlignment(Pos.CENTER);
        topBox.setPadding(new Insets(5));
        
        Label textLabel = new Label("Text:");
        TextField textField = new TextField();
        
        Label shiftLabel = new Label("Shift:");
        TextField shiftField = new TextField();
        
        Button decryptButton = new Button("Decrypt");
        decryptButton.setOnAction(e -> {
            String encryptedText = textField.getText();
            int shift = Integer.parseInt(shiftField.getText());
            String decryptedText = decryptEncrypt(encryptedText, shift);
            outputTextArea.setText(decryptedText);
        });
        
        topBox.getChildren().addAll(textLabel, textField, shiftLabel, shiftField, decryptButton);
        
        // Center section - output area
        outputTextArea = new TextArea();
        outputTextArea.setEditable(false);
        outputTextArea.setWrapText(true);
        
        // Put it all together
        root.setTop(topBox);
        root.setCenter(outputTextArea);
        
        // Set up the scene and show the window
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Encrypted Text Decryptor");
        primaryStage.show();
    }
    
    public static String decryptEncrypt(String encryptedText, int shift) {
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
    
    public static void main(String[] args) {
        launch(args);
    }
}

