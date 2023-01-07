package cz.vse.adventura.gui;

import cz.vse.adventura.logika.HerniPlan;
import cz.vse.adventura.logika.Hra;
import cz.vse.adventura.logika.Prostor;
import cz.vse.adventura.observer.Observer;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import cz.vse.adventura.observer.Observer;
import java.util.Map;
import java.util.Objects;
/**
 * @author Eduard Wolf
 * @Date 5/29/2022
 *
 * Tato trida prida hraci plochu
 */
public class HraciPlocha implements Observer {

        private AnchorPane anchorPane = new AnchorPane();
        private Circle poziceHrace = new Circle(10.0, Paint.valueOf("red"));

        private Map<String, Point2D> mapaPozicHrace;
        private HerniPlan herniPlan;
        public HraciPlocha(Hra hra){

            herniPlan = hra.getHerniPlan();
            Prostor aktualniProstor = herniPlan.getAktualniProstor();

            herniPlan.register(this);

            update();

            Image image = new Image(Objects.requireNonNull(HraciPlocha.class.getResourceAsStream("images/vez.png")),400,250,false,false);
            ImageView imageView = new ImageView(image);
            anchorPane.getChildren().addAll(imageView, poziceHrace);

        }

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    @Override
    public void update() {
        Prostor aktualniProstor = herniPlan.getAktualniProstor();
        AnchorPane.setLeftAnchor(poziceHrace, aktualniProstor.getX());
        AnchorPane.setTopAnchor(poziceHrace, aktualniProstor.getY());
    }
}
