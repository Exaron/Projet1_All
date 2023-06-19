package fr.isika.CDA25.fx;

import TestProject1.Stagiaire;
import TestProject1.TxtToData;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;

import fr.isika.CDA25.fx.LogIn;
import fr.isika.CDA25.fx.Logue;
import fr.isika.CDA25.fx.Modif;
import fr.isika.CDA25.fx.NonLogue;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
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
//	public TxtToData data;
//	
//	data= new TxtToData();
//	try {
//		data.lireFichier();
//	} catch (URISyntaxException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	};
	
	

	@Override
    public void start(Stage stage) throws IOException {
    	
    	nonLogue = new NonLogue();
    	logIn = new LogIn();
    	logue = new Logue();
    	modif = new Modif();
    	
        
        Scene scene = new Scene(nonLogue, 640, 480);
        
        
        
        nonLogue.getLogin().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				scene.setRoot(logIn);
			}
			
		});
        
        logIn.getValider().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if ((logIn.getTxtUtilisateur().equals("isika"))&&(logIn.getTxtMotDePasse().equals("cda25"))){
				scene.setRoot(logue);
				}else {
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
				scene.setRoot(nonLogue);
			}
			
		});
        
        logue.getModif().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				scene.setRoot(modif);
			}
			
		});
        
        logue.getSuprim().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
			}
			
		});
        
        modif.getValider().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
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