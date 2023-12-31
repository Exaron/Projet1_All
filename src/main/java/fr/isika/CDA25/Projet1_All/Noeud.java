package fr.isika.CDA25.Projet1_All;

public class Noeud {
	public Stagiaire stagiaire;
	public Noeud filsGauche;
	public Noeud filsDroit;
	public ListChain suivant;


	public Noeud(Stagiaire stagiaire, Noeud filsGauche, Noeud filsDroit, ListChain suivant) {
		super();
		this.stagiaire = stagiaire;
		this.filsGauche = filsGauche;
		this.filsDroit = filsDroit;
		this.suivant = suivant;
	}
	
	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}

	public Noeud getFilsGauche() {
		return filsGauche;
	}

	public void setFilsGauche(Noeud filsGauche) {
		this.filsGauche = filsGauche;
	}

	public Noeud getFilsDroit() {
		return filsDroit;
	}

	public void setFilsDroit(Noeud filsDroit) {
		this.filsDroit = filsDroit;
	}

	public ListChain getSuivant() {
		return suivant;
	}

	public void setSuivant(ListChain suivant) {
		this.suivant = suivant;
	}

	
	}