package view;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import controller.ControllerKlasse;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author Kai Reffert
 * Diese Klasse stellt das Fenster f�r den Covid-Tracker und die Errormeldung dar.
 * Au�erdem wird die Ereignisbehandlung f�r die Buttons hier implementiert.
 */

public class ViewKlasse extends JFrame {
	public ControllerKlasse ck;
	private Container c;
	
	private JMenuBar menubar;
	private JMenu menu;
	private JMenuItem personenaufnahme;
	private JMenuItem risikoermittlung;
	
	private JLabel label1;
	private JTextField nachname;
	private JTextField vorname;
	
	private JLabel label2;
	private JTextField telefonnummer;
	
	private JLabel label3;
	private JTextField startdatum;
	private JTextField enddatum;
	
	private JButton eintragen;
	
	private JLabel errorbild;
	
	/**
	 * In diesem Konstruktor wird der Contentpane das men� hinzugef�gt, mit welchem man zwischn der Personenaufnahme und der Risikoermittlung wechseln kann.
	 * Au�erdem wird die Contentpane in der Personenaufnahmeform dargestellt, sodass man bei Programmstart direkt mit der Personenaufnahme beginnen kann.  
	 */
	public ViewKlasse() {
		c = getContentPane();
		c.setLayout(new GridLayout(4,0));
		//Men�erstellung
		menubar = new JMenuBar();
		c.add(menubar);
		menu = new JMenu("Men�");
		personenaufnahme = new JMenuItem("Personenaufnahme");
		risikoermittlung = new JMenuItem("Risikoermittlung");
		menu.add(personenaufnahme);
		menu.add(risikoermittlung);
		menubar.add(menu);
		setJMenuBar(menubar);
		
		
		
		//Namensbereich
		label1 = new JLabel();
		label1.setLayout(new GridLayout(2,2));
		label1.add(new JLabel("Nachname"));
		label1.add(new JLabel("Vorname"));
		nachname = new JTextField();	
		label1.add(nachname);
		vorname = new JTextField();
		label1.add(vorname);
		c.add(label1);
		
		//Telefonnummerbereich
		 label2 = new JLabel();
		label2.setLayout(new GridLayout(2,0));
		label2.add(new JLabel("Telefonnummer"));
		 telefonnummer =new JTextField();
		label2.add(telefonnummer);
		c.add(label2);
		
		//Datumsbereich
		 label3 = new JLabel();
		label3.setLayout(new GridLayout(2,2));
		label3.add(new JLabel("Startdatum (dd.MM.yyyy HH:mm)"));
		label3.add(new JLabel("Enddatum (dd.MM.yyyy HH:mm)"));
		startdatum = new JTextField();	
		label3.add(startdatum);
		enddatum = new JTextField();
		label3.add(enddatum);
		c.add(label3);
		
		//Button zum Eintragen
		eintragen = new JButton("Eintragen");
		c.add(eintragen);
		
		
		
	}
	
	/**
	 * @param ck ControllerKlassen-Objekt mit welchem man den Zugriff auf die ActionListener erlangt
	 * hier werden den oben implementierten Buttons und Men�s die jeweiligen ActionListener zugef�gt, welche in der Klasse "ControllerKlasse" implementiert sind.
	 */
	public void setController(ControllerKlasse ck) {
		this.ck = ck;
		eintragen.addActionListener(ck.getB1(telefonnummer,nachname,vorname,startdatum,enddatum));
		risikoermittlung.addActionListener(ck.getRE(this.c));
		personenaufnahme.addActionListener(ck.getPA(this.c));
	}
	
	
	/**
	 * @param s	Text der Errormeldung
	 * In diesem Konstruktor wird das Errorbild erstellt, welches die abzubildene Errornachricht �bergeben bekommt.
	 */
	public ViewKlasse (String s) {
		c = getContentPane();
		c.setLayout(new GridLayout(1,1));
		errorbild= new JLabel(s);
		
		
		c.setBackground(Color.red);
		c.add(errorbild);
		
	}
	
	/**
	 * @param c Contentpane auf welcher das Bild ge�ndert wird
	 * In dieser Methode wird das Layout des �bergebenen Container in die Form der Risikoermittlung gebracht 
	 */
	public void setRisikoermittlungBild(Container c) {
		
		c.removeAll();
		c.revalidate();
		
		c.setLayout(new GridLayout(3,1));

		//Namensbereich
		JLabel label1 = new JLabel();
		label1.setLayout(new GridLayout(2,2));
		label1.add(new JLabel("Nachname"));
		label1.add(new JLabel("Vorname"));
		JTextField nachname = new JTextField();	
		label1.add(nachname);
		JTextField vorname = new JTextField();
		label1.add(vorname);
		c.add(label1);
		
		//Telefonnummerbereich
		JLabel label2 = new JLabel();
		label2.setLayout(new GridLayout(2,0));
		label2.add(new JLabel("Telefonnummer"));
		JTextField telefonnummer =new JTextField();
		label2.add(telefonnummer);
		c.add(label2);
		
		//"Untersuchen"-Button
		JButton untersuchen = new JButton("Untersuchen");
		c.add(untersuchen);
		//Hinzuf�gen des Action Listeners f�r den Untersuchen-Button
		untersuchen.addActionListener(ck.getB2(this.c,telefonnummer,nachname,vorname));
	}
	
	/**
	 * @param c Contentpane auf welcher das Bild ge�ndert wird
	 *  In dieser Methode wird das Layout des �bergebenen Container in die Form der Personenaufnahme gebracht
	 */
	public void setPersonenaufnahmeBild(Container c) {
		
		c.removeAll();
		c.revalidate();
		c.setLayout(new GridLayout(4,1));
		//Namenbereich
		JLabel label1 = new JLabel();
		label1.setLayout(new GridLayout(2,2));
		label1.add(new JLabel("Nachname"));
		label1.add(new JLabel("Vorname"));
		JTextField nachname = new JTextField();	
		label1.add(nachname);
		JTextField vorname = new JTextField();
		label1.add(vorname);
		c.add(label1);
		
		//Telefonnummerbereich
		JLabel label2 = new JLabel();
		label2.setLayout(new GridLayout(2,0));
		label2.add(new JLabel("Telefonnummer"));
		JTextField telefonnummer =new JTextField();
		label2.add(telefonnummer);
		c.add(label2);
		
		//Datumsbereich
		JLabel label3 = new JLabel();
		label3.setLayout(new GridLayout(2,2));
		label3.add(new JLabel("Startdatum (dd.MM.yyyy HH:mm)"));
		label3.add(new JLabel("Enddatum (dd.MM.yyyy HH:mm)"));
		JTextField startdatum = new JTextField();	
		label3.add(startdatum);
		JTextField enddatum = new JTextField();
		label3.add(enddatum);
		c.add(label3);
		
		//Eintrage-Button
		JButton eintragen = new JButton("Eintragen");
		c.add(eintragen);
		
		//HInzuf�gen des ActionListeners zum Eintragen-Button
		eintragen.addActionListener(ck.getB1(telefonnummer,nachname,vorname,startdatum,enddatum));
		
	}
	
}
