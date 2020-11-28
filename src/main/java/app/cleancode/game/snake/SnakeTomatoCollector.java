package app.cleancode.game.snake;

import app.cleancode.game.GameListener;
import app.cleancode.game.tomato.TomatoGameObject;

public class SnakeTomatoCollector extends GameListener {
public SnakeGameObject snake;
public TomatoGameObject tomato;

@Override
public void update() {
	if(snake.node.checkCollision(tomato.node.getSpriteBoundsInParent())) {
		snake.addSegments();
		tomato.collected = true;
	}
}
@Override
public void startup() {
	
}
@Override
public String[] getGameObjects() {
	return new String[] {
			"snake",
			"tomato"
	};
}
}
