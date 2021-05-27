import java.util.ArrayList;

public class Percorso {
    private ArrayList<Citta> citta;
    private ArrayList<Citta> rotta;
    private double mat[][];

    public Percorso(ArrayList<Citta> citta) {
        this.citta = citta;
        this.mat = new double[citta.size()][citta.size()];
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

    public double creaRotta(int tipoVeicolo){
        generaMat(tipoVeicolo);
        stampaMat();
        rotta();
        System.out.println("ciao");
        return 0;
    }

    public void generaMat(int tipoVeicolo){

        boolean trovato;

        for(int i=0; i<citta.size(); i++){
            for (int j=0; j<citta.size(); j++){
                if(i==j){
                    mat[i][j] = -1;
                }else{
                    trovato = false;
                    for (int k=0; k<citta.get(i).getLink().size() && trovato==false; k++){
                        if (citta.get(i).getLink().get(k)==j){
                            switch (tipoVeicolo){
                                case 0:
                                    mat[i][j] = calcolaDistanzaTipo0(citta.get(i),citta.get(j));
                                    break;
                                case 1:
                                    mat[i][j] = calcolaDistanzaTipo1(citta.get(i),citta.get(j));
                                    break;
                            }
                            trovato = true;
                        }
                    }
                    if (trovato == false){
                        mat[i][j] = -1;
                    }

                }
            }
        }

    }


    public double calcolaDistanzaTipo0(Citta citta1, Citta citta2){

        return Math.sqrt(Math.pow(citta2.getPosizione().getX()-citta1.getPosizione().getX(),2) + Math.pow(citta2.getPosizione().getY()-citta1.getPosizione().getY(),2));

    }

    public double calcolaDistanzaTipo1(Citta citta1, Citta citta2){

        double distanza;

        distanza = citta1.getPosizione().getZ() - citta2.getPosizione().getZ();

        if (distanza >= 0) {
            return distanza;
        }else {
            return distanza * (-1);
        }

    }

    public void stampaMat(){
        for (int i=0; i<citta.size();i++){
            for (int j=0; j<citta.size();j++){
                System.out.print(" "+mat[i][j]);
            }
            System.out.println();
        }
    }
    public void rotta()
    {

        int n = citta.size();
        int p[]= new int[citta.size()];
        boolean v[] = new boolean[citta.size()];
        double d[]= new double[citta.size()];
        double m=999999999;
        int j=0;
        for(int i=1;i<n;i++)
        {
            d[i]=999999999;
            v[i]=false;
            p[i]=0;
        }
        d[0]=0;
        do{
            m=999999999;
            for(int i=1;i<n;i++) {
                System.out.println("ciao");
                if (v[i] == false)
                {
                    if (d[i] <= m) {
                        m = d[i];
                        j = i;
                    }
                }
            }
            if(m!=999999999)
                v[j]=true;
            for(int i=1;i<n;i++)
            {
                if(mat[i][j]>0) {
                    if (d[i] > d[j] + mat[i][j]) {
                        d[i] = d[j] + mat[i][j];
                        p[i]=j;
                    }
                }
            }

        }while (m==999999999);
        for (int i=1;i<n;i++)
            System.out.println("da campo base a "+p[i]+ " la distanza è :"+d[i]);
    }
}
