package javafxtest;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Q6Combine_JavaFx extends Application {

    private TextField textInput;
    private TextField shiftInput;
    private TextField numSubInput;
    private Label resultLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Encrypted Text");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        Button basicFeatureButton = new Button("Basic Feature");
        GridPane.setConstraints(basicFeatureButton, 0, 0);
        basicFeatureButton.setOnAction(e -> showBasicFeatureInput());

        Button extraFeatureButton = new Button("Extra Feature");
        GridPane.setConstraints(extraFeatureButton, 1, 0);
        extraFeatureButton.setOnAction(e -> showExtraFeatureInput());

        resultLabel = new Label();
        GridPane.setConstraints(resultLabel, 0, 3, 2, 1);

        grid.getChildren().addAll(basicFeatureButton, extraFeatureButton, resultLabel);

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.getChildren().add(grid);

        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showBasicFeatureInput() {
        Stage basicFeatureStage = new Stage();
        basicFeatureStage.setTitle("Basic Feature Input");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        Label textLabel = new Label("Text:");
        GridPane.setConstraints(textLabel, 0, 0);

        textInput = new TextField();
        GridPane.setConstraints(textInput, 1, 0);

        Label shiftLabel = new Label("Shift:");
        GridPane.setConstraints(shiftLabel, 0, 1);

        shiftInput = new TextField();
        GridPane.setConstraints(shiftInput, 1, 1);

        Button decryptButton = new Button("Decrypt");
        GridPane.setConstraints(decryptButton, 0, 2);
        decryptButton.setOnAction(e -> performBasicFeatureDecryption(basicFeatureStage));

        grid.getChildren().addAll(textLabel, textInput, shiftLabel, shiftInput, decryptButton);

        Scene scene = new Scene(grid, 250, 150);
        basicFeatureStage.setScene(scene);
        basicFeatureStage.show();
    }

    private void showExtraFeatureInput() {
        Stage extraFeatureStage = new Stage();
        extraFeatureStage.setTitle("Extra Feature Input");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        Label textLabel = new Label("Text:");
        GridPane.setConstraints(textLabel, 0, 0);

        textInput = new TextField();
        GridPane.setConstraints(textInput, 1, 0);

        Label shiftLabel = new Label("Shift:");
        GridPane.setConstraints(shiftLabel, 0, 1);

        shiftInput = new TextField();
        GridPane.setConstraints(shiftInput, 1, 1);

        Label numSubLabel = new Label("Number to Subtract:");
        GridPane.setConstraints(numSubLabel, 0, 2);

        numSubInput = new TextField();
        GridPane.setConstraints(numSubInput, 1, 2);

        Button decryptButton = new Button("Decrypt");
        Button ecryptButton = new Button("Extra Encrypt");
        GridPane.setConstraints(decryptButton, 0, 3);
        decryptButton.setOnAction(e -> performExtraFeatureDecryption(extraFeatureStage));
        ecryptButton.setOnAction(e -> performExtraFeatureDecryption(extraFeatureStage));
        
        grid.getChildren().addAll(textLabel, textInput, shiftLabel, shiftInput, numSubLabel, numSubInput, decryptButton);

        Scene scene = new Scene(grid, 300, 180);
        extraFeatureStage.setScene(scene);
        extraFeatureStage.show();
    }

    private void performBasicFeatureDecryption(Stage stage) {
        String encryptedText = textInput.getText();
        int shift = Integer.parseInt(shiftInput.getText());

        String decryptedText = decryptEncrypt(encryptedText, shift);
        resultLabel.setText("Decrypted Text: " + decryptedText);

        stage.close();
    }

    private void performExtraFeatureDecryption(Stage stage) {
    String encryptedText = textInput.getText();
    int shift = Integer.parseInt(shiftInput.getText());
    int numToSub = Integer.parseInt(numSubInput.getText());

    String extraEncrypt = encrypt(encryptedText, numToSub);
    String decryptedText = decryptEncrypt2(extraEncrypt, shift, numToSub);

    resultLabel.setText("Extra Encrypted Text: " + extraEncrypt + "\nDecrypted Text: " + decryptedText);

    stage.close();
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
   
   public static String decryptEncrypt2(String extraEncrypt, int shift, int numToSub) 
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

    public static String encrypt(String encryptedText, int numToSub) {
        
        String extraEncryption="";
        for(int i=0; i<encryptedText.length(); i++)
        {
            char c = encryptedText.charAt(i);  
            c-=numToSub;
            extraEncryption+=c;
        }
        return extraEncryption;
    }
}