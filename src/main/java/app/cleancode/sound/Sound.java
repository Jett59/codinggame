package app.cleancode.sound;

import javafx.scene.media.MediaPlayer;

public class Sound {
private MediaPlayer player;
public Sound(MediaPlayer player) {
	this.player = player;
	player.setOnEndOfMedia(player::stop);
}
public void play() {
	player.play();
}
public void stop() {
	player.stop();
}
public MediaPlayer getPlayer() {
	return player;
}
}
