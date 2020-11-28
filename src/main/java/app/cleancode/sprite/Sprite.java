package app.cleancode.sprite;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sprite extends ImageView {
private Bounds bounds;
public Sprite(Image img, Bounds bounds) {
	super(img);
	this.bounds = bounds;
}
public boolean checkCollision(Bounds otherBoundsInParent) {
	var newBounds = getSpriteBoundsInParent();
	return newBounds.intersects(otherBoundsInParent);
}
public Bounds getSpriteBoundsInParent() {
	var boundsInParent = new BoundingBox(getTranslateX()+getLayoutX()+bounds.getMinX(), getTranslateY()+getLayoutY()+bounds.getMinY(), bounds.getWidth(), bounds.getHeight());
	return boundsInParent;
}
public Bounds getSpriteBoundsInLocal() {
	return bounds;
}
}
