package be.iesca.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ApplicationCompteBancaire extends Application {
		
		@Override
		public void start(Stage primaryStage) {
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/be/iesca/vues/VuePrincipale.fxml"));
				Scene scene = new Scene(root, 614, 310);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.setTitle("E-Banque");
				//TO DO
				//Changer image
				primaryStage.getIcons().add(new Image("/be/iesca/application/banque.jpg"));
				primaryStage.setResizable(false);
				primaryStage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public static void lancer(String[] args) {
			launch(args);			
		}
}
