package app.cleancode.map;

public class MapBuilder {
public Map build(String fileName) {
	String path = String.format("/maps/%s", fileName);
	return new Map(getClass().getResourceAsStream(path));
}
}
