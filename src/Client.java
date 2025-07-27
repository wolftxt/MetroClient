
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

    private Socket s;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    private MessageCallback callback;

    public Client(String adress, int port, MessageCallback callback) throws IOException {

        s = new Socket(adress, port);
        out = new ObjectOutputStream(s.getOutputStream());
        in = new ObjectInputStream(s.getInputStream());

        this.callback = callback;
        Thread t = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        callback.newMessage((Message) in.readObject());
                    } catch (IOException ex) {
                        closeAll();
                        throw new RuntimeException("IOException");
                    } catch (ClassNotFoundException ex) {
                        closeAll();
                        throw new RuntimeException("ClassNotFoundException");
                    }
                }

            }
        };
        t.start();
    }

    public void sendMessage(Message message) throws IOException {
        out.writeObject(message);
    }
    
    public void closeAll(){
        try {
            s.close();
            out.close();
            in.close();
        } catch (IOException ex) {
            throw new RuntimeException("Error while closing the Socket, ObjectOutputStream and ObjectInputStream");
        }
    }

}
