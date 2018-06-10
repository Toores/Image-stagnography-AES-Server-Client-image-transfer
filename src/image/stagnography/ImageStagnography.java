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
             //System.out.println("pixel int "+imgPixel);
             
             String imgBin = Integer.toBinaryString(imgPixel);//convert decemal to binary
            // System.out.println("pixel Bin "+imgBin);
             
             
             char[] imgBinArray = imgBin.toCharArray(); //array of bits of pixel
             
             char C_LSB = imgBinArray[imgBinArray.length-1];//as char
             
            // System.out.println("pixel char LSB "+C_LSB);
             
             String S_LSB = String.valueOf(C_LSB); //as string
             
            // System.out.println("pixel str LSB "+S_LSB);
             
             int LSB = Integer.parseInt(S_LSB);//as int
             
             //System.out.println("pixel LSB "+LSB);
             
             char C_enc = BinArray[inc]; //ecrypted bit as char
             
             //System.out.println("pixel char enc "+C_enc);
             
             String S_enc = String.valueOf(C_enc); //ecrypted bit as string
             
            // System.out.println("pixel str enc "+S_enc);
             
             int enc = Integer.parseInt(S_enc);//ecrypted bit as int
             
             //System.out.println("pixel enc "+enc);
             
             
             //System.out.println("enc bit "+BinArray[inc]);
             
             if(LSB == BinArray[inc]){
                 
             }else if(LSB == 1 && enc == 0){//change the LSB
                 
              imgBinArray[imgBinArray.length-1] = BinArray[inc];   
              
             }else if(LSB == 0 && enc == 1){
                 
              imgBinArray[imgBinArray.length-1] = BinArray[inc];
              
             }
             
             //encrypted = encrypted + BinArray[inc];
             //System.out.println("pixel LSB after change "+imgBinArray[imgBinArray.length-1]);
             
             String imgBin2 = ""; 
             for (Character c : imgBinArray){//new pixel value in binary
                 
             imgBin2 += c.toString();
             
             }
             
             //System.out.println("pixel BIN enc "+imgBin2);
           
             int imgPixel2 = Integer.parseInt(imgBin2, 2);// new pixel value in int
             
            // System.out.println("pixel INT enc "+imgPixel2);
             
             img.setRGB(x, y, imgPixel2);//set the new value in the image
             
            
             inc++;
            
         }
      }
     //System.out.println("pixels enc "+encrypted);
     
     File outputfile = new File("C:\\Users\\lenovo Y\\Desktop\\test.png");
     
     ImageIO.write(img, "png", outputfile);
     
     return img;
    }
    
    
    public String GetEncryptedText(BufferedImage img, int length){
        
        int inc = 0; String bits="";
        
     for(int x=0; x < img.getHeight()&& inc < length; x++){
        for(int y=0; y < img.getWidth() && inc < length; y++){
             
                
             int imgPixel = img.getRGB(x, y)& 0xFF;// get pixel in int
             
             //System.out.println("pixel dec "+imgPixel);
             
             String imgBin = Integer.toBinaryString(imgPixel);// pixel value in binary
             
             //System.out.println("pixel Bin "+imgBin);
             
             char[] imgBinArray = imgBin.toCharArray(); // array of bits of pixel
             
             char LSB = imgBinArray[imgBinArray.length-1]; //get LSB
             
            // System.out.println("pixel LSB "+LSB);
             
             bits = bits + LSB;// set of LSB bits (encrypted bits)
             
             inc++;
 
       }
     }
   
     System.out.println("Received bits of CipherText: "+bits);
     
     String output = "";
     
       for(int i = 0; i <= bits.length() - 8; i+=8){// cut each 8-bit and convert to string
           
    int k = Integer.parseInt(bits.substring(i, i+8), 2);
    
    //System.out.println(k + " "+ (char)k);
    
    output += (char) k;
    
    }    
        System.out.println("CipherText: "+output);

        return output; //return encryptedtext

    }
    
}
