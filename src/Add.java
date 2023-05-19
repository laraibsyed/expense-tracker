//importing the libraries required
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;

public class Add {
    String newCategory; //creating variables to be used later
    @FXML //annotating the buttons, text fields and labels
    TextField categoryName;
    @FXML
    Button save;
    @FXML
    Button addMore;
    @FXML
    Button done;
    @FXML
    Label success;

    Expenses parent; //connects the main class to this controller class


    public void saveCategory(ActionEvent actionEvent) { //creating the on click action for the button with the fx:id save
        newCategory = categoryName.getText(); //saves the category name that the user entered to the variable newCategory
        if (newCategory.isBlank()) { //checks if the text field is empty
            success.setText("Category name can't be empty!"); //if it is, the label is changed and this is displayed
        } else { //if the text field is not empty then the following is executing
            try { //
                File myFile = new File(newCategory); //creates new file object for the new category entered
                if (myFile.createNewFile()) { //checks if file is made
                    System.out.println("File created: " + myFile.getName()); //if it is then the file name is printed on the console
                    success.setText("Success!"); //label change
                } else { //if the file already exists
                    System.out.println("File Already Exists"); //prints on console
                    success.setText("Category Already Exists"); //changes label on screen
                }
            } catch (IOException ioException) { //input/output exception
                ioException.printStackTrace();
                success.setText("Error Occurred. Try again later.");
            }
        }
    }

    public void addAnother(ActionEvent actionEvent) { //on click action for the button with fx:id addMore
        categoryName.clear(); //clears the text field to allow the user to enter a new category name
        success.setText(""); //clears the label
    }

    public void finished(ActionEvent actionEvent) throws IOException { //on click action for the button with fx:id done
        parent.changeScene("AllocateMoney.fxml"); //changes the scene to AllocateMoney
    }
}
