//importing all the libraries required
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class AllocateMoney implements Initializable {
    String totalIncome, money, theCategory; //initialising a few variables that will be used later on
    int remainingMoney;

    List<String> results = new ArrayList<String>(); //creating an array list for my categories
    @FXML
    TextField income;
    @FXML
    Button save;
    @FXML
    ChoiceBox<String> categories;
    @FXML
    TextField amount;
    @FXML
    Button saveMoney;
    @FXML
    Button edit;
    @FXML
    Label incomeLabel;
    @FXML
    Button done;
    @FXML
    Button back;
    @FXML
    Label errorLabel;
    @FXML
    TextArea categoriesLabel;
    Expenses parent; //connects the main class to this controller class

    public void saveIncome(ActionEvent actionEvent) { //creating the on click action for the button with the fx:id save
        totalIncome = income.getText(); //saves the text from income to a variable called totalIncome
        try{
            Integer.parseInt(totalIncome); //checks if the entered value is a number nothing else
            System.out.println("Number"); //displays on the console
            incomeLabel.setText("The total income: " + totalIncome); //changes the label on the screen
            System.out.println("Income Saved");
        } catch (NumberFormatException nfe){
            System.out.println("Not number");
            incomeLabel.setText("The value entered is not a number!");
        }
        done.setDisable(true); //disables the add button to check whether the user has selected a category and allocated money to it

        System.out.println(results);
        try {
            for (int i = 0; i < results.size(); i++) {
                File myObj = new File(String.valueOf(results.get(i)));
                Scanner read = new Scanner(myObj);  //scanner objects to read the file
                while (read.hasNextLine()) { //while the file is not empty
                    String data = read.nextLine(); //reads the next line
                    categoriesLabel.appendText(String.valueOf(results.get(i))); //saves it onto the label
                    categoriesLabel.appendText(": ");
                    categoriesLabel.appendText(String.valueOf(data));
                    categoriesLabel.appendText("\n");
                }
                read.close(); //closes the file
            }
        } catch (FileNotFoundException e) {
            categoriesLabel.appendText("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { //method used to initialise the categories
        File[] files = new File("/Users/laraibsyed/IdeaProjects/project02").listFiles(); //gets the file names from the project folder
        for (File file : files) {
            if (file.isFile()) { //if the file exists
                results.add(file.getName()); //adds all the files to the choice box
                results.remove(".DS_Store"); //removes the unwanted files in the arraylist
                results.remove("project02.iml");
            }
        }
        for(int i=0;i<results.size();i++) { //repeats for as many files that are present
            categories.getItems().add(String.valueOf(results.get(i))); //using for loop to print items to the choice box
        }
    }

    public void saveAllocatedMoney(ActionEvent actionEvent) throws FileNotFoundException { //creating the on click action for the button with the fx:id saveMoney
        theCategory = categories.getValue(); //saves the chosen category into a variable
        money = amount.getText(); //saves the amount entered into a variable
        try{
            Integer.parseInt(money); //checks if the amount entered is a number
            System.out.println("Number");
            File files = new File(theCategory);
            if (files.isFile()) { //if file exists
                PrintWriter outputFile = new PrintWriter(files);
                outputFile.println(money); //saves the amount to the files
                outputFile.close(); //closes the file
            } else {
                System.out.println("Error"); //if the file doesn't an error is displayed
            }
        } catch (NumberFormatException nfe){
            System.out.println("Not number");
            errorLabel.setText("Error");
        }
        done.setDisable(false); //enables the add button
    }

    public void addMore(ActionEvent actionEvent){ //creating the on click action for the button with the fx:id done
        totalIncome = income.getText(); //saves the income into a variable
        remainingMoney = Integer.parseInt(totalIncome) - Integer.parseInt(money); //calculates the remaining money by minusing the income by the amount added
        System.out.println(remainingMoney);
        income.setText(String.valueOf(remainingMoney)); //sets the label with the remaining money
        incomeLabel.setText("The remaining income is: " + remainingMoney);
        amount.clear(); //clears the text field
        categories.setValue("Select Category"); //resets the choice box
        if (remainingMoney <= 0) { //checks if the money entered is 0
            incomeLabel.setText("You've used up your income!");
            done.setDisable(true); //prevents the user from adding since the income is 0
        }
    }

    public void editCategories() throws IOException { //creating the on click action for the button with the fx:id edit
        parent.changeScene("EditCategories.fxml"); //changes the screen
    }

    public void goBack(ActionEvent actionEvent) throws IOException { //creating the on click action for the button with the fx:id back
        parent.changeScene("Choices.fxml"); //changes the screen
    }
}