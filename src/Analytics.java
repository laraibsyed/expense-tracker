//importing the libraries required
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Analytics {
    List<String> results = new ArrayList<String>(); //creates an array list
    @FXML
    TextArea label;
    @FXML
    Button view;
    Expenses parent; //connects the main class to this controller class


    public void viewAnalytics(ActionEvent actionEvent) { //creating the on click action for the button with the fx:id view
        File[] files = new File("/Users/laraibsyed/IdeaProjects/project02").listFiles();
        for (File file : files) {
            if (file.isFile()) { //if the file exists
                results.add(file.getName()); //adds all the files to the choice box
                results.remove(".DS_Store"); //removes the unwanted files in the arraylist
                results.remove("project02.iml");
            }
        }
        System.out.println(results);
        try {
            for (int i = 0; i < results.size(); i++) {
                File myObj = new File(String.valueOf(results.get(i)));
                Scanner read = new Scanner(myObj);  //scanner objects to read the file
                while (read.hasNextLine()) { //while the file is not empty
                    String data = read.nextLine(); //reads the next line
                    label.appendText(String.valueOf(results.get(i))); //saves it onto the label
                    label.appendText(": ");
                    label.appendText(String.valueOf(data));
                    label.appendText("\n");
                }
                read.close(); //closes the file
            }
        } catch (FileNotFoundException e) {
            label.appendText("An error occurred.");
            e.printStackTrace();
        }
    }

    public void goBack(ActionEvent actionEvent) throws IOException { //on click action for the button with fx:id done
        parent.changeScene("Choices.fxml"); //changes the screen
    }
}
