package cz.vse.adventura.observer;


public interface SubjectOfChange {

    void register(Observer observer);

    void notifyObservers();

    void unregister(Observer observer);

}
