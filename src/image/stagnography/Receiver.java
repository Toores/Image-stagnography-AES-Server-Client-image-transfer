
package image.stagnography;

import java.awt.image.BufferedImage;
import java.net.*;
import java.io.*;
import java.sql.SQLException;
import javax.imageio.ImageIO;

public class Receiver extends Thread
{
       private ServerSocket serverSocket;
       Socket server;

       public Receiver(int port) throws IOException, SQLException, ClassNotFoundException, Exception{
          serverSocket = new ServerSocket(port);
          System.out.println("listing....");
       }

       public static void main(String[] args) throws SQLException, ClassNotFoundException, Exception{
           
       Receiver r = new Receiver(6066);
       
       r.GetPlainTextFromImage();
}
       public void GetPlainTextFromImage(){
          
               try
               {
                  
                  server = serverSocket.accept(); //accept the request from sender
                  
                  DataInputStream din=new DataInputStream(server.getInputStream()); //reader
                  
                  DataOutputStream dout=new DataOutputStream(server.getOutputStream()); //outer

                  dout.writeUTF("i am receiver"); //send
                  
                  System.out.println(din.readUTF()); //read
                  
                  int length = din.read(); //read length

                  BufferedImage img=ImageIO.read(ImageIO.createImageInputStream(server.getInputStream())); //read img obj
                  
                  System.out.println("Image received!!!!");
                  
                  ImageStagnography i = new ImageStagnography();
                  
                  Encryptor e = new Encryptor(); // obj of encryptor class
        
                  String key = "Bar12345Bar12345"; // 128 bit key
        
                  String initVector = "RandomInitVector"; // 16 bytes IV
                  
                  String result = e.decrypt(key, initVector, i.GetEncryptedText(img, length));
                  
                  PrintWriter writer = new PrintWriter("C:\\Users\\lenovo Y\\Desktop\\ReceivedPlainText.txt","UTF-8");
                  
                  writer.print(result);
                  
                  writer.close();
                  System.out.println("PlainText: "+ result); //get the cipertext, decrypt the cipertext
                  
                  
                  
              }
             catch(SocketTimeoutException st)
             {
                   System.out.println("Socket timed out!");
                
             }
             catch(IOException e)
             {
                  e.printStackTrace();
                 
             }
             catch(Exception ex)
            {
                  System.out.println(ex);
            }
          
       }
      
      
}
