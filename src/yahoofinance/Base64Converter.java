/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yahoofinance;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;
import javax.imageio.ImageIO;
import sun.misc.BASE64Decoder;

/**
 *
 * @author ricca
 */
public class Base64Converter {
  
  BufferedImage image;
  byte[] imageByte;
  BASE64Decoder decoder;
  ByteArrayInputStream bis;
  FileInputStream fileInputStreamReader;

  public Base64Converter() {
    image = null; 
          decoder = new BASE64Decoder();
  }
  
  public BufferedImage decodeToImage(String imageBase64) {
    try {
      imageByte = decoder.decodeBuffer(imageBase64);
      bis = new ByteArrayInputStream(imageByte);
      image = ImageIO.read(bis);
      bis.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return image;
    }
  
  public String encodeToBase64(String imagePath) {
    String base64Image = "";
    File file = new File(imagePath);
    try (FileInputStream imageInFile = new FileInputStream(file)) {
      // Reading a Image file from file system
      byte imageData[] = new byte[(int) file.length()];
      imageInFile.read(imageData);
      base64Image = Base64.getEncoder().encodeToString(imageData);
    } catch (FileNotFoundException e) {
      System.out.println("Image not found" + e);
    } catch (IOException ioe) {
      System.out.println("Exception while reading the Image " + ioe);
    }
    return base64Image;
}
}
