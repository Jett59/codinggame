package app.cleancode.game.tomato;

import app.cleancode.game.GameObject;
import app.cleancode.sprite.Sprite;
import app.cleancode.sprite.SpriteBuilder;

public class TomatoGameObject extends GameObject<Sprite> {
	public static double HEIGHT = 100;
	private SpriteBuilder spriteBuilder;
	public boolean collected;
public TomatoGameObject() {
	spriteBuilder = new SpriteBuilder();
	node = spriteBuilder.build("tomato", HEIGHT, false);
}
	@Override
	public String getName() {
		return "tomato";
	}

}
