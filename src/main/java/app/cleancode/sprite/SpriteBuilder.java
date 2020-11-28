package app.cleancode.sprite;

import java.awt.image.BufferedImage;

import app.cleancode.bounds.ImageToBounds;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Bounds;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SpriteBuilder {
public Sprite build(String name, double height, boolean reversed) {
	Image img = new Image(getClass().getResourceAsStream(getPath(name)));
	if(reversed) {
		ImageView view = new ImageView(img);
		img = view.snapshot(new SnapshotParameters(), null);
	}
	double scale = height/img.getHeight();
	var bufImg = SwingFXUtils.fromFXImage(img, null);
	var newImage = new BufferedImage((int)Math.ceil(img.getWidth()*scale), (int)Math.ceil(height), BufferedImage.TYPE_4BYTE_ABGR);
	var graphics = newImage.getGraphics();
	graphics.drawImage(bufImg, 0, 0, newImage.getWidth(), newImage.getHeight(), null);
	graphics.dispose();
	img = SwingFXUtils.toFXImage(newImage, null);
	Bounds bounds = ImageToBounds.getBounds(img);
	Sprite sprite = new Sprite(img, bounds);
	return sprite;
}
private String getPath(String spriteName) {
	return String.format("/sprites/%s.png", spriteName);
}
}
