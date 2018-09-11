import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceRemota extends Remote {
    String digaAlo() throws RemoteException;
}
 
