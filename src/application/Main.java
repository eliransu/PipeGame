package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			MyModel m = new MyModel();
			MyViewModel vm = new MyViewModel(m);
			m.addObserver(vm);
			FXMLLoader fx1 = new FXMLLoader();

			BorderPane root = fx1.load(getClass().getResource("GameFXML.fxml").openStream());
			Scene scene = new Scene(root,800,800);
			View v = fx1.getController();
			v.setViewModel(vm);

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
