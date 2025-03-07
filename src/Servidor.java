import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Servidor {
    private static final int PUERTO = 1200;

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        // Crear una instancia del objeto remoto
        Remote remote = UnicastRemoteObject.exportObject(new Interfaz() {
            @Override
            public float sumar(float numero1, float numero2) throws RemoteException {
                return numero1 + numero2;
            }

            @Override
            public float restar(float numero1, float numero2) throws RemoteException {
                return numero1 - numero2;
            }

            @Override
            public float multiplicar(float numero1, float numero2) throws RemoteException {
                return numero1 * numero2;
            }

            @Override
            public float dividir(float numero1, float numero2) throws RemoteException {
                if (numero2 == 0) {
                    throw new RemoteException("No se puede dividir por cero");
                }
                return numero1 / numero2;
            }

            @Override
            public float raizCuadrada(float numero) throws RemoteException {
                if (numero < 0) {
                    return Float.NaN;  // Si el número es negativo, devolver NaN
                }
                return (float) Math.sqrt(numero);  // Si no, devolver la raíz cuadrada
            }
        }, 0);

        // Crear y registrar el objeto en el registro RMI
        Registry registry = LocateRegistry.createRegistry(PUERTO);
        System.out.println("Servidor escuchando en el puerto " + PUERTO);
        registry.bind("Calculadora", remote);  // Registrar el objeto remoto como 'Calculadora'
    }
}