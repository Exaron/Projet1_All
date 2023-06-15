package TestProject1;

import java.io.BufferedReader;
import java.io.RandomAccessFile;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;

public class TxtToData {

	ArrayList<Stagiaire> listeStagiaires;

	public ArrayList<Stagiaire> getListeStagiaires() {
		return listeStagiaires;
	}

	public void lireFichier() throws URISyntaxException {
		listeStagiaires = new ArrayList<>();
		URL u = getClass().getClassLoader().getResource("donneesStagiaires.txt");
//    	InputStream is = getClass().getClassLoader().getResourceAsStream("/Projet1_Annuaire/src/main/resources/donneesStagiaires.txt");
//       System.out.println(is.toString());
//        InputStreamReader streamReader = new InputStreamReader(is);
		File file = new File(Paths.get(u.toURI()).toUri());

		Stagiaire stagiaire = new Stagiaire();
		int ligneCount = 0;

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;

			while ((line = br.readLine()) != null) {
				if (line.equals("*")) {

					listeStagiaires.add(stagiaire);
					System.out.println(stagiaire);
					ligneCount = 0;
					stagiaire = new Stagiaire();
					// stagiaire = new Stagiaire(line, line, line, line, line);
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

			// Insérer le dernier stagiaire dans l'annuaire
//            if (stagiaire != null) {
//                listeStagiaires.add(stagiaire);
//                System.out.println(stagiaire);
//            }
		} catch (IOException e) {
			e.printStackTrace();
		}
//        return listeStagiaires;
	}

	
	public void ajout() {
		Noeud racine = new Noeud(new Stagiaire());
		for (Stagiaire stagiaire : listeStagiaires) {
			racine.ajouterStagiaire(stagiaire);
		}
		System.out.println(racine);
		
	}
	
	public void aaaaa() {
	
	}
		
}
