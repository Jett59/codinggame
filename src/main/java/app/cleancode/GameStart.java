package app.cleancode;

import app.cleancode.game.GameListener;
import app.cleancode.game.GameLoop;
import app.cleancode.game.GameObject;
import app.cleancode.game.PhysicalLaw;
import app.cleancode.game.physics.Gravity;
import app.cleancode.game.physics.Movement;
import app.cleancode.game.snake.SnakeGameObject;
import app.cleancode.game.snake.SnakeMovementControler;
import app.cleancode.game.snake.SnakeSelfCollisionDetector;
import app.cleancode.game.snake.SnakeTomatoCollector;
import app.cleancode.game.tomato.TomatoGameObject;
import app.cleancode.game.tomato.TomatoPositioner;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameStart extends Application {
	private static GameListener[] gameListeners = new GameListener[] {
			new SnakeMovementControler(),
			new SnakeSelfCollisionDetector(),
			new SnakeTomatoCollector(),
			new TomatoPositioner()
	};
	@SuppressWarnings("unchecked")
	private static GameObject<Node> [] gameObjects = new GameObject[] {
			new SnakeGameObject(),
			new TomatoGameObject()
	};
	private static PhysicalLaw[] laws = new PhysicalLaw[] {
			new Movement(),
			new Gravity()
	};
public static void begin(String[] args) {
	launch(args);
}
private Pane nodes = new Pane();
 private Pane gamePane = new Pane();
@Override
public void start(Stage primaryStage) throws Exception {
	Scene scene = new Scene(gamePane);
	scene.getStylesheets().add("/app/cleancode/app.css");
	nodes.getChildren().add(new Text("Loading"));
	primaryStage.setTitle("Game");
	primaryStage.setFullScreen(true);
	primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
	primaryStage.setScene(new Scene(nodes));
	primaryStage.show();
	for(GameObject<Node> gameObject : gameObjects) {
		gameObject.addNode = this::addNode;
		addNode(gameObject.node);
	}
	for(GameListener listener : gameListeners) {
		for(String gameObjectName : listener.getGameObjects()) {
			for(GameObject<Node> gameObject : gameObjects) {
				if(gameObject.getName().equalsIgnoreCase(gameObjectName)) {
					try {
						var gameObjectField = listener.getClass().getDeclaredField(gameObjectName);
						gameObjectField.set(listener, gameObject);
						break;
					}catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
			}
		}
		listener.startup();
	}
	scene.setFill(Color.BLACK);
	primaryStage.setScene(scene);
	primaryStage.setFullScreen(true);
	GameLoop loop = new GameLoop(this::tick);
	loop.start();
}
public void tick() {
	for(GameListener gameListener : gameListeners) {
		gameListener.update();
	}
	for(PhysicalLaw law : laws) {
		for(GameObject<Node> gameObject : gameObjects) {
			law.handle(gameObject);
		}
	}
}
public void addNode(Node node) {
	gamePane.getChildren().add(node);
}
}
