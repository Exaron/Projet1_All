package TestProject1;
public class Stagiaire {
	
	private String nom;
	private String prenom;
	private String departement;
	private String formation;
	private String annee;
	
	public Stagiaire(String nom, String prenom, String departement, String formation, String annee) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.departement = departement;
		this.formation = formation;
		this.annee = annee;
	}
	
	public static final int NOM_LONG = 15;
	public static final int PRENOM_LONG = 15;
	public static final int DEPARTEMENT_LONG = 3;
	public static final int FORMATION_LONG = 10;
	public static final int ANNEE_LONG = 4;
	public static final int TAILLE_STAGIAIRE_OCTET = (NOM_LONG+PRENOM_LONG + DEPARTEMENT_LONG + FORMATION_LONG+ANNEE_LONG) * 2;
	
	//constante taille totale du stagiaire en octet (somme des attributs string *2)
	public Stagiaire() {
		
	}
	/*public String getNom() {
		return nom;
	}*/
	public String getNom() {
		StringBuilder sb = new StringBuilder(nom);
		if (nom.length()<NOM_LONG) {
			while (sb.length()<NOM_LONG) {
				sb.append(' ');
			}
			return sb.toString();
		}else if (nom.length()>NOM_LONG) {
			return nom.substring(0, NOM_LONG).toUpperCase();
		}
		return sb.toString();
	}
	public void setNom(String nom) {
		this.nom = nom.toUpperCase();
	}
	public String getVraiNom() {
		return this.nom.toUpperCase();
	}
	
	public String getVraiPrenom() {
		return this.prenom;
	}
	
	public String getVraiFormation() {
		return this.formation;
	}
	
	//public String nomString(){
		//return "nom";
	//}
	public String getPrenom() {
		StringBuilder sb = new StringBuilder(prenom);
		if (prenom.length()<PRENOM_LONG) {
			while (sb.length()<PRENOM_LONG) {
				sb.append(' ');
			}
			return sb.toString();
		}else if (prenom.length()>PRENOM_LONG) {
			 prenom =prenom.substring(0, PRENOM_LONG);
			 return prenom.substring(0,1).toUpperCase()+prenom.substring(1);
		}
		return sb.toString();
	}
	
	public void setPrenom(String prenom) {
		this.prenom = prenom.substring(0,1).toUpperCase()+prenom.substring(1);
	}
	public String getDepartement() {
		StringBuilder sb = new StringBuilder(departement);
		if (departement.length()<DEPARTEMENT_LONG) {
			while (sb.length()<DEPARTEMENT_LONG) {
				sb.append(' ');
			}
			return sb.toString();
		}else if (departement.length()>DEPARTEMENT_LONG) {
			return departement.substring(0, DEPARTEMENT_LONG);
		}
		return sb.toString();
	}
	public void setDepartement(String departement) {
		this.departement = departement;
	}
	public String getFormation() {
		StringBuilder sb = new StringBuilder(formation);
		if (formation.length()<FORMATION_LONG) {
			while (sb.length()<FORMATION_LONG) {
				sb.append(' ');
			}
			return sb.toString();
		}else if (formation.length()>FORMATION_LONG) {
			return formation.substring(0, FORMATION_LONG);
		}
		return sb.toString();
	}
	public void setFormation(String formation) {
		this.formation = formation;
	}
	public String getAnnee() {
		StringBuilder sb = new StringBuilder(annee);
		if (annee.length()<ANNEE_LONG) {
			while (sb.length()<ANNEE_LONG) {
				sb.append(' ');
			}
			return sb.toString();
		}else if (annee.length()>ANNEE_LONG) {
			return annee.substring(0, ANNEE_LONG);
		}
		return sb.toString();
	}
	public void setAnnee(String annee) {
		this.annee = annee;
	}
	@Override
	public String toString() {
		return nom + " "+ prenom+ " "+ departement + " "+ formation + " "+ annee+ " | ";
	}
	
	public int compareTo(Stagiaire stag) {
		return this.nom.compareTo(stag.nom);
		}
	
}