package gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
// import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import main.Constantes;
import main.Assets.ProxyAssetManager;

public class Balde extends Movel {
	public int pontos = 0;
	private static Balde instance;

	public static Balde getInstance() {
		if (instance == null) {
			instance = criarBalde();
		}

		return instance;
	}

	/**
	 * Fun��o para criar um balde
	 * 
	 * @param bucketImage imagem do alde
	 * @param rect        ret�ngulo do balde
	 * @return um novo balde
	 */
	private static Balde criarBalde() {
		// criar um Ret�ngulo para representar logicamente o balde
		Rectangle newRect = new Rectangle();
		newRect.x = Constantes.LARGURA / 2 - Constantes.GAMEOBJECT_DIM / 2; // center the bucket horizontally
		newRect.y = 20; // bottom left corner of the bucket is 20 pixels above the bottom screen edge
		newRect.width = Constantes.GAMEOBJECT_DIM;
		newRect.height = Constantes.GAMEOBJECT_DIM;
		Texture bucketImage = ProxyAssetManager.getInstance().getTexture("bucketImage");
		Balde balde = new Balde(bucketImage, newRect);
		balde.setVelocidade(Constantes.GAMEOBJECT_SPEED * 2, 0);
		return balde;
	}

	private Balde(Texture novoImage, Rectangle novoRect) {
		super(novoImage, novoRect);
	}

	public void update() {
		// processa entradas do usu�rio

		// Normal mode
		moveBaldeTeclado();

		// Hacker mode
		moveBaldeMouse();
	}

	private void moveBaldeTeclado() {
		// Seta velocidade
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			velocidade.x = -Constantes.GAMEOBJECT_SPEED * 2;
		} else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			velocidade.x = Constantes.GAMEOBJECT_SPEED * 2;
		} else {
			velocidade.x = 0;
		}

		// move o balde (MRU: PXf = PXi + vel * tempo)
		rect.setX(rect.getX() + velocidade.x * Gdx.graphics.getDeltaTime());

		// mantem o balde dentro da tela
		if (rect.getX() < 0) {
			rect.setX(0);
		} else if (rect.getX() > Constantes.LARGURA - Constantes.GAMEOBJECT_DIM) {
			rect.setX(Constantes.LARGURA - Constantes.GAMEOBJECT_DIM);
		}
	}

	private void moveBaldeMouse() {
		// Se o mouse for clicado, move o balde para a posi��o
		if (Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			rect.setX(touchPos.x - Constantes.GAMEOBJECT_DIM / 2);
		}
	}
}