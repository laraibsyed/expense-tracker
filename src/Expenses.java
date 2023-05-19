//importing the libraries required
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Expenses extends Application {
    private static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage; //mutator method to make the private stage into a public one
        primaryStage.setResizable(false); //doesnt allow the user to resize the screen
        FXMLLoader a = new FXMLLoader(getClass().getResource("ExpenseLogin.fxml")); //starting page
        Parent root = a.load();
        UserLogin b = (UserLogin) a.getController(); //gets the controller class called UserLogin
        b.parent = this; //links it to this parent class
        primaryStage.setTitle("MoneyWise Expense Tracker"); //sets the title of the stage
        primaryStage.setScene(new Scene(root, 645, 400)); //gets the dimensions of the stage
        primaryStage.show(); //shows the page
    }

    public void changeScene(String fxml) throws IOException {
        FXMLLoader a = new FXMLLoader(getClass().getResource(fxml));
        Parent pane = a.load();
        if (fxml.equals("ExpenseLogin.fxml")) { //checks if the fxml file name is equal to the name given
            UserLogin b = (UserLogin) a.getController(); //if it is then it executes the controller class
            b.parent = this; //connects it to this parent class
        } else if (fxml.equals("Choices.fxml")) { //checks for all the screens made
            Choices b = (Choices) a.getController();
            b.parent = this;
        } else if (fxml.equals("AllocateMoney.fxml")) {
            AllocateMoney b = (AllocateMoney) a.getController();
            b.parent = this;
        } else if (fxml.equals("EditCategories.fxml")) {
            Edit b = (Edit) a.getController();
            b.parent = this;
        } else if (fxml.equals("Add.fxml")) {
            Add b = (Add) a.getController();
            b.parent = this;
        } else if (fxml.equals("DailyExpense.fxml")) {
            DailyExpense b = (DailyExpense) a.getController();
            b.parent = this;
        } else if (fxml.equals("MonthlyBudget.fxml")) {
            MonthlyBudget b = (MonthlyBudget) a.getController();
            b.parent = this;
        } else if (fxml.equals("Analytics.fxml")) {
            Analytics b = (Analytics) a.getController();
            b.parent = this;
        } else {
        }
        stage.getScene().setRoot(pane);
    }
}
