import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class MapWindowController implements Initializable {
	private final double CELL_SIZE = 48;
	private int partyX;
	private int partyY;
	private GraphicsContext gc;
	private Scene scene;
	private GameClient client;
	
	@FXML
	Canvas mapCanvas;
	@FXML
	Label messageBox;
	@FXML
	VBox windowVBox;
	@FXML
	VBox buttonContainer;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		partyX = 0;
		partyY = 0;
		
		this.gc = mapCanvas.getGraphicsContext2D();
		drawMapGrid(gc);
		drawParty(gc);
		
		messageBox.setText("Game Ready");
	}
	
	public void setScene(Scene scene) {
		this.scene = scene;
		installKeyEventHandler(this.scene);
	}
	
	public void setGameClient(GameClient client) {
		this.client = client;
	}
	
	private void updateMap() {
		gc.clearRect(0, 0, mapCanvas.getWidth(), mapCanvas.getHeight());
		drawMapGrid(gc);
		drawParty(gc);
	}
	
	private void drawMapGrid(GraphicsContext gc) {
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(2);
		for (int i = 0; i <= mapCanvas.getWidth(); i += CELL_SIZE) {
			gc.strokeLine(i, 0, i, mapCanvas.getHeight());
			gc.strokeLine(0, i, mapCanvas.getWidth(), i);
		}
	}
	
	private void drawParty(GraphicsContext gc) {
		int x = partyX;
		int y = partyY;
		double padding = 4;
		double baseX = x * CELL_SIZE, baseY = y * CELL_SIZE;
		gc.strokePolygon(new double[]{baseX + (CELL_SIZE / 2.0), baseX + padding, baseX + CELL_SIZE - padding}, new double[]{baseY + padding, baseY + CELL_SIZE - padding, baseY + CELL_SIZE - padding}, 3);
	}
	
	private void installKeyEventHandler(final Scene keyNode) {
		final EventHandler<KeyEvent> keyEventHandler = 
				new EventHandler<KeyEvent>() {
			public void handle(final KeyEvent keyEvent) {
				if (keyEvent.getCode() == KeyCode.RIGHT) {
					messageBox.setText("Right Pressed");
					tryTravelRight();
					keyEvent.consume();
				} else if (keyEvent.getCode() == KeyCode.LEFT) {
					messageBox.setText("Left Pressed");
					tryTravelLeft();
					keyEvent.consume();
				} else if (keyEvent.getCode() == KeyCode.UP) {
					messageBox.setText("Up Pressed");
					tryTravelUp();
					keyEvent.consume();
				} else if (keyEvent.getCode() == KeyCode.DOWN) {
					messageBox.setText("Down Pressed");
					tryTravelDown();
					keyEvent.consume();
				} else {
					messageBox.setText("Key Pressed");
				}
			}
		};

	    keyNode.setOnKeyReleased(keyEventHandler);
	}
	
	private void tryTravelRight() {
		partyX++;
		updateMap();
	}
	
	private void tryTravelLeft() {
		partyX--;
		updateMap();
	}
	
	private void tryTravelUp() {
		partyY--;
		updateMap();
	}
	
	private void tryTravelDown() {
		partyY++;
		updateMap();
	}

}
