import java.io.*;
import java.net.*;

class fileserver {
    public static void main(String args[]) throws IOException {
        ServerSocket servsock = new ServerSocket(13267);
        while (true) {
            System.out.println("Waiting ... ");
            Socket sock = servsock.accept();
            System.out.println("Accept connection: " + sock);
            File myFile = new File("hello.txt");
            byte[] mybytearray = new byte[(int) myFile.length()];
            FileInputStream fis = new FileInputStream(myFile);
            BufferedInputStream bis = new BufferedInputStream(fis);
            bis.read(mybytearray, 0, mybytearray.length);
            OutputStream os = sock.getOutputStream();
            System.out.println("Sending... ");
            os.write(mybytearray, 0, mybytearray.length);
            os.flush();
            sock.close();
        }
    }
}
