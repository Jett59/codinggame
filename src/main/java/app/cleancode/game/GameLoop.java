package app.cleancode.game;

import javafx.animation.AnimationTimer;

public class GameLoop extends AnimationTimer {
private Runnable tick;
private long lastFrame = 0;
public GameLoop(Runnable tick) {
	this.tick = tick;
}
@Override
public void handle(long now) {
	if(lastFrame+30000000 <= System.nanoTime()) {
		lastFrame = System.nanoTime();
	tick.run();
	}
}
}
