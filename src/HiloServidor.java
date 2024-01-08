import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HiloServidor extends Thread{
    private Socket socket;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;
    private final int id;

    public HiloServidor(int id, Socket socket) {
        this.socket = socket;
        this.id = id;
        try {
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        }
        catch (IOException ex) {
            System.out.println("ERROR: No se pudo crear el servidor");
            throw new RuntimeException(ex);
        }
    }

    public void desconectar() {
        try {
            socket.close();
        }
        catch (IOException ex) {
            System.out.println("ERROR: Ha ocurrido un error al cerrar la conexion");
            throw new RuntimeException(ex);
        }
    }

    public void run() {
        String accion = "";
        try {
            accion = dataInputStream.readUTF();
        } catch (IOException e) {
            System.out.println("ERROR: Ha ocurrido un error al recibir el mensaje del cliente");
            throw new RuntimeException(e);
        }
        if(!accion.isEmpty()) {
            System.out.println("Cliente " + id + ": ->" + accion);
            try {
                dataOutputStream.writeUTF("Saludos desde el servidor!");
            } catch (IOException e) {
                System.out.println("Ha ocurrido un error al enviar respuesta al cliente");
                throw new RuntimeException(e);
            }
        }
        desconectar();
    }
}
