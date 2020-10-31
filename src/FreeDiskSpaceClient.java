import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class FreeDiskSpaceClient {
    private Socket socket;
    private BufferedWriter out;
    private BufferedReader in;
    public FreeDiskSpaceClient(String ip, String path) {
        try {
            socket = new Socket(InetAddress.getLocalHost(),4711);
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF8"));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out.write(path);
            out.newLine();
            out.flush();
            String response = in.readLine();
            System.out.println("Antwort:" + response);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public static void main(String[] args) {

        new FreeDiskSpaceClient(args[0],args[1]);
    }
}
