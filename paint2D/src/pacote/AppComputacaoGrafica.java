package pacote;

import javax.swing.JFrame;

import mdmatrakas.compGrafica.model.Janela;
import mdmatrakas.compGrafica.model.Ponto2D;
import mdmatrakas.compGrafica.model.Universo2D;

public class AppComputacaoGrafica {
	private static AppComputacaoGrafica app;
	private FrameComputacaoGrafica frame;
	//private Universo2D universo;
	
	private Documento doc;

	public static AppComputacaoGrafica getApp() {
		if (app == null) {
			app = new AppComputacaoGrafica();
			app.initialize();
		}

		return app;
	}

	private void initialize() {
		doc = new Documento();
		
		Universo2D universo = new Universo2D();
		universo.setInicio(new Ponto2D(-5, -5));
		universo.setFim(new Ponto2D(5, 5));
		universo.setUnidade("Milimetro");
		universo.setJanela(new Janela(new Ponto2D(-4, -2), new Ponto2D(4, 2)));
		
		doc.setUniverso(universo);

		frame = new FrameComputacaoGrafica(doc);
		frame.setTitle("Computação Gráfica");
		frame.setSize(1000, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	private AppComputacaoGrafica() { }

	public static void main(String[] args) {
		getApp();

	}

	public void setStatusText(String text) {
		frame.setStatusText(text);
	}

	public Documento getDocumento() {
		return doc;
	}

}
