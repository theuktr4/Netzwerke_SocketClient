import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class FreeDiskSpaceClient {
    public static final int PORT = 4711;
    public static final String CHARSET_NAME = "UTF8";
    private BufferedWriter out;
    private BufferedReader in;

    public FreeDiskSpaceClient(String ip, String path) {
        try (Socket socket = new Socket(ip, PORT)) {
            System.out.printf("Verbunden mit %s: %s%n", ip, PORT);
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), CHARSET_NAME));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //Anfrage senden
            out.write(path);
            out.newLine();
            out.flush();
            //Antwort
            String response = "";
            response = in.readLine();
            System.out.println("Antwort: " + response);
        } catch (UnknownHostException uhEx) {
            System.err.println("Verbindung zur übergebenen IP Adresse nicht möglich");
        } catch (SocketException sEx) {
            System.err.println("Keine Verbindung zum Server möglich");
        } catch (IOException ioEx) {
            System.err.println(ioEx);
        }
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Es wurden nicht genug Argumente übergeben: benötigt String<ip> String<Pfad>");
            System.exit(1);
        }
        new FreeDiskSpaceClient(args[0], args[1]);
    }
}
