package com.soprasteria.javaxml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class GestionBanque {

	public static void main(String[] args) {
		saisieCompte();
		afficherCompte();
		afficherCompte("Courant");
	}
	
	public static void saisieCompte() {
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
		catch(IOException io) {
			System.out.println(io.getMessage());
		}
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

	private static void afficherCompte(String typeCompte) {
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
                compte.setNumCompte(Integer.parseInt(compteElement.getAttributeValue("numCompte")));
                compte.setNomProprietaire(compteElement.getChildText("nomProprietaire"));
                compte.setSolde(Double.parseDouble(compteElement.getChildText("solde")));
                compte.setDateCreation(LocalDate.parse(compteElement.getChildText("dateCreation")));
                compte.setTypeCompte(compteElement.getChildText("typeCompte"));
                compteList.add(compte);
            }
	        
	        System.out.println("****************");
	        for (CompteBancaire compte: compteList) {
	        	if (compte.getTypeCompte().equals(typeCompte)) {
	        		System.out.println(compte);
	        	}
	        }
	        System.out.println("****************");
	        
		} catch (Exception e) {
			e.printStackTrace();
		} finally {}
	}

	private static void afficherCompte() {
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
                compte.setNumCompte(Integer.parseInt(compteElement.getAttributeValue("numCompte")));
                compte.setNomProprietaire(compteElement.getChildText("nomProprietaire"));
                compte.setSolde(Double.parseDouble(compteElement.getChildText("solde")));
                compte.setDateCreation(LocalDate.parse(compteElement.getChildText("dateCreation")));
                compte.setTypeCompte(compteElement.getChildText("typeCompte"));
                compteList.add(compte);
            }
	        
	        System.out.println("****************");
	        for (CompteBancaire compte: compteList) {
        		System.out.println(compte);
	        }
	        System.out.println("****************");
	        
		} catch (Exception e) {
			e.printStackTrace();
		} finally {}
	}
}
