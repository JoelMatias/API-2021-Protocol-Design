package ch.heigvd.api.calc;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Calculator client implementation
 */
public class Client {

    private static final Logger LOG = Logger.getLogger(Client.class.getName());

    /**
     * Main function to run client
     *
     * @param args no args required
     */
    public static void main(String[] args) {
        // Log output on a single line
        System.setProperty("java.util.logging.SimpleFormatter.format", "%4$s: %5$s%6$s%n");

        BufferedReader stdin = null;

        /* TODO: Implement the client here, according to your specification
         *   The client has to do the following:
         *   - connect to the server
         *   - initialize the dialog with the server according to your specification
         *   - In a loop:
         *     - read the command from the user on stdin (already created)
         *     - send the command to the server
         *     - read the response line from the server (using BufferedReader.readLine)
         */

        Socket clientSocket = null;
        BufferedReader reader = null;
        BufferedWriter writer = null;

        stdin = new BufferedReader(new InputStreamReader(System.in));

        try {
            clientSocket = new Socket("10.192.104.133", 4269);
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
            writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"));

            LOG.log(Level.INFO, "*** Response sent by the server: ***");

            String line;
            while (reader.ready()) {
                line = reader.readLine();
                LOG.log(Level.INFO, line);
            }

            while(true){
                String requete = stdin.readLine();
                writer.write(requete + '\n');
                writer.flush();
                if(requete.equals("Bye Bye"))
                    break;

                LOG.log(Level.INFO, "test");
                line = reader.readLine();
                LOG.log(Level.INFO, "test10");
                LOG.log(Level.INFO, line);
            }

            reader.close();
            writer.close();
            stdin.close();
            clientSocket.close();
        }
        catch (IOException ex) {
            LOG.log(Level.SEVERE, ex.toString(), ex);
        } finally {
            try {
                if (writer != null) writer.close();
            } catch (IOException ex) {
                LOG.log(Level.SEVERE, ex.toString(), ex);
            }
            try {
                if (reader != null) reader.close();
            } catch (IOException ex) {
                LOG.log(Level.SEVERE, ex.toString(), ex);
            }
            try {
                if (clientSocket != null && ! clientSocket.isClosed()) clientSocket.close();
            } catch (IOException ex) {
                LOG.log(Level.SEVERE, ex.toString(), ex);
            }
        }

    }
}
