
package image.stagnography;

import java.net.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;

public class sender{
    
    byte[] bytes;

   public static void main(String[] args) throws IOException, ClassNotFoundException, Exception {
        
        File image = new File ("C:\\Users\\lenovo Y\\Desktop\\Grayscale-Image.png"); //read image as file
        
        BufferedImage img = ImageIO.read(image); //read file as image
        
        String fileName = "C:\\Users\\lenovo Y\\Desktop\\plaintext.txt";// read file of plaintext
        
        String line = null;// lines of file
        
        char[] BinArray = null;// bits of all cypertext
        
        Encryptor e = new Encryptor(); // obj of encryptor class
        
        String key = "Bar12345Bar12345"; // 128 bit key
        
        String initVector = "RandomInitVector"; // 16 bytes IV
           
        
           try {
               
            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
           String bit = "";// bits of cyperline
           
            while((line = bufferedReader.readLine()) != null) {
                
                System.out.println("PlainText: "+line);
                
                line = e.encrypt(key, initVector, line); // encrypt the plaintext
                
                System.out.println("CipherText: "+line);
                
                char[] messChar = line.toCharArray(); 
                
                int ascii;      
                
                for(int i=0; i < messChar.length; i++){// concatination for all lines in one string 
                    
                ascii = (int) messChar[i];
                           
                String asciiBIN = Integer.toBinaryString(ascii);
                
                if (asciiBIN.length() < 8){
                    
                   asciiBIN = String.format("%8s", asciiBIN).replace(' ', '0');
                }
                
                bit+= asciiBIN; //all the line in bits
            
                }   
            } 
            System.out.println("CipherText Bits: "+bit);
            
            BinArray = bit.toCharArray(); // array of cipher bits
            
            ImageStagnography I = new ImageStagnography();
            
            SendImage(I.Stagnography(img, BinArray), BinArray.length);// hide the bits, send img and length of sent bits.
            
            bufferedReader.close();  // close
            
            
            
            
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
           catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            
        }     
    }  
    
   public static void SendImage(BufferedImage img, int length){
       
      String serverName = "localhost";
      int port = 6066;
      try
      {
         System.out.println("Connecting to " + serverName + " on port " + port);
         
         Socket client = new Socket(serverName, port); // send a request to receiver

         System.out.println("Connected to " + client.getRemoteSocketAddress());

        DataInputStream in= new DataInputStream(client.getInputStream()); //reader
        
        System.out.println(in.readUTF()); //read
        

         DataOutputStream out = new DataOutputStream(client.getOutputStream()); //sender

         out.writeUTF("Hello from sender"+ client.getLocalSocketAddress()); //send
         
         out.write(length); // send length of bit array

         

         ImageIO.write(img,"PNG",client.getOutputStream()); //send img
         
         System.out.println("Image sent!!!!");
         
         client.close();//close connection
         
         
      }catch(IOException e)
      {
         e.printStackTrace();
      }
   }
}