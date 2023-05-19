//importing the libraries required
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Calendar;
import java.util.Formatter;

public class MonthlyBudget {
    String theBudget; //creates variables that are used later
    @FXML
    TextField budget;
    @FXML
    Button save;
    @FXML
    Button edit;
    @FXML
    Label monthlyBudget;
    @FXML
    Button back;
    Expenses parent; //connects the main class to this controller class

    public void editBudget() { //on click action for the button with fx:id edit
        budget.setDisable(false); //enables all the buttons for use
        save.setDisable(false);
        edit.setDisable(false);
    }

    public void saveBudget(ActionEvent actionEvent) { //on click action for the button with fx:id save
        Formatter format = new Formatter();
        Calendar calendar = Calendar.getInstance();
        format.format("%tB", calendar); //uses the calendar and formatter function to display the current month on the screen
        theBudget = budget.getText();
        try {
            Integer.parseInt(theBudget); //checks if the value entered a number
            System.out.println("Number");
            monthlyBudget.setText("The budget for " + format + " is: " + theBudget); //if it is a number the user can proceed
        } catch (NumberFormatException nfe) {
            System.out.println("Not a Number");
            monthlyBudget.setText("The value entered is not a number"); //otherwise, it displays an error
        }
        budget.setDisable(true); //disables the buttons
        save.setDisable(true); //
        budget.clear(); //clears the text field
        while (edit.isPressed()) { //loop to repeat as long as the button is pressed
            editBudget(); //calls the method since the buttons are disabled there
        }
    }

    public void goBack(ActionEvent actionEvent) throws IOException { //on click action for the button with fx:id back
        parent.changeScene("Choices.fxml"); //changes the screen
    }
}
