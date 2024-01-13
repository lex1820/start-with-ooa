// THIS CLASS IS NOT NEEDED WHEN SERVER IS PROVIDED BY TEACHERS

// This class is responsible for setting up a server socket that listens for incoming client connections. 
// When a client connects, it accepts the connection, creates an ObjectOutputStream for that connection, and sends a Message object to the client.

public class ServerConnection {
    private static final Logger LOGGER = Logger.getLogger(ServerConnection.class.getName());

    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            while (true) {
                try (Socket socket = serverSocket.accept();
                     ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())) {
                    oos.writeObject(new Message(...));
                } catch (IOException ex) {
                    LOGGER.log(Level.WARNING, "Exception during communication with client.", ex);
                }
            }
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Unable to start server.", ex);
        }
    }
}