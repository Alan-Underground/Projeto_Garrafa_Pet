package mdmatrakas.compGrafica.transformacao;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import mdmatrakas.compGrafica.model.Ponto2D;

@XmlRootElement(name = "TRANSFORMACAO")
@XmlType(name = "TRANSFORMACAO", factoryMethod = "getNewInstance")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Transformacao {

	@XmlElement(name = "MATRIZ")
	private float[][] transformacao;
	
	public Transformacao() {
		transformacao = new float[3][3];
		for(int x = 0; x < 3; x++) {
			transformacao[x][x] = 1; 
		}
	}
	
	public void show(float[][] pontos) {
		for(int x = 0; x < pontos.length; x++) {
			for(int y = 0; y < 3; y++) {
				System.out.printf("%f; ", pontos[x][y]);
			}
			System.out.println("");
		}
	}
	
	public void show() {
		for(int x = 0; x < transformacao.length; x++) {
			for(int y = 0; y < 3; y++) {
				System.out.printf("%f; ", transformacao[x][y]);
			}
			System.out.println("");
		}
	}
	
	public static Transformacao getNewInstance() {
		return new Transformacao();
	}

	public float[][] aplicar(float[][] pontos)  throws IllegalArgumentException {
		if(pontos == null)
			throw new IllegalArgumentException("Matriz de pontos nula!");
		if(pontos[0].length != 3)
			throw new IllegalArgumentException("Número de coordenadas inválido");
		
		float[][] res = new float[pontos.length][3];
		
//		System.out.println("Matriz de transformacao");
//		show();
//		System.out.println("Matriz de pontos");
//		show(pontos);
		
		for(int x = 0; x < pontos.length; x++) {
			for(int y = 0; y < 3; y++) {
				for(int c = 0; c < 3; c++) {
					res[x][y] = res[x][y] + pontos[x][c] * transformacao[c][y];
				}
			}
		}
		
//		System.out.println("Matriz transformada");
//		show(res);
		
		return res;
	}
	
	public static Transformacao getIdentidade() {
		Transformacao t = new Transformacao();
		for(int x = 0; x < 3; x++) {
			t.transformacao[x][x] = 1; 
		}
		return t;
	}
	
	public static Transformacao getTranslacao(float dx, float dy) {
		Transformacao t = getIdentidade();
		t.transformacao[2][0] = dx;
		t.transformacao[2][1] = dy;
		return t;		
	}
	
	public static Transformacao getEscala(float sx, float sy) {
		Transformacao t = getIdentidade();
		t.transformacao[0][0] = sx;
		t.transformacao[1][1] = sy;
		return t;		
	}
	
	public static Transformacao getEscala(float sx, float sy, Ponto2D p) {
		Transformacao t = getIdentidade();
		t.compor(getTranslacao(-p.getX(), -p.getY()));
		t.compor(getEscala(sx, sy));
		t.compor(getTranslacao(p.getX(), p.getY()));
		return t;		
	}
	
	public static Transformacao getReflexao(boolean x, boolean y) {
		Transformacao t = getIdentidade();
		t.transformacao[0][0] = y ? -1 : 1;
		t.transformacao[1][1] = x ? -1 : 1;
		return t;		
	}

	public static Transformacao getCisalhamento(float cx, float cy) {
		Transformacao t = getIdentidade();
		t.transformacao[1][0] = cx;
		t.transformacao[0][1] = cy;
		return t;		
	}
	
	public static Transformacao getCisalhamento(float cx, float cy, Ponto2D p) {
		Transformacao t = getIdentidade();
		t.compor(getTranslacao(-p.getX(), -p.getY()));
		t.compor(getCisalhamento(cx, cy));
		t.compor(getTranslacao(p.getX(), p.getY()));
		return t;		
	}
	
	public static Transformacao getRotacao(float rad) {
		Transformacao t = getIdentidade();
		t.transformacao[0][0] = t.transformacao[1][1] = (float) Math.cos(rad);
		t.transformacao[0][1] = (float) Math.sin(rad);
		t.transformacao[1][0] = -((float) Math.sin(rad));
		return t;		
	}
	
	public static Transformacao getRotacao(float rad, Ponto2D p) {
		Transformacao t = getIdentidade();
		t.compor(getTranslacao(-p.getX(), -p.getY()));
		t.compor(getRotacao(rad));
		t.compor(getTranslacao(p.getX(), p.getY()));
		return t;		
	}
	
	public static Transformacao getRotacaoGraus(float g) {
		Transformacao t = getIdentidade();
		float rad = (float) Math.toRadians(g);
		t.transformacao[0][0] = t.transformacao[1][1] = (float) Math.cos(rad);
		t.transformacao[0][1] = (float) Math.sin(rad);
		t.transformacao[1][0] = -((float) Math.sin(rad));
		return t;		
	}
	
	public static Transformacao getRotacaoGraus(float g, Ponto2D p) {
		Transformacao t = getIdentidade();
		t.compor(getTranslacao(-p.getX(), -p.getY()));
		t.compor(getRotacaoGraus(g));
		t.compor(getTranslacao(p.getX(), p.getY()));
		return t;		
	}
	
	public void compor(Transformacao t) {
		float[][] aux = new float[3][3];
		for(int x = 0; x < 3; x++) {
			for(int y = 0; y < 3; y++) {
				for(int c = 0; c < 3; c++) {
					aux[x][y] += transformacao[x][c] * t.transformacao[c][y];
				}
			}
		}
		for(int x = 0; x < 3; x++) {
			for(int y = 0; y < 3; y++) {
				transformacao[x][y] = aux[x][y];
			}
		}
	}
	
}
