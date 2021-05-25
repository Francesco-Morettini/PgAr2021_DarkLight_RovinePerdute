public class Squadra {

    private String nome;
    private int tipoVeicolo;
    private double carburanteConsumato;
    private Percorso percorso;

    public Squadra(String nome, int tipoVeicolo) {
        setNome(nome);
        setTipoVeicolo(tipoVeicolo);
        percorso = new Percorso();
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

    public double getCarburanteConsumato() {
        return carburanteConsumato;
    }

    public void setCarburanteConsumato(double carburanteConsumato) {
        this.carburanteConsumato = carburanteConsumato;
    }

    public Percorso getPercorso() {
        return percorso;
    }

    public void setPercorso() {

    }
}
