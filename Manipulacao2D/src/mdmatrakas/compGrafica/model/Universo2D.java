package mdmatrakas.compGrafica.model;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlType;

import mdmatrakas.compGrafica.util.DrawUtil;

@XmlRootElement(name = "UNIVERSO")
@XmlType(name = "UNIVERSO", factoryMethod = "getNewInstance")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Universo2D {
	/**
	 * Nome da unidade de medida utilizada no universo.
	 */
	private String unidade;
	
	/**
	 * Especificação da janela de visualização utilizada no universo.
	 */
	private Janela janela;
	
	
	/**
	 * Ponto inícial das coordenadas válidas do universo.
	 */
	private Ponto2D inicio;
	/**
	 * Ponto final das coordenadas válidas do universo.
	 */
	private Ponto2D fim;
	
	/**
	 * Lista de modelos existentes no universo.
	 */
	@XmlElements({ 
	    @XmlElement(name="LINHA", type=Linha.class),
	    @XmlElement(name="PONTO", type=Ponto.class),
	    @XmlElement(name="TRIANGULO", type=Triangulo.class),
	    @XmlElement(name="RETANGULO", type=Retangulo.class),
	    @XmlElement(name="QUADRILATERO", type=Quadrilatero.class),
	    @XmlElement(name="BEZIER", type=Bezier.class)
	})
	@XmlElementWrapper(name="MODELOS")
	List<Modelo> objetos;
	
    public Universo2D() {
		super();
		this.unidade = "Milimetro";
		this.janela = new Janela();
		this.inicio = new Ponto2D();
		this.fim = new Ponto2D();
		this.objetos = new ArrayList<Modelo>();
	}

	public Universo2D(String unidade, Janela janela, Ponto2D inicio, Ponto2D fim) {
		super();
		this.unidade = unidade;
		this.janela = janela;
		this.inicio = inicio;
		this.fim = fim;
		this.objetos = new ArrayList<Modelo>();
	}

	@Override
	public String toString() {
		return "Universo2D [unidade=" + unidade + ", janela=" + janela + ", inicio=" + inicio + ", fim=" + fim
				+ ", objetos=" + objetos + "]";
	}
	
	public void desenharModelos(DrawUtil du) {
		for(Modelo m : objetos) {
			m.desenhar(du);
		}
		
	}

	@XmlElement(name="UNIDADE")
	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	@XmlTransient
	public float getLargura() {
		return janela.getLargura();
	}

	@XmlTransient
	public float getAltura() {
		return janela.getAltura();
	}

	@XmlElement(name="JANELA")
	public Janela getJanela() {
		return janela;
	}

	public void setJanela(Janela janela) {
		this.janela = janela;
	}

	@XmlElement(name="INICIO", type=Ponto2D.class)
	public Ponto2D getInicio() {
		return inicio;
	}

	public void setInicio(Ponto2D inicio) {
		this.inicio = inicio;
	}

	@XmlElement(name="FIM", type=Ponto2D.class)
	public Ponto2D getFim() {
		return fim;
	}

	public void setFim(Ponto2D fim) {
		this.fim = fim;
	}

	public static Universo2D getNewInstance() {
        return new Universo2D();
    }
	
	public void incluir(Modelo modelo) {
		objetos.add(modelo);
	}
	
	public boolean remover(Modelo modelo) {
		return objetos.remove(modelo);
	}
	
	public Modelo remove(int indice) {
		return objetos.remove(indice);
	}
	
	public List<Modelo> getModelos() {
		return objetos;
	}
}
