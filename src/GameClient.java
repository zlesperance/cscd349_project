
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameClient extends Application {
	private Game game;
	private Stage stage;
	private WindowController controller;
	private Scene mapScene;
	private MapWindowController mapController;
	private Scene inventoryScene;
	private InventoryWindowController inventoryController;
	private Scene engagementScene;
	private EngagementWindowController engagementController;

	@Override
	public void start(Stage primaryStage) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("mapWindow.fxml"));
		
		this.mapScene = new Scene(loader.<Parent>load());
		
		this.mapController = (MapWindowController) loader.getController();
		this.mapController.setScene(this.mapScene);
		this.mapController.setGameClient(this);
		/*
		loader = new FXMLLoader(getClass().getResource(""));
		
		this.inventoryScene = new Scene(loader.<Parent>load());
		
		this.inventoryController = (InventoryWindowController) loader.getController();
		this.inventoryController.setScene(this.inventoryScene);
		this.inventoryController.setGameClient(this);
		
		loader = new FXMLLoader(getClass().getResource(""));
		
		this.engagementScene = new Scene(loader.<Parent>load());
		
		this.engagementController = (EngagementWindowController) loader.getController();
		this.engagementController.setScene(this.engagementScene);
		this.engagementController.setGameClient(this);		
		*/
		primaryStage.setTitle("CSCD349 Final Project");
		primaryStage.setScene(this.mapScene);

		primaryStage.show();
		
		this.stage = primaryStage;
		this.controller = this.mapController;
		
		this.game = Game.getInstance();
		this.game.registerGameClient(this);
	}
	
	public void openInventory() {
		this.stage.setScene(this.inventoryScene);
	}
	
	public void openMap() {
		this.stage.setScene(this.mapScene);		
	}
	
	public void openEngagement() {
		this.stage.setScene(this.engagementScene);
	}
	
	public int makeSelection(String... options) {
		return this.controller.makeSelection(options);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
