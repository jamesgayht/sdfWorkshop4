package workshop4.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp 
{
    public static void main( String[] args )
    {
        //retrieve server port number from the first argument
        //java -cp fortunecookie.jar fc.Server 12345 cookie_file.txt
        //in this case, args[0] will be 12345 which is the port number 
        String serverPort = args[0]; 
        //retrueve cookie file from the second argument 
        String cookieFilePath = args[1]; 
        //set up try catch block 
        try {
            //start by letting client know that the cookie server has started 
            System.out.printf("Cookie server started at %s\n", serverPort);
            //instantiate the server socket class along with the port number 
            ServerSocket server = new ServerSocket(Integer.parseInt(serverPort)); 
            // waiting for connection from the client side 
            Socket sock = server.accept(); 
            // get the input and out stream in bytes    
            InputStream is = sock.getInputStream();  
            DataInputStream dis = new DataInputStream(is); 
            OutputStream os = sock.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os); 

            //fetch the request from client using the data input stream 
            String requestFromClient = dis.readUTF(); 
            System.out.printf("Received request from client: %s\n", requestFromClient);
            
            // if block to check user request 
            if(requestFromClient.equals("get-cookie")) {
                System.out.printf("file --> %s\n", cookieFilePath);

                // return random cookie if get-cookie request 
                String randomCookie = Cookie.getRandomCookie(cookieFilePath);
                System.out.println(randomCookie);
                //return in bytes
                dos.writeUTF("cookie-text" + randomCookie);
                
            // else invalid command 
            } else {
                dos.writeUTF("Invalid command! \n");
            }
            // close your streams & socket 
            is.close();
            os.close();
            sock.close();

        //catch exceptions 
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
