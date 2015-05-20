import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class GameClient extends Application {	
	public static void main(String args) {
		launch(args);		
	}

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("mapWindow.fxml"));
		
		Scene scene = new Scene(loader.<Parent>load());
		
		stage.setTitle("Dungeon Adventure Quest Fighter IV");
		stage.setScene(scene);
		stage.show();
	}
}
