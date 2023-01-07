package cz.vse.adventura.uiText;


import java.io.*;
import java.util.Scanner;

import cz.vse.adventura.logika.Hra;
import cz.vse.adventura.logika.IHra;
import org.w3c.dom.Text;

/**
 *  Class TextoveRozhrani
 * 
 *  Toto je uživatelského rozhraní aplikace Adventura
 *  Tato třída vytváří instanci třídy Hra, která představuje logiku aplikace.
 *  Čte vstup zadaný uživatelem a předává tento řetězec logice a vypisuje odpověď logiky na konzoli.
 *  
 *  
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 *@version    pro školní rok 2016/2017
 */

public class TextoveRozhrani {
    private IHra hra;

    /**
     *  Vytváří hru.
     */
    public TextoveRozhrani(IHra hra) {
        this.hra = hra;
    }

    public static void main(String[] args) {
        TextoveRozhrani textoveRozhrani = new TextoveRozhrani(new Hra());
        textoveRozhrani.hraj();
    }

    /**
     *  Hlavní metoda hry. Vypíše úvodní text a pak opakuje čtení a zpracování
     *  příkazu od hráče do konce hry (dokud metoda konecHry() z logiky nevrátí
     *  hodnotu true). Nakonec vypíše text epilogu.
     */
    public void hraj() {
        System.out.println(hra.vratUvitani());

        // základní cyklus programu - opakovaně se čtou příkazy a poté
        // se provádějí do konce hry.

        while (!hra.konecHry()) {
            String radek = prectiString();
            System.out.println(hra.zpracujPrikaz(radek));
        }

        System.out.println(hra.vratEpilog());
    }

    /**
     *  Metoda přečte příkaz z příkazového řádku
     *
     *@return    Vrací přečtený příkaz jako instanci třídy String
     */
    private String prectiString() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        return scanner.nextLine();
    }
    public void hrajZeSouboru(String soubor){
        try (BufferedReader cteni = new BufferedReader(new FileReader(soubor));
             PrintWriter zapis = new PrintWriter(new FileWriter("vystup.txt",true))){
            String radek = cteni.readLine();

            System.out.println(hra.vratUvitani());
            zapis.println(hra.vratUvitani());

            while(radek != null && !hra.konecHry()){
                System.out.println(radek);

                zapis.println( ">" +  radek);

                String odpoved = hra.zpracujPrikaz(radek);
                System.out.println(odpoved);
                radek = cteni.readLine();
                zapis.println(odpoved);

            }

            System.out.println(hra.vratEpilog());
            zapis.println(hra.vratEpilog());
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally{
            System.out.println("F");
        }
    }
}
