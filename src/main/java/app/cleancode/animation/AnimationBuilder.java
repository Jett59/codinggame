package app.cleancode.animation;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import app.cleancode.bounds.ImageToBounds;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.BoundingBox;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.util.Duration;

public class AnimationBuilder {
public Animation buildAnimation(String character, String animation, int cellCount, Duration duration, double height, boolean reversed) {
	String templatePath = String.format("/characters/%s/animations/%s/%s ([index]).png", character, animation.toLowerCase(), animation);
	Image testImage = new Image(getClass().getResourceAsStream(templatePath.replace("[index]", "1")));
	int imageHeight = (int) Math.ceil(testImage.getHeight());
	double scale = height/imageHeight;
	int cellWidth = (int) Math.ceil(testImage.getWidth()*scale);
	List<BoundingBox> boundingBoxes = new ArrayList<>();
	BufferedImage swingFilmStrip = new BufferedImage(cellWidth*cellCount, (int) height, BufferedImage.TYPE_4BYTE_ABGR);
	Graphics graphics = swingFilmStrip.getGraphics();
	for(int i = 1; i <= cellCount; i++) {
		System.out.println(i);
		String path = String.format("/characters/%s/animations/%s/%s (%d).png", character, animation.toLowerCase(), animation, i);
		BufferedImage bufferedCell = new BufferedImage(cellWidth, (int) height, BufferedImage.TYPE_4BYTE_ABGR);
		try {
			BufferedImage readCell = ImageIO.read(getClass().getResourceAsStream(path));
			Graphics cellGraphics = bufferedCell.getGraphics();
			cellGraphics.drawImage(readCell, 0, 0, cellWidth, (int) height, null);
			cellGraphics.dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image cell = SwingFXUtils.toFXImage(bufferedCell, null);
		BoundingBox imageBounds = ImageToBounds.getBounds(cell);
		boundingBoxes.add(imageBounds);
		if(reversed) {
			var reversedImage = new WritableImage(cellWidth, (int) height);
			ImageView imgView = new ImageView(cell);
			imgView.setScaleX(-1);
			imgView.snapshot(new SnapshotParameters(), reversedImage);
			cell = reversedImage;
		}
		graphics.drawImage(bufferedCell, cellWidth*(i-1), 0, cellWidth, (int) height, null);
	}
	graphics.dispose();
	Image filmStrip = SwingFXUtils.toFXImage(swingFilmStrip, null);
	var result = new Animation(cellCount, (int)filmStrip.getWidth(), (int)height, new ImageView(filmStrip), duration, null);
	return result;
}
}
