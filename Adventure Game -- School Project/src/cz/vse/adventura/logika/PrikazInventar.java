package cz.vse.adventura.logika;

/**
 * @author Eduard Wolf
 * @Date 5/30/2022
 */
class PrikazInventar implements IPrikaz{
    private Inventar inventar;
    public PrikazInventar(Inventar inventar){
        this.inventar = inventar;
    }


    //Zobrazit obsah inventáře
    public String provedPrikaz(String... parametry){
        String toReturn = "";
        for(Predmet predmet: inventar.getInventar()){
            toReturn += predmet.getNazev() + "\n";
        }
        if(toReturn.length() <1){
            return "V inventáři nic není.";
        }else{
            return toReturn;

        }
    }


    private static final String NAZEV = "inventar";
    public String getNazev() {
        return NAZEV;
    }
}
