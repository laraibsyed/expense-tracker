//importing the libraries required
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class Choices {
    @FXML
    Button allocate;
    @FXML
    Button dailyExpense;
    @FXML
    Button monthlyBudget;
    @FXML
    Button analytics;
    @FXML
    Button logout;
    Expenses parent; //connects the main class to this controller class

    public void allocating() throws IOException { //on click action for the button with fx:id allocate
        parent.changeScene("AllocateMoney.fxml"); //changes the screen
    }

    public void todaysExpense() throws IOException { //on click action for the button with fx:id dailyExpense
        parent.changeScene("DailyExpense.fxml"); //changes the screen
    }

    public void thisMonth() throws IOException { //on click action for the button with fx:id monthlyBudget
        parent.changeScene("MonthlyBudget.fxml"); //changes the screen
    }

    public void overallAnalysis() throws IOException { //on click action for the button with fx:id analytics
        parent.changeScene("Analytics.fxml"); //changes the screen
    }

    public void userLogout(ActionEvent actionEvent) throws IOException { //on click action for the button with fx:id logout
        parent.changeScene("ExpenseLogin.fxml"); //changes the screen
        System.out.println("Logout");
    }
}
