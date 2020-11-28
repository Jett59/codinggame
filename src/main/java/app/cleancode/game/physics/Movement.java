package app.cleancode.game.physics;

import app.cleancode.game.GameObject;
import app.cleancode.game.PhysicalLaw;
import app.cleancode.game.snake.Snake;
import javafx.scene.Node;

public class Movement extends PhysicalLaw {

	@Override
	public void handle(GameObject<Node> obj) {
		if(obj.node instanceof Snake) {
			if(obj.xVelocity != 0 || obj.yVelocity != 0) {
			Snake snake = (Snake)obj.node;
			snake.move(obj.xVelocity, obj.yVelocity);
			snake.prepareForRendering();
			
			}
		}
	}

}
