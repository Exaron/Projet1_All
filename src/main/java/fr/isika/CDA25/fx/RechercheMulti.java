package fr.isika.CDA25.fx;

import javafx.application.Application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Labeled;
import javafx.scene.control.Toggle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import TestProject1.Arbre;
import TestProject1.Stagiaire;
import javafx.stage.FileChooser;

public class RechercheMulti extends StackPane {
	public StackPane myStack = new StackPane();
	public VBox myVB = new VBox();
	public Label labelTitre = new Label("Veuillez saisir les données");
	public GridPane myGridPane = new GridPane();
//	public Label labelNom = new Label("Nom :");
	private ChoiceBox<String> filtre1;
	public TextField txt1 = new TextField();
	private ChoiceBox<String> filtre2;
//	public Label labelPrenom = new Label("Prénom :");
	public TextField txt2 = new TextField();
	private ChoiceBox<String> filtre3;
//	public Label labelDepart = new Label("Département :");
	public TextField txt3 = new TextField();
	private ChoiceBox<String> filtre4;
//	public Label labelProm = new Label("Promotion :");
	public TextField txt4 = new TextField();
	private ChoiceBox<String> filtre5;
//	public Label labelAnnee = new Label("Année :");
	public TextField txt5 = new TextField();
	public Button valider = new Button("Valider");
	public Button retour = new Button("Retour");
	private Label erreur = new Label("");

	private Logue logue = new Logue();
	private Arbre annuaire = new Arbre();
	ArrayList<Stagiaire> resultatRecherche = new ArrayList<>();

	public RechercheMulti() throws IOException {
		super();
		
		
		this.myStack = new StackPane();
		this.myVB = new VBox();
		this.labelTitre = new Label("");
		this.myGridPane = new GridPane();
		this.filtre1 = new ChoiceBox<>();
		this.txt1 = new TextField();
		this.filtre2 = new ChoiceBox<>();
//		this.labelPrenom = new Label("Prénom :");
		this.txt2 = new TextField();
		this.filtre3 = new ChoiceBox<>();
//		this.labelDepart = new Label("Département :");
		this.txt3 = new TextField();
		this.filtre4 = new ChoiceBox<>();
//		this.labelProm = new Label("Promotion :");
		this.txt4 = new TextField();
		this.filtre5 = new ChoiceBox<>();
//		this.labelAnnee = new Label("Année :");
		this.txt5 = new TextField();
		this.valider = new Button("Valider");
		this.retour = new Button("Retour");
		this.erreur = new Label("");

		filtre1.getItems().addAll("Filtre", "Nom", "Prénom", "Département", "Promotion", "Année");
		filtre1.getSelectionModel().select(0);
		filtre2.getItems().addAll("Filtre", "Nom", "Prénom", "Département", "Promotion", "Année");
		filtre2.getSelectionModel().select(0);
		filtre3.getItems().addAll("Filtre", "Nom", "Prénom", "Département", "Promotion", "Année");
		filtre3.getSelectionModel().select(0);
		filtre4.getItems().addAll("Filtre", "Nom", "Prénom", "Département", "Promotion", "Année");
		filtre4.getSelectionModel().select(0);
		filtre5.getItems().addAll("Filtre", "Nom", "Prénom", "Département", "Promotion", "Année");
		filtre5.getSelectionModel().select(0);

		filtre1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> recherche());
		txt1.textProperty().addListener((observable, oldValue, newValue) -> recherche());
		filtre2.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> recherche());
		txt2.textProperty().addListener((observable, oldValue, newValue) -> recherche());
		filtre3.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> recherche());
		txt3.textProperty().addListener((observable, oldValue, newValue) -> recherche());
		filtre4.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> recherche());
		txt4.textProperty().addListener((observable, oldValue, newValue) -> recherche());
		filtre5.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> recherche());
		txt5.textProperty().addListener((observable, oldValue, newValue) -> recherche());

		this.getChildren().add(myVB);
		myVB.getChildren().add(labelTitre);
		myVB.getChildren().add(myGridPane);
		myVB.getChildren().add(valider);
		myVB.getChildren().add(retour);
		myVB.getChildren().add(erreur);
		myVB.setAlignment(Pos.CENTER);
		myVB.setStyle("-fx-background-color:lightgrey");
		myGridPane.add(filtre1, 0, 0);
		myGridPane.add(txt1, 1, 0);
		myGridPane.add(filtre2, 0, 1);
		myGridPane.add(txt2, 1, 1);
		myGridPane.add(filtre3, 0, 2);
		myGridPane.add(txt3, 1, 2);
		myGridPane.add(filtre4, 0, 3);
		myGridPane.add(txt4, 1, 3);
		myGridPane.add(filtre5, 0, 4);
		myGridPane.add(txt5, 1, 4);
		myGridPane.setAlignment(Pos.CENTER);
		myGridPane.setStyle("-fx-background-color:lightgrey");
		myGridPane.setPadding(new Insets(10, 10, 10, 10));
		myGridPane.setVgap(10);
		myGridPane.setHgap(10);

	}

	public ArrayList<Stagiaire> rechercher(String critere, String recherche, ArrayList<Stagiaire> list) {
		// Parcourir la liste des stagiaires pour trouver les correspondances
		
		ArrayList<Stagiaire> resultatRechercher = new ArrayList<>();
		for (Stagiaire stagiaire : list) {
			if (critere.equals("Nom") && stagiaire.getVraiNom().trim().equalsIgnoreCase(recherche)) {
				resultatRechercher.add(stagiaire);

			} else if (critere.equals("Prénom") && stagiaire.getPrenom().trim().equalsIgnoreCase(recherche)) {
				resultatRechercher.add(stagiaire);

			} else if (critere.equals("Département") && stagiaire.getDepartement().trim().equalsIgnoreCase(recherche)) {
				resultatRechercher.add(stagiaire);

			} else if (critere.equals("Promotion") && stagiaire.getFormation().trim().equalsIgnoreCase(recherche)) {
				resultatRechercher.add(stagiaire);

			} else if (critere.equals("Année") && stagiaire.getAnnee().trim().equalsIgnoreCase(recherche)) {
				resultatRechercher.add(stagiaire);
			}
		}
		if (resultatRechercher != null) {
			return resultatRechercher;
		} else {
			return list;
		}
	}

	public int recherche() {
		String critere1 = filtre1.getValue();
		String recherche1 = txt1.getText().toUpperCase();
		String critere2 = filtre2.getValue();
		String recherche2 = txt2.getText();
		String critere3 = filtre3.getValue();
		String recherche3 = txt3.getText();
		String critere4 = filtre4.getValue();
		String recherche4 = txt4.getText();
		String critere5 = filtre5.getValue();
		String recherche5 = txt5.getText();

		// Réinitialiser la liste des stagiaires affichés dans la table

		// Vérifier si un critère de recherche et une valeur ont été sélectionnés

		if (critere1 != null && !recherche1.isEmpty()) {
			try {
				annuaire.lister(0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logue.getTable().getItems().clear();
			ArrayList<Stagiaire> resultatRecherche1 = rechercher(critere1, recherche1, annuaire.getListeTrie());
			resultatRecherche = resultatRecherche1;
			if (critere2 != null && !recherche2.isEmpty()) {
				ArrayList<Stagiaire> resultatRecherche2 = rechercher(critere2, recherche2, resultatRecherche);
				resultatRecherche = resultatRecherche2;
				if (critere3 != null && !recherche3.isEmpty()) {
					ArrayList<Stagiaire> resultatRecherche3 = rechercher(critere3, recherche3, resultatRecherche);
					resultatRecherche = resultatRecherche3;
					if (critere4 != null && !recherche4.isEmpty()) {
						ArrayList<Stagiaire> resultatRecherche4 = rechercher(critere4, recherche4, resultatRecherche);
						resultatRecherche = resultatRecherche4;
						if (critere5 != null && !recherche5.isEmpty()) {
							ArrayList<Stagiaire> resultatRecherche5 = rechercher(critere5, recherche5,
									resultatRecherche);
							resultatRecherche = resultatRecherche5;

						}
					}
				}
			}
			return 1;
		}else {
			return -1;
		}
	}

	public void setRetour(Button retour) {
		this.retour = retour;
	}

	public StackPane getMyStack() {
		return myStack;
	}

	public VBox getMyVB() {
		return myVB;
	}

	public Label getLabelTitre() {
		return labelTitre;
	}

	public GridPane getMyGridPane() {
		return myGridPane;
	}

	public TextField getTxt1() {
		return txt1;
	}

	public void setTxt1(TextField txt1) {
		this.txt1 = txt1;
	}

	public TextField getTxt2() {
		return txt2;
	}

	public void setTxt2(TextField txt2) {
		this.txt2 = txt2;
	}

	public TextField getTxt3() {
		return txt3;
	}

	public void setTxt3(TextField txt3) {
		this.txt3 = txt3;
	}

	public TextField getTxt4() {
		return txt4;
	}

	public void setTxt4(TextField txt4) {
		this.txt4 = txt4;
	}

	public TextField getTxt5() {
		return txt5;
	}

	public void setTxt5(TextField txt5) {
		this.txt5 = txt5;
	}

	public Button getValider() {
		return valider;
	}

	public Button getRetour() {
		return retour;
	}

	public Label getErreur() {
		return erreur;
	}

	public ArrayList<Stagiaire> getResultatRecherche() {
		return resultatRecherche;
	}

	public void setResultatRecherche(ArrayList<Stagiaire> resultatRecherche) {
		this.resultatRecherche = resultatRecherche;
	}

}
