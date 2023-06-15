package fr.isika.CDA25.Projet1_All;

public class ListChain {

	Stagiaire stagiaire;
	ListChain suivant;
	public ListChain(Stagiaire stagiaire, ListChain suivant) {
		this.stagiaire = stagiaire;
		this.suivant = suivant;
	}
	public Stagiaire getStagiaire() {
		return stagiaire;
	}
	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}
	public ListChain getSuivant() {
		return suivant;
	}
	public void setSuivant(ListChain suivant) {
		this.suivant = suivant;
	}
	
	
	
}
