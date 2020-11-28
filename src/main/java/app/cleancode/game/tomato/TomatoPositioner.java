package app.cleancode.game.tomato;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Random;

import app.cleancode.game.GameListener;

public class TomatoPositioner extends GameListener {
private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
public TomatoGameObject tomato;
private Random rand = new Random();

@Override
public void update() {
	if(tomato.collected) {
	double tomatoWidth = tomato.node.getSpriteBoundsInLocal().getWidth();
	double tomatoHeight = tomato.node.getSpriteBoundsInLocal().getHeight();
	double newX = Math.min(screenSize.width-tomatoWidth, rand.nextDouble()*screenSize.width);
	double newY = Math.min(screenSize.height-tomatoHeight, rand.nextDouble()*screenSize.height);
	System.out.printf("%.1fx%.1f\n", newX, newY);
	tomato.node.setTranslateX(newX);
	tomato.node.setTranslateY(newY);
	tomato.collected = false;
	}
}

@Override
public void startup() {
	
}

@Override
public String[] getGameObjects() {
	return new String[] {
			"tomato"
	};
}
}
