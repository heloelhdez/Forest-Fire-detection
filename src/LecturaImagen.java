import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

//cd /cygdrive/c/Users/heloe/Documents/NetBeansProjects/LecturaImagen/src/   
class LecturaImagen{
    public static ArrayList<File> archivos = new ArrayList<File>();
    
    public static void main(String[] args) {
     
        final File folder = new File("../src");
        listFilesForFolder(folder);
        try{
            for (File object: archivos) {
                
                int i = object.getAbsolutePath().lastIndexOf('.');
                String extension = "";
                
                    extension = object.getAbsolutePath().substring(i+1);
                    
                if (extension.compareTo("jpeg") == 0||extension.compareTo("jpg") == 0 || extension.compareTo("png") == 0) {
                    
               System.out.println("Estoy en: "+object.getAbsolutePath()); 
        BufferedImage image = ImageIO.read(new File(object.getAbsolutePath()));
    
        int w = image.getWidth();
        int h = image.getHeight();
 
        int[][] pixels = new int[w][h];
        for (int y = 0;y<h; y++) {
            for (int x = 0;x<w ; x++) {
                    pixels[x][y]=image.getRGB(x,y);
                    Color colores = new Color(pixels[x][y]);
                    //System.out.print("["+x+","+y+"]("+colores.getRed()+","+colores.getGreen()+","+colores.getBlue()+")");   // = (dataBuffInt[100] >> 16) & 0xFF
                    // = (dataBuffInt[100] >> 8)  & 0xFF
                    // = (dataBuffInt[100] >> 0)  & 0xFF
                }
                //System.out.println("");
                }
            }
                
            }
            System.out.println("Hice todo");
        }
        catch(Exception e){
            System.out.println("Entre en exepcion");
            e.printStackTrace();
        }
      
    }
    
    
    
    public static void listFilesForFolder(final File folder) {
    for (final File fileEntry : folder.listFiles()) {
        if (fileEntry.isDirectory()) {
            listFilesForFolder(fileEntry);
        } else {
            archivos.add(fileEntry);
            //System.out.println(archivos.get(archivos.size()-1).getParent()+""+archivos.get(archivos.size()-1).getName());
        }
    }
}

}
