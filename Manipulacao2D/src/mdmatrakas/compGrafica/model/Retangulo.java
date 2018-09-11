package mdmatrakas.compGrafica.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import mdmatrakas.compGrafica.model.util.Manipulador;
import mdmatrakas.compGrafica.model.util.ManipuladorRetangulo;

@XmlRootElement(name = "REATANGULO")
@XmlType(name = "REATANGULO", factoryMethod = "getNewInstance")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Retangulo extends Quadrilatero {

	@Override
	public String toString() {
		return "Retangulo [a=" + a + ", b=" + b + ", c=" + c + ", d=" + d + " transformação=" + t + "]";
	}

	public static Retangulo getNewInstance() {
		return new Retangulo();
	}

	public Manipulador criarManipulador() {
		return new ManipuladorRetangulo(this);
	}

}
