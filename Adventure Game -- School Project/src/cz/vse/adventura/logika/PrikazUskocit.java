package cz.vse.adventura.logika;

/**
 * @author Eduard Wolf
 * @Date 5/30/2022
 */
class PrikazUskocit implements IPrikaz{
    private HerniPlan herniPlan;
    public PrikazUskocit(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }
    public String provedPrikaz(String... parametry){
        String toReturn = "";
        if(herniPlan.getAktualniProstor().getNazev().equals("patro2") && herniPlan.getAktualniProstor().vratSousedniProstor("patro3").getLocked()){
            toReturn = "Podařilo se ti uskočit před balvanem, ten se zakutálel do přízemí";
            herniPlan.getAktualniProstor().vratSousedniProstor("patro3").setLocked();
        }else{
            toReturn = "Není před čím uskočit";
        }
        return toReturn;
    }

    private static final String NAZEV = "uskocit";
    public String getNazev(){
        return NAZEV;
    }
}
