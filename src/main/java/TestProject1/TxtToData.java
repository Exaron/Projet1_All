package TestProject1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TxtToData {

    private ArrayList<Stagiaire> listeStagiaires;

    public ArrayList<Stagiaire> getListeStagiaires() {
        return listeStagiaires;
    }

    public void lireFichier() throws URISyntaxException {
        listeStagiaires = new ArrayList<>();
        URL u = getClass().getClassLoader().getResource("donneesStagiaires.txt");
        File file = new File(Paths.get(u.toURI()).toUri());

        Stagiaire stagiaire = new Stagiaire();
        int ligneCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.equals("*")) {
                    listeStagiaires.add(stagiaire);
               
                    ligneCount = 0;
                    stagiaire = new Stagiaire(line, line, line, line, line);
                } else {
                    switch (ligneCount) {
                        case 0:
                            stagiaire.setNom(line);
                            break;
                        case 1:
                            stagiaire.setPrenom(line);
                            break;
                        case 2:
                            stagiaire.setDepartement(line);
                            break;
                        case 3:
                            stagiaire.setFormation(line);
                            break;
                        case 4:
                            stagiaire.setAnnee(line);
                            break;
                    }
                    ligneCount++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    

    public void ecrireFichierBinaire(String nomFichier) {
        try (RandomAccessFile raf = new RandomAccessFile(nomFichier, "test")) {
            for (Stagiaire stagiaire : listeStagiaires) {
                raf.writeUTF(stagiaire.getNom());
                raf.writeUTF(stagiaire.getPrenom());
                raf.writeUTF(stagiaire.getDepartement());
                raf.writeUTF(stagiaire.getFormation());
                raf.writeUTF(stagiaire.getAnnee());
            }
           
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public List<Stagiaire> getarbre() {
		// TODO Auto-generated method stub
		return null;
	}

}
