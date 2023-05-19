//importing the libraries required
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class Edit {
    @FXML
    Button add;
    @FXML
    Button done;
    Expenses parent; //connects the main class to this controller class

    public void addNew(ActionEvent actionEvent) throws IOException { //on click action for the button with fx:id add
        parent.changeScene("Add.fxml"); //changes the screen
    }

    public void finished(ActionEvent actionEvent) throws IOException { //on click action for the button with fx:id done
        parent.changeScene("AllocateMoney.fxml"); //changes the screen
    }
}
