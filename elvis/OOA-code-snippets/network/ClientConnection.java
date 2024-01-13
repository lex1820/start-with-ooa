// This class is responsible for setting up a client socket that connects to the server. 
// It creates an ObjectOutputStream and an ObjectInputStream for the connection, reads a Message object from the server, and processes it.

public class ClientConnection {
    private static final Logger LOGGER = Logger.getLogger(ClientConnection.class.getName());

    public void startClient() {
        try (Socket socket = new Socket("localhost", 1234);
             // ObjectOutputStream needs to be created before ObjectInputStream!
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {
            Message msg = (Message) ois.readObject();
            // do something with message
        } catch (IOException | ClassNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "Unable to read message from network.", ex);
            throw new NetworkException("Unable to retrieve message.", ex);
        }
    }
}
