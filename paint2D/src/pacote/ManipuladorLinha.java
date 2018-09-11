package pacote;

import mdmatrakas.compGrafica.model.Linha;
import mdmatrakas.compGrafica.model.Ponto2D;

public class ManipuladorLinha implements Manipulador {

	private Linha linha;
	private int click;
	private boolean finalizado = false;
	private boolean cancelado = false;
//	private boolean drag = true;

	public ManipuladorLinha(Linha linha) {
		this.linha = linha;
		this.click = 0;
	}

	public void click(float x, float y) {
		if (finalizado || cancelado)
			return;
		if (click == 0) {
			linha.setInicio(new Ponto2D(x, y));
			linha.setFim(new Ponto2D(x, y));
			click++;
//			drag = false;
		} else if (click == 1) {
			linha.setFim(new Ponto2D(x, y));
			finalizado = true;
		}  
	}

	public void press(float x, float y) {
//		linha.setInicio(new Ponto2D(x, y));
//		linha.setFim(new Ponto2D(x, y));
//		System.out.println("ManipuladorLinha1: " + linha);
	}

	public void release(float x, float y) {
//		if (!drag)
//			return;
//		linha.setFim(new Ponto2D(x, y));
//		finalizado = true;
//		System.out.println("ManipuladorLinha2: " + linha);
	}

	public void drag(float x, float y) {
//		if (finalizado || cancelado)
//			return;
//		if (!drag)
//			return;
//
//		linha.setFim(new Ponto2D(x, y));
//		System.out.println("ManipuladorLinha move: " + linha);
	}

	public void move(float x, float y) {
		if (finalizado || cancelado)
			return;
		if (click == 1)
			linha.setFim(new Ponto2D(x, y));
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public boolean isCancelado() {
		return cancelado;
	}

}
