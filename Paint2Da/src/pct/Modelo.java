package mdmatrakas.compGrafica.model;

import java.util.List;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.sun.xml.internal.bind.AnyTypeAdapter;

import mdmatrakas.compGrafica.model.util.Manipulador;
import mdmatrakas.compGrafica.transformacao.Transformacao;
import mdmatrakas.compGrafica.util.DrawUtil;

@XmlJavaTypeAdapter(AnyTypeAdapter.class)
public interface Modelo {
	
	public abstract Ponto2D getCentro();
	public abstract float getMaxX();
	public abstract float getMinX();
	public abstract float getMaxY();
	public abstract float getMinY();
	
	public abstract float getLargura();
	public abstract float getAltura();

	public abstract List<Ponto2D> getListPontos();
	public abstract float[][] getPontos2D();
	public abstract float[][] getPontosCoordHom();
	public abstract void setListPontos(List<Ponto2D> pontos) throws IllegalArgumentException;
	public abstract void setPontos2D(float[][] v) throws IllegalArgumentException;
	public abstract void setPontosCoordHom(float[][] v) throws IllegalArgumentException;
	
	public abstract void setTransformacao(Transformacao t);
	public abstract void comporTransformacao(Transformacao t);
	
	public abstract void desenhar(DrawUtil du);
	public abstract Manipulador criarManipulador();

}
