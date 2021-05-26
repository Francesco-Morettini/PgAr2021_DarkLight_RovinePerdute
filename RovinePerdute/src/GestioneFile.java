import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.util.ArrayList;

public class GestioneFile {

    private final static String ERRORE_READER = "Errore nell'inizializzazione del reader:";
    private final static String NOME_FILE_TEST = "RovinePerdute/test_file/PgAr_Map_5.xml";

    private ArrayList<Citta> citta;
    private Citta city;
    private int id, numeroCitta, x, y, z;
    private Posizione posizione;
    private String nome;

    public GestioneFile(){
        citta = new ArrayList<Citta>();
    }

    public void leggiFile(){

        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr = null;

        boolean errore = false;

        try {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(NOME_FILE_TEST, new FileInputStream(NOME_FILE_TEST));
        } catch (Exception e) {
            errore = true;
            System.out.println(ERRORE_READER);
            System.out.println(e.getMessage());
        }

        if(!errore){
            try{

                while (xmlr.hasNext()) { // continua a leggere finché ha eventi a disposizione

                    switch (xmlr.getEventType()) { // switch sul tipo di evento

                        case XMLStreamConstants.START_ELEMENT: // inizio di un elemento

                            switch (xmlr.getLocalName()){

                                case "map":

                                    numeroCitta = Integer.parseInt(xmlr.getAttributeValue(0)); //imposta numero città = all'attributo di <map>

                                    break;

                                case "city":

                                    id = Integer.parseInt(xmlr.getAttributeValue(0));
                                    nome = xmlr.getAttributeValue(1);
                                    x = Integer.parseInt(xmlr.getAttributeValue(2));
                                    y = Integer.parseInt(xmlr.getAttributeValue(3));
                                    z =  Integer.parseInt(xmlr.getAttributeValue(4));

                                    city = new Citta(id,nome,x,y,z);

                                    break;

                                case "link":

                                    city.getLink().add(Integer.parseInt(xmlr.getAttributeValue(0)));

                                    break;

                            }

                            break;

                        case XMLStreamConstants.END_ELEMENT:

                            if (xmlr.getLocalName().equals("city"))

                                citta.add(city);

                                break;

                    }

                    xmlr.next();

                }

            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

    }

    /*public void scriviFile(ArrayList<Squadra> squadre){

    }*/

    /*public ArrayList<Citta> getCitta(){

    }*/

    public void stampaCitta(){

        for (int i=0; i<citta.size(); i++){
            System.out.println("id: "+citta.get(i).getId());
            System.out.println("nome: "+citta.get(i).getNome());
            System.out.println("x: "+citta.get(i).getPosizione().getX());
            System.out.println("y: "+citta.get(i).getPosizione().getY());
            System.out.println("z: "+citta.get(i).getPosizione().getZ());
            for (int j=0; j<citta.get(i).getLink().size(); j++){
                System.out.println("Link: " + citta.get(i).getLink().get(j));
            }
            System.out.println("\n");
        }


    }

}
