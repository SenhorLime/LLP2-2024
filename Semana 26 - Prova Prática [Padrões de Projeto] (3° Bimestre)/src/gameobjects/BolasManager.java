package gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import main.Constantes;
import main.Assets.ProxyAssetManager;

public class BolasManager {
	Bola bolas[];

	public BolasManager() {
		bolas = new Bola[2];
		for (int i = 0; i < bolas.length; i++) {
			bolas[i] = criarBola();
			Publisher.getInstance().subscribe(bolas[i]);
		}
	}

	public Bola criarBola() {
		Rectangle rect = new Rectangle();
		Vector2 velocidade = new Vector2();

		rect.x = MathUtils.random(Constantes.GAMEOBJECT_DIM, Constantes.LARGURA - Constantes.GAMEOBJECT_DIM);
		rect.y = MathUtils.random(Constantes.GAMEOBJECT_DIM, Constantes.ALTURA - Constantes.GAMEOBJECT_DIM);
		rect.width = Constantes.GAMEOBJECT_DIM;
		rect.height = Constantes.GAMEOBJECT_DIM;
		velocidade.x = MathUtils.random(-Constantes.GAMEOBJECT_SPEED * 2, Constantes.GAMEOBJECT_SPEED * 2);
		velocidade.y = MathUtils.random(-Constantes.GAMEOBJECT_SPEED * 2, Constantes.GAMEOBJECT_SPEED * 2);
		Texture bollImage = ProxyAssetManager.getInstance().getTexture("bollImage2");
		Bola bola = new Bola(bollImage, rect);
		bola.setVelocidade(velocidade);

		return bola;
	}

	public void update() {
		for (int i = 0; i < bolas.length; i++) {
			bolas[i].update();
		}
	}

	public void draw(SpriteBatch batch) {
		for (int i = 0; i < bolas.length; i++) {
			bolas[i].draw(batch);
		}
	}

	public void setObjetivo(Movel pingo) {
		for (int i = 0; i < bolas.length; i++) {
			if (bolas[i].objetivo == null) {
				bolas[i].objetivo = pingo;
				break;
			}
		}		
		
	}

}