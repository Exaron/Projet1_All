package TestProject1;

public class Noeud extends TxtToData {
    private Stagiaire cle;
    private Noeud filsGauche;
    private Noeud filsDroit;
    private Noeud suivant;

    public Noeud(Stagiaire cle) {
        this.cle = cle;
    }

    public Noeud(Stagiaire cle, Noeud filsGauche, Noeud filsDroit, Noeud suivant) {
        this.cle = cle;
        this.filsGauche = filsGauche;
        this.filsDroit = filsDroit;
        this.suivant = suivant;
    }

    public Stagiaire getCle() {
        return cle;
    }

    public void setCle(Stagiaire cle) {
        this.cle = cle;
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

    public Noeud getSuivant() {
        return suivant;
    }

    public void setSuivant(Noeud suivant) {
        this.suivant = suivant;
    }

    public String toString() {

		String res = "";

		if (filsGauche != null) {
			res = res + filsGauche.toString();
		}
		res = res + this.cle.toString();
		
		if (suivant !=null) {
			res = res + suivant.toString();
		}

		if (this.filsDroit != null) {
			res = res + filsDroit.toString();
		}

		return res;
	}

	public void ajouterStagiaire(Stagiaire newStagiaire) {

		if (cle.getNom() == null ) {
			
			this.cle = newStagiaire;
			System.out.println(this);

		} else if (this.cle.compareTo(newStagiaire) < 0) {

			if (this.filsDroit == null) {
				this.filsDroit = new Noeud(newStagiaire);
			} else {
				this.filsDroit.ajouterStagiaire(newStagiaire);
			}

		} else if ((this.cle.compareTo(newStagiaire) > 0)) {
			
			if (this.filsGauche == null) {
				this.filsGauche = new Noeud(newStagiaire);
			} else {
				this.filsGauche.ajouterStagiaire(newStagiaire);
			}

		} else {
			
			if (this.suivant == null) {
				this.suivant = new Noeud(newStagiaire);
			} else {
				this.suivant.ajouterStagiaire(newStagiaire);
			}
			
		}

	}

//	public String recherchePrenom(String monPrenom) {
//
//		String recherche = "Le prénom " + monPrenom + " n'a pas été trouvé";
//
//		if (cle.getPrenom().equals(monPrenom)) {
//			recherche = monPrenom;
//		} else {
//			if (cle.getPrenom().compareTo(monPrenom) < 0) {
//				if (filsDroit != null) {
//					recherche = filsDroit.recherchePrenom(monPrenom);
//				}
//			} else {
//				if (filsGauche != null) {
//					recherche = filsGauche.recherchePrenom(monPrenom);
//				}
//			}
//		}
//		return recherche;
//	}
//
//	public int nbNoeud() {
//
//		int nb = 0;
//
//		if (filsGauche != null) {
//			nb = nb + filsGauche.nbNoeud();
//		}
//		nb = nb + 1;
//
//		if (this.filsDroit != null) {
//			nb = nb + filsDroit.nbNoeud();
//		}
//
//		return nb;
//	}
//
//	public int hauter() {
//
//		int count = 0;
//		int countDroit = 0;
//		int countGauche = 0;
//
//		if ((filsGauche != null) || (this.filsDroit != null)) {
//			countGauche = countGauche + filsGauche.nbNoeud();
//
//			countDroit = countDroit + filsDroit.nbNoeud();
//		}
//
//		if (countDroit <= countGauche) {
//			count = countGauche;
//		} else {
//			count = countDroit;
//		}
//
//		return count;
//
//	}

}

    
    
    

