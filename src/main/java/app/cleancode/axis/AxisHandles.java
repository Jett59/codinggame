package app.cleancode.axis;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;

public class AxisHandles {
public final Pane x;
public final Pane y;
public final Pane z;
public AxisHandles(Pane x, Pane y, Pane z) {
	this.x = x;
	this.y = y;
	this.z = z;
}
public static AxisHandles create(Node node) {
	var x = new Pane(node);
	x.setRotationAxis(Rotate.X_AXIS);
	var y = new Pane(node);
	y.setRotationAxis(Rotate.Y_AXIS);
	var z = new Pane(node);
	z.setRotationAxis(Rotate.Z_AXIS);
	return new AxisHandles(x, y, z);
}
}
