import javax.xml.stream.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class GestioneFile {

    private final static String ERRORE_READER = "Errore nell'inizializzazione del reader:";
    private final static String ERRORE_WRITER = "Errore nell'inizializzazione del writer:";
    private final static String ERRORE_SCRITTURA_FILE = "Errore nella scrittura del file";
    private final static String NOME_FILE_OUTPUT = "RovinePerdute/test_file/Routes.xml";

    private ArrayList<Citta> citta;
    private Citta city;
    private int id, numeroCitta, x, y, z;
    private Posizione posizione;
    private String nome;

    public GestioneFile(){
        citta = new ArrayList<Citta>();
    }

    public void leggiFile(String nomeFile){

        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr = null;

        boolean errore = false;

        try {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(nomeFile, new FileInputStream(nomeFile));
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

    public boolean scriviFile(ArrayList<Squadra> squadre){

            XMLOutputFactory xmlof = null;
            XMLStreamWriter xmlw = null;

            //inizializzazione del writer con controllo eccezioni
            try {
                xmlof = XMLOutputFactory.newInstance();
                xmlw = xmlof.createXMLStreamWriter(new FileOutputStream(NOME_FILE_OUTPUT), "utf-8");
                xmlw.writeStartDocument("utf-8", "1.0");
            } catch (Exception e) {
                System.out.println(ERRORE_WRITER);
                System.out.println(e.getMessage());
                return false;
            }

            try { // blocco try per raccogliere eccezioni
                xmlw.writeStartElement( "routes"); // scrittura del tag radice <routes>

                for (int i=0; i<squadre.size(); i++){
                    generaTagRoute(squadre.get(i), xmlw);
                }

                xmlw.writeEndElement();//chiudo tag radice </routes>

                xmlw.writeEndDocument();

                xmlw.flush(); // svuota il buffer e procede alla scrittura
                xmlw.close(); // chiusura del documento e delle risorse impiegate

                return true;

            } catch (Exception e) { // se c’è un errore viene eseguita questa parte
                System.out.println(ERRORE_SCRITTURA_FILE);
                System.out.println(e.getMessage());
                return false;
            }

    }

    public void generaTagRoute(Squadra squadra, XMLStreamWriter xmlw) throws XMLStreamException {

        xmlw.writeStartElement("route"); // scrittura del tag <route> ...
        xmlw.writeAttribute("team" , squadra.getNome()); //... con attributo team = nome squadra
        xmlw.writeAttribute("cost" , Double.toString(squadra.getCarburanteConsumato())); //... con attributo cost = carburante consumato
        xmlw.writeAttribute("cities" , Integer.toString(squadra.getPercorso().getRotta().size())); //... con attributo cities = numero città toccate

        for (int i=0; i<squadra.getPercorso().getRotta().size();i++){
            xmlw.writeStartElement("city");
            xmlw.writeAttribute("id" , Integer.toString(squadra.getPercorso().getRotta().get(i).getId()));
            xmlw.writeAttribute("name" , squadra.getPercorso().getRotta().get(i).getNome());
        }

        xmlw.writeEndElement();//chiudo tag </route>

    }



    public ArrayList<Citta> getCitta(){
        return citta;
    }

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
