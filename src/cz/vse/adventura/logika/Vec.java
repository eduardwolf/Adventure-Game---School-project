package cz.vse.adventura.logika;

/**
 * @author Eduard Wolf
 * @Date 5/29/2022
 */
public class Vec {
    private String nazev;
    private String porozhlednoutResult;
    private boolean prohlednuto;

    public Vec(String nazev, String porozhlednoutResult, boolean prohlednuto) {
        this.nazev = nazev;
        this.porozhlednoutResult = porozhlednoutResult;
        this.prohlednuto = prohlednuto;
    }

    public String getNazev() {
        return nazev;
    }

    public String getPorozhlednoutResult() {
        return porozhlednoutResult;
    }

    public boolean isProhlednuto() {
        return prohlednuto;
    }

    public void setProhlednuto(boolean prohlednuto) {
        this.prohlednuto = prohlednuto;
    }
}
