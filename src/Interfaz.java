import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Interfaz extends Remote {
    float sumar(float numero1, float numero2) throws RemoteException;
    float restar(float numero1, float numero2) throws RemoteException;
    float multiplicar(float numero1, float numero2) throws RemoteException;
    float dividir(float numero1, float numero2) throws RemoteException;
    float raizCuadrada(float numero) throws RemoteException;  // Método raíz cuadrada
}