package com.soprasteria.javaxml;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class WriteXML {

	public static void main(String[] args) {
		try {
			Document doc = new Document();
			doc.setRootElement(new Element("CompteBancaires"));
			
			Scanner clavier = new Scanner(System.in);
			System.out.println("Combien voulez-vous ajouter de comptes ?");
			int nbCompte = clavier.nextInt();
			
			for (int i=0 ; i<nbCompte ; i++) {
				CompteBancaire compte = newAccount(clavier);
				doc.getRootElement().addContent(createCompteXMLElement(compte));
			}
						
			clavier.close();
			
			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter("compteBancaire.xml"));
			System.out.println("File Saved!");
		}
		catch(IOException io) {}
		finally {}
	}

	private static Element createCompteXMLElement(CompteBancaire compte) {
		Element compteBancaire = new Element("CompteBancaire");
		compteBancaire.setAttribute(new Attribute("numCompte",""+compte.getNumCompte()));
		compteBancaire.addContent(new Element("nomProprietaire").setText(compte.getNomProprietaire()));
		compteBancaire.addContent(new Element("solde").setText("" + compte.getSolde()));
		compteBancaire.addContent(new Element("dateCreation").setText("" + compte.getDateCreation()));
		compteBancaire.addContent(new Element("typeCompte").setText("" + compte.getTypeCompte()));
		return compteBancaire;
	}
	
	private static CompteBancaire newAccount(Scanner clavier) {
		System.out.println("Numéro de compte:");
		int numCompte = clavier.nextInt();
		System.out.println("Nom du compte:");
		String nomProprietaire = clavier.next();
		System.out.println("Solde:");
		double solde = clavier.nextDouble();
		System.out.println("Type de compte:");
		String typeCompte = clavier.next();
		
		CompteBancaire compte = new CompteBancaire(numCompte,nomProprietaire,solde,LocalDate.now(),typeCompte);
		return compte;
	}
}
