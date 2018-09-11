package mdmatrakas.compGrafica.model.util;

import mdmatrakas.compGrafica.model.Ponto2D;
import mdmatrakas.compGrafica.model.Triangulo;

public class ManipuladorTriangulo implements Manipulador {

	private Triangulo triangulo;
	private int click;
	private boolean finalizado = false;
	private boolean cancelado = false;

	public ManipuladorTriangulo(Triangulo triangulo) {
		this.triangulo = triangulo;
		this.click = 0;
	}

	public void click(float x, float y) {
		if (finalizado || cancelado)
			return;
		if (click == 0) {
			triangulo.setA(new Ponto2D(x, y));
			triangulo.setB(new Ponto2D(x, y));
			triangulo.setC(new Ponto2D(x, y));
			click++;
		} else if (click == 1) {
			triangulo.setB(new Ponto2D(x, y));
			click++;
		} else if (click == 2) {
			triangulo.setC(new Ponto2D(x, y));
			finalizado = true;
		}
	}

	public void press(float x, float y) { }

	public void release(float x, float y) { }

	public void drag(float x, float y) { }

	public void move(float x, float y) {
		if (finalizado || cancelado)
			return;
		if (click == 1) {
			triangulo.setB(new Ponto2D(x, y));
		} else if (click == 2) {
			triangulo.setC(new Ponto2D(x, y));
		}
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public boolean isCancelado() {
		return cancelado;
	}
}
