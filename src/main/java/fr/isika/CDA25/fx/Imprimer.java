package fr.isika.CDA25.fx;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import com.itextpdf.kernel.color.DeviceRgb;
import javafx.application.Application;
import javafx.stage.Stage;

import TestProject1.Stagiaire;

public class Imprimer extends Application {
    public static final String DESTINATION = "src/main/resources/impression.pdf";

    // Crée le pdf, ouvre l'écriture dessus, puis écrit chaque stagiaire de la liste.
    public void imprimer(List<Stagiaire> listeRecherche) throws FileNotFoundException {
        FileOutputStream fos = new FileOutputStream(DESTINATION);
        PdfWriter writer = new PdfWriter(fos);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        Table tableauStagiaires = new Table(new float[]{3, 3, 2, 2, 2});

        // Mise en page du tableau
        tableauStagiaires.setWidthPercent(100f);
        tableauStagiaires.addHeaderCell(createHeaderCell("Nom"));
        tableauStagiaires.addHeaderCell(createHeaderCell("Prénom"));
        tableauStagiaires.addHeaderCell(createHeaderCell("Département"));
        tableauStagiaires.addHeaderCell(createHeaderCell("Formation"));
        tableauStagiaires.addHeaderCell(createHeaderCell("Année"));

        int i = 0;
        for (Stagiaire stagiaire : listeRecherche) {
            Cell cellNom = createCell(stagiaire.getNom());
            Cell cellPrenom = createCell(stagiaire.getPrenom());
            Cell cellDepartement = createCell(stagiaire.getDepartement());
            Cell cellFormation = createCell(stagiaire.getFormation());
            Cell cellAnnee = createCell(String.valueOf(stagiaire.getAnnee()));

            tableauStagiaires.addCell(i % 2 != 0 ? createColoredCell(cellNom) : cellNom);
            tableauStagiaires.addCell(i % 2 != 0 ? createColoredCell(cellPrenom) : cellPrenom);
            tableauStagiaires.addCell(i % 2 != 0 ? createColoredCell(cellDepartement) : cellDepartement);
            tableauStagiaires.addCell(i % 2 != 0 ? createColoredCell(cellFormation) : cellFormation);
            tableauStagiaires.addCell(i % 2 != 0 ? createColoredCell(cellAnnee) : cellAnnee);

            i++;
        }

        document.add(new Paragraph("Liste des stagiaires recherchés")
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(16)
                .setBold()
                .setMarginBottom(20));

        document.add(tableauStagiaires);

        document.close();
    }

    private Cell createHeaderCell(String content) {
        return new Cell().add(new Paragraph(content)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER)
                .setPadding(4)
                .setBackgroundColor(new DeviceRgb(192, 192, 192)));
    }

    private Cell createColoredCell(Cell cellNom) {
        Cell cell = new Cell();
        cell.add(new Paragraph());
        cell.setBackgroundColor(new DeviceRgb(211, 211, 211));
        return cell;
    }
    private Cell createCell(String content) {
        Cell cell = new Cell();
        cell.add(new Paragraph(content));
        return cell;
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		List<Stagiaire> listeRecherche = new ArrayList<>();
	    imprimer(listeRecherche);
	}

	
}
