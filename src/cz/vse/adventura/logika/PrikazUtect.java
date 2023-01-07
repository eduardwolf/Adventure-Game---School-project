package cz.vse.adventura.logika;

/**
 * @author Eduard Wolf
 * @Date 5/30/2022
 */
class PrikazUtect implements IPrikaz{
    private HerniPlan herniPlan;
    private Hra hra;
    public PrikazUtect(HerniPlan herniPlan, Hra hra) {
        this.herniPlan = herniPlan;
        this.hra = hra;
    }
    public String provedPrikaz(String... parametry){
        String toReturn = "Nelze nikam utéct";
        if(herniPlan.getAktualniProstor().getNazev().equals("patro4") && herniPlan.getAktualniProstor().vratSousedniProstor("patro5").getLocked()){
            toReturn = "Podařilo se ti utéct do bezpečného místa, zdi se vrátily na své původní místo a průchod vypadá bezpečně.";
            herniPlan.getAktualniProstor().vratSousedniProstor("patro5").setLocked();
        }else if(herniPlan.getAktualniProstor().getNazev().equals("patro6")){
            toReturn = "Podařilo se ti utéct z věže. Nemáš poklad, ale aspoň jsi naživu. Konec hry.";
            hra.setKonecHry(true);
        }
        return toReturn;
    }

    private static final String NAZEV = "utect";
    public String getNazev(){
        return NAZEV;
    }
}
