import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.lang.management.MonitorInfo;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private String host;
    private int port;
    private String nickname;
    public FrameTabuleiro frame;
    ObjectOutputStream out;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //new Client("192.168.0.4", 12345).run();
        new Client("localhost", 12345).run();
    }

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() throws IOException, ClassNotFoundException {
        // connect client to server
        Socket client = new Socket(host, port);
        Scanner sc = new Scanner(System.in);
        System.out.println("Client successfully connected to server!");

        //set inputs e outputs do server
        OutputStream outputStrem =  client.getOutputStream();
        InputStream  inputStream =  client.getInputStream();


        //stream de objetos
        out = new ObjectOutputStream(outputStrem);
        ObjectInputStream in = new ObjectInputStream(inputStream);

        Movimento m = (Movimento) in.readObject();



        //retorna qual jogador é
        //String tipo = (String) in.readObject();



        // create a new thread for server messages handling
        new Thread(new ReceivedMessagesHandler(inputStream,this,in)).start();


        // ask for a nickname



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


        frame = new FrameTabuleiro(m.cor,this);


        System.out.println("teste3");
        //sc.close();
        //client.close();
    }

    public void  enviaMovimento(int linha, int coluna, int linhaAntiga, int colunaAntiga, Color cor) throws IOException {
        Movimento a = new Movimento(linha, coluna, linhaAntiga, colunaAntiga);
        a.corCasa = cor;
        out.writeObject(a);
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
                System.out.println(response.coluna);
                client.frame.tabuleiro.movePecas(response.linha,response.coluna,response.linhaAntiga,response.colunaAntiga,response.corCasa);
                client.frame.trocaCronometro();
            } catch (IOException e) {
            } catch (ClassNotFoundException e) {
               // e.printStackTrace();
            }

        }
    }
}