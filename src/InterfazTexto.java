import static org.junit.jupiter.api.Assertions.assertEquals;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InterfazTexto {

    private Servidor servidor;
    private Interfaz interfaz;

    @BeforeEach
    void setUp() throws RemoteException, AlreadyBoundException, NotBoundException {
        // Inicializar el servidor y obtener la interfaz remota
        servidor = new Servidor();
        Registry registry = LocateRegistry.getRegistry("localhost", 1200);
        interfaz = (Interfaz) registry.lookup("Calculadora");
    }

    @Test
    void testSumar() throws RemoteException {
        assertEquals(5.0f, interfaz.sumar(2.0f, 3.0f));
    }

    @Test
    void testRestar() throws RemoteException {
        assertEquals(1.0f, interfaz.restar(3.0f, 2.0f));
    }

    @Test
    void testMultiplicar() throws RemoteException {
        assertEquals(5.0f, interfaz.multiplicar(2.0f, 3.0f));
    }

    @Test
    void testDividir() throws RemoteException {
        assertEquals(2.0f, interfaz.dividir(6.0f, 3.0f));
    }

    @Test
    void testRaizCuadrada() throws RemoteException {
        assertEquals(3.0f, interfaz.raizCuadrada(4.0f));
    }
}
