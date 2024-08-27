//http://resizeimage.net/
//https://www.freesound.org//

package gpjecc.blogspot.com;

import java.util.Iterator;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class SimpleGame extends ApplicationAdapter {
	private Texture dropImage;
	private Texture bucketImage;
	private Texture bolaTexture;
	private Texture raioImage;
	private Texture bigornaImage;
	private Sound dropSound;
	private Sound raioSound;
	private Sound bigornaSound;
	private Music rainMusic;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private BitmapFont font;
	private Movel bucket;
	private Array<Movel> moveis;
	private long lastDropTime;

	@Override
	public void create() {
		Texture.setEnforcePotImages(false);

		// load the images for the droplet and the bucket, 64x64 pixels each
		dropImage = new Texture(Gdx.files.internal("assets/droplet.png"));
		raioImage = new Texture(Gdx.files.internal("assets/raio.png"));
		bucketImage = new Texture(Gdx.files.internal("assets/bucket.png"));
		bolaTexture = new Texture(Gdx.files.internal("assets/bubble.png"));
		bigornaImage = new Texture(Gdx.files.internal("assets/anvil.png"));

		// load the drop sound effect and the rain background "music"
		dropSound = Gdx.audio.newSound(Gdx.files.internal("assets/drop.wav"));
		raioSound = Gdx.audio.newSound(Gdx.files.internal("assets/thunder.mp3"));
		bigornaSound = Gdx.audio.newSound(Gdx.files.internal("assets/anvil.wav"));
		rainMusic = Gdx.audio.newMusic(Gdx.files.internal("assets/rain.mp3"));

		// start the playback of the background music immediately
		rainMusic.setLooping(true);
		rainMusic.play();

		// create the camera and the SpriteBatch
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		batch = new SpriteBatch();

		font = new BitmapFont();
		font.setColor(Color.YELLOW);
		font.setScale(2);

		// create a Rectangle to logically represent the bucket
		spawnBucket();

		// create the raindrops array and spawn the first raindrop
		moveis = new Array<Movel>();
		spawnMovel();
	}

	private void spawnBucket() {
		Rectangle rectangle = new Rectangle();
		rectangle.x = 800 / 2 - 64 / 2; // center the bucket horizontally
		rectangle.y = 20; // bottom left corner of the bucket is 20 pixels above
		// the bottom screen edge
		rectangle.width = 64;
		rectangle.height = 64;
		bucket = new Bucket(rectangle, bucketImage, null);
	}

	private void spawnMovel() {
		Rectangle raindrop = new Rectangle();
		raindrop.x = MathUtils.random(0, 800 - 64);
		raindrop.y = 480;
		raindrop.width = 64;
		raindrop.height = 64;

		int spawnSelector = MathUtils.random(0, 3);
		System.out.println(spawnSelector);

		if (spawnSelector == 0) {
			moveis.add(new Chuva(raindrop, dropImage, dropSound));
		}

		if (spawnSelector == 1) {
			Rectangle thunder = new Rectangle();
			thunder.x = MathUtils.random(0, 800 - 64);
			thunder.y = 480;
			thunder.width = 286;
			thunder.height = 474;

			moveis.add(new Raio(raindrop, raioImage, raioSound));
		}

		if (spawnSelector == 2) {
			moveis.add(new BolaDeSabao(raindrop, bolaTexture, dropSound));
		}

		if (spawnSelector == 3) {
			moveis.add(new Anvil(raindrop, bigornaImage, bigornaSound));
		}

		lastDropTime = TimeUtils.nanoTime();
	}

	@Override
	public void render() {
		// clear the screen with a dark blue color. The
		// arguments to glClearColor are the red, green
		// blue and alpha component in the range [0,1]
		// of the color to be used to clear the screen.
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// tell the camera to update its matrices.
		camera.update();

		// tell the SpriteBatch to render in the
		// coordinate system specified by the camera.
		batch.setProjectionMatrix(camera.combined);

		// begin a new batch and draw the bucket and
		// all drops
		batch.begin();
		batch.draw(bucket.getmImage(), bucket.getmRectangle().x, bucket.getmRectangle().y);
		for (Movel movel : moveis) {
			movel.desenha(batch);
			;
		}
		font.draw(batch, "Pontos = " + ((Bucket) bucket).pontos, 650, 460);
		batch.end();

		// process user input
		bucket.handleEvent(camera);

		// check if we need to create a new raindrop
		if (TimeUtils.nanoTime() - lastDropTime > 1000000000)
			spawnMovel();

		// move the raindrops, remove any that are beneath the bottom edge of
		// the screen or that hit the bucket. In the later case we play back
		// a sound effect as well.
		Iterator<Movel> iter = moveis.iterator();
		while (iter.hasNext()) {
			Movel movel = iter.next();

			movel.move();

			if (movel instanceof BolaDeSabao) {
				if (movel.getmRectangle().y + 64 < 0) {
					iter.remove();
				}
			}

			if (movel instanceof Chuva) { // Testa se o movel é da classe chuva
				if (movel.getmRectangle().y + 64 < 0) {
					iter.remove();
				}

				if (movel.getmRectangle().overlaps(bucket.getmRectangle())) {
					movel.PlaySound();
					bucket.PlaySound();
					((Bucket) bucket).pontos++;
					iter.remove();
				}
			}

			if (movel instanceof Anvil) {
				if (movel.getmRectangle().y + 64 < 0) {
					iter.remove();
				}

				if (movel.getmRectangle().overlaps(bucket.getmRectangle())) {
					movel.PlaySound();
					bucket.PlaySound();
					((Bucket) bucket).pontos--;
					iter.remove();
				}
			}

			if (movel instanceof Raio) {
				if (movel.getmRectangle().y > 480 + 474) {
					iter.remove();
				}

				if (movel.getmRectangle().overlaps(bucket.getmRectangle())) {
					movel.PlaySound();
					bucket.PlaySound();

					((Bucket) bucket).pontos = 0;
					iter.remove();
				}
			}
		}
	}

	@Override
	public void dispose() {
		// dispose of all the native resources
		dropImage.dispose();
		bucketImage.dispose();
		raioImage.dispose();

		font.dispose();

		dropSound.dispose();
		rainMusic.dispose();
		batch.dispose();
	}
}

abstract class Movel {

	protected Texture mImage;
	protected Rectangle mRectangle;
	protected Sound mSound;

	public Movel(Rectangle rectangle, Texture texture, Sound sound) {
		mRectangle = rectangle;
		mImage = texture;
		mSound = sound;
	}

	public abstract void move();

	public abstract void handleEvent(OrthographicCamera camera);

	public void desenha(SpriteBatch batch) {
		batch.draw(mImage, getmRectangle().x, getmRectangle().y);
	}

	public void PlaySound() {
		if (mSound != null) {
			mSound.play();
		}

	}

	public Texture getmImage() {
		return mImage;
	}

	public void setmImage(Texture mImage) {
		this.mImage = mImage;
	}

	public Rectangle getmRectangle() {
		return mRectangle;
	}

	public void setmRectangle(Rectangle mRectangle) {
		this.mRectangle = mRectangle;
	}

	public Sound getmSound() {
		return mSound;
	}

	public void setmSound(Sound mSound) {
		this.mSound = mSound;
	}
}

class Bucket extends Movel {

	public int pontos = 3;

	public Bucket(Rectangle rectangle, Texture texture, Sound sound) {
		super(rectangle, texture, sound);

	}

	@Override
	public void handleEvent(OrthographicCamera camera) {
		if (Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			mRectangle.x = touchPos.x - 64 / 2;
		}
		if (Gdx.input.isKeyPressed(Keys.LEFT))
			mRectangle.x -= 200 * Gdx.graphics.getDeltaTime();
		if (Gdx.input.isKeyPressed(Keys.RIGHT))
			mRectangle.x += 200 * Gdx.graphics.getDeltaTime();

		// make sure the bucket stays within the screen bounds
		if (mRectangle.x < 0)
			mRectangle.x = 0;
		if (mRectangle.x > 800 - 64)
			mRectangle.x = 800 - 64;
	}

	@Override
	public void move() {
	}

}

class Chuva extends Movel {

	public Chuva(Rectangle rectangle, Texture texture, Sound sound) {
		super(rectangle, texture, sound);
	}

	@Override
	public void move() {
		getmRectangle().y -= 200 * Gdx.graphics.getDeltaTime();
	}

	@Override
	public void handleEvent(OrthographicCamera camera) {
	}

}

class Raio extends Movel {
	public Raio(Rectangle rectangle, Texture texture, Sound sound) {
		super(rectangle, texture, sound);
	}

	@Override
	public void move() {
		getmRectangle().y -= 400 * Gdx.graphics.getDeltaTime();
	}

	@Override
	public void handleEvent(OrthographicCamera camera) {
	}
}

class BolaDeSabao extends Movel {

	public BolaDeSabao(Rectangle rectangle, Texture texture, Sound sound) {
		super(rectangle, texture, sound);
		setmRectanglePosition();
	}

	private void setmRectanglePosition() {
		getmRectangle().y = 0 - 64;
	}

	@Override
	public void move() {
		getmRectangle().y += 200 * Gdx.graphics.getDeltaTime();
	}

	@Override
	public void handleEvent(OrthographicCamera camera) {
	}
}

class Anvil extends Movel {
	public Anvil(Rectangle rectangle, Texture texture, Sound sound) {
		super(rectangle, texture, sound);
	}

	@Override
	public void move() {
		getmRectangle().y -= 200 * Gdx.graphics.getDeltaTime();
	}

	@Override
	public void handleEvent(OrthographicCamera camera) {
	}

}
