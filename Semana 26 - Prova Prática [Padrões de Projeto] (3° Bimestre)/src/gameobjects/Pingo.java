package gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

class Pingo extends Movel {

	Pingo(Texture novoImage, Sound novoSound, Music novoMusic, Rectangle novoRect) {
		super(novoImage, novoSound, novoMusic, novoRect);
	}

	@Override
	public void update() {
		setY(getY() - velocidade.y * Gdx.graphics.getDeltaTime());

	}
		
}