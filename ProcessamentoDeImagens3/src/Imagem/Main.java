package Imagem;



public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
    LeituraImagem leitura = new LeituraImagem();
    leitura.jButton1ActionPerformed("FotoIdeal2.jpg","FotoTeste3.jpg");
    
    LeituraImagem imprime1 = new LeituraImagem();
    imprime1.imprime1();
    
    LeituraImagem imprime2 = new LeituraImagem();
    imprime2.imprime2();
    
    LeituraImagem imprime3 = new LeituraImagem();
    imprime3.imprime3();
    
    LeituraImagem imprime4 = new LeituraImagem();
    imprime4.imprime4();
	}

}