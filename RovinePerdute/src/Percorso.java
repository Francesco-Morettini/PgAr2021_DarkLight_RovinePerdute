import java.util.ArrayList;

public class Percorso {
    private ArrayList<Citta> citta;
    private ArrayList<Citta> rotta;

    public Percorso() {
    }

    public ArrayList<Citta> getCitta() {
        return citta;
    }

    public void setCitta(ArrayList<Citta> citta) {
        this.citta = citta;
    }

    public ArrayList<Citta> getRotta() {
        return rotta;
    }

    public void setRotta(ArrayList<Citta> rotta) {
        this.rotta = rotta;
    }
}
