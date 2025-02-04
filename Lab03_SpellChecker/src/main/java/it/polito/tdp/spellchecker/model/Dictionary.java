package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Dictionary {
	
	List<String> dizionario = new ArrayList<String>();
	List<RichWord> paroleCorrette = new ArrayList<RichWord>();
	String paroleSbagliate = "";
	int numberError = 0;
	
	
	public void loadDictionary(String language) {
		try {
			FileReader fr = new FileReader("src/main/resources/"+language+".txt") ;
			BufferedReader br = new BufferedReader(fr);
			
			String word2;
			
			while((word2 = br.readLine()) != null) {
				dizionario.add(word2);
				
			}
			br.close();
		}
		catch (IOException e) {
			System.out.println("Errore nella lettura del file");
		}
	}
	
/*	public List<RichWord> spellCheckText(List<String> inputTextList){
		
		boolean flag ;
		String word ="";
		
		
		for(String w : inputTextList) {
			word = w;
			flag = false;
			for(String d : dizionario) {
			    if(w.equals(d)) {
			    	flag = true;
			    	break;
			    } 	
			}
			paroleCorrette.add(new RichWord(word, flag));
		}
		
		
		for(RichWord s : paroleCorrette) {
			if(s.corretto == false) {
				paroleSbagliate = paroleSbagliate + s.word + "\n" ;
				numberError++;
			}
		}
		return paroleCorrette;		
	}
*/
       public List<RichWord> spellCheckTextLinear(List<String> inputTextList){
		
		boolean flag ;
		String word ="";
		
		
		for(String w : inputTextList) {
			word = w;
			flag = false;
			for(String d : dizionario) {
			    if(w.equals(d)) {
			    	flag = true;
			    	break;
			    } 	
			}
			paroleCorrette.add(new RichWord(word, flag));
		}
		
		
		for(RichWord s : paroleCorrette) {
			if(s.corretto == false) {
				paroleSbagliate = paroleSbagliate + s.word + "\n" ;
				numberError++;
			}
		}
		return paroleCorrette;		
	}
       
    
       public List<RichWord> spellCheckTextDicotomic(List<String> inputTextList){
   		
   		boolean flag ;
   		String word ="";
   		
   		
   		for(String w : inputTextList) {
   			word = w;
   			flag = false;
   			List<String> dizionario2 = new ArrayList<String>();
   			dizionario2 = dizionario;
   			
   			int i = dizionario2.size()/2;
   			
   			while(!(word.equals(dizionario2.get(i))) && dizionario2.size()>1) {
   			
   				
   			
   			if(word.compareTo(dizionario2.get(i)) < 0)
   			{
   			 dizionario2 = dizionario2.subList(0, i);
   			 i = i/2;
   			 
   			 
   				 				
   			}   
   			else if (word.compareTo(dizionario2.get(i)) >0) {
   				
   				dizionario2 =  dizionario2.subList(i+1, dizionario2.size());
   				i = i/2;
   				
   				
   			}
   			}
   			
   			if(word.equals(dizionario2.get(i))) {
   				flag = true;
   				paroleCorrette.add(new RichWord(word, flag));
   				
   			}
   			else {
   				paroleCorrette.add(new RichWord(word, flag));
   				
   			}
   		}
   		
   		
   		for(RichWord s : paroleCorrette) {
   			if(s.corretto == false) {
   				paroleSbagliate = paroleSbagliate + s.word + "\n" ;
   				numberError++;
   			}
   		}
   		return paroleCorrette;		
   	}

	public String getParoleSbagliate() {
		return paroleSbagliate;
	}

	public void setParoleSbagliate(String paroleSbagliate) {
		this.paroleSbagliate = paroleSbagliate;
	}

	public int getNumberError() {
		return numberError;
	}

	public void setNumberError(int numberError) {
		this.numberError = numberError;
	}
	
	
	

}
