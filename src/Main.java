import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
//modifica los valores de los pixeles de cada subimagen, porque va de 0 a 40, pero hay que sumar y y x o algo asi

public class Main {
    public static ArrayList<File> archivos = new ArrayList<File>();
    public static double red, green, blue, avgRed, avgGreen, avgBlue, avgHue, avgSat, avgInten;
    public static double theta, hue, saturation, intensity;
    public static String clase = "''";
    public static int[][] pixels;
    public static void main(String[] args) {
	    try{
    	    final File folder = new File("../src");
            listFilesForFolder(folder);
            for (File object: archivos) {
                int i = object.getAbsolutePath().lastIndexOf('.');
                String extension = "";
                extension = object.getAbsolutePath().substring(i+1);
                if (extension.compareTo("jpeg") == 0||extension.compareTo("jpg") == 0 || extension.compareTo("png") == 0) {
                    System.out.println("Estoy en: "+object.getAbsolutePath()); 
                    BufferedImage image = ImageIO.read(new File(object.getAbsolutePath()));
                    int w = image.getWidth();
                    int h = image.getHeight();
                    pixels = new int[w][h];
                    for (int y = 0;y<h; y++) {
                        for (int x = 0;x<w ; x++) {
                            pixels[x][y]=image.getRGB(x,y);
                            Color colores = new Color(pixels[x][y]);
                            System.out.print("["+x+","+y+"]("+colores.getRed()+","+colores.getGreen()+","+colores.getBlue()+")");   // = (dataBuffInt[100] >> 16) & 0xFF
                            // = (dataBuffInt[100] >> 8)  & 0xFF
                            // = (dataBuffInt[100] >> 0)  & 0xFF
                        }
                        System.out.println("");
                 }
                }
            }
        }
        catch(Exception e){
            System.out.println("Entre en exepcion");
            e.printStackTrace();
            }   
        }

    public int minim (int r, int g, int b){
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
    
    public double valueTheta (int posX, int posY){
        int color =  pixels[posX][posY];
        Color colorRGB = new Color(color);
        double thethaV;
        int rMinG=0,rMinB=0, gMinB=0; 
        rMinG = colorRGB.getRed() - colorRGB.getGreen();
        rMinB = colorRGB.getRed() - colorRGB.getBlue();
        gMinB = colorRGB.getRed() - colorRGB.getGreen();
        double numerador = 0.5 * (rMinG + rMinB);
        thethaV=  Math.toDegrees(Math.acos(numerador / Math.pow((Math.pow(rMinG,2) + (rMinG * gMinB)),0.5)));
        return thethaV;
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




