import java.util.ArrayList;

public class Citta {
    private int id;
    private String nome;
    private Posizione posizione;
    private ArrayList<Integer> link;

    public Citta(int id, String nome, int x, int y, int z) {
        this.id = id;
        this.nome = nome;
        this.posizione = new Posizione(x,y,z);
        this.link = new ArrayList<Integer>();
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Posizione getPosizione() {
        return posizione;
    }

    public void setPosizione(Posizione posizione) {
        this.posizione = posizione;
    }

    public ArrayList<Integer> getLink() {
        return link;
    }

    public void setLink(ArrayList<Integer> link) {
        this.link = link;
    }


}
