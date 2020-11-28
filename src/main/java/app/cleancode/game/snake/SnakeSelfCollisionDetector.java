package app.cleancode.game.snake;

import app.cleancode.game.GameListener;

public class SnakeSelfCollisionDetector extends GameListener {
public SnakeGameObject snake;

@Override
public void update() {
	if(snake.node.checkSelfCollision()) {
		snake.node.destroySegments();
		snake.node.position(SnakeGameObject.initialX, SnakeGameObject.initialY);
		snake.addSegments();
		snake.node.position(SnakeGameObject.initialX, SnakeGameObject.initialY-Snake.Segment.HEIGHT*2);
	}
}

@Override
public void startup() {
	
}

@Override
public String[] getGameObjects() {
	return new String[] {
			"snake"
	};
}
}
