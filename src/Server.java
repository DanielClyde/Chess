import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static Hashtable<Boolean, ObjectOutputStream> writers = new Hashtable<>();
    private static int connectedUsers = 0;

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(58901);
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean isWhite = true;
        while (true) {
            try {
                socket = serverSocket.accept();
                new Handler(socket, isWhite).start();
                isWhite = !isWhite;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static class Handler extends Thread{
        public Socket socket;
        public boolean isWhite;
        public ObjectInputStream input;
        public ObjectOutputStream output;

        Handler(Socket s, boolean white) {
            this.socket = s;
            this.isWhite = white;
            String str = white ? "Welcome White! Waiting for black connection..." : "Welcome Black! Let's play!";
            System.out.println(str);
        }

        @Override
        public void run() {
            try {
                System.out.println("In Run of Handler");
                System.out.println("test");
                this.input = new ObjectInputStream(socket.getInputStream());
                this.output = new ObjectOutputStream(socket.getOutputStream());
                writers.put(this.isWhite, output);
                while (true) {
                    this.output.writeObject(new GameMessage(MessageType.INIT, null, null, this.isWhite));
                    this.output.flush();
                    GameMessage m  = (GameMessage) this.input.readObject();
                    writers.get(!this.isWhite).writeObject(m);
                    writers.get(!this.isWhite).flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (output != null) {
                    writers.remove(this.isWhite);
                }
                try {socket.close(); } catch (IOException e) {e.printStackTrace();}
            }
        }
    }

}