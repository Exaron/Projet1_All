package fr.isika.CDA25.fx;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import TestProject1.Arbre;
import TestProject1.Stagiaire;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterAttributes;
import javafx.print.PrinterJob;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.scene.control.Pagination;

public class Logue extends GridPane {
	private Button logOut;
	private Button ajouter;
	private Button modif;
	private Button suprim;
	private Button imprimer;
	private Button rechercheMulti;
	private Label erreur;

	private TextField txtRecherche;
	private ChoiceBox<String> filtre;
	private TableView<Stagiaire> table;
	public Arbre annuaire = new Arbre();
// private final CheckBox caseACocher = new CheckBox();

	public Logue() throws IOException {
		super();
		annuaire.lister(0);

		Label recherche = new Label("Rechercher par :");
		this.filtre = new ChoiceBox<>();
		filtre.getItems().addAll("Filtre", "Nom", "Prénom", "Département", "Promotion", "Année");
		filtre.getSelectionModel().select(0);
		this.txtRecherche = new TextField();
		this.table = new TableView<Stagiaire>();
		table.setEditable(true);
		StackPane tableau = new StackPane();
		tableau.getChildren().add(table);

		filtre.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> rechercher());
		txtRecherche.textProperty().addListener((observable, oldValue, newValue) -> rechercher());

		this.logOut = new Button("Log out");
		this.modif = new Button("Modifier");
		this.suprim = new Button("Supprimer");
		this.ajouter = new Button("Ajouter");
		this.imprimer = new Button("Imprimer");
		this.rechercheMulti = new Button("Recherche multicritère");
		this.erreur = new Label("");

		this.add(logOut, 4, 0);
		this.add(erreur, 1, 0, 2, 1);
		this.add(ajouter, 2, 2);
		this.add(imprimer, 0, 2);
		this.add(recherche, 1, 1);
		this.add(filtre, 2, 1);
		this.add(rechercheMulti, 3, 0);
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
		nomCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("Nom"));
		TableColumn<Stagiaire, String> prenomCol = new TableColumn<Stagiaire, String>("Prénom");
		prenomCol.setMinWidth(50);
		TableColumn<Stagiaire, String> departCol = new TableColumn<Stagiaire, String>("Département");
		departCol.setMinWidth(50);
		TableColumn<Stagiaire, String> promoCol = new TableColumn<Stagiaire, String>("Promotion");
		promoCol.setMinWidth(50);
		TableColumn<Stagiaire, String> anneeCol = new TableColumn<Stagiaire, String>("Année");
		anneeCol.setMinWidth(50);
		// TableColumn<Stagiaire, Boolean> caseCol = new TableColumn<>("Sélection");
		// caseCol.setMinWidth(50);

		nomCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("nom"));
		prenomCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("prenom"));
		departCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("departement"));
		promoCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("formation"));
		anneeCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("annee"));
		imprimer.setOnAction(event -> {
//			imprimerTable();
		});
		// caseCol.setCellFactory(CheckBoxTableCell.forTableColumn(caseCol));
		// caseCol.setCellValueFactory(new PropertyValueFactory<>(" ")) ;

		for (Stagiaire s : annuaire.getListeTrie()) {
			System.out.println(s);
		}

		table.getColumns().addAll(nomCol, prenomCol, departCol, promoCol, anneeCol);
		table.setItems(FXCollections.observableArrayList(annuaire.getListeTrie()));
	}

	public Button getAjouter() {
		return ajouter;
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

	public TableView<Stagiaire> getTable() {
		return table;
	}

	public Label getErreur() {
		return erreur;
	}

	public Button getRechercheMulti() {
		return rechercheMulti;
	}

	public Button getImprimer() {
		return imprimer;
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
				} else if (critere.equals("Département")
						&& stagiaire.getDepartement().trim().equalsIgnoreCase(recherche)) {
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
			// Aucun critère de recherche ou valeur sélectionnés, afficher tous les
			// stagiaires
			table.setItems(FXCollections.observableArrayList(annuaire.getListeTrie()));
		}
	}

	public ObservableList<Stagiaire> getSelectedStagiaires() {
		return table.getSelectionModel().getSelectedItems();
	}

	public Stagiaire getSelectedStagiaire() {
		// System.out.println("methode get selected stafiaires");
		return table.getSelectionModel().getSelectedItem();
	}

	public void setStagiaires(ArrayList<Stagiaire> stagiaires) {
		table.setItems(FXCollections.observableArrayList(stagiaires));

	}

	public void imprimerTable(ArrayList<Stagiaire> listeStagiaires) {
		PrinterJob job = PrinterJob.createPrinterJob();
		if (job != null) {

			double printableHeight = job.getJobSettings().getPageLayout().getPrintableHeight();
			int itemsPerPage = (int) Math.floor(printableHeight / 20);
			StringBuilder content = new StringBuilder();
			for (Stagiaire stagiaire : listeStagiaires) {
				content.append(stagiaire.getNom()).append("\t").append(stagiaire.getPrenom()).append("\t")
						.append(stagiaire.getDepartement()).append("\t").append(stagiaire.getFormation()).append("\t")
						.append(stagiaire.getAnnee()).append("\n");
			}
			TextArea textArea = new TextArea(content.toString());
			textArea.setFont(Font.font("Monospaced"));
			Pagination pagination = new Pagination();
			int pageCount = (int) Math.ceil((double) listeStagiaires.size() / itemsPerPage);
			pagination.setPageCount(pageCount);
			pagination.setPageFactory(pageIndex -> {
				int fromIndex = pageIndex * itemsPerPage;
				int toIndex = Math.min(fromIndex + itemsPerPage, listeStagiaires.size());
				if (fromIndex < toIndex) {
					List<Stagiaire> pageStagiaires = listeStagiaires.subList(fromIndex, toIndex);
					StringBuilder pageContent = new StringBuilder();
					for (Stagiaire stagiaire : pageStagiaires) {
						pageContent.append(stagiaire.getNom()).append("\t").append(stagiaire.getPrenom()).append("\t")
								.append(stagiaire.getDepartement()).append("\t").append(stagiaire.getFormation())
								.append("\t").append(stagiaire.getAnnee()).append("\n");
					}
					TextArea pageTextArea = new TextArea(pageContent.toString());
					pageTextArea.setFont(Font.font("Monospaced"));
					pageTextArea.setEditable(false);
					pageTextArea.setWrapText(true);
					return pageTextArea;
				} else {
					return null;
				}
			});
			
			boolean showDialog = job.showPrintDialog(null);
			if (showDialog) {
				for (int pageIndex = 0; pageIndex < pageCount; pageIndex++) {
					pagination.setCurrentPageIndex(pageIndex);
					boolean impressionReussie = job.printPage(textArea);
					if (!impressionReussie) {
						System.out.println("ÉCHEC");
						break;
					}
				}
				
				System.out.println("Impression réussie");
				job.endJob();
			}
		}
	}


}
