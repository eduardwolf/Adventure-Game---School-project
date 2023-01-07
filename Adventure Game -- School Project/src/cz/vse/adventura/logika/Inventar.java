package cz.vse.adventura.logika;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import cz.vse.adventura.observer.Observer;
import cz.vse.adventura.observer.SubjectOfChange;

/**
 * @author Eduard Wolf
 * @Date 5/29/2022
 */
public class Inventar implements SubjectOfChange{
    static Set<Predmet> inventar = new HashSet<>();
    static Set<String> seznamVeci = new HashSet<>();

    public Inventar() {
        this.inventar = inventar;
    }

    public static void vyprazdni() {
        inventar = new HashSet<>();
        seznamVeci = new HashSet<>();
    }

    public Set<Predmet> getInventar() {
        return inventar;
    }
    private Set<Observer> observers = new HashSet<>();
    // Vložit předmět do inventáře
    public boolean vlozPredmet(Predmet predmet){
        seznamVeci.add(predmet.getNazev());
        notifyObservers();
        return inventar.add(predmet);
    };
    //Odstranit předmět z inventáře
    public void odstranPredmet(String nazev){
        Predmet toRemove = new Predmet(nazev);
        for(Predmet x : inventar){
            if(x.getNazev().equals(nazev)){
                 toRemove = x;
            }
        }
        inventar.remove(toRemove);
        seznamVeci.remove(toRemove.getNazev());
        notifyObservers();
    };

    public Set<String> getMnozinaNazvuPredmetuVInventari() {
        System.out.println(seznamVeci);

        return seznamVeci;
    }

    @Override
    public void register(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }

    }

    @Override
    public void unregister(Observer observer) {
        this.observers.remove(observer);
    }

}
