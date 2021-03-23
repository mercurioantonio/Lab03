package it.polito.tdp.spellchecker;

import java.net.URL;
import java.sql.Time;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import it.polito.tdp.spellchecker.model.Dictionary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	private Dictionary model ;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> choiceLanguage;

    @FXML
    private TextArea txtInput;

    @FXML
    private TextArea txtOutput;

    @FXML
    private Label lblNumberError;

    @FXML
    private Label lblTimeSpell;

    @FXML
    void doClearText(ActionEvent event) {
    	txtInput.clear();
    	txtOutput.clear();
    	lblNumberError.setText("Number error");
    	lblTimeSpell.setText("Time spell");
    	choiceLanguage.setValue(null);
    	
    	

    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	List<String> parole = new LinkedList<String>();
    	String input = txtInput.getText();
    	if(input.equals("")) {
    		txtOutput.setText("Devi inserire una stringa");
    		return;
    	}
    	
    	String language = choiceLanguage.getValue();
    	model.loadDictionary(language);
    	
    	input = input.toLowerCase();
    	input = input.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "") ;
    	
    	String[] campi = input.split(" ");
    	
    	for(String s : campi) {
    		parole.add(s);
    	}
    	
    	long in = System.nanoTime();
    	model.spellCheckTextDicotomic(parole);
    	long fin = System.nanoTime();
    	
    	double diff =  (double) ((fin-in) * 10E-9);
    	
    	
        lblTimeSpell.setText("Spell check completed in: " + Double.toString( diff ) + " seconds");
    	
    	txtOutput.setText(model.getParoleSbagliate());
    	lblNumberError.setText("The text contains " + String.valueOf(model.getNumberError()) + "errors");
    	
    	txtInput.clear();
    	
    	

    }

    @FXML
    void initialize() {
        assert choiceLanguage != null : "fx:id=\"choiceLanguage\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtOutput != null : "fx:id=\"txtOutput\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblNumberError != null : "fx:id=\"lblNumberError\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblTimeSpell != null : "fx:id=\"lblTimeSpell\" was not injected: check your FXML file 'Scene.fxml'.";

    }

	public void setDictionary(Dictionary m) {
		this.model = m ;
		choiceLanguage.getItems().addAll("English", "Italian");
		
	}
}
