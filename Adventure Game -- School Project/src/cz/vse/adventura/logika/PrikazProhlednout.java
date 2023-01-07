package cz.vse.adventura.logika;

import java.util.Set;

/**
 * @author Eduard Wolf
 * @Date 5/29/2022
 */
 class PrikazProhlednout implements IPrikaz{
    private HerniPlan plan;
    private Inventar inventar;
    public PrikazProhlednout(HerniPlan plan,Inventar inventar) {
        this.plan = plan;
        this.inventar = inventar;
    }

    //Hráč si prohlédne věc, kterou našel příkazem porozhlednoutse
    public String provedPrikaz(String... parametry){
        String toReturn = "";
        if (parametry.length == 0) {
            // pokud chybí druhé slovo
            toReturn = "Co mám prohlédnout?";
        }else{
            boolean doneCheck = false;
            for(Vec vec : plan.getAktualniProstor().getVeci()){
                if(vec.isProhlednuto()){
                    toReturn = "Již jste prohlédli.";
                }else if(vec.getNazev().equals(parametry[0])){
                    toReturn = vec.getPorozhlednoutResult();
                    doneCheck = true;

                    switch (vec.getNazev()){
                        case "Balvan":
                            inventar.vlozPredmet(new Predmet("KlíčP6"));
                            vec.setProhlednuto(true);
                            break;
                        case "Koberec":
                            inventar.vlozPredmet(new Predmet("KlíčP2"));
                            vec.setProhlednuto(true);

                            break;
                        case "Lektvar":
                            inventar.vlozPredmet(new Predmet("Lektvar"));
                            vec.setProhlednuto(true);

                            break;
                        case "Stul":
                            inventar.vlozPredmet(new Predmet("Dýka"));
                            vec.setProhlednuto(true);
                            break;
                        case "Obr":
                            inventar.vlozPredmet(new Predmet("Celer"));
                            inventar.vlozPredmet(new Predmet("Ančovičky"));
                            inventar.vlozPredmet(new Predmet("Brokolice"));
                            vec.setProhlednuto(true);
                            break;

                    }
                }else{
                    if(!doneCheck){
                        toReturn = parametry[0] + " zde není.";
                    }
                }
            }
        }

        return toReturn;
    }


    private static final String NAZEV = "prohlednout";
    public String getNazev() {
        return NAZEV;
    }
}
