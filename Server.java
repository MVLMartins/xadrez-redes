import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server {

    private int port;
    private List<ObjectOutputStream> clients;
    private ServerSocket server;
    private Socket playerBranco;
    private Socket playerPreto;


    public static void main(String[] args) throws IOException {
        new Server(12345).run();
    }

    public Server(int port) {
        this.port = port;
        this.clients = new ArrayList<ObjectOutputStream>();
    }

    public void run() throws IOException {
        server = new ServerSocket(port) {
            protected void finalize() throws IOException {
                this.close();
            }
        };
        System.out.println("Port 12345 is now open.");
        System.out.println("teste1");

        while (true) {
            // accepts a new client
            Socket client = server.accept();
            System.out.println("Connection established with client: " + client.getInetAddress().getHostAddress());

            if(playerBranco == null){
                playerBranco = client;

                ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(client.getInputStream());
                Movimento m = new Movimento(1,1,1,1);
                m.cor = "branca";
                out.writeObject(m);
                this.clients.add(out);

                // create a new thread for client handling
                new Thread(new ClientHandler(this, client.getInputStream(),in)).start();
            }else if (playerPreto == null){
                playerPreto = client;

                ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(client.getInputStream());
                Movimento m = new Movimento(1,1,1,1);
                m.cor = "preta";
                out.writeObject(m);

                this.clients.add(out);

                // create a new thread for client handling
                new Thread(new ClientHandler(this, client.getInputStream(),in)).start();
            }

        }
    }

    void broadcastMessages(Movimento response) throws IOException {
        for (ObjectOutputStream client : this.clients) {
            client.writeObject(response);
        }
    }
}

//manda mensagens pros clientes
class ClientHandler implements Runnable {

    private Server server;
    private InputStream client;
    private ObjectInputStream in;

    public ClientHandler(Server server, InputStream client,ObjectInputStream in) {
        this.server = server;
        this.client = client;
        this.in = in;
    }

    @Override
    public void run() {
        String message;

        boolean primeira = false;

        // when there is a new message, broadcast to all
        while (true){
            try {
                //Movimento obj = new Movimento(2,2,2,2);
                Movimento obj = (Movimento) in.readObject();
                System.out.println("olhaaaaa\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                server.broadcastMessages(obj);
            } catch (IOException e) {
                System.out.println("1");
                //primeira = !primeira;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        }




    }
}
