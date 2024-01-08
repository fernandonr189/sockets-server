import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    static final int PUERTO = 8080;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket;
        System.out.println("Iniciando servidor...");
        try {
            serverSocket = new ServerSocket(PUERTO);
        }
        catch (Exception ex) {
            System.out.println("ERROR, no se ha podido inicializar el servidor!");
            throw new RuntimeException(ex);
        }
        System.out.println("Servidor inicializado");
        int id = 0;

        try {
            while(true) {
                Socket socket = serverSocket.accept();
                System.out.println("Nueva conexion entrante!" + socket);
                new HiloServidor(id, socket).start();
                id++;
            }
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}