package ch.heigvd.api.calc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Calculator server implementation - multi-thread
 */
public class Server {

    private final static Logger LOG = Logger.getLogger(Server.class.getName());

    /**
     * Main function to start the server
     */
    public static void main(String[] args) {
        // Log output on a single line
        System.setProperty("java.util.logging.SimpleFormatter.format", "%4$s: %5$s%6$s%n");

        (new Server()).start();
    }

    /**
     * Start the server on a listening socket.
     */
    private void start() {

        /* TODO: implement the receptionist server here.
         *  The receptionist just creates a server socket and accepts new client connections.
         *  For a new client connection, the actual work is done in a new thread
         *  by a new ServerWorker.
         */

        ServerSocket serverSocket = null;
        Socket clientSocket = null;

        LOG.log(Level.INFO, "Creating a server socket and binding it on any of the available network interfaces and on port {0}", new Object[]{Integer.toString(4269)});
        try{
            serverSocket = new ServerSocket(4269);
            while(true){
                LOG.log(Level.INFO, "Waiting (blocking) for a connection request on {0} : {1}", new Object[]{serverSocket.getInetAddress(), Integer.toString(serverSocket.getLocalPort())});
                clientSocket = serverSocket.accept();

                LOG.info("Starting the Receptionist Worker on a new thread...");
                new Thread(new ServerWorker(clientSocket)).start();
            }
        }
        catch (IOException e){

        }

    }
}
