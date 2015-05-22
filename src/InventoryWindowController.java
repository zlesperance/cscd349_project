import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.Scene;


public class InventoryWindowController implements Initializable {
	private Scene scene;
	private GameClient client;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}
	
	public void setScene(Scene scene) {
		this.scene = scene;
	}
	
	public void setGameClient(GameClient client) {
		this.client = client;
	}

}
