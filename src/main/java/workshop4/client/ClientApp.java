package workshop4.client;

import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientApp {
    //create a main method for client side 
    public static void main(String[] args) {
        
        //take in the argument from the server side args[0] because 
        // java -cp fortunecookie.jar fc.Client locahost:12345
        // we split by ":"
        System.out.println("Cookie Client ");
        String[] arr = args[0].split(":"); 

        // create try catch block to receive input from server side 
        try {
            // socket connects to the <host>:<port> at arr[0] and arr[1]
            Socket sock = new Socket(arr[0], Integer.parseInt(arr[1])); 

            //setup IO network 
            InputStream is = sock.getInputStream();
            DataInputStream dis = new DataInputStream(is); 
            OutputStream os = sock.getOutputStream(); 
            DataOutputStream dos = new DataOutputStream(os); 

            //set up console to prompt for command 
            Console cons = System.console(); 
            String input = cons.readLine("Send command to server > ");
            dos.writeUTF(input);
            //remember to flush to allow subsequent commands to come in
            dos.flush();

            //set up if block to check if cookie text was returned from the server 
            String response = dis.readUTF(); 
            if(response.contains("cookie-text")) {
                System.out.println(response);
                String[] cookieValue = response.split(" "); 
                System.out.printf("Cookie returned from the server >> %s\n", cookieValue[1]);
            }

            // rmb to close your input/output streams & socket
            is.close();
            os.close();
            sock.close();

        //catch numberformat exception, unknownhost exception and ioexception 
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        } catch (UnknownHostException uhe) {
            uhe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
