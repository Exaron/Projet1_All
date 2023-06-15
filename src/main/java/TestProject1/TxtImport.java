package TestProject1;

//import java.io.BufferedReader;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
 
public class TxtImport {
	
    public static void main(String[] args) {
    	
    	TxtToData data = new TxtToData();
    	try {
			data.lireFichier();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
//    	try {
//    		RandomAccessFile raf = new RandomAccessFile("C:\\Uses\\Utilisateur\\Desktop\\Isika\\CDA25\\OOP\\Projet1_All\\resources\\arbre.bin", "rw");
//    		raf.writeChars(g);
//    		raf.close();
//    		} catch (IOException e) {
//    			e.printStackTrace();
//    		}
    	
    	
    	data.ajout();
    	System.out.println(data.getListeStagiaires().size());
    	
//    	data.toString();
    
   
//    
	
    	
//        File file = new File("C:\\ISIKA\\Z.PROJET\\1.Projet1 06-26\\STAGIAIRES\\STAGIAIRES.DON");
// 
//        try (BufferedReader br = new BufferedReader(new FileReader(file)))
//        {
//            String line;
//            while ((line = br.readLine()) != null) {
//                System.out.println(line);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        
    }
}

