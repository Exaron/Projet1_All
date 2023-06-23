package fr.isika.CDA25.fx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.collections.FXCollections;
import java.io.IOException;
import java.util.ArrayList;

import TestProject1.Arbre;
import TestProject1.Stagiaire;

public class NonLogue extends GridPane {

    private Button login;
    private Button ajouter;
    private TextField txtRecherche;
    private ChoiceBox<String> filtre;
    private Button rechercheMulti;
   
    private TableView<Stagiaire> table;
    public Arbre annuaire = new Arbre();

    public NonLogue() throws IOException {
        super();

        if (annuaire.getRaf().length() == 0) {
            System.out.println("fichier vide création");
            annuaire.ajout();
            annuaire.lister(0);
        } else {
            System.out.println("arbre existe deja");
            annuaire.lister(0);
        }

        Label recherche = new Label("Rechercher par :");
        this.filtre = new ChoiceBox<>();
        //filtre = new ChoiceBox<>();
        filtre.getItems().addAll("Filtre", "Nom", "Prénom", "Département", "Promotion", "Année");
        filtre.getSelectionModel().select(0);
        
        this.txtRecherche = new TextField();
        this.table = new TableView<Stagiaire>();
        this.table.setEditable(true);
        StackPane tableau = new StackPane();
        tableau.getChildren().add(table);
        this.login = new Button("Log in");
        this.ajouter = new Button ("Ajouter");
        this.rechercheMulti = new Button("Recherche multicritère");
       


        filtre.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> rechercher());
        txtRecherche.textProperty().addListener((observable, oldValue, newValue) -> rechercher());

        this.add(login, 4, 0);
        this.add(ajouter, 4, 1);
        this.add(recherche, 1, 1);
        this.add(rechercheMulti, 3, 0);
        this.add(filtre, 2, 1);
        this.add(txtRecherche, 3, 1);
        this.add(tableau, 0, 2, 5, 6);

        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-background-color:lightgrey");
        this.setPadding(new Insets(10, 10, 10, 10));
        this.setVgap(10);
        this.setHgap(10);

        TableColumn<Stagiaire, String> nomCol = new TableColumn<Stagiaire, String>("Nom");
        nomCol.setMinWidth(75);
        TableColumn<Stagiaire, String> prenomCol = new TableColumn<Stagiaire, String>("Prénom");
        prenomCol.setMinWidth(75);
        TableColumn<Stagiaire, String> departCol = new TableColumn<Stagiaire, String>("Département");
        departCol.setMinWidth(50);
        TableColumn<Stagiaire, String> promoCol = new TableColumn<Stagiaire, String>("Promotion");
        promoCol.setMinWidth(50);
        TableColumn<Stagiaire, String> anneeCol = new TableColumn<Stagiaire, String>("Année");
        anneeCol.setMinWidth(50);

        nomCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("nom"));
        prenomCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("prenom"));
        departCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("departement"));
        promoCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("formation"));
        anneeCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("annee"));

        for (Stagiaire s : annuaire.getListeTrie()) {
            System.out.println(s);
        }

        table.getColumns().addAll(nomCol, prenomCol, departCol, promoCol, anneeCol);
        table.setItems(FXCollections.observableArrayList(annuaire.getListeTrie()));
    }

    public Button getLogin() {
        return login;
    }
    
    public Button getAjouter() {
		return ajouter;
	}

    public TableView<Stagiaire> getTable() {
        return table;
    }

    public Button getRechercheMulti() {
		return rechercheMulti;
	}

	public void rechercher() {
        String critere = filtre.getValue();
        String recherche = txtRecherche.getText();

        // Réinitialiser la liste des stagiaires affichés dans la table
        table.getItems().clear();

        // Vérifier si un critère de recherche et une valeur ont été sélectionnés
        if (critere != null && !recherche.isEmpty()) {
            ArrayList<Stagiaire> resultatRecherche = new ArrayList<>();

            // Parcourir la liste des stagiaires pour trouver les correspondances
            for (Stagiaire stagiaire : annuaire.getListeTrie()) {
                if (critere.equals("Nom") && stagiaire.getNom().trim().equalsIgnoreCase(recherche)) {
                    resultatRecherche.add(stagiaire);
                } else if (critere.equals("Prénom") && stagiaire.getPrenom().trim().equalsIgnoreCase(recherche)) {
                    resultatRecherche.add(stagiaire);
                } else if (critere.equals("Département") && stagiaire.getDepartement().trim().equalsIgnoreCase(recherche)) {
                    resultatRecherche.add(stagiaire);
                } else if (critere.equals("Promotion") && stagiaire.getFormation().trim().equalsIgnoreCase(recherche)) {
                    resultatRecherche.add(stagiaire);
                } else if (critere.equals("Année") && stagiaire.getAnnee().trim().equalsIgnoreCase(recherche)) {
                    resultatRecherche.add(stagiaire);
                }
            }

            // Mettre à jour la table avec les résultats de recherche
            table.setItems(FXCollections.observableArrayList(resultatRecherche));
        } else {
            // Aucun critère de recherche ou valeur sélectionnés, afficher tous les stagiaires
            table.setItems(FXCollections.observableArrayList(annuaire.getListeTrie()));
        }
    }
    public void setStagiaires(ArrayList<Stagiaire> stagiaires) {
		table.setItems(FXCollections.observableArrayList(stagiaires));
		
	}
}