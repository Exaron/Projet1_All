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
import javafx.scene.control.PasswordField;
import javafx.scene.control.Toggle;
import java.io.File;
import javafx.stage.FileChooser;

public class LogIn extends GridPane {

	private Button valider;
	private TextField txtUtilisateur;
	private PasswordField txtMotDePasse;
	private Label labelUtilisateur;
	private Label labelMotDePasse;
	private Button retour;
	private Label erreur;

	public LogIn() {
		super();
		this.labelUtilisateur = new Label("Utilisateur :");
		this.txtUtilisateur = new TextField();
		this.labelMotDePasse = new Label("Mot de passe :");
		this. txtMotDePasse = new PasswordField();
		this.valider = new Button("Valider");
		this.retour = new Button("Retour");
		this.erreur = new Label("");

		this.add(erreur, 0, 0, 2, 1);
		this.add(labelUtilisateur, 0, 1);
		this.add(txtUtilisateur, 1, 1);
		this.add(labelMotDePasse, 0, 2);
		this.add(txtMotDePasse, 1, 2);
		this.add(valider, 1, 3);
		this.add(retour,1, 4);

		this.setAlignment(Pos.CENTER);
		this.setStyle("-fx-background-color:lightgrey");
		this.setPadding(new Insets(10, 10, 10, 10));
		this.setVgap(10);
		this.setHgap(10);
	}

	public Button getValider() {
		return valider;
	}

	public TextField getTxtUtilisateur() {
		return txtUtilisateur;
	}

	public PasswordField getTxtMotDePasse() {
		return txtMotDePasse;
	}

	public Label getLabelUtilisateur() {
		return labelUtilisateur;
	}

	public Label getLabelMotDePasse() {
		return labelMotDePasse;
	}
	
	public Button getRetour() {
		return retour;
	}

	public Label getErreur() {
		return erreur;
	}

}
