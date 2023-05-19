//importing the libraries required
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class DailyExpense implements Initializable {
    String chosenCategory; //initialising a few variables that will be used later on
    int remaining, currentAmount;
    List<String> results = new ArrayList<String>(); //creating an array list for my categories
    @FXML
    ChoiceBox<String> categories;
    @FXML
    TextField amount;
    @FXML
    Button spend;
    @FXML
    Label money;
    @FXML
    Button save;
    @FXML
    Button saveCategory;
    @FXML
    Button back;
    Expenses parent; //connects the main class to this controller class


    public void showRemaining(ActionEvent actionEvent) { //on click action for the button with fx:id saveCategory
        chosenCategory = categories.getValue(); //saves the chosen category
        try {
            File myObj = new File(String.valueOf(categories.getValue()));
            Scanner myReader = new Scanner(myObj);
            String data = myReader.next(); //reads the data in the file
            money.setText("The money in " + chosenCategory + " is " + data); //displays it on the gui
        } catch (FileNotFoundException e) {
            money.setText("An error occurred.");
            e.printStackTrace();
        }
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        File[] files = new File("/Users/laraibsyed/IdeaProjects/project02").listFiles(); //lists the files present in the path given
        for (File file : files) {
            if (file.isFile()) {
                results.add(file.getName()); //saves it to the array list
                results.remove(".DS_Store"); //removes the unwanted values
                results.remove("project02.iml");
            }
        }
        for (int i = 0; i < results.size(); i++) {
            categories.getItems().add(String.valueOf(results.get(i))); //displays all the file names
        }
    }

    public void saveAmount(ActionEvent actionEvent) throws FileNotFoundException { //on click action for the button with fx:id save
        chosenCategory = categories.getValue(); //saves the category into the variable chosenCategory
        try {
            File myObj = new File(String.valueOf(categories.getValue()));
            Scanner myReader = new Scanner(myObj);
            String data = myReader.next(); //reads the data in the files
            currentAmount = Integer.parseInt(data);
            remaining = currentAmount - Integer.parseInt(amount.getText()); //calculates the remaining amount
            //money.setText("The remaining money in " + chosenCategory + " is " + remaining);
            if(remaining <= 0){
                money.setText("All the money in " + chosenCategory + " is used up.");
                System.out.println("No more money");
                spend.setDisable(true);
            } else {
                money.setText("The remaining money in " + chosenCategory + " is " + remaining);
                System.out.println("Proceed");
            }
        } catch (FileNotFoundException e) { //exception handling
            money.setText("An error occurred."); //displays on the screen
            e.printStackTrace(); //prints the exception
        }
        File files = new File(categories.getValue());
        if (files.isFile()) {
            PrintWriter outputFile = new PrintWriter(files); //saves the new amount into the file by writing to it
            outputFile.println(remaining);
            outputFile.close();
        } else {
            System.out.println("Error");
        }
        System.out.println("Saved");
        saveCategory.setDisable(true); ///disables the buttons from use
        amount.setDisable(true);
        save.setDisable(true);
    }

    public void spendMore(ActionEvent actionEvent) { //on click action for the button with fx:id spend
        saveCategory.setDisable(false); //enables the buttons
        amount.setDisable(false);
        save.setDisable(false);
    }

    public void goBack(ActionEvent actionEvent) throws IOException { //on click action for the button with fx:id back
        parent.changeScene("Choices.fxml"); //changes the screen
    }
}
