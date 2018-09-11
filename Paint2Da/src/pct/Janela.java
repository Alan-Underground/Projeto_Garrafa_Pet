package mdmatrakas.compGrafica.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import mdmatrakas.compGrafica.model.util.Manipulador;
import mdmatrakas.compGrafica.transformacao.Transformacao;
import mdmatrakas.compGrafica.util.DrawUtil;

@XmlRootElement(name = "JANELA")
@XmlType(name = "JANELA", factoryMethod = "getNewInstance")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Janela implements Modelo {
	protected Ponto2D inicio;
	protected Ponto2D fim;
	
	protected Transformacao t = Transformacao.getIdentidade();

	public Janela() {
		inicio = new Ponto2D(0,0);
		fim = new Ponto2D(0,0);
	}
	public Janela(Ponto2D inicio, Ponto2D fim) {
		this.inicio = inicio;
		this.fim = fim;
	}

	@Override
	public String toString() {
		return "Janela [inicio=" + inicio + ", fim=" + fim + " transformação=" + t + "]";
	}
	public static Janela getNewInstance() {
        return new Janela();
    }
		
	@XmlElement(name="TRANSFORMACAO", type=Transformacao.class)
	public Transformacao getTransformacao() {
		return t;
	}

	public void setTransformacao(Transformacao t) {
//		this.t = t;
	}

	public void comporTransformacao(Transformacao t) {
//		if(this.t== null) {
//			this.t= Transformacao.getIdentidade();
//		}
//		this.t.compor(t);
	}

	@XmlElement(name="INICIO", type=Ponto2D.class)
	public Ponto2D getInicio() {
		return inicio;
	}
	
	public void setInicio(Ponto2D inicio){
		this.inicio = inicio;
	}
	
	@XmlElement(name="FIM", type=Ponto2D.class)
	public Ponto2D getFim() {
		return fim;
	}

	public void setFim(Ponto2D fim){
		this.fim = fim;
	}
	
	@XmlTransient
	public Ponto2D getCentro() {
		return new Ponto2D((inicio.getX()+fim.getX())/2, (inicio.getY()+fim.getY())/2);
	}

	@XmlTransient
	public float getMaxX() {
		return Math.max(inicio.getX(), fim.getX());
	}

	@XmlTransient
	public float getMinX() {
		return Math.min(inicio.getX(), fim.getX());
	}

	@XmlTransient
	public float getMaxY() {
		return Math.max(inicio.getY(), fim.getY());
	}

	@XmlTransient
	public float getMinY() {
		return Math.min(inicio.getY(), fim.getY());
	}

	@XmlTransient
	public float getLargura() {
		return Math.abs(getMaxX() - getMinX());
	}

	@XmlTransient
	public float getAltura() {
		return Math.abs(getMaxY() - getMinY());
	}

	@XmlTransient
	public List<Ponto2D> getListPontos() {
		List<Ponto2D> l = new ArrayList<Ponto2D>();
		l.add(inicio);
		l.add(fim);
		return l;
	}

	public void setListPontos(List<Ponto2D> pontos) throws IllegalArgumentException {
		if(pontos.size() != 2)
			throw new IllegalArgumentException("Número do pontos inválido");
		
		inicio = pontos.get(0);
		fim = pontos.get(1);
	}

	@XmlTransient
	public float[][] getPontos2D() {
		float[][] v = new float[2][2];
		v[0] = inicio.getCoord2D();
		v[1] = fim.getCoord2D();
		return v;
	}

	@XmlTransient
	public float[][] getPontosCoordHom() {
		float[][] v = new float[2][3];
		v[0] = inicio.getCoordHom();
		v[1] = fim.getCoordHom();
		return v;
	}

	public void setPontos2D(float[][] v)  throws IllegalArgumentException {
		if(v.length != 2 && v[0].length != 2)
			throw new IllegalArgumentException("Número de coordenadas inválido");
		
		inicio.setCoord2D(v[0]);
		fim.setCoord2D(v[1]);
	}

	public void setPontosCoordHom(float[][] v)  throws IllegalArgumentException {
		if(v.length != 2 && v[0].length != 3)
			throw new IllegalArgumentException("Número de coordenadas inválido");
		
		inicio.setCoordHom(v[0]);
		fim.setCoordHom(v[1]);
	}

	public void desenhar(DrawUtil du) {}
	public Manipulador criarManipulador() {
		return null;
	}

}
