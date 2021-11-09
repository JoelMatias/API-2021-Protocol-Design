package ch.heigvd.api.calc;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Calculator worker implementation
 */
public class ServerWorker implements Runnable {

    private final static Logger LOG = Logger.getLogger(ServerWorker.class.getName());

    private Socket clientSocket = null;
    private BufferedReader reader = null;
    private BufferedWriter writer = null;

    /**
     * Instantiation of a new worker mapped to a socket
     *
     * @param clientSocket connected to worker
     */
    public ServerWorker(Socket clientSocket) {

        // Log output on a single line
        System.setProperty("java.util.logging.SimpleFormatter.format", "%4$s: %5$s%6$s%n");

        /* TODO: prepare everything for the ServerWorker to run when the
         *   server calls the ServerWorker.run method.
         *   Don't call the ServerWorker.run method here. It has to be called from the Server.
         */

        try{
            this.clientSocket = clientSocket;
            LOG.log(Level.INFO, "Getting a Reader and a Writer connected to the client socket...");
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
            writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"));
        }
        catch (IOException e){

        }
    }

    /**
     * Run method of the thread.
     */
    @Override
    public void run() {

        /* TODO: implement the handling of a client connection according to the specification.
         *   The server has to do the following:
         *   - initialize the dialog according to the specification (for example send the list
         *     of possible commands)
         *   - In a loop:
         *     - Read a message from the input stream (using BufferedReader.readLine)
         *     - Handle the message
         *     - Send to result to the client
         */

        try{
            writer.write("Bonjour et bienvenue. Quel est le calcul dont vous souhaitez connaitre le résultat?\n");
            writer.write("Opérations possibles : Addition (+), Soustration (-), Multiplication (*), Division (/)\n");
            writer.write("Pour quitter, tapez 'Bye Bye'\n");
            writer.flush();

            while(true){
                String request = reader.readLine();
                if(request.equals("Bye Bye"))
                    break;

                int indexBegin = 0, indexEnd, val1, val2;
                try {
                    indexEnd = request.indexOf(' ', indexBegin);
                    val1 = Integer.parseInt(request.substring(indexBegin, indexEnd));

                    indexBegin = indexEnd+1;
                    indexEnd = request.indexOf(' ', indexBegin);
                    val2 = Integer.parseInt(request.substring(indexBegin, indexEnd));

                    indexBegin = indexEnd+1;
                    if(indexBegin != request.length()-1)
                        throw new NumberFormatException();

                    switch (request.charAt(indexBegin)){
                        case '+' :
                            writer.write("OK " + (val1+val2) + '\n');
                            break;
                        case '-' :
                            writer.write("OK " + (val1-val2) + '\n');
                            break;
                        case '/' :
                            if(val2 == 0)
                                writer.write("Erreur : Division par 0\n");
                            else
                                writer.write("OK " + (val1/val2) + '\n');

                            break;
                        case '*' :
                            writer.write("OK " + (val1*val2) + '\n');
                            break;
                        default :
                            writer.write("Erreur : Opération inexistante\n");
                            break;
                    }
                    writer.flush();
                }
                catch (NumberFormatException e){
                    writer.write("Erreur : Format de calcul pas respecté\n");
                    writer.flush();
                }
            }

            writer.close();
            reader.close();
            clientSocket.close();
        }
        catch (IOException e){

        }

    }
}