package it.polito.tdp.spellchecker.model;

import java.io.*;
import java.util.*;

public class Dictionary {

/*	public List<String> run(String t, String language){

	} */
	
	private Set<String> inglese = new HashSet<String>();
	private Set<String> italiano = new HashSet<String>();
	
	private boolean it_load = false;
	private boolean en_load = false;
	
	private List<RichWord> paroleErrate = new LinkedList<RichWord>();
	
	public void loadDictionary(String language) {
		try {
			FileReader r = new FileReader("rsc/English.txt");
			BufferedReader br = new BufferedReader(r);
			String word;
			while((word = br.readLine())!=null) {
				inglese.add(word);
			}
			this.en_load = true;
			br.close();
		}
		catch(IOException e){
			System.out.println("Errore nella lettura del file");
		}
		
		try {
			FileReader r = new FileReader("rsc/Italian.txt");
			BufferedReader br = new BufferedReader(r);
			String word;
			while((word = br.readLine())!=null) {
				italiano.add(word);
			}
			this.it_load = true;
			br.close();
		}
		catch(IOException e){
			System.out.println("Errore nella lettura del file");
		}
	}
	
	
	
	
	public List<RichWord> spellCheckText(List<String> inputTextList, String lingua){
		this.clearParoleErrate();
		Set<String> dizionario =  new HashSet<String>();
		if(lingua.equals("Inglese")) {
			if(isEn_load() == false)
				this.loadDictionary("Inglese");
			 dizionario = inglese ;
		}
		else if(lingua.equals("Italiano")) {
			if(isIt_load() == false)
				this.loadDictionary("Italiano");
			 dizionario = italiano ;
		}
		for(String s : inputTextList) {
			if(dizionario.contains(s) == false) {
				RichWord word = new RichWord(s, false);
				paroleErrate.add(word);
			}
		}
		
		return paroleErrate;
	}

	public boolean isIt_load() {
		return it_load;
	}

	public boolean isEn_load() {
		return en_load;
	}
	
	public void clearParoleErrate()
	{
		this.paroleErrate = new LinkedList<RichWord>();
	}

}
