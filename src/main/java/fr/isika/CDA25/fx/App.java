package fr.isika.CDA25.fx;

import TestProject1.*;
import TestProject1.TxtToData;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import fr.isika.CDA25.fx.LogIn;
import fr.isika.CDA25.fx.Logue;
import fr.isika.CDA25.fx.Modif;
import fr.isika.CDA25.fx.NonLogue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterAttributes;
import javafx.print.PrinterJob;
import javafx.scene.paint.Color;
import TestProject1.Stagiaire;

/**
 * JavaFX App
 */
public class App extends Application {

	public LogIn logIn;
	public NonLogue nonLogue;
	public Logue logue;
	public Modif modif;
	public AjouterNonLogue ajoutNonLogue;
	public AjouterLoguee ajoutLogue;
	public RechercheMulti multicritere;
	public Scene scene;
	public int critere = 0;

	Arbre annuaire = new Arbre();
	ArrayList<Stagiaire> listeStagiaires = annuaire.getListeTrie();

	public App() {

	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	@Override
	public void start(Stage stage) throws IOException {

		nonLogue = new NonLogue();
		logIn = new LogIn();
		logue = new Logue();
		modif = new Modif();
		ajoutNonLogue = new AjouterNonLogue();
		ajoutLogue = new AjouterLoguee();
		multicritere = new RechercheMulti();

		Scene scene = new Scene(nonLogue, 640, 480);

		nonLogue.getLogin().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					annuaire.refresh();
					annuaire.lister(0);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				logue.setStagiaires(annuaire.getListeTrie());
				logue.getTable().setItems(FXCollections.observableArrayList(annuaire.getListeTrie()));
				scene.setRoot(logIn);
			}

		});

		logIn.getValider().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if ((logIn.getTxtUtilisateur().getText().equals("isika"))
						&& (logIn.getTxtMotDePasse().getText().equals("cda25"))) {
					scene.setRoot(logue);
					logIn.getTxtUtilisateur().setText("");
					logIn.getTxtMotDePasse().setText("");

				} else {
					logIn.getLabelUtilisateur().setTextFill(Color.RED);
					logIn.getLabelMotDePasse().setTextFill(Color.RED);
					logIn.getErreur().setText("L'utilisateur ou le mot de passe est incorrect");
				}

			}

		});

		logIn.getRetour().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				scene.setRoot(nonLogue);
			}
		});

		logue.getLogOut().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					annuaire.refresh();
					annuaire.lister(0);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				nonLogue.setStagiaires(annuaire.getListeTrie());
				nonLogue.getTable().setItems(FXCollections.observableArrayList(annuaire.getListeTrie()));
				scene.setRoot(nonLogue);
			}

		});

		logue.getModif().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				scene.setRoot(modif);
			}

		});
		logue.getAjouter().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				scene.setRoot(ajoutLogue);

			}

		});

		logue.getRechercheMulti().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				logue.setStagiaires(annuaire.getListeTrie());
				logue.getTable().setItems(FXCollections.observableArrayList(annuaire.getListeTrie()));
				scene.setRoot(multicritere);
				critere = 1;
			}

		});

		multicritere.getValider().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				if (multicritere.recherche() > 0) {
					// Mettre à jour la table avec les résultats de recherche
					if (critere > 0) {
						logue.getTable()
								.setItems(FXCollections.observableArrayList(multicritere.getResultatRecherche()));
					} else {
						nonLogue.getTable()
								.setItems(FXCollections.observableArrayList(multicritere.getResultatRecherche()));
					}
				} else {
					// Aucun critère de recherche ou valeur sélectionnés, afficher tous les
					// stagiaires
					try {
						annuaire.refresh();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						annuaire.lister(0);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					logue.getTable().setItems(FXCollections.observableArrayList(annuaire.getListeTrie()));
				}
				if (critere > 0) {
					scene.setRoot(logue);
				} else {
					scene.setRoot(nonLogue);
				}

			}

		});

		multicritere.getRetour().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (critere > 0) {
					scene.setRoot(logue);
					try {
						annuaire.refresh();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						annuaire.lister(0);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					logue.getTable().setItems(FXCollections.observableArrayList(annuaire.getListeTrie()));
				} else {
					scene.setRoot(nonLogue);

					try {
						annuaire.refresh();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						annuaire.lister(0);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					nonLogue.getTable().setItems(FXCollections.observableArrayList(annuaire.getListeTrie()));
				}

			}

		});

		// Ajout depuis la page Non-Logue
		nonLogue.getAjouter().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				scene.setRoot(ajoutNonLogue);
			}

		});

		nonLogue.getRechercheMulti().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				scene.setRoot(multicritere);
				critere = -1;
				nonLogue.setStagiaires(annuaire.getListeTrie());
				nonLogue.getTable().setItems(FXCollections.observableArrayList(annuaire.getListeTrie()));
			}

		});

		// Retour de ajout à Logue
		ajoutLogue.getRetourAjoutLogue().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				scene.setRoot(logue);
			}
		});
		// Retour de ajout à Non-Logue
		ajoutNonLogue.getRetourAjoutNonLogue().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				scene.setRoot(nonLogue);
			}
		});

		ajoutLogue.getValiderAjoutL().setOnAction(new EventHandler<ActionEvent>() {
			Arbre annuaire = new Arbre();
			ArrayList<Stagiaire> listeStagiaires = annuaire.getListeTrie();

			@Override
			public void handle(ActionEvent event) {

				String nom = ajoutLogue.txtNom.getText().toUpperCase();
				String prenom = ajoutLogue.txtPrenom.getText();
				prenom = prenom.substring(0, 1).toUpperCase() + prenom.substring(1);
				String department = ajoutLogue.txtDepart.getText();
				String formation = ajoutLogue.txtProm.getText();
				String annee = ajoutLogue.txtAnnee.getText();
				try {
					if (!nom.equals("") && !prenom.equals("") && !department.equals("") && !formation.equals("")
							&& !annee.equals("")) {
						annuaire.ajouter(nom, prenom, department, formation, annee);
						System.out.println(department + "departement");
						annuaire.refresh();
						annuaire.lister(0);
						logue.getTable().setItems(FXCollections.observableArrayList(annuaire.getListeTrie()));
						logue.setStagiaires(annuaire.getListeTrie());
						scene.setRoot(logue);
					} else {
						ajoutLogue.getErreur().setTextFill(Color.RED);
						ajoutLogue.getErreur().setText("Toutes les cases doivent être remplies");
					}
					logue.setStagiaires(annuaire.getListeTrie());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		// Validation de l'ajout depuis la page Non-Logue
		ajoutNonLogue.getValiderAjoutNL().setOnAction(new EventHandler<ActionEvent>() {
			Arbre annuaire = new Arbre();
			ArrayList<Stagiaire> listeStagiaires = annuaire.getListeTrie();

			@Override
			public void handle(ActionEvent event) {

				String nom = ajoutNonLogue.txtNom.getText().toUpperCase();
				String prenom = ajoutNonLogue.txtPrenom.getText();
				prenom = prenom.substring(0, 1).toUpperCase() + prenom.substring(1);
				String department = ajoutNonLogue.txtDepart.getText();
				System.out.println(department + "departement");
				String formation = ajoutNonLogue.txtProm.getText();
				String annee = ajoutNonLogue.txtAnnee.getText();

				try {
					if (!nom.equals("") && !prenom.equals("") && !department.equals("") && !formation.equals("")
							&& !annee.equals("")) {
						annuaire.ajouter(nom, prenom, department, formation, annee);
						annuaire.refresh();
						annuaire.lister(0);
						nonLogue.setStagiaires(annuaire.getListeTrie());
						nonLogue.getTable().setItems(FXCollections.observableArrayList(annuaire.getListeTrie()));
						scene.setRoot(nonLogue);
					} else {
						ajoutNonLogue.getErreur().setTextFill(Color.RED);
						ajoutNonLogue.getErreur().setText("Toutes les cases doivent être remplies");
					}
					nonLogue.setStagiaires(annuaire.getListeTrie());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		logue.getSuprim().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				StringBuffer sb = new StringBuffer();
				System.out.println("suppression");

				try {
					Stagiaire stagiaire = logue.getSelectedStagiaire();
					annuaire.supprimer(stagiaire.getVraiNom(), stagiaire.getVraiPrenom(), stagiaire.getVraiFormation());
					annuaire.refresh();
					logue.setStagiaires(annuaire.getListeTrie());
					logue.getTable().setItems(FXCollections.observableArrayList(annuaire.getListeTrie()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});

		logue.getModif().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				StringBuffer sb = new StringBuffer();
				Stagiaire stagiaire = logue.getSelectedStagiaire();
				if (stagiaire != null) {
					modif.getTxtNom().setText(stagiaire.getVraiNom());
					modif.getTxtPrenom().setText(stagiaire.getVraiPrenom());
					modif.getTxtDepart().setText(stagiaire.getDepartement());
					modif.getTxtProm().setText(stagiaire.getVraiFormation());
					modif.getTxtAnnee().setText(stagiaire.getAnnee());
					scene.setRoot(modif);
				} else {
					logue.getErreur().setTextFill(Color.RED);
					logue.getErreur().setText("Veuillez choisir un stagiaire");
				}
			}
		});
		modif.getValider().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				StringBuffer sb = new StringBuffer();
				System.out.println("modification");
				String nomModif = modif.getTxtNom().getText().toUpperCase();
				String prenomModif = modif.getTxtPrenom().getText();
				prenomModif = prenomModif.substring(0, 1).toUpperCase() + prenomModif.substring(1);
				String departModif = modif.getTxtDepart().getText();
				String promModif = modif.getTxtProm().getText();
				String anneeModif = modif.getTxtAnnee().getText();
				try {
					Stagiaire stagiaire = logue.getSelectedStagiaire();

					System.out.println("stagiaire a supp " + stagiaire);
					if (!nomModif.equals("") && !prenomModif.equals("") && !departModif.equals("")
							&& !promModif.equals("") && !anneeModif.equals("")) {
						annuaire.modifier(stagiaire.getVraiNom(), stagiaire.getVraiPrenom(),
								stagiaire.getVraiFormation(), nomModif, prenomModif, departModif, promModif,
								anneeModif);
						annuaire.refresh();
						annuaire.lister(0);
						logue.setStagiaires(annuaire.getListeTrie());
						logue.getTable().setItems(FXCollections.observableArrayList(annuaire.getListeTrie()));
						scene.setRoot(logue);

					} else {
						modif.getErreur().setTextFill(Color.RED);
						modif.getErreur().setText("Toutes les cases doivent être remplies");
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		modif.getRetour().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				scene.setRoot(logue);
			}
		});

		logue.getImprimer().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				logue.imprimerTable(annuaire.getListeTrie());
				scene.setRoot(logue);
			}
		});

		stage.setTitle("Annuaire Isika");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}

}