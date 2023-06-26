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
		Noeud noeud1 = noeud.lireNoeud(raf);
		
		if (noeud1.getFilsGauche() != -1) {
			this.lister((int) (noeud1.getFilsGauche() * TAILLE_NOEUD));

		}
	
		listeTrie.add(noeud1.getCle());
		
		if (noeud1.getSuivant() != -1) {
			this.lister((int) (noeud1.getSuivant() * TAILLE_NOEUD));
		}

		if (noeud1.getFilsDroit() != -1) {
			this.lister((int) (noeud1.getFilsDroit() * TAILLE_NOEUD));
		}
	}

	public String supprimer(String nom, String prenom, String formation) throws IOException {
		Noeud noeud = new Noeud(new Stagiaire());
		if(raf.length() == 0) {
			
			return "";
		} else {
			raf.seek(0);
			Noeud racine = noeud.lireNoeud(raf);
			return racine.rechercherPourSupprimer( nom, prenom, formation, 0, -1, raf);
		}
	}
	
public void modifier(String rechercheNom, String recherchePrenom, String rechercheFormation,String nom, String prenom, String departement, String formation, String annee) throws IOException {
		
		String validation = this.supprimer(rechercheNom, recherchePrenom, rechercheFormation);		
		
		if (validation.equals("")) {
			
		} else {
			Stagiaire stagiaireModif = new Stagiaire(nom, prenom, departement, formation, annee);
			raf.seek(0);
			Noeud noeud = new Noeud();
			Noeud racine = noeud.lireNoeud(raf);
			racine.ajouterStagiaire(stagiaireModif, raf);
		}
		
	}
	
public void ajouter(String nom, String prenom, String departement, String formation, String annee) throws IOException {
	Stagiaire stagiaireModif = new Stagiaire(nom, prenom, departement, formation, annee);
	raf.seek(0);
	Noeud noeud = new Noeud();
	Noeud racine = noeud.lireNoeud(raf);
		
		if (noeud.rechercherStagiaire(nom, prenom, formation, 0, -1, raf).equals("")) {
			racine.ajouterStagiaire(stagiaireModif, raf);
			
		} else {
			
			
		}
		
	}
}

