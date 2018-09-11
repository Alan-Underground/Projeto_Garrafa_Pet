package udc.edu.psw;

public class MesaDeRestaurante {
private int qtdClientes; //clientes na mesa
private int qtdBebidas; //bebidas servidas na mesa
private int limiteClientes; //quantidade de clientes na mesa;

//criando um construtor para definir quantos clientes cabem em uma mesa.
public MesaDeRestaurante() {
	
}
	
	//adiciona uma bebida na mesa
			public void adicionaBebida() {
				qtdBebidas++;	
			}
	//adiciona um cliente na mesa		
			public void adicionaCliente() {
				qtdClientes++;
				if (qtdClientes < limiteClientes)
					qtdClientes++;
			}	
			
			//caclua o valor da conta de uma deeterminada mesa 
			public float calculaConta(float precoBebida, float precoPrato) { //conta da mesa conforme numero de clientes e de bebidas.
				float total = qtdBebidas * precoBebida;
				total += qtdClientes * precoPrato;
				//parte 3 do exercicio da pra fazer de duas formas a primeira esta na linha abaixo
				//float totalDivido = qtdClientePorMesa[mesa] / total;
				return total;	
				}
			
			
			public void fecharMesa() {
				qtdBebidas = 0;
				qtdClientes = 0;
			}
}
