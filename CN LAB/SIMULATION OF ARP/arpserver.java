import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
class arpserver {
  public static void main(String args[]) throws IOException {
        try {
            ServerSocket soc = new ServerSocket(2500);
            System.out.println("Server started");
            Socket client = null;
            client = soc.accept();
            String str;
            PrintStream ps = new PrintStream(client.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            Runtime r = Runtime.getRuntime();
            Process process = Runtime.getRuntime().exec("ipconfig /all");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Physical Address")) {
                    int index = line.indexOf(":") + 1;
                    String macAddress = line.substring(index).trim();
                    System.out.println(macAddress);
                    break;
                }
            }
            Thread.sleep(5000);
            reader.close();
            ps.close();
            br.close();
            client.close();
            soc.close();}

catch(IOException io) {
    System.err.println("Exception: "+io.toString());
} catch(InterruptedException e) {
    throw new RuntimeException(e);
}}}
