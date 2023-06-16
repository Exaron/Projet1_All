package fr.isika.CDA25.Projet1_All;

public class Annuaire {
    private Noeud racine;

    public Annuaire() {
        racine = null;
    }

    public void ajouterStagiaire(Stagiaire stagiaire) {
    	Noeud nouveauNoeud = new Noeud(stagiaire, null, null, null); // Prendre tout les argument de stagiaire

        if (racine == null) {
            racine = nouveauNoeud;
        } else {
            racine = insererNoeud(racine, nouveauNoeud); // faire try and catch sortir de la boucle ou recupere exception de insererNoeud
        }
    }

    private Noeud insererNoeud(Noeud parent, Noeud nouveauNoeud) {
        if (parent == null) {
            return nouveauNoeud;
        }

        if (nouveauNoeud.getStagiaire().getNom().compareTo(parent.getStagiaire().getNom()) < 0) {
            parent.setFilsGauche(insererNoeud(parent.getFilsGauche(), nouveauNoeud));
            
        } else if (nouveauNoeud.getStagiaire().getNom().compareTo(parent.getStagiaire().getNom()) > 0) {
            parent.setFilsDroit(insererNoeud(parent.getFilsDroit(), nouveauNoeud));
            
        } else {
        	parent.setSuivant(insererListChain());
        }
            

        return parent;
    }
    


    private ListChain insererListChain() {
		
		return null;
	}

	public void supprimerStagiaire(Stagiaire stagiaire) {
        racine = supprimerNoeud(racine, stagiaire);
    }

    private Noeud supprimerNoeud(Noeud noeud, Stagiaire stagiaire) {
        if (noeud == null) {
            return null;
        }

        if (stagiaire.getNom().compareTo(noeud.getStagiaire().getNom()) < 0) {
            noeud.setFilsGauche(supprimerNoeud(noeud.getFilsGauche(), stagiaire));
        } else if (stagiaire.getNom().compareTo(noeud.getStagiaire().getNom()) > 0) {
            noeud.setFilsDroit(supprimerNoeud(noeud.getFilsDroit(), stagiaire));
        } else {
            if (noeud.getFilsGauche() == null) {
                return noeud.getFilsDroit();
            } else if (noeud.getFilsDroit() == null) {
                return noeud.getFilsGauche();
            }

            Noeud suivant = minimumValueNode(noeud.getFilsDroit());
            noeud.setStagiaire(suivant.getStagiaire());
            noeud.setFilsDroit(supprimerNoeud(noeud.getFilsDroit(), suivant.getStagiaire()));
        }

        return noeud;
    }

    private Noeud minimumValueNode(Noeud noeud) {
        Noeud courant = noeud;

        while (courant.getFilsGauche() != null) {
            courant = courant.getFilsGauche();
        }

        return courant;
    }

    public void modifierStagiaire(Stagiaire ancienStagiaire, Stagiaire nouveauStagiaire) throws DoublonException { // !! A supprimer qd creation du try and catch  
        supprimerStagiaire(ancienStagiaire);
        ajouterStagiaire(nouveauStagiaire);
    }

 
    public Noeud getRacine() {
        return racine;
    }
}

