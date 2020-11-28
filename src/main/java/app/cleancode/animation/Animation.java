package app.cleancode.animation;

import java.util.List;

import javafx.animation.Transition;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/*
 * a class for creating animations. This uses a film strip system to switch frames.
 */
public class Animation extends Transition {
private int currentFramePosition = 0;
private int cellWidth;
private int cellHeight;
private int cellCount;
private ImageView filmStrip;
private List<BoundingBox> bound;

public Animation(int cellCount, int totalWidth, int cellHeight, ImageView imageView,  Duration duration, List<BoundingBox> boundingBoxes) {
	this.cellCount = cellCount;
	this.cellHeight = cellHeight;
	this.cellWidth = totalWidth/cellCount;
	this.filmStrip = imageView;
	this.bound = boundingBoxes;
	super.setCycleDuration(duration);
	super.setCycleCount(-1);
}
	@Override
	protected void interpolate(double frac) {
		updateViewPort(frac);
	}
private void updateViewPort(double frac) {
	double frameLength = 1d/cellCount;
	System.out.println(frameLength);
	currentFramePosition = (int) (Math.floor(frac/frameLength));
	filmStrip.setViewport(new Rectangle2D(currentFramePosition*cellWidth, 0, cellWidth, cellHeight));
	
}
/*
 * gets the bounds of this animation.
 * 
 * changing the values on this field will not effect the animation.
 * @return the bounds of this animation.
 */
public Bounds getBounds() {
	return new BoundingBox(bound.get(currentFramePosition).getMinX(), bound.get(currentFramePosition).getMinY(), bound.get(currentFramePosition).getWidth(), bound.get(currentFramePosition).getHeight());
}
/*
 * returns the image view for this animation.
 * @return the image view for this animation
 */
public ImageView getView() {
	return filmStrip;
}
}
