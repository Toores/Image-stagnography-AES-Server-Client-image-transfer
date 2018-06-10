package image.stagnography;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageStagnography {

   
    public BufferedImage Stagnography(BufferedImage img, char[] BinArray) throws IOException{
      
            int inc = 0;//incremanter for pixel
            String encrypted = ""; 
     for(int x=0; x < img.getHeight()&& inc < BinArray.length; x++){
         for(int y=0; y < img.getWidth() && inc < BinArray.length; y++){
             
                
             int imgPixel = img.getRGB(x, y)& 0xFF; //decemal number of pixel
             
             String imgBin = Integer.toBinaryString(imgPixel);//convert decemal to binary
             
             
             char[] imgBinArray = imgBin.toCharArray(); //array of bits of pixel
             
             char C_LSB = imgBinArray[imgBinArray.length-1];//as char
             
             
             String S_LSB = String.valueOf(C_LSB); //as string
             
             
             int LSB = Integer.parseInt(S_LSB);//as int
             
             
             char C_enc = BinArray[inc]; //ecrypted bit as char
             
             
             String S_enc = String.valueOf(C_enc); //ecrypted bit as string
             
             
             int enc = Integer.parseInt(S_enc);//ecrypted bit as int
             
                          
             if(LSB == BinArray[inc]){
                 
             }else if(LSB == 1 && enc == 0){//change the LSB
                 
              imgBinArray[imgBinArray.length-1] = BinArray[inc];   
              
             }else if(LSB == 0 && enc == 1){
                 
              imgBinArray[imgBinArray.length-1] = BinArray[inc];
              
             }
             
             
             String imgBin2 = ""; 
             for (Character c : imgBinArray){//new pixel value in binary
                 
             imgBin2 += c.toString();
             
             }
             
           
             int imgPixel2 = Integer.parseInt(imgBin2, 2);// new pixel value in int
             
             
             img.setRGB(x, y, imgPixel2);//set the new value in the image
             
            
             inc++;
            
         }
      }
     
     File outputfile = new File("C:\\Users\\lenovo Y\\Desktop\\test.png");
     
     ImageIO.write(img, "png", outputfile);
     
     return img;
    }
    
    
    public String GetEncryptedText(BufferedImage img, int length){
        
        int inc = 0; String bits="";
        
     for(int x=0; x < img.getHeight()&& inc < length; x++){
        for(int y=0; y < img.getWidth() && inc < length; y++){
             
                
             int imgPixel = img.getRGB(x, y)& 0xFF;// get pixel in int
             
             
             String imgBin = Integer.toBinaryString(imgPixel);// pixel value in binary
             
             
             char[] imgBinArray = imgBin.toCharArray(); // array of bits of pixel
             
             char LSB = imgBinArray[imgBinArray.length-1]; //get LSB
             
             
             bits = bits + LSB;// set of LSB bits (encrypted bits)
             
             inc++;
 
       }
     }
   
     System.out.println("Received bits of CipherText: "+bits);
     
     String output = "";
     
       for(int i = 0; i <= bits.length() - 8; i+=8){// cut each 8-bit and convert to string
           
    int k = Integer.parseInt(bits.substring(i, i+8), 2);
        
    output += (char) k;
    
    }    
        System.out.println("CipherText: "+output);

        return output; //return encryptedtext

    }
    
}
