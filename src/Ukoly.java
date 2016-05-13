import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by Ota on 12.05.2016.
 */
public class Ukoly extends Application {

    Stage window;
    Button but, but2, but3;
    ChoiceBox<String> box;
    ListView<String> list;
    ArrayList<Poznamka> poznamky;
    ArrayList<Uzivatel> lide;

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;

        but = new Button("Nacist ukoly");
        but.setOnAction(e -> Load());

        but2 = new Button("Pridat");
        but2.setOnAction(e -> AddTask(box.getValue()));

        but3 = new Button("Smazat");
        but3.setOnAction(e -> RemoveTask(box.getValue(), list.getSelectionModel().getSelectedIndex()));

        box = new ChoiceBox<String>();

        poznamky = new ArrayList<>();
        poznamky.add(new Poznamka("Pepa", "nevim"));
        poznamky.add(new Poznamka("Pepa", "trol"));
        poznamky.add(new Poznamka("Pepa", "achjo"));
        poznamky.add(new Poznamka("Frantisek", "bbbbb"));
        poznamky.add(new Poznamka("Frantisek", "aaaaaa"));
        poznamky.add(new Poznamka("Frantisek", "acccccca"));
        poznamky.add(new Poznamka("Frantisek", "axxxxxxx"));

        lide = new ArrayList<>();
        lide.add((new Uzivatel("Pepa")));
        lide.add((new Uzivatel("Frantisek")));

        // defaultne je vybrany pepa
        box.getItems().addAll(GetUsers());
        box.setValue("Pepa");

        // vybere poznamky pro pepu
        list = new ListView<>();
        list.getItems().addAll(GetTasks());

        // zobrazeni okna
        VBox layout = new VBox();
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(box,list, but,but2, but3);

        Scene scene = new Scene(layout, 300, 250);
        window.setScene(scene);

        window.show();
    }

    // vycisti stare hodnoty a naplni znovu
    public void Load(){
        list.getItems().clear();
        list.getItems().addAll(GetTasks());
    }

    public String[] GetTasks(){
        // napocita velikost pomocneho pole
        int t = 0;
        for (int i = 0; i < poznamky.size(); i++){
            if(poznamky.get(i).Clovek.equals(box.getValue()))t++;
        }

        // naplni pole vysledku pro vybraneho cloveka
        String[] pom = new String[t];
        t = 0;
        for (int i = 0; i < poznamky.size(); i++){
            if(poznamky.get(i).Clovek.equals(box.getValue())) {
                pom[t] = poznamky.get(i).Obsah;
                t++;
            }
        }
        return pom;
    }

    //arraylist => array
    public String[] GetUsers(){
        String[] pom = new String[lide.size()];
        for (int i = 0; i < lide.size(); i++){
                pom[i] = lide.get(i).Jmeno;
        }
        return pom;
    }

    public void AddTask(String clovek){
        String task = Pridat.display();
        poznamky.add(new Poznamka(clovek, task));
        Load();
    }

    // projde poznamky a pocita vyskyty u jednotlivych lidi protoze to odpovida ID z tabulky
    public void RemoveTask(String clovek, int id){
        int t = 0;
        for (int i = 0; i < poznamky.size(); i++){
            if(poznamky.get(i).Clovek.equals(clovek)) {
                if(t == id)poznamky.remove(i);
                t++;
            }
        }
        Load();
    }
}
