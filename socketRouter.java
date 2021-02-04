import java.io.*;
import java.net.*;
import java.util.*;


public class socketRouter{
    private static ServerSocket serverSocket; 
    private static InetAddress HOST;
    private static final int routerPORT = 1000;
    private static final int recvierPORT = 1099;
    private static Socket recvierConnaction = null; 
    private static Socket senderConnaction = null; 
    private static String closeWord = "*close";

    public static void main(String[] args){
        System.out.println("* Router: Opening port! \n");
        try {  
            serverSocket = new ServerSocket(routerPORT);
            HOST = InetAddress.getLocalHost(); 
            handelRouter(); 
	    }catch(IOException ioEx){  
            System.out.println("Unable to attach to port for router!");  
            System.exit(1);
        } 
    }

    private static void handelRouter(){
        try{
            senderConnaction = serverSocket.accept();
            recvierConnaction = new Socket(HOST,recvierPORT);

            Scanner inputSenderData = new Scanner(senderConnaction.getInputStream());
            PrintWriter outputSenderData = new PrintWriter(senderConnaction.getOutputStream(),true);
            
            PrintWriter outputRecvierData = new PrintWriter(recvierConnaction.getOutputStream(),true);

            
            int isClose = 0;
            int numMessages = 0;
            String message = inputSenderData.nextLine(); 

            while(isClose != 1){

                numMessages++;

                // filter for close key :) 
                if(!message.equals(closeWord)){
                    System.out.print("Sender: "+numMessages + ":" + message+"\n");
                }
                
                
                if(message.equals(closeWord)){
                    outputRecvierData.println("*"+closeWord);
                    outputSenderData.println("*"+closeWord);
                    isClose = 1;
                    break;
                }
                outputRecvierData.println(message);
                message = inputSenderData.nextLine();
                

            }

            



        }catch(IOException ioEx){  
            System.out.println("Unable to reciver found!");  
            System.exit(1);
        }

        finally{  
            try{  
                System.out.println("\n ** Closing connections **");  
                senderConnaction.close();                    
                recvierConnaction.close();                    
            }catch(IOException ioEx){  
                System.out.println("\n ** Unable to disconnect! **");  
                System.exit(1);  
            }  
        } 
    
    }

}