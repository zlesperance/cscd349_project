import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.Scene;


public class EngagementWindowController implements Initializable {
	private Scene scene;
	private GameClient client;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
	
	public void setScene(Scene scene) {
		this.scene = scene;
	}
	
	public void setGameClient(GameClient client) {
		this.client = client;
	}

}
