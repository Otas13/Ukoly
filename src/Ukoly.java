import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by Ota on 12.05.2016.
 */
public class Ukoly extends Application {

    Stage window;
    Button addBut, remBut;
    ChoiceBox<String> box;
    ListView<String> list;
    ArrayList<Poznamka> poznamky;
    ArrayList<Uzivatel> lide;

    @Override
    public void start(Stage primaryStage){

        window = primaryStage;
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        addBut = new Button("Pridat");
        addBut.setOnAction(e -> AddTask());


        remBut = new Button("Smazat");
        remBut.setOnAction(e -> RemoveTask(box.getValue(), list.getSelectionModel().getSelectedIndex()));

        box = new ChoiceBox<String>();

        // vytvoreni nejakych uzivatelu
        lide = new ArrayList<>();
        lide.add((new Uzivatel("Pepa")));
        lide.add((new Uzivatel("Frantisek")));

        // naplneni poznamek
        poznamky = new ArrayList<>();
        poznamky.add(new Poznamka("Pepa", "nevim"));
        poznamky.add(new Poznamka("Pepa", "trol"));
        poznamky.add(new Poznamka("Pepa", "achjo"));
        poznamky.add(new Poznamka("Frantisek", "bbbbb"));
        poznamky.add(new Poznamka("Frantisek", "aaaaaa"));
        poznamky.add(new Poznamka("Frantisek", "acccccca"));
        poznamky.add(new Poznamka("Frantisek", "axxxxxxx"));

        // defaultne je vybrany pepa
        box.getItems().addAll(GetUsers());
        box.setValue("Pepa");
        box.getSelectionModel().selectedItemProperty().addListener((choiceBox, oldVal, newVal) -> Load());

        // vybere poznamky pro pepu
        list = new ListView<>();
        list.getItems().addAll(GetTasks());

        // zobrazeni okna

        GridPane.setConstraints(box, 0, 0);
        GridPane.setConstraints(list, 0, 1, 2, 1);
        GridPane.setConstraints(addBut, 0, 2);
        GridPane.setConstraints(remBut, 1, 2);

        grid.getChildren().addAll(box,list, addBut,remBut);

        Scene scene = new Scene(grid, 300, 250);
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
        String val = box.getValue();
        int t = 0;
        for (int i = 0; i < poznamky.size(); i++){
            if(poznamky.get(i).Clovek.equals(val))t++;
        }

        // naplni pole vysledku pro vybraneho cloveka
        String[] pom = new String[t];
        t = 0;
        for (int i = 0; i < poznamky.size(); i++){
            if(poznamky.get(i).Clovek.equals(val)) {
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

    public void AddTask(){
        ArrayList response = Pridat.display(GetUsers());
        String[] pp = (String[]) response.get(0);
        String ukol = (String) response.get(1).toString();
        System.out.println(pp.length);
        for (int i = 0; i < pp.length; i++) {
            poznamky.add(new Poznamka(pp[i], ukol));
        }
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
