package gameobjects;



import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Movel {
	protected Texture image;
	protected Sound sound;
	protected Music music;
	protected boolean isAlive;

	protected Rectangle rect;
	
	protected Vector2 velocidade;

	public Vector2 getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(Vector2 velocidade) {
		this.velocidade = velocidade;
	}

	public void setVelocidade(float vx, float vy) {
		this.velocidade.x = vx;
		this.velocidade.y = vy;
	}
	
	Movel(Texture novoImage, Rectangle novoRect) {
		this(novoImage, null, null, novoRect);
	}

	Movel(Texture novoImage, Sound novoSound, Music novoMusic, Rectangle novoRect) {
		image = novoImage;
		sound = novoSound;
		music = novoMusic;
		rect = novoRect;
		velocidade = new Vector2(0,0);	
		isAlive = true;
	}

	public void playMusic() {
		//if (music == null)
		//	return;
		// inicia a música imediatamente em loop.
		music.setLooping(true);
		music.play();

	}

	public void playSound() {
		//if (sound == null)
		//	return;
		sound.play();
	}

	public void draw(SpriteBatch batch) {
		//if ((image == null) || (rect == null))
		// 		return;
		batch.draw(image, rect.x, rect.y);		
	}

	public void dispose() {	
	}

	public float getX() {
		return rect.x;
	}

	public void setX(float x) {
		this.rect.x = x;
	}

	public float getY() {
		return rect.y;
	}

	public void setY(float y) {
		this.rect.y = y;
	}

	public Rectangle getRect() {
		return rect;
	}

	public void setRect(Rectangle rect) {
		this.rect = rect;
	}

	public abstract void update();

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
}
