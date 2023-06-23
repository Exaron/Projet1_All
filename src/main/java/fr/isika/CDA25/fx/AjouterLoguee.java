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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Labeled;
import javafx.scene.control.Toggle;
import java.io.File;
import javafx.stage.FileChooser;

public class AjouterLoguee extends StackPane {

	public StackPane myStack = new StackPane();
	public VBox myVB = new VBox();
	public Label labelTitre = new Label("Veuillez saisir les données");
	public GridPane myGridPane = new GridPane();
	public Label labelNom = new Label("Nom :");
	public TextField txtNom = new TextField();
	public Label labelPrenom = new Label("Prénom :");
	public TextField txtPrenom = new TextField();
	public Label labelDepart = new Label("Département :");
	public TextField txtDepart = new TextField();
	public Label labelProm = new Label("Promotion :");
	public TextField txtProm = new TextField();
	public Label labelAnnee = new Label("Année :");
	public TextField txtAnnee = new TextField();
	public Button validerAjoutL = new Button("Valider");
	public Button retourAjoutLogue = new Button ("Retour");
	private Label erreur;

	public AjouterLoguee() {
		super();

		this.myStack = new StackPane();
		this.myVB = new VBox(5);
		this.labelTitre = new Label("Veuillez saisir les données");
		this.myGridPane = new GridPane();
		this.labelNom = new Label("Nom :");
		this.txtNom = new TextField();
		this.labelPrenom = new Label("Prénom :");
		this.txtPrenom = new TextField();
		this.labelDepart = new Label("Département :");
		this.txtDepart = new TextField();
		this.labelProm = new Label("Promotion :");
		this.txtProm = new TextField();
		this.labelAnnee = new Label("Année :");
		this.txtAnnee = new TextField();
		this.validerAjoutL = new Button("Valider");
		this.retourAjoutLogue = new Button("Retour");
		this.erreur = new Label("");

		this.getChildren().add(myVB);

		myVB.getChildren().add(labelTitre);
		myVB.getChildren().add(myGridPane);
		myVB.getChildren().add(validerAjoutL);
		myVB.getChildren().add(retourAjoutLogue);
		myVB.getChildren().add(erreur);
		myVB.setAlignment(Pos.CENTER);
		myVB.setStyle("-fx-background-color:lightgrey");

		myGridPane.add(labelNom, 0, 0);
		myGridPane.add(txtNom, 1, 0);
		myGridPane.add(labelPrenom, 0, 1);
		myGridPane.add(txtPrenom, 1, 1);
		myGridPane.add(labelDepart, 0, 2);
		myGridPane.add(txtDepart, 1, 2);
		myGridPane.add(labelProm, 0, 3);
		myGridPane.add(txtProm, 1, 3);
		myGridPane.add(labelAnnee, 0, 4);
		myGridPane.add(txtAnnee, 1, 4);
		

		myGridPane.setAlignment(Pos.CENTER);
		myGridPane.setStyle("-fx-background-color:lightgrey");
		myGridPane.setPadding(new Insets(10, 10, 10, 10));
		myGridPane.setVgap(10);
		myGridPane.setHgap(10);
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

	public Label getLabelNom() {
		return labelNom;
	}

	public TextField getTxtNom() {
		return txtNom;
	}

	public Label getLabelPrenom() {
		return labelPrenom;
	}

	public TextField getTxtPrenom() {
		return txtPrenom;
	}

	public Label getLabelDepart() {
		return labelDepart;
	}

	public TextField getTxtDepart() {
		return txtDepart;
	}

	public Label getLabelProm() {
		return labelProm;
	}

	public TextField getTxtProm() {
		return txtProm;
	}

	public Label getLabelAnnee() {
		return labelAnnee;
	}

	public TextField getTxtAnnee() {
		return txtAnnee;
	}

	public Button getValiderAjoutL() {
		return validerAjoutL;
	}
	public Button getRetourAjoutLogue() {
		return retourAjoutLogue;
	}
	public Label getErreur() {
		return erreur;
	}

}