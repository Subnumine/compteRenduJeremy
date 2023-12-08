package com.soprasteria.javaxml;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

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
			
			CompteBancaire compte1 = newAccount(clavier);
			CompteBancaire compte2 = newAccount(clavier);

			doc.getRootElement().addContent(createCompteXMLElement(compte1));
			doc.getRootElement().addContent(createCompteXMLElement(compte2));
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
		compteBancaire.addContent(new Element("numCompte").setText(""+compte.getNumCompte()));
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
		System.out.println("Date de création du compte:");
		System.out.println("Année:");
		int annee = clavier.nextInt();
		System.out.println("Mois:");
		int mois = clavier.nextInt();
		System.out.println("Jour:");
		int jour = clavier.nextInt();
		
		CompteBancaire compte = new CompteBancaire(numCompte,nomProprietaire,solde,LocalDate.of(annee, mois, jour),typeCompte);
		return compte;
	}
}
