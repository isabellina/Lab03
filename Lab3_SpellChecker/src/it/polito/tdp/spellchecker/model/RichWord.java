package it.polito.tdp.spellchecker.model;

public class RichWord {

	
	private String parola;
	private boolean corretto;
	
	
	public RichWord(String parola, boolean corretto) {
		
		this.parola = parola;
		this.corretto = corretto;
	}
	public String getParola() {
		return parola;
	}
	public boolean isCorretto() {
		return corretto;
	}
	
	
	
	
	
}
