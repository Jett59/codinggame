package app.cleancode.map;

import java.io.InputStream;

import javafx.scene.image.Image;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;

public class Map extends Image {

	public Map(InputStream is) {
		super(is);
	}
public Sphere asSphere(double radius) {
	Sphere sphere = new Sphere(radius);
	PhongMaterial mat = new PhongMaterial();
	mat.setDiffuseMap(this);
	sphere.setMaterial(mat);
	return sphere;
}
}
