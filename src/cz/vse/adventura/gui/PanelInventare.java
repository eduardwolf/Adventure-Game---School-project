package cz.vse.adventura.gui;

import cz.vse.adventura.logika.HerniPlan;
import cz.vse.adventura.logika.Hra;
import cz.vse.adventura.logika.Inventar;
import cz.vse.adventura.observer.Observer;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
/**
 * @author Eduard Wolf
 * @Date 5/29/2022
 *
 * Tato trida prida Panel inventare
 */
public class PanelInventare implements Observer {


    private FlowPane flowPane = new FlowPane();
    private Label popisek = new Label("Věci v inventáři: ");
    private VBox vBox = new VBox();
    private Inventar inventar;

    public PanelInventare(Hra hra) {
        this.inventar = hra.getInventar();
        inventar.register(this);

        vBox.getChildren().addAll(popisek, flowPane);
        vBox.setMaxWidth(100.0);
        update();
    }

    public VBox getVBox() {
        return this.vBox;
    }

    @Override
    public void update() {
        flowPane.getChildren().clear();
        for (String vec : inventar.getMnozinaNazvuPredmetuVInventari()) {
            Image image = new Image(PanelInventare.class.getResourceAsStream("images/" + vec + ".jpg"), 80,80, false,false);
            ImageView imageView = new ImageView(image);
            flowPane.getChildren().addAll(imageView);
        }

    }
}
