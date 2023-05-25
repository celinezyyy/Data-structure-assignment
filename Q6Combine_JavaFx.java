package javafxtest;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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

    Label numSubLabel = new Label("Pin number:");
    GridPane.setConstraints(numSubLabel, 0, 2);

    numSubInput = new TextField();
    GridPane.setConstraints(numSubInput, 1, 2);

    Button decryptButton = new Button("Decrypt");
    //Button encryptButton = new Button("Extra Encrypt");
    GridPane.setConstraints(decryptButton, 0, 3);
    //GridPane.setConstraints(encryptButton, 1, 3);

    // Register event handler for decryptButton
    decryptButton.setOnAction(e -> {
        performExtraFeatureDecryption(extraFeatureStage);
    });
    
    grid.getChildren().addAll(textLabel, textInput, shiftLabel, shiftInput, numSubLabel, numSubInput, decryptButton);

    Scene scene = new Scene(grid, 300, 180);
    extraFeatureStage.setScene(scene);
    extraFeatureStage.show();
}

    
    private void performBasicFeatureDecryption(Stage stage) {
        String encryptedText = textInput.getText();
        int shift = Integer.parseInt(shiftInput.getText());

        Q6MyQueuejavafx encryptTextQueue = new Q6MyQueuejavafx();
        for(int i=0; i<encryptedText.length(); i++)     
            encryptTextQueue.enqueue(encryptedText.charAt(i));    //add the text into queue (easy to access, FIFO)
        
        
        Q6MyQueuejavafx decryptedText = decrypt(encryptTextQueue, shift);
        resultLabel.setText("Basic feature\nDecrypted Text: " + decryptedText);

        stage.close();
    }

 private void performExtraFeatureDecryption(Stage stage) {
    String encryptedText = textInput.getText();
    int shift = Integer.parseInt(shiftInput.getText());
    String pin = numSubInput.getText();

    Q6MyQueuejavafx encryptTextQueue = new Q6MyQueuejavafx();
    char[] pinStack = new char[12];

    for (int i = 0; i < encryptedText.length(); i++)
        encryptTextQueue.enqueue(encryptedText.charAt(i));    // add the text into queue (easy to access, FIFO)

    for (int i = 0; i < pinStack.length; i++)
        pinStack[i] = pin.charAt(i);

    Q6MyQueuejavafx extraEncrypt = encryptWithExtra(encryptTextQueue, pinStack);
    Q6MyQueuejavafx decryptedText = decryptWithExtra(extraEncrypt, shift, pinStack);
    
    // Append decrypted text and extra encrypted text to resultLabel
    resultLabel.setText("Extra Feature (After decrypt the text that encrypt with extra pin number) \nDecrypted Text: " + decryptedText);

    stage.close();
}

private void performExtraFeatureEncryption(Stage stage) {
    String encryptedText = textInput.getText();
    int shift = Integer.parseInt(shiftInput.getText());
    String pin = numSubInput.getText();

    Q6MyQueuejavafx encryptTextQueue = new Q6MyQueuejavafx();
    char[] pinStack = new char[12];

    for (int i = 0; i < encryptedText.length(); i++)
        encryptTextQueue.enqueue(encryptedText.charAt(i));    // add the text into queue (easy to access, FIFO)

    for (int i = 0; i < pinStack.length; i++)
        pinStack[i] = pin.charAt(i);

    Q6MyQueuejavafx extraEncrypt = encryptWithExtra(encryptTextQueue, pinStack);
    resultLabel.setText("Encrypted Text: " + extraEncrypt);

    stage.close();
}
    // Basic decryption method
    private Q6MyQueuejavafx decrypt(Q6MyQueuejavafx encryptTextQueue, int shift) 
    {
        Q6MyQueuejavafx decryptedText = new Q6MyQueuejavafx();
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
                Q6MyStackjavafx temp = new Q6MyStackjavafx();       //using stack to keep the text in bracket
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
    // Extra encryption method
    private Q6MyQueuejavafx encryptWithExtra(Q6MyQueuejavafx encryptedText, char[] pinStack)
    {
        Q6MyQueuejavafx extraEncryption = new Q6MyQueuejavafx();
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
    
    // Extra decryption method
    private Q6MyQueuejavafx decryptWithExtra(Q6MyQueuejavafx extraEncrypt, int shift, char[] pinStack) 
    {
        final int SIZE = pinStack.length;
            final int ENCRYPTSIZE = extraEncrypt.getSize();
            Q6MyQueuejavafx decryptedText = new Q6MyQueuejavafx();

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

                    Q6MyStackjavafx temp = new Q6MyStackjavafx();       //using stack to keep the text in bracket
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
