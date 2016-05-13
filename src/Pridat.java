import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by Ota on 13.05.2016.
 */
public class Pridat {

    static ListView<String> list;
    static String[] ppls;
    static ArrayList response;

    public static ArrayList display(String[] userList){
        response = new ArrayList();

        Stage window = new Stage();
        window.setTitle("Přidat poznámku");

        // seznam uzivatelu
        list = new ListView<>();
        list.getItems().addAll(userList);
        list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        Label message = new Label("Vyberte uživatele");
        Label message1 = new Label("Text poznámky");

        TextField text = new TextField();

        Button but = new Button("OK");
        but.setOnAction(e-> {
            // ziskani oznacenych uzivatelu a vytvoreni pole
            ObservableList<String> items;
            items = list.getSelectionModel().getSelectedItems();

            // napocita velikost pole
            int i = 0;
            for (String m : items){
                i++;
            }
            // naplni pole
            ppls = new String[i];
            i = 0;
            for (String m : items){
                ppls[i] = m;
                i++;
            }
            // okno se nezavre dokud neni neco napsano
            if(text.getText().length() > 0) {
                // trochu prasarna
                response.add(ppls);
                response.add(text.getText());
                window.close();
            }
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(message, list, message1, text, but);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout, 200, 200);
        window.setScene(scene);
        window.showAndWait();

        return response;
    }
}
