package javaapplication_05;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import javax.imageio.ImageIO;
import javax.print.PrintException;

public class MeuConversorImagem extends ConversorImagemAED{

    private BufferedImage imagem;
    private int radius=10;
    private int intensityLevels=24;

    public MeuConversorImagem(String urlImagemOrigem){
      super(urlImagemOrigem);

      try {
        imagem = ImageIO.read(new File(urlImagemOrigem));
    } catch (Exception e) {
        e.printStackTrace();

    }
  }
        
    public boolean inRange(int cx,int cy,int i,int j){
      double d;
      d = Point2D.distance(i, j,cx,cy);
      return d<radius;
    }
    
    public void OilPaint(String urlImagem){

    try{

      BufferedImage imagemOP = new BufferedImage(imagem.getWidth(), imagem.getHeight(), BufferedImage.TYPE_INT_RGB);
  
      int averageR[] = new int[intensityLevels];
      int averageG[]=new int[intensityLevels];
      int averageB[]=new int[intensityLevels];
      int intensityCount[]=new int[intensityLevels];
 
  
      for(int x=0;x< imagem.getWidth();++x){
      int left = Math.max(0,x-radius);
      int right = Math.min(x+radius,imagemOP.getWidth()-1);

      for(int y=0;y< imagem.getHeight();++y){
      
      int top = Math.max(0,y-radius);
      int bottom = Math.min(y+radius,imagemOP.getHeight()-1);
      
      Arrays.fill(averageR,0);
      Arrays.fill(averageG,0);
      Arrays.fill(averageB,0);
      Arrays.fill(intensityCount,0);
      int maxIndex=-1;
      
        for(int j=top;j<=bottom;++j){
          for(int i=left;i<=right;++i){
            if(!inRange(x,y,i, j)) continue;
          
           int rgb = imagem.getRGB(i,j);
          
          int red = (rgb >> 16)&0xFF;
          int green = (rgb >>8)&0xFF;
          int blue = (rgb )&0xFF;
          int intensityIndex = (int)((((red+green+blue)/3.0)/256.0)*intensityLevels);
          
          intensityCount[intensityIndex]++;
          averageR[intensityIndex] += red;
          averageG[intensityIndex] += green;
          averageB[intensityIndex] += blue;
          
          if( maxIndex==-1 ||intensityCount[maxIndex]< intensityCount[intensityIndex]){
            maxIndex = intensityIndex;
            }
          }
        }
      
      int curMax = intensityCount[maxIndex];
      int r = averageR[maxIndex] / curMax;
      int g = averageG[maxIndex] / curMax;
      int b = averageB[maxIndex] / curMax;
      
      int rgb=((r << 16) | ((g << 8) | b));
      imagemOP.setRGB(x,y,rgb);
      }
    }
      ImageIO.write(imagemOP, "jpg", new File(urlImagem));
      System.out.println("Oil Paint gerado.");
    }catch(Exception e){
    e.printStackTrace();
  }
  
}


public void resize(String logotype, String outputImagePath, int scaledWidth, int scaledHeight){

  try{

    File inputFile = new File(logotype);
    BufferedImage inputImage = ImageIO.read(inputFile);
    
    // creates output image
    BufferedImage outputImage = new BufferedImage(scaledWidth, scaledHeight, inputImage.getType());

    // scales the input image to the output image
    Graphics2D g2d = outputImage.createGraphics();
    g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
    g2d.dispose();
  
    // extracts extension of output file
    String formatName = outputImagePath.substring(outputImagePath.lastIndexOf(".") + 1);
  
    // writes to output file
    ImageIO.write(outputImage, formatName, new File(logotype));
    System.out.println("Resize da imagem concluido. ");

  }catch(Exception e) {
    e.printStackTrace();
  }
  
}

/**
* Resizes an image by a percentage of original size (proportional).
* @param inputImagePath Path of the original image
* @param outputImagePath Path to save the resized image
* @param percent a double number specifies percentage of the output image
* over the input image.
* @throws IOException
*/
public void AddLogo(String urlImagem, String logotype, String outputImagePath, double original_percent, double margin){

  try{
    
    File inputFile = new File(urlImagem);
    BufferedImage inputImage = ImageIO.read(inputFile);
  
    int scaledWidth = (int) (inputImage.getWidth() *  original_percent);
    int marginWidth = (int) (inputImage.getWidth() *  margin);
    int marginHeight = (int) (inputImage.getHeight() *  margin);
    int scaledHeight = (int) (inputImage.getHeight() *  original_percent);
  
    resize(logotype, outputImagePath, scaledWidth, scaledHeight);
  
    int printingWidth = inputImage.getWidth() - (scaledWidth + marginWidth);
    int printingHeight = inputImage.getHeight() - (scaledHeight + marginHeight);
  
    File rezisedLogoFile = new File(outputImagePath);
    BufferedImage rezisedLogo = ImageIO.read(rezisedLogoFile); 
    Graphics g = inputImage.createGraphics();
    g.drawImage(rezisedLogo, printingWidth, printingHeight, null);     
    g.dispose();
  
    // extracts extension of output file
    String formatName = outputImagePath.substring(outputImagePath.lastIndexOf(".") + 1);
  
    // writes to output file
    ImageIO.write(inputImage, formatName, new File(outputImagePath));
    System.out.println("Logo adicionada a imagem");
  }catch(Exception e){
    e.printStackTrace();
    }
  
  } 
}
