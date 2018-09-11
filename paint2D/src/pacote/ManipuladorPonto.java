package pacote;

import mdmatrakas.compGrafica.model.Ponto;

public class ManipuladorPonto implements Manipulador {
	private Ponto ponto;
	private boolean finalizado = false;
	private boolean cancelado = false;

	public ManipuladorPonto(Ponto ponto) {
		this.ponto = ponto;
	}

	public void click(float x, float y) {
		if (finalizado || cancelado)
			return;
		
		ponto.setPonto(x, y);
			finalizado = true;
	}

	public void press(float x, float y) { }

	public void release(float x, float y) { }

	public void drag(float x, float y) { }

	public void move(float x, float y) { }

	public boolean isFinalizado() {
		return finalizado;
	}

	public boolean isCancelado() {
		return cancelado;
	}

}
