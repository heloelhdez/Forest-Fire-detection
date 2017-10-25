import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.awt.Color;
//modifica los valores de los pixeles de cada subimagen, porque va de 0 a 40, pero hay que sumar y y x o algo asi

public class Main {
    public static ArrayList<File> archivos = new ArrayList<File>();
    public static float red, green, blue, avgRed, avgGreen, avgBlue, avgHue, avgSat, avgInten;
    public static float theta, hue , saturation, intensity, suma;
    public static String clase = "FALSE";
    public static int[][] pixels;
    public static void main(String[] args) {
        //AQUÍ VAN LOS PRINTS PARA FORMATO ARF
        System.out.println("@relation fire.detection");
        System.out.println();
        System.out.println("@attribute Red numeric");
        System.out.println("@attribute Green numeric");
        System.out.println("@attribute Blue numeric");
        System.out.println("@attribute Hue numeric");
        System.out.println("@attribute Saturation numeric");
        System.out.println("@attribute Brightness numeric");
        System.out.println("@attribute Class {TRUE, FALSE}");
        System.out.println();
	    System.out.println("@data");
	    int totalImganes=0;
	    try{
    	    final File folder = new File("../src");
            listFilesForFolder(folder);
            for (File object: archivos) {
                
                int i = object.getAbsolutePath().lastIndexOf('.');
                String extension = "";
                extension = object.getAbsolutePath().substring(i+1);
                if (extension.compareTo("jpeg") == 0||extension.compareTo("jpg") == 0 || extension.compareTo("png") == 0||extension.compareTo("JPG") == 0 ) {
                    totalImganes++;
                    BufferedImage image = ImageIO.read(new File(object.getAbsolutePath()));
                    System.out.println("%Imagen "+totalImganes+" : "+object.getAbsolutePath()); 
                    //int w = image.getWidth();
                    //int h = image.getHeight();
                    pixels = new int[800][600];
                    for (int y=0;y<5; y++){
                		for (int x=0;x<5;x++){
                		    System.out.println("%Subimagen: "+y+","+x);
                			for (int py=0;py<120;py++){
                	           for(int px=0;px<160;px++){
                	               pixels[px+x*160][py+y*120]=image.getRGB(px+x*160,py+y*120);
                	               
                                   Color colorRGB = new Color(pixels[px+x*160][py+y*120]);
                                   float[] hsb = Color.RGBtoHSB(colorRGB.getRed(), colorRGB.getGreen(), colorRGB.getBlue(), null);
                	               red+= colorRGB.getRed();
                	               green+= colorRGB.getGreen();
                	               blue+= colorRGB.getBlue();
                                   hue += hsb[0];
                                   saturation += hsb[1];
                                   intensity += hsb[2];
                                }
                            }
                            avgRed= red/1600.0f;
                            avgGreen= green/1600.0f;
                            avgBlue= blue/1600.0f;
                			avgHue = hue/1600.0f;
                			avgSat = saturation/1600.0f;
                			avgInten = intensity/1600.0f;
                			System.out.format("%f,%f,%f,%f,%f,%f,%s\n",avgRed,avgGreen,avgBlue,avgHue,avgSat,avgInten,clase); 
                			red=green=blue = 0.0f;
                			avgRed = 0.0f;
                			avgGreen = 0.0f;
                			avgBlue =0.0f;
                			avgHue=0.0f;
                			avgSat=0.0f;
                			avgInten=0.0f;
                			hue = 0.0f;
                            saturation = 0.0f;
                            intensity = 0.0f;
                			}
                		}
                	}
                }
                System.out.println("%Se leyeron "+totalImganes+" imagenes");
        }
        catch(Exception e){
            System.out.println("Entre en exepcion");
            e.printStackTrace();
            }   
        }

    public static int minim (int r, int g, int b){
    	int min=0;
    	if (r>g){
    		if(g>=b)
    			min = b;
    		else 
    			min = g;
    	}
    	else if(g>r){
    	    if(r>=b)
    			min = b;
    		else 
    			min = r;
        }
        else {
    	   if(g>=b)
    			min = b;
    		else 
    			min = g;
        }
        return min;
        
    }
    
   public static int getHue(int red, int green, int blue) {

    float min = Math.min(Math.min(red, green), blue);
    float max = Math.max(Math.max(red, green), blue);

    float huev = 0f;
    if (max == red) {
        huev = (green - blue) / (max - min);

    } else if (max == green) {
        huev = 2f + (blue - red) / (max - min);

    } else {
        huev = 4f + (red - green) / (max - min);
    }

    huev = huev * 60;
    if (huev < 0) huev = huev + 360;

    return Math.round(huev);
}
    
 public static void listFilesForFolder(final File folder) {
    for (final File fileEntry : folder.listFiles()) {
        if (fileEntry.isDirectory()) {
            listFilesForFolder(fileEntry);
        } else {
            archivos.add(fileEntry);
        }
    }
}    
}



////////////////////////////////////////////////////////////////////////////////////////////7

/*
leer hasta final de carpeta (aquí estarán todas nuestras imágenes){
	for each image{
	for (int y=0;y<15; i++){
		for (int x=0;x<20;x++){
			for (int py=0;py<40;py++){
	           for(int px=0;px<40;px++){
	               red+= getRed(px,py);
	               green+= getGreen(px,py)
	               blue+= getBlue(px,py)
	               if (getGreen(py,px)>=getBlue(px,py))
		              hue += valueTheta (px, py);
	               else 
		              hue+= (360-valueTheta(px,py));
	               saturation += 1 - (3/(getRed+getGreen+ getBlue)) * minim(getRed,getGreen,getBlue);
	               intensity += (getRed + getGreen + getBlue)/3;
                }
            }
            avgRed= red/1600;
            avgGreen= green/1600;
            avgBlue= blue/1600;
avgHue = hue/1600;
avgSat = saturation/1600;
avgInten = intensity/1600;
System.out.format("%f,%f,%f,%f,%f,%f,%s\n",avRed,avgGreen,avgBlue,avgHue,avgSat,avgInten,clase); 
red=green=blue = 0;
avgRed = avgGreen = avgBlue =avgHue=avgSat=avgInten=0;
}
}
}
}

*/




