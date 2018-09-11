package mdmatrakas.compGrafica.model;

public class ModeloFactory {
    public static Class<?> PONTO = Ponto.class;
    public static Class<?> LINHA = Linha.class;
    public static Class<?> TRIANGULO = Triangulo.class;
    public static Class<?> RETANGULO = Retangulo.class;
    public static Class<?> QUADRILATERO = Quadrilatero.class;
    public static Class<?> BEZIER = Bezier.class;

    /**
     * Factory method for instantiation of concrete factories.
     */
    public static Modelo novoModelo(Class<?> modeloClass) {
    	if(modeloClass.asSubclass(Modelo.class) == null)
    		throw new IllegalArgumentException("Classe desconhecida: " + modeloClass.getSimpleName());
    	
        try {
            Modelo modelo = (Modelo) modeloClass.newInstance();
            return modelo;
        }
        catch (Exception ex) {
            throw new RuntimeException("Can not instantiate Modelo: " + modeloClass, ex);
        }
    }

}
