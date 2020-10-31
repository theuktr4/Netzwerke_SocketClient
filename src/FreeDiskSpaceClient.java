import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class FreeDiskSpaceClient {
    public static final int PORT = 4711;
    public static final String CHARSET_NAME = "UTF8";
    private Socket socket;
    private BufferedWriter out;
    private BufferedReader in;
    public FreeDiskSpaceClient(String ip, String path) {
        try {
            socket = new Socket(ip, PORT);
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), CHARSET_NAME));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //Anfrage senden
            out.write(path);
            out.newLine();
            out.flush();
            //Antwort
            String response ="";
            response = in.readLine();
            System.out.println("Antwort:" + response);

        } catch (UnknownHostException uhEx){
            System.out.println("Verbindung zur übergebenen IP Adresse nicht möglich");
        } catch (SocketException sEx) {
            System.out.println("Keine Verbindung zum Server möglich");
        } catch (IOException ioEx) {
            System.out.println(ioEx);
        }
    }

    public static void main(String[] args) {
        if(args.length < 2){
            System.err.println("Es wurden nicht genug Argumente übergeben: benötigt String<ip> String<Pfad>");
        }
        new FreeDiskSpaceClient(args[0],args[1]);
    }
}
