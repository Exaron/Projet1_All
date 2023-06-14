package fr.isika.CDA25.fx;

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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Labeled;
import javafx.scene.control.Toggle;
import java.io.File;

import fr.isika.CDA25.fx.Stagiaire;
import javafx.stage.FileChooser;

public class NonLogue extends GridPane {

	private Button login;
	private TableView<Stagiaire> table;

	public NonLogue() {
		super();

		Label recherche = new Label("Rechercher par :");
		ChoiceBox<String> filtre = new ChoiceBox<>();
		filtre.getItems().addAll("Filtre", "Nom", "Prénom", "Département", "Promotion", "Année");
		filtre.getSelectionModel().select(0);
		TextField txtRecherche = new TextField();
		this.table = new TableView<Stagiaire>();
		table.setEditable(true);
		StackPane tableau = new StackPane();
		tableau.getChildren().add(table);
		this.login = new Button("Log in");

		this.add(login, 4, 0);
		this.add(recherche, 1, 1);
		this.add(filtre, 2, 1);
		this.add(txtRecherche, 3, 1);
		this.add(tableau, 0, 2, 5, 6);

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

	public Button getLogin() {
		return login;
	}
	
	

}