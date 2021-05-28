public class Main {

    public static void main(String[] args) {

        GestioneFile file = new GestioneFile();
        file.leggiFile();
        //file.stampaCitta();
        Squadra squadra1 = new Squadra("Tonatiuh",0, file.getCitta());
        Squadra squadra2 = new Squadra("Metztli", 1, file.getCitta());
        //squadra1.setCarburanteConsumato();
        squadra2.setCarburanteConsumato();
        //aggiungere approssimazione distanza in matrice
        System.out.println(squadra2.getCarburanteConsumato()+"  "+ squadra2.getPercorso().getRotta());

    }

}
