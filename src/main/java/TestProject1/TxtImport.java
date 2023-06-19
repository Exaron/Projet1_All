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

		annuaire.ajout();
		annuaire.lister(0);
		
		for (Stagiaire s : annuaire.getListeTrie()) {
			System.out.println(s);
		}
	}
}
