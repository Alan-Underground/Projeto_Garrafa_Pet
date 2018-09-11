package mdmatrakas.compGrafica;

import mdmatrakas.compGrafica.model.Modelo;
import mdmatrakas.compGrafica.model.Universo2D;
import mdmatrakas.compGrafica.model.util.Manipulador;
import mdmatrakas.compGrafica.util.DrawUtil;

public class Documento {
	private Universo2D universo;
	private Modelo novoModelo;
	private Manipulador manipulador;

	public Documento() {
	}

	public Documento(Universo2D universo) {
		this.universo = universo;
	}

	public void setUniverso(Universo2D universo) {
		this.universo = universo;
	}

	public Universo2D getUniverso() {
		return universo;
	}

	public void mouseClick(float x, float y) {
		if (manipulador != null) {
			manipulador.click(x, y);
			testaManipulador();
		}
	}

	public void mousePress(float x, float y) {
		if (manipulador != null) {
			manipulador.press(x, y);
			testaManipulador();
		}
	}

	public void mouseRelease(float x, float y) {
		if (manipulador != null) {
			manipulador.release(x, y);
			testaManipulador();
		}
	}

	public void mouseDrag(float x, float y) {
		if (manipulador != null) {
			manipulador.drag(x, y);
			testaManipulador();
		}
	}

	public void mouseMove(float x, float y) {
		if (manipulador != null) {
			manipulador.move(x, y);
			testaManipulador();
		}
	}

	public void novoModelo(Modelo m) {
		novoModelo = m;
		manipulador = m.criarManipulador();
	}

	public void desenharModelos(DrawUtil du) {
		if (universo != null)
			universo.desenharModelos(du);
		if (novoModelo != null)
			novoModelo.desenhar(du);
	}

	private void testaManipulador() {
		if (manipulador.isFinalizado()) {
			universo.incluir(novoModelo);
			novoModelo = null;
			manipulador = null;
		} else if (manipulador.isCancelado()) {
			novoModelo = null;
			manipulador = null;
		}
	}

}
