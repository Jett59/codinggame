package app.cleancode.sound;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundBuilder {
public Sound build(String name, String format) {
	try {
		Media media = new Media(getClass().getResource(getPath(name, format)).toExternalForm());
		MediaPlayer player = new MediaPlayer(media);
		return new Sound(player);
	}catch (Exception e) {
		throw new RuntimeException(e);
	}
	}
private String getPath(String name, String format) {
	return String.format("/sounds/%s.%s", name, format);
}
}
