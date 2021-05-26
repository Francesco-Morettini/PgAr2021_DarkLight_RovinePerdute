import java.util.ArrayList;

public class Squadra {

    private String nome;
    private int tipoVeicolo;
    private double carburanteConsumato;
    private Percorso percorso;

    /*
    0 -> veicolo che consuma sulle coordinate x,y
    1 -> veicolo che consuma su altezza
     */
    public Squadra(String nome, int tipoVeicolo, ArrayList<Citta> citta) {
        setNome(nome);
        setTipoVeicolo(tipoVeicolo);
        percorso = new Percorso(citta);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTipoVeicolo() {
        return tipoVeicolo;
    }

    public void setTipoVeicolo(int tipoVeicolo) {
        this.tipoVeicolo = tipoVeicolo;
    }

    public Percorso getPercorso() {
        return percorso;
    }

    public void setPercorso() {

    }

    public double getCarburanteConsumato() {
        return carburanteConsumato;
    }

    public void setCarburanteConsumato() {
        this.carburanteConsumato = percorso.creaRotta(tipoVeicolo);
    }


}
