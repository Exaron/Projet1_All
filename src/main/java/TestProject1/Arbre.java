package TestProject1;

import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Arbre {

	TxtToData liste = new TxtToData();
	RandomAccessFile raf;
	ArrayList<Stagiaire> listeTrie = new ArrayList<>();

	private static final long TAILLE_NOEUD = Stagiaire.TAILLE_STAGIAIRE_OCTET + 3 * 4;

	public Arbre() {
		try {
			raf = new RandomAccessFile("arbre.bin", "rw");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Stagiaire> getListeTrie() {
		return listeTrie;
	}

	public void setListeTrie(ArrayList<Stagiaire> listeTrie) {
		this.listeTrie = listeTrie;
	}

	public void ajout() throws IOException {

		try {
			liste.lireFichier();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Noeud arbre = new Noeud(new Stagiaire());
		try {
			for (Stagiaire stagiaire : liste.getListeStagiaires()) {
				if (raf.length() == 0) {
					arbre = new Noeud(stagiaire);
					raf.seek(0);
					arbre.sauvegarderNoeud(stagiaire, -1, -1, -1, raf);
					// ecrire racine dans le fichier binaire
					// ecrit le stagiaire
					// les index à -1
				} else {
					raf.seek(0);
					// lire le premier noeud
					Noeud racine = arbre.lireNoeud(raf);
					racine.ajouterStagiaire(stagiaire, raf);
					// Noeud racine = lireNoeud(raf);
					// racine.ajouterStagiaire(stagiaire);
				}
				// arbre.ajouterStagiaire(stagiaire);
			}
			System.out.println(arbre);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void lister(int index) throws IOException {

		// Stagiaire stagiaire = new Stagiaire();
		Noeud noeud = new Noeud(new Stagiaire());
	
		raf.seek(index);
//		System.out.println(raf.getFilePointer());
		Noeud noeud1 = noeud.lireNoeud(raf);
//		System.out.println(noeud1.getCle());
//		System.out.println(noeud1.getFilsGauche());
		if (noeud1.getFilsGauche()!=-1) {
			this.lister((int) (noeud1.getFilsGauche()*TAILLE_NOEUD));
		
		} 
			listeTrie.add(noeud1.getCle());
	
		if (noeud1.getFilsDroit()!=-1) {
			this.lister((int) (noeud1.getFilsDroit()*TAILLE_NOEUD));
		}
		
	}
}
		
		
//		
