package fr.isika.CDA25.fx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import fr.isika.CDA25.Projet1_All.Stagiaire;

public class Logue extends GridPane {
	private Button logOut;
	private Button modif;
	private Button suprim;
	private TextField txtRecherche;
	private ChoiceBox<String> filtre;
	private TableView<Stagiaire> table;

	public Logue() {
		super();

		Label recherche = new Label("Rechercher par :");
		this.filtre = new ChoiceBox<>();
		filtre.getItems().addAll("Filtre", "Nom", "Prénom", "Département", "Promotion", "Année");
		filtre.getSelectionModel().select(0);
		this.txtRecherche = new TextField();
		this.table = new TableView<Stagiaire>();
		table.setEditable(true);
		StackPane tableau = new StackPane();
		tableau.getChildren().add(table);
		this.logOut = new Button("Log out");
		this.modif = new Button("Modifier");
		this.suprim = new Button("Supprimer");

		this.add(logOut, 4, 0);
		this.add(recherche, 1, 1);
		this.add(filtre, 2, 1);
		this.add(txtRecherche, 3, 1);
		this.add(modif, 3, 2);
		this.add(suprim, 4, 2);
		this.add(tableau, 0, 3, 5, 6);

		this.setAlignment(Pos.CENTER);
		this.setStyle("-fx-background-color:lightgrey");
		this.setPadding(new Insets(10, 10, 10, 10));
		this.setVgap(10);
		this.setHgap(10);

		TableColumn<Stagiaire, String> nomCol = new TableColumn<Stagiaire, String>("Nom");
		nomCol.setMinWidth(50);
//		nomCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("Nom"));
		TableColumn<Stagiaire, String> prenomCol = new TableColumn<Stagiaire, String>("Prénom");
		prenomCol.setMinWidth(50);
		TableColumn<Stagiaire, String> departCol = new TableColumn<Stagiaire, String>("Département");
		departCol.setMinWidth(50);
		TableColumn<Stagiaire, String> promoCol = new TableColumn<Stagiaire, String>("Promotion");
		promoCol.setMinWidth(50);
		TableColumn<Stagiaire, String> anneeCol = new TableColumn<Stagiaire, String>("Année");
		anneeCol.setMinWidth(50);

		table.getColumns().addAll(nomCol, prenomCol, departCol, promoCol, anneeCol);

	}

	public Button getLogOut() {
		return logOut;
	}

	public Button getModif() {
		return modif;
	}

	public Button getSuprim() {
		return suprim;
	}

	public TextField getTxtRecherche() {
		return txtRecherche;
	}

	public ChoiceBox<String> getFiltre() {
		return filtre;
	}

}
