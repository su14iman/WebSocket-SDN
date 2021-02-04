import java.io.*;
import java.net.*;
import java.util.*;

public class socketRecvier{
    private static ServerSocket serverSocket; 
    private static Socket connaction = null; 
    private static final int PORT = 1099;
    private static String closeWord = "**close";

    public static void main(String[] args){
        System.out.println("* Reciver: Opening port! \n");
        try {  
            serverSocket = new ServerSocket(PORT);
            handelData(); 
	    }catch(IOException ioEx){  
            System.out.println("Unable to attach to port for receiver!");  
            System.exit(1);
        } 
    }

    private static void handelData(){
        try{
            connaction = serverSocket.accept();

            Scanner inputData = new Scanner(connaction.getInputStream());
            // PrintWriter outputData = new PrintWriter(connaction.getOutputStream(),true);
            
            
            
            int numMessages = 0;
            String message = inputData.nextLine(); 

            while (!message.equals(closeWord)){

                numMessages++;

                System.out.print("ID: "+numMessages + ":" + message+"\n");
                message = inputData.nextLine();
                
            }

            



        }catch(IOException ioEx){  
            System.out.println("Unable to load data!");  
            System.exit(1);
        }

        finally{  
            try{  
                System.out.println("\n ** Closing connection **");  
                connaction.close();                    
            }catch(IOException ioEx){  
                System.out.println("\n ** Unable to disconnect! **");  
                System.exit(1);  
            }  
        } 
    }
}