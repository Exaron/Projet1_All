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

	public RandomAccessFile getRaf() {
		return raf;
	}

	public void setRaf(RandomAccessFile raf) {
		this.raf = raf;
	}

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

	public void refresh() throws IOException {
		listeTrie = new ArrayList<>();
		if(raf.length() != 0) {
			lister(0);
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
		if (noeud1.getFilsGauche() != -1) {
			this.lister((int) (noeud1.getFilsGauche() * TAILLE_NOEUD));

		}
		System.out.println(noeud1);
		listeTrie.add(noeud1.getCle());
		
		if (noeud1.getSuivant() != -1) {
			this.lister((int) (noeud1.getSuivant() * TAILLE_NOEUD));
		}

		if (noeud1.getFilsDroit() != -1) {
			this.lister((int) (noeud1.getFilsDroit() * TAILLE_NOEUD));
		}

		

	}

	public void supprimer(String nom, String prenom, String formation) throws IOException {
		Noeud noeud = new Noeud(new Stagiaire());
		if(raf.length() == 0) {
			System.out.println("arbre vide");
		} else {
			raf.seek(0);
			Noeud racine = noeud.lireNoeud(raf);
			racine.rechercherPourSupprimer( nom, prenom, formation, 0, -1, raf);
		}
	}
	
	public void modifier(String nom, String prenom, String departement, String formation, String annee) throws IOException {

		this.supprimer(nom, prenom, formation);
		this.ajout();
		
		
	}
	
//	
//	public int rechercherParent(String nom, String formation, int index, int indexParent) throws IOException {
//		Noeud noeud = new Noeud(new Stagiaire());
//		raf.seek(index * TAILLE_NOEUD);
//		Noeud noeud1 = noeud.lireNoeud(raf);
//		int indexPere = -1;
//		System.out.println(index + " index");
//		if (nom.equals(noeud1.getCle().getVraiNom())) {
//			System.out.println("meme nom");
//			if (formation.equals(noeud1.getCle().getVraiFormation())) {
//				System.out.println("trouvé");
//				indexPere = indexParent;
//				System.out.println(index + " " + indexPere + " " + noeud1.getCle().getVraiNom());
//			} else {
//				if (noeud1.getSuivant() >= 0) {
//					System.out.println("suivant");
//					this.rechercherParent(nom, formation, (int) (noeud1.getSuivant()), index);
//
//				} else {
//					System.out.println("pas trouvé");
//				}
//			}
//		} else {
//			if (nom.compareTo(noeud1.getCle().getVraiNom()) < 0) {
//				if (noeud1.getFilsGauche() >= 0) {
//					this.rechercherParent(nom, formation, (int) (noeud1.getFilsGauche()), index);
//					System.out.println("gauche");
//				}
//			} else {
//				if (noeud1.getFilsDroit() >= 0) {
//					this.rechercherParent(nom, formation, (int) (noeud1.getFilsDroit()), index);
//					System.out.println("droite");
//				}
//			}
//		}
////		System.out.println(indexPere);
//		return indexPere;
//	}

	
}

//		
