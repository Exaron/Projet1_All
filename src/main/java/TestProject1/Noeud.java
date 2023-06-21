package TestProject1;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Noeud extends TxtToData {
	private static final long TAILLE_NOEUD = Stagiaire.TAILLE_STAGIAIRE_OCTET + 3 * 4;
	private Stagiaire cle;
	private int filsGauche;
	private int filsDroit;
	private int suivant;
	// private RandomAccessFile raf;

	public Noeud(Stagiaire cle) {
		this.cle = cle;
		this.filsGauche = -1;
		this.filsDroit = -1;
		this.suivant = -1;
	}

	public Noeud(Stagiaire cle, int filsGauche, int filsDroit, int suivant) {
		this.cle = cle;
		this.filsGauche = filsGauche;
		this.filsDroit = filsDroit;
		this.suivant = suivant;
		// this.raf = null;
	}

	public Noeud() {

	}

	public Stagiaire getCle() {
		return cle;
	}

	public void setCle(Stagiaire cle) {
		this.cle = cle;
	}

	public int getFilsGauche() {
		return filsGauche;
	}

	public void setFilsGauche(int filsGauche) {
		this.filsGauche = filsGauche;
	}

	public int getFilsDroit() {
		return filsDroit;
	}

	public void setFilsDroit(int filsDroit) {
		this.filsDroit = filsDroit;
	}

	public int getSuivant() {
		return suivant;
	}

	public void setSuivant(int suivant) {
		this.suivant = suivant;
	}

	/*
	 * public String toString() {
	 * 
	 * String res = "";
	 * 
	 * if (filsGauche != null) { res = res + filsGauche.toString(); } res = res +
	 * this.cle.toString();
	 * 
	 * if (suivant !=null) { res = res + suivant.toString(); }
	 * 
	 * if (this.filsDroit != null) { res = res + filsDroit.toString(); }
	 * 
	 * return res; }
	 */

	public Noeud lireNoeud(RandomAccessFile raf) {
		Noeud noeud = null;
		try {

			// raf = new RandomAccessFile("arbre.bin", "rw");
//			if (raf.length() == 0) {
//
//			} else {
			// int index = (int) (raf.length() / TAILLE_NOEUD); // Permet d'obtenir l'index
			// actuel
			// raf.seek(index * TAILLE_NOEUD);
			// 5 Boucle for readchar

			String nom = "";
			for (int i = 0; i < Stagiaire.NOM_LONG; i++) {
				nom += raf.readChar();
			}
			String prenom = "";
			for (int i = 0; i < Stagiaire.PRENOM_LONG; i++) {
				prenom += raf.readChar();
			}
			String departement = "";
			for (int i = 0; i < Stagiaire.DEPARTEMENT_LONG; i++) {
				departement += raf.readChar();
			}
			String formation = "";
			for (int i = 0; i < Stagiaire.FORMATION_LONG; i++) {
				formation += raf.readChar();
			}
			String annee = "";
			for (int i = 0; i < Stagiaire.ANNEE_LONG; i++) {
				annee += raf.readChar();
			}

			Stagiaire stagiaire = new Stagiaire(nom.trim(), prenom.trim(), departement.trim(), formation.trim(),
					annee.trim());

			int filsGauche = raf.readInt();
			int filsDroit = raf.readInt();
			int suivant = raf.readInt();
			noeud = new Noeud(stagiaire, filsGauche, filsDroit, suivant); // Noeud noeud = new Noeud(cle);
			// }
			// raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return noeud;
	}

	public void ajouterStagiaire(Stagiaire newStagiaire, RandomAccessFile raf) throws IOException {
		if (this.cle.compareTo(newStagiaire) < 0) {
			if (this.filsDroit == -1) {
				this.filsDroit = (int) (raf.length() / Noeud.TAILLE_NOEUD);
				// mon pointeur est à la fin du noeud courant (this)
				// je remonte de 8 octets pour ecrire à l'index FD du noeud courant

				raf.seek(raf.getFilePointer() - 8);
				raf.writeInt(this.filsDroit);
				// j'ajoute le nouveau noeud dans le fichier
				raf.seek(raf.length());
				sauvegarderNoeud(newStagiaire, -1, -1, -1, raf); // Nouveau nœud en tant que fils droit
			} else {
				raf.seek(this.filsDroit * TAILLE_NOEUD);

				Noeud filsDroitNode = lireNoeud(raf);
				filsDroitNode.ajouterStagiaire(newStagiaire, raf); // Appel récursif sur le fils droit
			}
		} else if (this.cle.compareTo(newStagiaire) > 0) {
			if (this.filsGauche == -1) {
				// même chose que pour droite
				this.filsGauche = (int) (raf.length() / Noeud.TAILLE_NOEUD);
				// mais on recule le curseur de -12
				raf.seek(raf.getFilePointer() - 12);
				raf.writeInt(this.filsGauche);
				// j'ajoute le nouveau noeud dans le fichier
				raf.seek(raf.length());
				sauvegarderNoeud(newStagiaire, -1, -1, -1, raf);

			} else {
				raf.seek(this.filsGauche * TAILLE_NOEUD);
				Noeud filsGaucheNode = lireNoeud(raf);
				filsGaucheNode.ajouterStagiaire(newStagiaire, raf); // Appel récursif sur le fils gauche
			}
		} else {
			if (this.suivant == -1) {
				this.suivant = (int) (raf.length() / Noeud.TAILLE_NOEUD);
				// meme chose que pour droite
				// on recule le curseur de -4
				raf.seek(raf.getFilePointer() - 4);
				raf.writeInt(this.suivant);
				// j'ajoute le nouveau noeud dans le fichier
				raf.seek(raf.length());
				sauvegarderNoeud(newStagiaire, -1, -1, -1, raf);
			} else {
				raf.seek(this.suivant * TAILLE_NOEUD);
				Noeud suivantNode = lireNoeud(raf);
				suivantNode.ajouterStagiaire(newStagiaire, raf); // Appel récursif sur le suivant
			}
		}

	}

	public void sauvegarderNoeud(Stagiaire cle, int filsGauche, int filsDroit, int suivant, RandomAccessFile raf) {
		try {
			raf.seek(raf.length()); // Positionnement à la fin du fichier
			raf.writeChars(cle.getNom());
			raf.writeChars(cle.getPrenom());
			raf.writeChars(cle.getDepartement());
			raf.writeChars(cle.getFormation());
			raf.writeChars(cle.getAnnee());
			raf.writeInt(filsGauche);
			raf.writeInt(filsDroit);
			raf.writeInt(suivant);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void reecrire(Stagiaire cle, int filsGauche, int filsDroit, int suivant, RandomAccessFile raf) {
		try {
			raf.writeChars(cle.getNom());
			raf.writeChars(cle.getPrenom());
			raf.writeChars(cle.getDepartement());
			raf.writeChars(cle.getFormation());
			raf.writeChars(cle.getAnnee());
			raf.writeInt(filsGauche);
			raf.writeInt(filsDroit);
			raf.writeInt(suivant);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void rechercherPourSupprimer(String nom, String prenom, String formation, int index, int indexParent,
			RandomAccessFile raf) throws IOException {

		raf.seek(index * TAILLE_NOEUD);
		Noeud noeud1 = lireNoeud(raf);
		System.out.println(index + " index");
		System.out.println(indexParent + " indexParent");
		if (nom.compareTo(noeud1.getCle().getVraiNom()) == 0) {
			System.out.println("meme nom");
			if (prenom.compareTo(noeud1.getCle().getVraiPrenom()) == 0) {

				if (formation.equals(noeud1.getCle().getVraiFormation())) {
					System.out.println(noeud1);
					System.out.println("c'est le noeud à supprimer");
					noeud1.supprimer(index, indexParent, raf);
				} else {

					if (noeud1.getSuivant() >= 0) {

						this.rechercherPourSupprimer(nom, prenom, formation, (int) (noeud1.getSuivant()), index, raf);
						System.out.println("suivant");

					} else {
						System.out.println("pas trouvé");
					}
				}
			} else {
				if (noeud1.getSuivant() >= 0) {

					this.rechercherPourSupprimer(nom, prenom, formation, (int) (noeud1.getSuivant()), index, raf);
					System.out.println("suivant");

				} else {
					System.out.println("prenom pas trouvé");
				}
				
			}

		} else {

				if (nom.compareTo(noeud1.getCle().getVraiNom()) < 0) {

					if (noeud1.getFilsGauche() >= 0) {
						this.rechercherPourSupprimer(nom, prenom, formation, (int) (noeud1.getFilsGauche()), index,
								raf);
						System.out.println("gauche");
					}
				} else {

					if (noeud1.getFilsDroit() >= 0) {
						this.rechercherPourSupprimer(nom, prenom, formation, (int) (noeud1.getFilsDroit()), index, raf);
						System.out.println("droite");
					}
				}
		}
		
		
		
	}

	public void supprimer(int index, int indexParent, RandomAccessFile raf) throws IOException {

		// si il a un suivant
		if (this.getSuivant() != -1) {
			// le suisvant le remplace
			// on récupere le neoud suivant
			raf.seek(this.getSuivant() * TAILLE_NOEUD);
			Noeud suivant = lireNoeud(raf);
			// on se met sur le noeud à supprimer
			raf.seek(index * TAILLE_NOEUD);
			reecrire(suivant.cle, this.filsGauche, this.filsDroit, suivant.getSuivant(), raf);

		} else if (this.filsGauche == -1 && this.filsDroit == -1) {
			raf.seek(indexParent * TAILLE_NOEUD);
			Noeud noeudParent = this.lireNoeud(raf);
			// si c'est une feuille
			// c'est une feuille
			System.out.println("pas besoin remplacant");
			if (this.getCle().getVraiNom().compareTo(noeudParent.getCle().getVraiNom()) < 0) {
				// fils gauche
				raf.seek(indexParent * TAILLE_NOEUD);
				reecrire(noeudParent.getCle(), -1, noeudParent.getFilsDroit(), noeudParent.getSuivant(), raf);

			} else if (this.getCle().getVraiNom().compareTo(noeudParent.getCle().getVraiNom()) > 0) {
				// fils droit
				raf.seek(indexParent * TAILLE_NOEUD);
				reecrire(noeudParent.getCle(), noeudParent.getFilsGauche(), -1, noeudParent.getSuivant(), raf);

			}
		} else if (this.filsGauche == -1 || this.filsDroit == -1) {
			raf.seek(indexParent * TAILLE_NOEUD);
			Noeud noeudParent = this.lireNoeud(raf);
			// il a un seul fils {
			if (this.filsGauche != -1) {
				// il a un fils gauche
				if (this.getCle().getVraiNom().compareTo(noeudParent.getCle().getVraiNom()) < 0) {
					// fils gauche
					raf.seek(indexParent * TAILLE_NOEUD);
					reecrire(noeudParent.getCle(), this.filsGauche, noeudParent.getFilsDroit(),
							noeudParent.getSuivant(), raf);

				} else if (this.getCle().getVraiNom().compareTo(noeudParent.getCle().getVraiNom()) > 0) {
					// fils droit
					raf.seek(indexParent * TAILLE_NOEUD);
					reecrire(noeudParent.getCle(), noeudParent.getFilsGauche(), this.filsGauche,
							noeudParent.getSuivant(), raf);

				}
			} else {
				// il a un fils droit
				if (this.getCle().getVraiNom().compareTo(noeudParent.getCle().getVraiNom()) < 0) {
					// fils gauche
					raf.seek(indexParent * TAILLE_NOEUD);
					reecrire(noeudParent.getCle(), this.filsDroit, noeudParent.getFilsDroit(), noeudParent.getSuivant(),
							raf);

				} else if (this.getCle().getVraiNom().compareTo(noeudParent.getCle().getVraiNom()) > 0) {
					// fils droit
					raf.seek(indexParent * TAILLE_NOEUD);
					reecrire(noeudParent.getCle(), noeudParent.getFilsGauche(), this.filsDroit,
							noeudParent.getSuivant(), raf);

				}
			}
		} else {
			// il a un fils droit et un fils gauche
			// on va chercher le remplacant cad le plus petit du sous arbre droit
			raf.seek(this.filsDroit * TAILLE_NOEUD);
			Noeud droit = lireNoeud(raf);
			Noeud remplacant = droit.chercherRemplacant(raf);
			raf.seek(index * TAILLE_NOEUD);
			reecrire(remplacant.cle, this.filsGauche, this.filsDroit, remplacant.suivant, raf);

			// ensuite on suppriome le remplacant du sous arbre droit
			droit.rechercherPourSupprimer(remplacant.cle.getVraiNom(), remplacant.cle.getVraiPrenom(), remplacant.cle.getVraiFormation(),
					this.filsDroit, index, raf);

		}

	}

	public Noeud chercherRemplacant(RandomAccessFile raf) throws IOException {
		// Noeud remplacant = null;
		if (this.filsGauche == -1) {
			return this;
		} else {
			raf.seek(this.filsGauche * TAILLE_NOEUD);
			Noeud gauche = lireNoeud(raf);
			return gauche.chercherRemplacant(raf);
		}

		// return remplacant;
	}
}
