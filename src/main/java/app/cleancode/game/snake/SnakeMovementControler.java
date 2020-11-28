package app.cleancode.game.snake;

import app.cleancode.game.GameListener;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class SnakeMovementControler extends GameListener {
public SnakeGameObject snake;

	@Override
	public void update() {
		
	}

	@Override
	public void startup() {
		snake.node.getScene().addEventHandler(KeyEvent.KEY_PRESSED, evt->{
			Direction direction = Direction.forCode(evt.getCode());
			if(direction != null) {
				switch (direction) {
				case LEFT: {
					snake.xVelocity = -10;
					break;
				}
				case RIGHT: {
					snake.xVelocity = 10;
					break;
				}
				case DOWN: {
					snake.yVelocity = 10;
					break;
				}
				case UP: {
					snake.yVelocity = -10;
					break;
				}
				}
			}
		});
		snake.node.getScene().addEventHandler(KeyEvent.KEY_RELEASED, evt->{
			Direction direction = Direction.forCode(evt.getCode());
			if(direction != null) {
				switch (direction) {
				case UP:
				case DOWN: {
					snake.yVelocity = 0;
					break;
				}
				case LEFT:
				case RIGHT: {
					snake.xVelocity = 0;
					break;
				}
				}
			}
		});
	}

	@Override
	public String[] getGameObjects() {
		return new String[] {
				"snake"
		};
	}
private static enum Direction {
	UP(KeyCode.UP, KeyCode.W),
	DOWN(KeyCode.DOWN, KeyCode.S),
	RIGHT(KeyCode.RIGHT, KeyCode.D),
	LEFT(KeyCode.LEFT, KeyCode.A);
	private KeyCode primary, secondary;
	private Direction(KeyCode primary, KeyCode secondary) {
		this.primary = primary;
		this.secondary = secondary;
	}
	public static Direction forCode(KeyCode code) {
		for(Direction direction : Direction.values()) {
			if(direction.primary.equals(code) || direction.secondary.equals(code)) {
				return direction;
			}
		}
		return null;
	}
}
}
