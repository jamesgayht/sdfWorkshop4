package workshop4.server;

import java.io.*;
import java.util.*;

public class Cookie {
    //create object to get random cookie 
    public static String getRandomCookie(String path) {
        File cookieFile = new File(path); 
        List<String> cookies = new LinkedList<>(); 
        String randomCookie = ""; 

        //create scanner to read cookie file 
        Scanner scanner; 

        //try catch block for scanner to catch filenotfound exception 
        try {
            scanner = new Scanner(cookieFile); 
            
            //while loop to add cookies into cookies linkedlist until the last line in cookieFile 
            while(scanner.hasNextLine());
                cookies.add(scanner.nextLine()); 

            //rmb to close scanner
            scanner.close();

            System.out.println(cookies);
            Random rand = new Random(); 
            //get the item from the cookies linked list using rand.next int, since cookies is a list,
            // use size instead of length 
            randomCookie = cookies.get(rand.nextInt(cookies.size())); 
            System.out.println(randomCookie);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return randomCookie; 
    }
}
