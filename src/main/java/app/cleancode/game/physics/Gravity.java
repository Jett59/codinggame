package app.cleancode.game.physics;

import app.cleancode.game.GameObject;
import app.cleancode.game.PhysicalLaw;
import javafx.scene.Node;

public class Gravity extends PhysicalLaw {
public static double GRAVITY = 1;

	@Override
	public void handle(GameObject<Node> obj) {
		if(!obj.isTouchingGround) {
		double acceloration = obj.mass*GRAVITY;
		obj.yVelocity+=acceloration;
		}
	}

}
