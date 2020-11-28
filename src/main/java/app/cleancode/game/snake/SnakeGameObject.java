package app.cleancode.game.snake;

import app.cleancode.game.GameObject;

public class SnakeGameObject extends GameObject<Snake> {
public static double initialX = 500, initialY = 500;
public SnakeGameObject() {
	node = new Snake(initialX, initialY);
	addSegments();
	node.prepareForRendering();
}
public void addSegments() {
	for(int i = 0; i < 10; i++) {
		node.createSegment();
	}
	node.prepareForRendering();
}
	@Override
	public String getName() {
		return "snake";
	}

}
