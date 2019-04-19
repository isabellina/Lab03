package it.polito.tdp.spellchecker.controller;

import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class SpellCheckerController {
	
	private Dictionary model;
	private List<String> listaParole = new LinkedList<String>();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> btnTendina;

    @FXML
    private TextArea txtInput;

    @FXML
    private Button btnResult;

    @FXML
    private TextArea txtOutput;

    @FXML
    private Button btnClear;
    
    @FXML
    private Label errorNumbers;
    
    @FXML
    private Label time;

    @FXML
    void doClearText(ActionEvent event) {
          txtInput.clear();
          txtOutput.clear();
          this.listaParole = new LinkedList<String>();
          this.model.clearParoleErrate();
    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	long iniziale = System.nanoTime();
    	this.txtOutput.clear();
    	this.listaParole = new LinkedList<String>();
    String testo = 	txtInput.getText();
    testo = testo.toLowerCase();
    testo = testo.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "");
    String word[] = testo.split("\\s");
    for(String s : word) {
    	listaParole.add(s);
    }
    List<RichWord> mot = this.model.spellCheckText(listaParole, btnTendina.getValue());
    for(RichWord r: mot) {
    	txtOutput.appendText(r.getParola() +"\n");
    }
    errorNumbers.setText(String.format("Il testo contiene: %d", mot.size()  ));
    long finale = System.nanoTime()-iniziale;
    time.setText("Spell check text completed in "+Long.toString(finale) +" nanoseconds");
    }

    @FXML
    void initialize() {
        assert btnTendina != null : "fx:id=\"btnTendina\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnResult != null : "fx:id=\"btnResult\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtOutput != null : "fx:id=\"txtOutput\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'SpellChecker.fxml'.";

    }
    public void setModel(Dictionary  model) {
    	this.model = model;
    	btnTendina.setEditable(false);
    	btnTendina.setValue("Italiano");
    	btnTendina.getItems().addAll("Italiano", "Inglese");
    	
    }
}
