import java.util.ArrayList;

public class Percorso {
    private ArrayList<Citta> citta;
    private ArrayList<Citta> rotta;
    private double mat[][];
    private final static int infinito=999999999;
    private int r[];
    public Percorso(ArrayList<Citta> citta) {
        this.citta = citta;
        this.mat = new double[citta.size()][citta.size()];
        this.rotta= new ArrayList<Citta>();
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
       // stampaMat();
        return rotta();
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
    public double rotta()
    {

        int n = citta.size();
        int p[]= new int[citta.size()];
        double d[]= new double[citta.size()];
        double m,carburante=0;
        int j=0;
        for(int i=0;i<n;i++)
        {
            d[i]=infinito;//setto ad infinito la distanza
            p[i]=-1;// setto le celle per la rotta a -1
        }
            for(int i=0;i<n-1;i++)
            {
                for(j=i;j<n;j++)
                if(mat[i][j]>0) {//se la distanza è >0
                    if (d[i] > mat[i][j]) {//se la distanza del arco è minore di quella gia settata
                        d[i] = mat[i][j];// setto la distanza del arco
                        p[i]=j;// scrivo l'id del nodo per cui passo
                    }
                }
                if(p[i]!=-1)//se ho settato una posizione in p[i] allora
                    i=p[i]-1;// setto i come p[i] -1 cosi non torno indietro ma vado avanti nella mat
            }
        int t=0;
        for(int i=0 ; i<n; i++)
            if(d[i]<infinito) {
                carburante += d[i];//sommo tutte le distanze e trovo il carburante richiesto
                        t++;
            };
            j=0;
            r=new int [t];
        for(int i=0 ; i<n; i++)
            if(p[i]>0)//se la p[i]>0 ovvero se è presente un nodo a cui andare
            {
               r[j]=p[i];//inserisco il valore cosi da ottenere il percorso
               j++;
            }
        rotta.add(citta.get(0));//setto la prima citta "punto di partenza" della rotta
        for(int i=0;i<r.length;i++)
        {
            rotta.add(citta.get(r[i]));//setto la rotta con la citta contenuta in r[i]
        }
        return carburante;
}


}