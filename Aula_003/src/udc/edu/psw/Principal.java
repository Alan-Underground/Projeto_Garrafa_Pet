package udc.edu.psw; 
//cada classe apos compilada vira um bytecode
//posso pegar todas as classes do programa(todos os bytecodes) e transformar em um arquivo .jar (.zip)

import java.util.Scanner; // para entrada de dados. 
//importa java.util.* traz todas as classes de java.util

public class Principal {
	
	public static void main (String[] arg){
		RestauranteCaseiro restaurante = new RestauranteCaseiro(25);
		
		//criando restaurante com 25 mesas
		//preciso criar um construtor pq tem o nome da classe...  
		// restaurante.precoBebida = 2.5F;  //assim define preço fixo de 2,5 para bebida
		
		//restaurante.custoBebida(2.5F);
		//restaurante.custoPrato(3.7F);
		//restaurante.adicionaCliente(1); //adiciona na mesa 1
		//restaurante.adicionaBebida(1); //adiciona na mesa 1
		
		//Scanner sc; //Scaner tem que importar como se fosse o include do C.  serve leitura mas tem que instanciar...
		Scanner sc = new Scanner(System.in); //System.in é a entrada padrão do sistema TECLADO 	//pega do teclado e passe pelo scanner.
		System.out.println("Qual o custo da bebida?");
		float f = sc.nextFloat();
		restaurante.custoBebida(f);
		
		System.out.println("Qual o custo do prato?");
		f = sc.nextFloat();
		restaurante.custoPrato(f);
		
		restaurante.adicionaCliente(1);
		restaurante.adicionaBebida(1);
		
		System.out.printf("valor da conta da mesa 1 : %.2f", restaurante.calculaConta(1));
		System.out.printf("\nValor da conta da mesa por por cliente: %.2f",restaurante.calculaContaPorCliente(1));
			
	}
}

//fazer um menu de opção para prixima aula. 
// nex le so uma palavra   nextline le ate que o usuário pressione enter