package cz.vse.adventura.gui;

import cz.vse.adventura.logika.HerniPlan;
import cz.vse.adventura.logika.Hra;
import cz.vse.adventura.logika.Prostor;
import cz.vse.adventura.observer.Observer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.util.Collection;
import java.util.Set;
/**
 * @author Eduard Wolf
 * @Date 5/29/2022
 *
 * Tato trida prida Panel vychodu
 */
public class PanelVychodu implements Observer {

    ObservableList<String> observableList = FXCollections.observableArrayList();
    ListView<String> listView = new ListView<>(observableList);
    HerniPlan herniPlan;

    public PanelVychodu(Hra hra) {

        herniPlan = hra.getHerniPlan();
        herniPlan.register(this);

        observableList.addAll("patro1");
    }

    public void nastavVychody() {
        observableList.clear();
        Collection<Prostor> vychody = herniPlan.getAktualniProstor().getVychody();
        for (Prostor vychod : vychody) {
            observableList.add(vychod.getNazev());
        }
    }

    public ListView<String> getListView() {
        return listView;
    }

    @Override
    public void update() {
        nastavVychody();
    }
}

