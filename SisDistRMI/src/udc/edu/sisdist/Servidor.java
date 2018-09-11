import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
	 
public class Servidor implements InterfaceRemota {
	
    public Servidor() {}

    public String digaAlo() {
	return "Alo, mundo!";
    }
	
	//public double Somar(double a, double b) { //retorna a + b
	//return "a+b";
    //}
	
	//public double Subtrair(double a, double b) //retorna a – b
	//return "a-b";
    //}
	
	//double dividir(double a, double b) // retorna a/b
	//return "a/b";
    //}
	
	//public double multi(double a, double b) //retorn a*b
	//return "a*b";
	//}
	
	
    public static void main(String args[]) {	
	try {
	    Servidor obj = new Servidor();
            // nao tirar o segundo argumento = 0 
            // se nao ele busca um Servidor_stub. O retorno do metodoo sera diferente
            // Maiores detalhes em http://java.sun.com/j2se/1.5.0/docs/guide/rmi/relnotes.html ou
            // http://forum.java.sun.com/thread.jspa?threadID=5150623&messageID=9564591
            InterfaceRemota proxy = (InterfaceRemota)UnicastRemoteObject.exportObject(obj, 0);
       
            String refRemota = proxy.toString();
            System.out.println("* Proxy: " + refRemota.substring(refRemota.indexOf("endpoint")));
 	    // Bind the remote object's stub in the registry            
	    Registry registro = LocateRegistry.getRegistry();
            refRemota = registro.toString();
            System.out.println("* Registro: " + refRemota.substring(refRemota.indexOf("endpoint")));
            // se o string alo ja estiver associado a outro objeto remoto, uma excecao ocorre
	    registro.rebind("Alo", proxy);
            System.err.println("* Servidor pronto");
	} catch (Exception e) {
	    System.err.println("! Erro no servidor: " + e.getMessage());
//	    e.printStackTrace();
	}
    }
}
