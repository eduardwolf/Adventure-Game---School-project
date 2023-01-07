package cz.vse.adventura.logika;

import java.util.Set;

/**
 * @author Eduard Wolf
 * @Date 5/26/2022
 */
 class PrikazPorozhlednoutSe implements IPrikaz  {
    private HerniPlan plan;
    public PrikazPorozhlednoutSe(HerniPlan plan) {
        this.plan = plan;
    }


    //Vypíše všechny věci, které se nacházejí v místnosti, kde se nachází hráč
    public String provedPrikaz(String... parametry){
        String toReturn;
        String veciString = "";
        for(Vec vec : plan.getAktualniProstor().getVeci()){
            if(veciString.length() > 0){
                veciString += ", " + vec.getNazev();
            }else{
                veciString = vec.getNazev();
            }
        }
        if(veciString.length() > 0){
            //Skryje balvan, pokud ještě nespadl z druhého patra
            if(plan.getAktualniProstor().getNazev().equals("přízemí") && plan.getAktualniProstor().vratSousedniProstor("patro1").vratSousedniProstor("patro2").vratSousedniProstor("patro3").getLocked()){
                toReturn = "Nic tu není";
            }else{
                toReturn = "Nalezeno: " + veciString;

            }
        }else{
            if(plan.getAktualniProstor().getNazev().equals("patro5")){
                toReturn = "Nic tu není, ale mluvící kočka Vám řekla, že v balvanu, který Vás téměř zabil,\nbyla díra, v které bylo něco lesklého.";
            }else{
                toReturn = "Nic tu není";

            }
        }
        return toReturn;

    }

    private static final String NAZEV = "porozhlednoutse";
    public String getNazev() {
        return NAZEV;
    }
}
