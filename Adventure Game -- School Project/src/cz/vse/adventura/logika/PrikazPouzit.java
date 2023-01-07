package cz.vse.adventura.logika;

import java.util.Set;

/**
 * @author Eduard Wolf
 * @Date 5/29/2022
 */
class PrikazPouzit implements IPrikaz {

    private HerniPlan herniPlan;
    private Inventar inventar;
    private Hra hra;

    public PrikazPouzit(HerniPlan herniPlan, Inventar inventar, Hra hra){
        this.herniPlan = herniPlan;
        this.inventar = inventar;
        this.hra = hra;
    }


    //Hráč použije předmět, pokud se nachází v inventáři a pokud lze použít
    public String provedPrikaz(String... parametry) {
        String toReturn = "";
        if (parametry.length == 0) {
            // pokud chybí druhé slovo
            toReturn = "Co mám použít?";
        } else {
            boolean jeVInventari = false;
            Set<Predmet> predmety = inventar.getInventar();
            for(Predmet predmet : predmety){
                if(parametry[0].equals(predmet.getNazev())){
                    jeVInventari = true;

                }
            }
            if(jeVInventari){
                switch (parametry[0]){
                    case "KlíčP2":
                        if(herniPlan.getAktualniProstor().vratSousedniProstor("patro2").getLocked() && herniPlan.getAktualniProstor().getNazev().equals("patro1")){
                            herniPlan.getAktualniProstor().vratSousedniProstor("patro2").setLocked();
                            toReturn = ("Zámek byl odemčen");
                        }else{
                            toReturn = "Tento předmět nelze v této chvíli použít";
                        }
                        break;
                    case "Ančovičky":
                        inventar.odstranPredmet(parametry[0]);
                        toReturn = "Obr spokojeně zakýval hlavou, přidej další 2 ingredience";
                        break;
                    case "Brokolice":
                        boolean jsouAncovicky = false;
                        for(Predmet predmet : predmety){
                            if(predmet.getNazev().equals("Ančovičky")){
                                jsouAncovicky = true;
                                System.out.println("Přidali jste ingredience ve špatném pořadí, obr Vás zabil. Konec Hry");
                                hra.setKonecHry(true);
                            }
                        }
                        if(!jsouAncovicky){
                            inventar.odstranPredmet(parametry[0]);
                            toReturn = "Obr spokojeně zakýval hlavou, přidej  poslední ingredienci";
                        }
                        break;
                    case "Celer":
                        boolean jsouAneboB = false;
                        for(Predmet predmet : predmety){
                            if(predmet.getNazev().equals("Ančovičky")){
                                jsouAneboB = true;
                                System.out.println("Přidali jste ingredience ve špatném pořadí, obr Vás zabil. Konec Hry");
                                hra.setKonecHry(true);
                            }else if(predmet.getNazev().equals("Brokolice")){
                                jsouAneboB = true;
                                System.out.println("Přidali jste ingredience ve špatném pořadí, obr Vás zabil. Konec Hry");
                                hra.setKonecHry(true);
                            }
                        }
                        if(!jsouAneboB){
                            inventar.odstranPredmet(parametry[0]);
                            toReturn = "Obr je spokojen, dovolil Vám projít dál.";
                            herniPlan.getAktualniProstor().vratSousedniProstor("patro4").setLocked();
                        }
                        break;
                    case "KlíčP6":
                        if(herniPlan.getAktualniProstor().vratSousedniProstor("patro6").getLocked() && herniPlan.getAktualniProstor().getNazev().equals("patro5")){
                            herniPlan.getAktualniProstor().vratSousedniProstor("patro6").setLocked();
                            toReturn = ("Zámek byl odemčen");
                        }else{
                            toReturn = "Tento předmět nelze v této chvíli použít";
                        }
                        break;
                    case "Lektvar":
                        toReturn ="Dali jste kočce napít lektvaru a ta se proměnila v čarodějnici. \nTa Vám řekne, že Vás nechá naživu, pokud utečete a necháte jí truhlu s pokladem, která se objevila uprostřed místnosti. Přijímáte tuto nabídku?";
                        break;
                    case "Dýka":
                        boolean jizLektvar = false;
                        for(Predmet predmet : inventar.getInventar()){
                            if(predmet.getNazev().equals("Lektvar")){
                                jizLektvar = true;
                            }
                        }
                        if(jizLektvar){
                            toReturn = "Tajně sis vzal z kapsy svou dýku a zabil čarodejnici předtím, než stihla zareagovat. Poklad je tvůj a jsi v bezpečí. Blahopřeji, konec hry.";
                            hra.setKonecHry(true);
                        }
                }
            }else{
                toReturn = "Tato věc není v inventáři";
            }

        }
        return toReturn;
    }
    private static final String NAZEV = "pouzit";
    public String getNazev(){
        return NAZEV;
    }
}