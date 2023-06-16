package TestProject1;


import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

public class TxtImport {

    public static void main(String[] args) {
        TxtToData data = new TxtToData();
        try {
            data.lireFichier();
        } catch (Exception e) {
            e.printStackTrace();
        }

        data.ajout();

        List<Stagiaire> listeStagiaires = data.getListeStagiaires();

        try (RandomAccessFile raf = new RandomAccessFile("arbre.bin", "rw")) {
            for (Stagiaire stagiaire : listeStagiaires) {
                String ligne = stagiaire.toString();
                raf.writeBytes(ligne);
            }
            System.out.println("Données sauvegardées avec succès dans le fichier arbre.bin.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
