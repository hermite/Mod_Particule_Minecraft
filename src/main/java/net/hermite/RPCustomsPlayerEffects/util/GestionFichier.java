package net.hermite.RPCustomsPlayerEffects.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GestionFichier {
	public static String[] lire_fichier(String fichier){
		String[] contenu_fichier = new String [100000];
		try{
			InputStream ips=new FileInputStream(fichier); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			int i = 0;
			while ((ligne=br.readLine())!=null){
				contenu_fichier[i] = ligne;
				i++;
			}
			br.close(); 
		}		
		catch (Exception exp){
			System.out.println(exp.toString());
		}
		return contenu_fichier;
	}

	public static void ecrire_fichier(String chaine, String fichier, boolean reecrire){
		try{
			FileWriter fw = new FileWriter(fichier, reecrire);
			BufferedWriter output = new BufferedWriter(fw);
			output.write(chaine);
			output.newLine();
			output.flush();
			output.close();
		}		
		catch (Exception exp){
			System.out.println(exp.toString());
		}
	}
}
