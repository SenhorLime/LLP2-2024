package gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import main.Constantes;
import main.Assets.ProxyAssetManager;

public class Bola extends Movel implements EventListener {
	public Movel objetivo;

	Bola(Texture novoImage, Rectangle novoRect) {// , Sound novoSound, Music novoMusic, Rectangle novoRect) {
		super(novoImage, null, null, novoRect);
		objetivo = null;
	}

	@Override
	public void update() {
		if (objetivo == null) {// Sem persegui��o
			moveLivre();
		} else { // persegui��o
			if (objetivo.isAlive()) {
				persegue();
			} else {
				removeObjetivo();
			}
		}
		mantemBolaNaTela();
	}

	private void moveLivre() {

		float posx = getX() + velocidade.x * Gdx.graphics.getDeltaTime();
		setX(posx);
		// Se saiu da tela por algum lado ou pelo outro, volta.
		if ((posx + Constantes.GAMEOBJECT_DIM > Constantes.LARGURA) || (posx < 0)) {
			velocidade.x = -velocidade.x;
		}

		float posy = getY() + velocidade.y * Gdx.graphics.getDeltaTime();
		setY(posy);
		if ((posy + Constantes.GAMEOBJECT_DIM > Constantes.ALTURA) || (rect.getY() < 0)) {
			velocidade.y = -velocidade.y;
		}

	}

	private void persegue() {
		// Calcular a diferen�a de posi��o nos eixos x e y
		float deltaX = objetivo.getX() - getX();
		float deltaY = objetivo.getY() - getY();

		// Atualizar a posi��o no eixo x
		if (deltaX > 5) {
			velocidade.x = Math.abs(velocidade.x);
		} else if (deltaX < -5) {
			velocidade.x = -Math.abs(velocidade.x);
		}
		rect.setX(rect.getX() + velocidade.x * Gdx.graphics.getDeltaTime());

		// Atualizar a posi��o no eixo y
		if (deltaY > 5) {
			velocidade.y = -Math.abs(velocidade.y);
		} else if (deltaY < -5) {
			velocidade.y = Math.abs(velocidade.y);
		}
		rect.setY(rect.getY() - velocidade.y * Gdx.graphics.getDeltaTime());

		// Verificar se o objetivo foi alcan�ado (toler�ncia de 10 unidades em ambos os
		// eixos)
		if (Math.abs(deltaX) < 10 && Math.abs(deltaY) < 10) {
			removeObjetivo();
		}

	}

	private void novaVelocidade() {
		velocidade.x = MathUtils.random(-Constantes.GAMEOBJECT_SPEED * 2, Constantes.GAMEOBJECT_SPEED * 2);
		velocidade.y = MathUtils.random(-Constantes.GAMEOBJECT_SPEED * 2, Constantes.GAMEOBJECT_SPEED * 2);
	}

	private void mantemBolaNaTela() {
		// Verificar e ajustar a posi��o no eixo x
		if (rect.getX() < 0) {
			rect.setX(0);
		} else if (rect.getX() > Constantes.LARGURA - Constantes.GAMEOBJECT_DIM) {
			rect.setX(Constantes.LARGURA - Constantes.GAMEOBJECT_DIM);
		}

		// Verificar e ajustar a posi��o no eixo y
		if (rect.getY() < 0) {
			rect.setY(0);
		} else if (rect.getY() > Constantes.ALTURA - Constantes.GAMEOBJECT_DIM) {
			rect.setY(Constantes.ALTURA - Constantes.GAMEOBJECT_DIM);
		}
	}

	public Movel getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(Movel objetivo) {
		this.objetivo = objetivo;
		image = ProxyAssetManager.getInstance().getTexture("bollImage1");
	}

	public void removeObjetivo() {
		image = ProxyAssetManager.getInstance().getTexture("bollImage2");
		novaVelocidade();
		objetivo.setAlive(false);
		objetivo = null;
	}

	@Override
	public String toString() {
		return "Bola [sem objetivo=" + (objetivo == null) + ", rect=" + rect + ", velocidade=" + velocidade + "]";
	}

	@Override
	public void update(Movel movel) {
		this.objetivo = movel;
		this.update();
	}
}