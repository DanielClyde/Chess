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
        connectedUsers = 0;
        System.out.println("Chess Server is Running...");
        ExecutorService pool = Executors.newFixedThreadPool(2);
        try (ServerSocket listener = new ServerSocket(58901)) {
            pool.execute(new Handler(listener.accept(), true));
            connectedUsers++;
            pool.execute(new Handler(listener.accept(), false));
            connectedUsers++;
        }
    }

    private static class Handler implements Runnable{
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
                System.out.println("In handler");
                this.input = new ObjectInputStream(socket.getInputStream());
                this.output = new ObjectOutputStream(socket.getOutputStream());
                writers.put(this.isWhite, output);
                while(connectedUsers < 2) {
                    System.out.print("#");
                }
                System.out.println();
                while (true) {
                    try {
                        GameMessage m  = (GameMessage) this.input.readObject();
                        writers.get(!this.isWhite).writeObject(m);
                        writers.get(!this.isWhite).flush();
                    } catch (Exception e) {e.printStackTrace();}
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                if (output != null) {
                    writers.remove(this.isWhite);
                }
                try {socket.close(); } catch (IOException e) {}
            }
        }
    }

}