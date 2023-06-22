package TestProject1;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

public class TxtImport {

	public static void main(String[] args) throws IOException {
		TxtToData data = new TxtToData();
		Arbre annuaire = new Arbre();

		try {
			data.lireFichier();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if(annuaire.getRaf().length() ==0) {
			System.out.println("fichier vide création");
			annuaire.ajout();
			annuaire.lister(0);
			
		} else {
			System.out.println("arbre existe deja");
			annuaire.lister(0);
		}
		
		for (Stagiaire s : annuaire.getListeTrie()) {
			System.out.println(s);
		}
		
		annuaire.supprimer("AKHIAD","Brahim", "AI 60");
		
		for (Stagiaire s : annuaire.getListeTrie()) {
			System.out.println(s);
		}
	}
}
