import java.io.*;  
import java.net.*;  
import java.util.*;

public class socketSender{
    private static InetAddress HOST;  
    private static Socket connaction = null;
    private static final int PORT = 1000;
    private static String closeWord = "*close";

    public static void main(String[] args){
        System.out.println("* Sender: \n");

        try{  
            HOST = InetAddress.getLocalHost(); 
        }catch(Exception uhEx){  
            System.out.println("Host address can't found!");  
            System.exit(1);  
        } 
        connectionAccess();
    }


    private static void connectionAccess(){
        try{
            connaction = new Socket(HOST,PORT);

            // Scanner inputData = new Scanner(connaction.getInputStream());
            PrintWriter outputData = new PrintWriter(connaction.getOutputStream(),true);
            

                int isClose = 0;
                
                while(isClose != 1){

                    System.out.println("Enter your Message? ");
                    Scanner msgScanner = new Scanner(System.in);
                    
                    String Message = msgScanner.nextLine(); 
                    
                    if(Message.equals(closeWord)){
                        isClose = 1;
                    }

                    outputData.println(Message);
                    
                }

                
                 
            
            
        }catch(IOException ioEx){
            System.out.println("Unable to connact to server!");  
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