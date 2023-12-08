package com.soprasteria.javaxml;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class ReadXML {
	public static void main(String[] args) {
		final String filename = "compteBancaire.xml";
		try {
			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File(filename);
			Document jdomDoc = (Document) builder.build(xmlFile);
			
			Element root = jdomDoc.getRootElement();
			List < Element > listOfComptes = root.getChildren("CompteBancaire");
	        List <CompteBancaire> compteList = new ArrayList<CompteBancaire>();
	        
	        for(Element compteElement: listOfComptes) {
                CompteBancaire compte = new CompteBancaire();
                compte.setNumCompte(Integer.parseInt(compteElement.getChildText("numCompte")));
                compte.setNomProprietaire(compteElement.getChildText("nomProprietaire"));
                compte.setSolde(Double.parseDouble(compteElement.getChildText("solde")));
                compte.setDateCreation(LocalDate.parse(compteElement.getChildText("dateCreation")));
                compte.setTypeCompte(compteElement.getChildText("typeCompte"));
                compteList.add(compte);
            }
	        
	        for (CompteBancaire compte: compteList) {
	        	if (compte.getTypeCompte().equals("Courant")) {
	        		System.out.println(compte);
	        	}
	        }
	        
		} catch (Exception e) {
			e.printStackTrace();
		} finally {}
	}
}
