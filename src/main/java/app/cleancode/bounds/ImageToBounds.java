package app.cleancode.bounds;

import javafx.geometry.BoundingBox;
import javafx.scene.image.Image;

public class ImageToBounds {
public static BoundingBox getBounds(Image img) {
	var reader = img.getPixelReader();
	int minX = 0;
	for(int x = 0; x < img.getWidth(); x++) {
		double opacitySum = 0;
		for(int y = 0; y < img.getHeight(); y++) {
			opacitySum+=reader.getColor(x, y).getOpacity();
		}
		if(opacitySum > 0d) {
			minX = x;
			break;
		}
	}
	int minY = 0;
	for(int y = 0; y < img.getHeight(); y++) {
		double opacitySum = 0;
		for(int x = 0; x < img.getWidth(); x++) {
			opacitySum+=reader.getColor(x, y).getOpacity();
		}
		if(opacitySum > 0d) {
			minY = y;
			break;
		}
	}
	double maxX = img.getWidth()-1;
	for(double x = img.getWidth()-1; x > 0; x--) {
		double opacitySum = 0;
		for(int y = 0; y < img.getHeight(); y++) {
			opacitySum+=reader.getColor((int) x, y).getOpacity();
		}
		if(opacitySum > 0d) {
			maxX = x;
			break;
		}
	}
	double maxY = img.getHeight()-1;
	for(double y = img.getHeight()-1; y > 0; y--) {
		double opacitySum = 0;
		for(int x = 0; x < img.getWidth(); x++) {
			opacitySum+=reader.getColor(x, (int) y).getOpacity();
		}
		if(opacitySum > 0d) {
			maxY = y;
			break;
		}
	}
	return new BoundingBox(minX, minY, maxX-minX, maxY-minY);
}
}
