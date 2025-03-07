import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.NotBoundException;
import java.util.Scanner;

public class Cliente {
    private static final String IP = "localhost";  // Cambia a la IP si es necesario
    private static final int PUERTO = 1200;  // Puerto que debe coincidir con el servidor

    public static void main(String[] args) throws RemoteException, NotBoundException {
        // Conectarse al registro RMI en la IP y el puerto indicados
        Registry registry = LocateRegistry.getRegistry(IP, PUERTO);
        Interfaz interfaz = (Interfaz) registry.lookup("Calculadora");

        Scanner sc = new Scanner(System.in);
        int eleccion;
        float numero1 = 0, numero2 = 0, resultado = 0;  // Inicializar las variables
        String menu = "\n\n------------------\n\n[-1] => Salir\n[0] => Sumar\n[1] => Restar\n[2] => Multiplicar\n[3] => Dividir\n[4] => Raíz Cuadrada\nElige: ";

        do {
            System.out.println(menu);

            try {
                eleccion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                eleccion = -1;  // Si hay error de entrada, salir
            }

            if (eleccion != -1) {
                if (eleccion != 4) {  // Si no es la opción de raíz cuadrada
                    System.out.println("Ingresa el número 1: ");
                    try {
                        numero1 = Float.parseFloat(sc.nextLine());
                    } catch (NumberFormatException e) {
                        numero1 = 0;
                    }

                    System.out.println("Ingresa el número 2: ");
                    try {
                        numero2 = Float.parseFloat(sc.nextLine());
                    } catch (NumberFormatException e) {
                        numero2 = 0;
                    }
                } else {  // Para la opción de raíz cuadrada, solo un número
                    System.out.println("Ingresa el número para calcular la raíz cuadrada: ");
                    try {
                        numero1 = Float.parseFloat(sc.nextLine());
                    } catch (NumberFormatException e) {
                        numero1 = 0;
                    }
                }

                // Realizar la operación según la elección del usuario
                switch (eleccion) {
                    case 0:
                        resultado = interfaz.sumar(numero1, numero2);
                        break;
                    case 1:
                        resultado = interfaz.restar(numero1, numero2);
                        break;
                    case 2:
                        resultado = interfaz.multiplicar(numero1, numero2);
                        break;
                    case 3:
                        resultado = interfaz.dividir(numero1, numero2);
                        break;
                    case 4:
                        resultado = interfaz.raizCuadrada(numero1);
                        break;
                }

                // Mostrar el resultado o un mensaje de error si es NaN
                if (Float.isNaN(resultado)) {
                    System.out.println("No se puede calcular la raíz cuadrada de un número negativo.");
                } else {
                    System.out.println("Resultado => " + resultado);
                }

                System.out.println("Presiona ENTER para continuar");
                sc.nextLine();  // Para pausar antes de continuar
            }
        } while (eleccion != -1);  // El ciclo continúa hasta que el usuario elija salir
    }
}