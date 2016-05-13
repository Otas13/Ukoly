import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by Ota on 13.05.2016.
 */
public class Pridat {

    static String Ukol;

    public static String display(){
        Ukol = null;

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(250);
        Label message = new Label("Přidat poznamku");
        TextField text = new TextField();

        Button but = new Button("OK");
        but.setOnAction(e-> {
            if(text.getText().length() > 0) {
                Ukol = text.getText();
                window.close();
            }
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(message, text, but);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return Ukol;
    }
}
