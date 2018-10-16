import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private String host;
    private int port;
    private String nickname;
    public FrameTabuleiro frame;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new Client("localhost", 12345).run();
    }

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() throws IOException, ClassNotFoundException {
        // connect client to server
        Socket client = new Socket(host, port);
        System.out.println("Client successfully connected to server!");

        //set inputs e outputs do server
        OutputStream outputStrem =  client.getOutputStream();
        InputStream  inputStream =  client.getInputStream();


        //stream de objetos
        ObjectOutputStream out = new ObjectOutputStream(outputStrem);
        ObjectInputStream in = new ObjectInputStream(inputStream);

        //retorna qual jogador é
        String tipo = (String) in.readObject();

        // create a new thread for server messages handling
        new Thread(new ReceivedMessagesHandler(inputStream,this,in)).start();


        // ask for a nickname
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a nickname: ");
        nickname = sc.nextLine();

        // read messages from keyboard and send to server
        System.out.println("Send messages: ");

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (ClassNotFoundException e) {
           // e.printStackTrace();
        } catch (InstantiationException e) {
           // e.printStackTrace();
        } catch (IllegalAccessException e) {
            //e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
           // e.printStackTrace();
        }


        frame = new FrameTabuleiro(tipo,out);


        System.out.println("teste3");
        sc.close();
        client.close();
    }

    public void recebiMovimento(){

    }

}


class ReceivedMessagesHandler implements Runnable {

    private InputStream serverIn;
    private Client client;
    private ObjectInputStream in ;

    public ReceivedMessagesHandler(InputStream in, Client client,ObjectInputStream out) {
        this.serverIn = in;
        this.client = client;
        this.in = out;
    }

    @Override
    public void run() {
        // receive server messages and print out to screen


        while (true){
            try {
                Movimento response = (Movimento) in.readObject();
                client.frame.tabuleiro.movePecas(response.getLinha(),response.getColuna(),response.getLinhaAntiga(),response.getColunaAntiga());

            } catch (IOException e) {
            } catch (ClassNotFoundException e) {
               // e.printStackTrace();
            }

        }
    }
}