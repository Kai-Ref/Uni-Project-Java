package controller;

import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import model.ModelKlasse;
import view.ViewKlasse;

/**
 * @author Kai Reffert
 *Diese Klasse beinhaltet die Listener welche auf die Ereignisbehandlung eingehen werden.
 */
public class ControllerKlasse {
	
	private ViewKlasse view;
	
	public void setView(ViewKlasse view) {
		this.view = view;
	}
	
	
//Aufrufen der Listener, von außen,  über get-Methoden	
public Button1Listener getB1() {
		return new Button1Listener();
	}

public Button1Listener getB1(JTextField l, JTextField nachname,JTextField vorname,JTextField start,JTextField ende) {
	return new Button1Listener(l,nachname,vorname,start,ende);
}

public Button2Listener getB2(Container c,JTextField l, JTextField nachname,JTextField vorname) {
	return new Button2Listener(c,l,nachname,vorname);
}

public PersonenaufnahmeListener getPA(Container c) {
	return new PersonenaufnahmeListener(c);
}
public RisikoermittlungListener getRE(Container c) {
	return new RisikoermittlungListener(c);
}

 /**
 * @author KAI
 *Diese Klasse implementiert das Interface ActionListener und ist für die Ereignisbehandlung des Personenaufnahmebuttons gedacht.
 */
static class Button1Listener implements ActionListener{
		private JTextField nachnamefeld;
		private JTextField vornamefeld;
		private JTextField startfeld;
		private JTextField endefeld;
		private JTextField lfeld;
		private String nachname;
		private String vorname;
		private Date start;
		private Date ende;
		private long l;
		private SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		private ModelKlasse ueberpruefer = new ModelKlasse();
		public static ModelKlasse p = new ModelKlasse();
		
		/**
		 *Allgmeiner Konstruktor zur Objekterzeugung 
		 */
		public Button1Listener() {}
		
		/**
		 * @param l Textfeld der Telefonnummer
		 * @param nachname	Textfeld des Nachnamens
		 * @param vorname Textfeld des Vornamens
		 * @param start Textfeld des Startdatums
		 * @param ende Textfeld des Enddatums
		 * Hier bekommen die Privaten Textfelder die Instanzierung durch die Übergebenen Textfelder
		 */
		public Button1Listener(JTextField l, JTextField nachname,JTextField vorname,JTextField start,JTextField ende) {
			
			this.nachnamefeld=nachname;
			this.vornamefeld=vorname;
				this.lfeld= l;
				this.startfeld= start;
				this.endefeld = ende;

		}
		/**
		 *In dieser überschriebenen actionPerformed-Methode werden zuerst die Textfleder ausgelesen und die eingegeben Daten richtig formatiert.
		 *Danach werden mehrere Eingabebedingungen geprüft, falls diese alle erfüllt sind werden der HashMap die Personendaten hinzugefügt und der Text in den Textfeldern wird gelöscht. 
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
	
			
			//Hier bekommen die privaten Variablen die Daten der Textfelder zugewiesen, dabei muss der Text bei manchen Feldern vorher noch formatiert werden. 
			this.nachname=nachnamefeld.getText();
			this.vorname=vornamefeld.getText();
			try {
				this.l= Long.parseLong(lfeld.getText());
				this.start= formatter.parse(startfeld.getText());
				this.ende = formatter.parse(endefeld.getText());
			} catch (ParseException e1) {
				
			}catch(NumberFormatException e2) {
				
			}
			
			try {
			if(!(nachnamefeld.getText().isEmpty()) & !(vornamefeld.getText().isEmpty()) & !(startfeld.getText().isEmpty()) & !(endefeld.getText().isEmpty()) & !(lfeld.getText().isEmpty())) {
				//Überprufen dass kein Feld leer ist
				if(ueberpruefer.datumUeberpruefer(start, ende)) {
					//Überprüfen, dass das Startdatum zeitlich vor dem Enddatum liegt
					if(ueberpruefer.namenUeberpruefer(nachname)) {
						//Überprüfen, dass der Nachname keine Zahlen beinhaltet
						if(ueberpruefer.namenUeberpruefer(vorname)) {
							//Überprüfen, dass der Vorname keine Zahlen beinhaltet
							if(ueberpruefer.nummerUeberpruefer(lfeld.getText())) {
								//Überprüfen, dass die Telefonnummer nur Zahlen beinhaltet
								for(int k =0; k<p.getMap().size();k++) {
									//Durchlaufen aller bisherigen Besucher
									if(p.getMap().get("Besuch "+(k+1)).getName().equals(nachname) & p.getMap().get("Besuch "+(k+1)).getVorname().equals(vorname) & p.getMap().get("Besuch "+(k+1)).getTelefonnummer()==l) {
										//Überprüfen ob es schon einen Eintrag mit gleichem Nachnamen,Vornamen und Telefonnummmer gibt
										if(ueberpruefer.zeitgleich(p.getMap().get("Besuch "+(k+1)).getStart(), start, p.getMap().get("Besuch "+(k+1)).getEnde(), ende)) {
											//wenn die Zeiten zeitgleich zu einem bisherigen eintrag sind kommt ein errorfeld, dass besagt man solle sich nicht zweimal zur gleichen zeit eintragen
											throw new IllegalArgumentException("SIE DÜRFEN SICH NICHT ZWEIMAL FÜR DIE GLEICHE ZEIT EINTRAGEN!");
										}
									}
									if((!(p.getMap().get("Besuch "+(k+1)).getName().equals(nachname)) & p.getMap().get("Besuch "+(k+1)).getTelefonnummer()==l) ||(!(p.getMap().get("Besuch "+(k+1)).getVorname().equals(vorname)) & p.getMap().get("Besuch "+(k+1)).getTelefonnummer()==l)) {
										// Wenn die Telefonnummer übereinstimmt, aber der Nachname und/oder der Vorname nicht , kommt folgende Errormeldung	
										throw new IllegalArgumentException("ES HAT BEREITS JEMAND DIESE TELEFONNUMMER ANGEGEBEN!");
										}	
								}
								//sollten alle Bedingungen erfüllt sein werden die Personendaten der HashMap hinzugefügt
								p.getMap().put("Besuch " + (p.getMap().size()+1), new ModelKlasse(l,nachname,vorname,start,ende));
								// Danach werden die Texteinträge entfernt
								nachnamefeld.setText(null);
								vornamefeld.setText(null);
								lfeld.setText(null);
								startfeld.setText(null);
								endefeld.setText(null);
								
							
							//hier folgen die entsprechenden fehlerhaften Eingaben, welche dann mit Errormeldungen an den Benutzer übergeben werden
							}else {
								throw new IllegalArgumentException("IHRE TELEFONNUMMER DARF NUR AUS ZIFFERN BESTEHEN!");
							}
						}else {
							throw new IllegalArgumentException("DER VORNAME DARF KEINE ZIFFERN BEINHALTEN!");
						}
					}else {
						throw new IllegalArgumentException("DER NACHNAME DARF KEINE ZIFFERN BEINHALTEN!");
					} 
				}else {
					throw new IllegalArgumentException("DAS STARTDATUM MUSS VOR DEM ENDDATUM LIEGEN!");
				}
			}else {
				throw new IllegalArgumentException("ES FEHLEN EINZUTRAGENDE FELDER!");
			}
			}catch(IllegalArgumentException e3) {
				//Hier werden die geworfenen Exceptions in Form von Errormeldungen an den Benutzer weitergegeben
				ViewKlasse errorbild =new ViewKlasse(e3.getMessage());
				errorbild.setVisible(true);
				errorbild.setTitle("FALSCHE EINGABE");
				errorbild.setSize(350,150);
				errorbild.setResizable(false);
				errorbild.setLocation(900,450);
			}catch(NullPointerException e4) {
				//Bei Übersetzungsschwierigkeiten soll die richtige Eingabe mit diesem catch-Block sichergestellt werden
				ViewKlasse errorbild =new ViewKlasse("ACHTEN SIE AUF DAS RICHTIGE EINGABEFORMAT BEI TELEFONNUMMER & DATUM");
				errorbild.setVisible(true);
				errorbild.setTitle("FALSCHE EINGABE");
				errorbild.setSize(500,150);
				errorbild.setResizable(false);
				errorbild.setLocation(800,450);
			}
			
			
		}

	}
	
	
	/**
	 * @author Kai Reffert
	 * Diese Klasse implementiert das Interface ActionListener und ist für die Ereignisbehandlung des Risikoermittlungsbuttons gedacht.
	 */
	class Button2Listener implements ActionListener{
		private JTextField nachnamefeld;
		private JTextField vornamefeld;
		private JTextField lfeld;
		private String nachname;
		private String vorname;
		private long l;
		
		private int q=0;
		private int hilfvar=0;
		private ModelKlasse ueberpruefer = new ModelKlasse();
		private Container c;
		private Map<String, ModelKlasse> covidzeiten = new HashMap<String, ModelKlasse>();
		private ModelKlasse p = new Button1Listener().p;
		private SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		
		
		/**
		 * @param c	ContentPane
		 * @param l	Telefonnummer-Textfeld
		 * @param nachname Nachname-Textfeld
		 * @param vorname Vorname-Textfeld
		 * Dieser Konstruktor instanziert die privaten Textfeld-Variablen mit den übergeben Textfeldern
		 */
		public Button2Listener(Container c,JTextField l, JTextField nachname,JTextField vorname) {
			this.c=c;
			this.nachnamefeld=nachname;
			this.vornamefeld=vorname;
				this.lfeld= l;
		}
		
		
		/**
		 *In dieser überschriebenen actionPerformed-Methode werden zuerst die Textfeldwerte zugewiesen und formattiert.
		 *Danach wir die bereits beschriebene HashMap durchlaufen und auf die übergegebenen Daten (Nachname,Vorname,Telefonnummer) überprüft.
		 *Stimmen die Daten mit welchen auf der HashMap überein, so werden das Start- und Enddatum einer neuen HashMap hinzugefügt, welche covidzeiten heißt.
		 *Danach wird die ursprüngliche Personenaufnahme Hashmap durchlaufen und es wird geschaut welche Personen zeitgleich vor Ort waren, dabei werden diese Besucher dann in die Covidmap eingespeichert.
		 *Am Ende wird die Covidmap dann auf der Gui ausgegeben.
		 *
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// Textfelder werden zugewiesen und vorher noch formatiert
			this.nachname=nachnamefeld.getText();
			this.vorname=vornamefeld.getText();
			try {
				this.l= Long.parseLong(lfeld.getText());
			}catch(NumberFormatException e1) {
				
			}
			try {
			for(int i =0; i<p.getMap().size();i++) {
				//hier wirs die Personenaufnahme HashMap durchlaufen
				if(p.getMap().get("Besuch "+(i+1)).getName().equals(nachname) &	p.getMap().get("Besuch "+(i+1)).getVorname().equals(vorname) & p.getMap().get("Besuch "+(i+1)).getTelefonnummer()==l ) {
					//überprüfen ob der oben übergegebenen Nachname,Vorname und Telefonnummer mit einem Eintrag übereinstimmen, falls ja werden dessen Zeiten unter covidzeiten eingetragen
					covidzeiten.put(""+hilfvar, new ModelKlasse(p.getMap().get("Besuch "+(i+1)).getStart(),p.getMap().get("Besuch "+(i+1)).getEnde()));
					hilfvar=hilfvar+1;
					
				}
					}
			if(covidzeiten.isEmpty()) {
				//wenn die Liste nach der obigen Iteration der for-Schleife immernoch leer ist, wird eine Exception geworfen
				throw new IllegalArgumentException("DIE EINGEGEBENEN DATEN STIMMEN MIT KEINER PERSON AUF DER PERSONENAUFNAHMELISTE ÜBEREIN!");
			}
					
					for(int j =0;j<p.getMap().size();j++) {
						//durchlaufen aller,in die ursprüngliche Personenaufnahmeliste eingetragenen Personen
						for(int k =0;k<covidzeiten.size();k++) {
							//durchlaufen der Covidzeitenliste
							if(ueberpruefer.zeitgleich(p.getMap().get("Besuch "+(j+1)).getStart(),covidzeiten.get(""+k).getCovidstart() , p.getMap().get("Besuch "+(j+1)).getEnde(), covidzeiten.get(""+k).getCovidende())) {
								//Überprüfen ob die Zeiten zeitgleich mit einer anderen Person sind. Wenn ja, eintragen in die CovidMap.
								
								
								p.getCovidMap().put(""+q, new ModelKlasse(p.getMap().get("Besuch "+(j+1)).getTelefonnummer(),p.getMap().get("Besuch "+(j+1)).getName(),p.getMap().get("Besuch "+(j+1)).getVorname(),p.getMap().get("Besuch "+(j+1)).getStart(),p.getMap().get("Besuch "+(j+1)).getEnde()));
								
								q=q+1;
								k=covidzeiten.size();
								if(p.getCovidMap().get(""+(q-1)).getName().equals(nachname)/*/& /*/) {
									//überprüfen ob der Name der Zielperson gerade hinzugefügt wurde. Wenn ja, letzten Eintrag entfernen.
									p.getCovidMap().remove(""+(q-1));
									q=q-1;
								}
				}
			}
		}if(p.getCovidMap().isEmpty()) {
			//wenn die CovidMap am Ende noch leer ist wird die folgende Exception geworfen 
					throw new IllegalArgumentException("KEINE PERSON GEFUNDEN DIE ZEITGLEICH VOR ORT WAR!");
				}
			//Ausgabe der Covidmap
			
			c.removeAll();
			c.revalidate();
			c.setLayout(new GridLayout(1,1));
			JTextArea textarea = new JTextArea(p.getCovidMap().size(),1);
			JPanel panel = new JPanel();
			String s[]= new String[p.getCovidMap().size()];
			String z="";
			for(int h =0;h<p.getCovidMap().size();h++) {
				//durchlaufen der Covidmap und einspeichern der Daten (Nachname, Vorname, Telefonnummer, Startzeit , Endzeit) in den String z
			s [h]= ""+(h+1)+". "+"NACHNAME: "+p.getCovidMap().get(""+h).getName()+" VORNAME: "+p.getCovidMap().get(""+h).getVorname()+" TELEFONNUMMER: "+p.getCovidMap().get(""+h).getTelefonnummer()+"\n"+"    STARTDATUM: "+formatter.format(p.getCovidMap().get(""+h).getStart())+" ENDDATUM: "+formatter.format(p.getCovidMap().get(""+h).getEnde())+"\n";
			z=z+s[h];
			}
			
			textarea.setText(z);
			textarea.setLineWrap(true);
			textarea.setWrapStyleWord(true);
			JScrollPane scrollpane = new JScrollPane(textarea);
			panel.add(scrollpane);
			c.add(textarea);
			
			c.setVisible(true);
		
		
		
		
			}	
		catch(IllegalArgumentException e2) {
			//ausgeben von geworfener Exceptions mittels Errormessages
			ViewKlasse errorbild = new ViewKlasse(e2.getMessage());
			errorbild.setVisible(true);
			errorbild.setTitle("ERRORMELDUNG");
			errorbild.setSize(350,150);
			errorbild.setResizable(false);
			errorbild.setLocation(900,450);
			
		}
		}
	}

	/**
	 * @author KAI
	 * Diese Klasse implementiert das Interface ActionListener
	 **Bei Objekterzeugung eines PersonenaufnahmeListener muss ein Container übergeben werden, 
	 * mit dem man dann in der überschriebenen actionPerformed-Methode die Methode "setPersonenaufnahmeBild" anwendet,
	 * diese ändert die Contentpane zur Darstellung der Personenaufnahme 
	 */
	class PersonenaufnahmeListener implements ActionListener{
		private Container c;
		
		public PersonenaufnahmeListener (Container c) {
			this.c =c;	 
		}	
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			view.setPersonenaufnahmeBild(this.c);
		}
	}
	
	
	 /**
	 * @author Kai Reffert
	 *Diese Klasse implementiert das Interface ActionListener. 
	 *Bei Objekterzeugung eines RisikoermittlungListener muss ein Container übergeben werden, 
	 * mit dem man dann in der überschriebenen actionPerformed-Methode die Methode "setRisikoermittlungsBild" anwendet,
	 * diese ändert die Contentpane zur Darstellung der Risikoermittlung 
	 */
	class RisikoermittlungListener implements ActionListener{
			private Container c;
			
			public RisikoermittlungListener (Container c) {
				this.c =c;
			}
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				view.setRisikoermittlungBild(this.c);
			}

		}
}
