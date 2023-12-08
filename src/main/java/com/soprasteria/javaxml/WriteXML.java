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
			
			int numCompte;
			String nomProprietaire;
			double solde;
			String typeCompte;
			int annee;
			int mois;
			int jour;
			Scanner clavier = new Scanner(System.in);
			
			for (int i=0 ; i<2 ; i++) {
				System.out.println("Numéro de compte:");
				numCompte = clavier.nextInt();
				System.out.println("Nom du compte:");
				nomProprietaire = clavier.next();
				System.out.println("Solde:");
				solde = clavier.nextDouble();
				System.out.println("Type de compte:");
				typeCompte = clavier.next();
				System.out.println("Date de création du compte:");
				System.out.println("Année:");
				annee = clavier.nextInt();
				System.out.println("Mois:");
				mois = clavier.nextInt();
				System.out.println("Jour:");
				jour = clavier.nextInt();
				
				CompteBancaire compte = new CompteBancaire(numCompte,nomProprietaire,solde,LocalDate.of(annee, mois, jour),typeCompte);
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
		compteBancaire.addContent(new Element("numCompte").setText(""+compte.getNumCompte()));
		compteBancaire.addContent(new Element("nomProprietaire").setText(compte.getNomProprietaire()));
		compteBancaire.addContent(new Element("solde").setText("" + compte.getSolde()));
		compteBancaire.addContent(new Element("dateCreation").setText("" + compte.getDateCreation()));
		compteBancaire.addContent(new Element("typeCompte").setText("" + compte.getTypeCompte()));
		return compteBancaire;
	}
}
