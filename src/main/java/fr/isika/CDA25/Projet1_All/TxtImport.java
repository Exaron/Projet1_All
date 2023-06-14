package fr.isika.CDA25.Projet1_All;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
 
public class TxtImport
{
    public static void main(String[] args)
    {
    	TxtToData data = new TxtToData();
    	try {
			data.lireFichier();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
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

