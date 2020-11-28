package app.cleancode.game.snake;

import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Snake extends Pane {
private Head initialSegment;
public Snake(double initialX, double initialY) {
	initialSegment = new Head(initialX, initialY);
	getChildren().add(initialSegment);
}
public void destroySegments() {
	initialSegment.next = null;
	getChildren().clear();
	getChildren().add(initialSegment);
}
public boolean checkCollision(Bounds otherBoundsInParent) {
	return otherBoundsInParent.intersects(initialSegment.getBoundsInParent());
}
public void move(double xIncrement, double yIncrement) {
	position(initialSegment.getTranslateX()+xIncrement, initialSegment.getTranslateY()+yIncrement);
}
public void position(double x, double y) {
	initialSegment.position(x, y);
}
public void prepareForRendering() {
	initialSegment.prepareForRendering();
}
public void createSegment() {
	Segment newSegment = initialSegment.newSegment();
	newSegment.prepareForRendering();
	getChildren().add(newSegment);
}
public boolean checkSelfCollision() {
	return initialSegment.checkCollision();
}
public static class Segment extends Rectangle {
	public static double WIDTH = 5, HEIGHT = 5;
	public static Color col = new Color(0.5, 1, 0.5, 1);
	private double x, y;
	protected Segment next;
	public Segment() {
		super(0, 0, col);
		setWidth(WIDTH);
		setHeight(HEIGHT);
		setVisible(true);
	}
	public void position(double x, double y) {
		if(next != null) {
			next.position(this.x, this.y);
		}
		this.x = x;
		this.y = y;
	}
	public Segment newSegment() {
		if(next == null) {
			next = new Segment();
			next.x = x;
			next.y = y;
			return next;
		}else {
			return next.newSegment();
		}
	}
	public void prepareForRendering() {
		setTranslateX(x);
		setTranslateY(y);
		if(next != null) {
			next.prepareForRendering();
		}
	}
	public boolean checkCollision(Segment head) {
		if(getBoundsInParent().intersects(head.getBoundsInParent())) {
			return true;
		}else {
			if(next != null) {
				return next.checkCollision(head);
			}else {
				return false;
			}
		}
	}
}
public static class Head extends Segment {
	public Head(double initialX, double initialY) {
		super();
		position(initialX, initialY);
	}
	public boolean checkCollision() {
		return checkCollision(this);
	}
	@Override
	public boolean checkCollision(Segment head) {
		if(next != null && next.next != null) {
		boolean result = next.next.checkCollision(head);
		return result;
		}else {
			return false;
		}
	}
}
}
