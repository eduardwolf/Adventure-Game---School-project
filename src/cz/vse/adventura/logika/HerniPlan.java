package cz.vse.adventura.logika;


import java.util.HashSet;
import java.util.Set;
import cz.vse.adventura.observer.Observer;
import cz.vse.adventura.observer.SubjectOfChange;


/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 *@version    pro školní rok 2016/2017
 */
public class HerniPlan implements SubjectOfChange{
    private boolean balvanCheck = false;
    public boolean getBalvanCheck(){
        return balvanCheck;
    }
    public void setBalvanCheck(){
        balvanCheck = true;
    }
    private Prostor aktualniProstor;

    private Set<Observer> seznamPozorovatelu = new HashSet<>();
    
     /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan() {
        zalozProstoryHry();

    }
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví domeček.
     */
    public void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor prizemi = new Prostor("přízemí","přízemí", false,190.00,200.00);
        Prostor patro1 = new Prostor("patro1","Patro 1", false,190.00,157.00);
        Prostor patro2 = new Prostor("patro2","Patro 2",true,190.00,130.00);
        Prostor patro3 = new Prostor("patro3", "Patro 3",true,190.00,107.00);
        Prostor patro4 = new Prostor("patro4","Patro 4,",true,190.00,85.00);
        Prostor patro5 = new Prostor("patro5","Patro 5",true,190.00,60.00);
        Prostor patro6 = new Prostor("patro6","Patro 6",true,190.00,37.00);

        // přiřazují se průchody mezi prostory (sousedící prostory)
        prizemi.setVychod(patro1);
        patro1.setVychod(prizemi);
        patro1.setVychod(patro2);
        patro2.setVychod(patro1);
        patro2.setVychod(patro3);
        patro3.setVychod(patro2);
        patro3.setVychod(patro4);
        patro4.setVychod(patro3);
        patro4.setVychod(patro5);
        patro5.setVychod(patro4);
        patro5.setVychod(patro6);
        patro6.setVychod(patro5);


        //Vytváření a přiřazení věcí do místností
        Vec Koberec = new Vec("Koberec", "Pod kobercem jste našli klíč.",false);
        Vec Skrin = new Vec("Skrin", "Ve skříni nic není.",false);
        Vec Obr = new Vec("Obr", "Vzbudili jste spícího obra. Řekl, že má velký hlad a že by si dal polévku. Dal Vám tři ingredience.\nPřidejte ingredience do kotle ve správném pořadí, máte jeden pokus, jinak se naštve a zabije Vás.",false);
        Vec Stul = new Vec("Stul", "Na stolu jste našli starou dýku, ale když jste ji zvedli, tak se zdi místnosti začaly rychle zužovat.\nMusíte reagovat rychle, než budete rozmáčknuti.",false);
        Vec Lektvar = new Vec("Lektvar", "Lektvar je zelené barvy, nevíte však, co dělá.",false);
        Vec Balvan = new Vec("Balvan", "Strčili jste do díry v balvanu ruku a vytáhli jste z něj klíč.",false);
        patro1.setVec(Koberec);
        patro1.setVec(Skrin);
        patro3.setVec(Obr);
        patro4.setVec(Stul);
        patro6.setVec(Lektvar);
        prizemi.setVec(Balvan);



        aktualniProstor = prizemi;  // hra začíná v domečku
    }
    
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
        this.notifyObservers();
    }


    @Override
    public void register(Observer observer) {
        seznamPozorovatelu.add(observer);
    }
    @Override
    public void notifyObservers() {
        for (Observer observer : seznamPozorovatelu) {
            observer.update();
        }
    }
    @Override
    public void unregister(Observer observer) {

    }
}