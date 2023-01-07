package cz.vse.adventura.main;

import cz.vse.adventura.gui.HraciPlocha;
import cz.vse.adventura.gui.PanelVychodu;
import cz.vse.adventura.gui.PanelInventare;
import cz.vse.adventura.logika.Hra;
import cz.vse.adventura.logika.IHra;
import cz.vse.adventura.uiText.TextoveRozhrani;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Arrays;
/**
 * @author Eduard Wolf
 * @Date 5/29/2022
 *
 * Trida  spusti text interface pri argumentu "text" nebo GUI bez argumentu
 */
public class AdventuraZaklad extends Application {

    public static void main(String[] args) {
        //moznost hrat pouze s textem pri argumentu "text"
        if(Arrays.toString(args).equals("[text]")){
            IHra hra = new Hra();
            TextoveRozhrani ui = new TextoveRozhrani(hra);
            ui.hraj();
        }else {
            launch();
        }

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //javaFX setup
        BorderPane borderPane = new BorderPane();
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Adventura Věž");
        primaryStage.setScene(scene);
        primaryStage.setHeight(1000);
        primaryStage.setWidth(1000);

        //vytvoreni full duplex komunikace mezi pc a user

        Hra hra = new Hra();

        TextArea textArea = new TextArea();
        textArea.setText(hra.vratUvitani());

        borderPane.setCenter(textArea);

        TextField uzivatelskyVstup = new TextField();
        borderPane.setBottom(uzivatelskyVstup);


        uzivatelskyVstup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(hra.konecHry()){
                    String odpovedHry = "Hra již skončila";
                    textArea.appendText("\n" + odpovedHry + "\n");
                    uzivatelskyVstup.clear();
                    uzivatelskyVstup.setText("");
                }else{
                String prikaz = uzivatelskyVstup.getText();
                String odpovedHry = hra.zpracujPrikaz(prikaz);
                textArea.appendText("\n" + odpovedHry + "\n");
                uzivatelskyVstup.clear();
                uzivatelskyVstup.setText("");
            }}
        });

        textArea.setEditable(false);

        Label label = new Label("Zadej příkaz: ");
        label.setFont(Font.font("Arial", FontWeight.BLACK, 14.0));

        FlowPane flowPane = new FlowPane();

        borderPane.setBottom(flowPane);

        flowPane.getChildren().addAll(label, uzivatelskyVstup);

        uzivatelskyVstup.requestFocus();

        //add hraciplochy
        HraciPlocha hraciPlocha = new HraciPlocha(hra);
        AnchorPane anchorPane = hraciPlocha.getAnchorPane();
        borderPane.setTop(anchorPane);

        flowPane.setAlignment(Pos.CENTER);

        // Pridani Panelu Vychodu
        PanelVychodu panelVychodu = new PanelVychodu(hra);
        borderPane.setRight(panelVychodu.getListView());

        //Pridani Panelu Inventare
        PanelInventare panelInventare = new PanelInventare(hra);
        borderPane.setLeft(panelInventare.getVBox());
        //Pridani menu
        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("Hra");
        MenuItem add = new MenuItem("Začít novou hru");
        menuFile.getItems().addAll(add);
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                //sem dat zacit novou hru
                hra.restart();
                textArea.setText(hra.vratUvitani());
                uzivatelskyVstup.requestFocus();
                panelInventare.update();
                panelVychodu.nastavVychody();
                hraciPlocha.update();
                hra.setKonecHry(false);
            }
        });
        Menu menuFile2 = new Menu("Nápověda");
        MenuItem add2 = new MenuItem("Otevřít nápovědu");
        menuFile2.getItems().addAll(add2);

        //zobrazit napovedu
        add2.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Stage primaryStage1 = new Stage();
                    WebView webView = new WebView();

                    URL url = this.getClass().getResource("prirucka.html");
                    webView.getEngine().load(url.toString());
                    VBox vBox = new VBox(webView);
                    Scene scene2 = new Scene(vBox, 960, 600);
                    primaryStage1.setTitle("Adventura Věž");
                    primaryStage1.setScene(scene2);
                    primaryStage1.setHeight(800);
                    primaryStage1.setWidth(800);
                    primaryStage1.show();





            }
        });

        menuBar.getMenus().addAll(menuFile,menuFile2);

        root.setTop(menuBar);
        root.setCenter(borderPane);


        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

