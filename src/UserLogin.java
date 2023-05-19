//importing the libraries required
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.io.IOException;

public class UserLogin {
    int maxValue = 2; //creates max value for the number of entries allowed (constant)
    int attempts;
    @FXML
    Button login;
    @FXML
    Label loginLabel;
    @FXML
    TextField username;
    @FXML
    PasswordField password;
    @FXML
    Button exit;

    Expenses parent; //connects the main class to this controller class

    public void userLogin() throws IOException { //on click action for the button with fx:id login
        System.out.println("Login");
        for (int i = 0; i < maxValue; i++) {
            if (attempts == 2) { //if the attempts is equal to 2
                loginLabel.setText("Too Many Attempts.");
                loginLabel.setFont(Font.font(15));
                loginLabel.setTextAlignment(TextAlignment.CENTER);
                username.setDisable(true); //disables the username text field hence "locking the user out"
                password.setDisable(true); //disables the password text field hence "locking the user out"
            } else {
                if (username.getText().equals("abc") && password.getText().equals("1234")) { //checks if the password and username entered is correct
                    loginLabel.setText("Login Successful");
                    loginLabel.setFont(Font.font(15));
                    loginLabel.setTextAlignment(TextAlignment.CENTER);
                    parent.changeScene("Choices.fxml"); //changes the screen
                } else if (username.getText().isEmpty() || password.getText().isEmpty()) { //checks if the fields are empty
                    loginLabel.setText("Enter your details."); //prompts the user to enter their details again
                    loginLabel.setFont(Font.font(15));
                    loginLabel.setTextAlignment(TextAlignment.CENTER);
                } else { //otherwise
                    loginLabel.setText("Incorrect Username or Password."); //the username or password is incorrect
                    loginLabel.setFont(Font.font(15));
                    loginLabel.setTextAlignment(TextAlignment.CENTER);
                    attempts = attempts + 1;
                }
            }
        }
    }

    public void byeBye(ActionEvent actionEvent) {
        Platform.exit();
    }
}
