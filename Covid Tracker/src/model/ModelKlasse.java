package model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Kai Reffert
 * In dieser KLasse werden Methoden, sowie Objekte erzeugt mit denen vor allem in der ControllerKlasse gearbeitet wird
 */

public class ModelKlasse {
	
	
	private Date covidstart;
	private Date covidende;
	private long telefonnummer;
	private String name;
	private String vorname;
	private Date start;
	private Date ende;
	private Map<String, ModelKlasse> num = new HashMap<String, ModelKlasse>();
	private Map<String, ModelKlasse> covid = new HashMap<String, ModelKlasse>();
	public static ModelKlasse p;
	
	
	
	
	
	
	
	/**
	 *Standard Konstruktor zur Objekterzeugung 
	 */
	public ModelKlasse() {}
	
	/**
	 * @param start Startdatum
	 * @param ende Enddatum
	 * Konstruktor der in der ControllerKLasse für die covidzeiten-HashMap relevant ist
	 * 
	 */
	public ModelKlasse(Date start, Date ende) {
		this.covidstart=start;
		this.covidende=ende;
	}
	
	/**
	 * @param tel Telefonnummer
	 * @param name	Nachname
	 * @param vorname	Vorname
	 * @param start	Startzeit
	 * @param ende	Endzeit
	 * Übergabe und Zuweisen der Daten die eine Person bei der Personenaufnahme eingibt
	 */
	public ModelKlasse(long tel,String name, String vorname, Date start, Date ende) {
		this.telefonnummer=tel;
		this.name=name;
		this.vorname=vorname;
		this.start=start;
		this.ende=ende;
	}
	
	
	/**
	 * @param s	Nachname oder Vorname 
	 * @return Diese Methode überprüft ob gegebener String Zahlen enthält, wenn dies der Fall ist,ist der Rückgabewert false, ansonsten ist er true.
	 */
	public boolean namenUeberpruefer(String s) {

		boolean b = true;
		for (char c : s.toCharArray()) {
			if(Character.isDigit(c)) {
				b=false;
			}
		}
    return b;
	}
	
	/**
	 * @param s	Telefonnummer
	 * @return	Diese Methode überprüft ob gegebener String nur  Zahlen enthält, wenn dies der Fall ist,ist der Rückgabewert true, ansonsten ist er false.
	 */
	public boolean nummerUeberpruefer(String s) {
		boolean b = true;
		for (char c : s.toCharArray()) {
			if(!Character.isDigit(c)) {
				b=false;
			}
		}
    return b;
	}
	
	/**
	 * @param start Startdatum
	 * @param ende Enddatum
	 * @return	Überprüft ob das Startdatum vor dem Enddatum liegt, ist dies der Fall, so ist der RÜckgabewert true, asnonsten ist er false.
	 */
	public boolean datumUeberpruefer(Date start, Date ende) {
		boolean b =true;
			
			
		if(start.after(ende)) {
			b=false;
		}
		return b;
	}
	
	/**
	 * @param s1	Startdatum 1
	 * @param s2	Startdatum 2
	 * @param e1	Enddatum 1
	 * @param e2	Enddatum 2
	 * @return	Überprüft ob sich die gegebenen Zeitabschnitte (s1-e1 und s2-e2) zeitlich überschneiden, ist dies der Fall ist der Rückgabewert true, ansonsten ist er false.
	 */
	public boolean zeitgleich(Date s1,Date s2, Date e1, Date e2) {
		if(e1.before(s2) || e2.before(s1)) {
			//Überprüfen ob das jeweilige Ende vor dem Start des anderen liegt.
			return false;
		}if(e1.equals(s2)|| e2.equals(s1)) {
			//wenn sich die Daten so treffen, dass das Ende des einen dem Start des anderen entspricht, so sind gibt es immernoch keine Überschneidung. 
			return false;
		}
		else {
			return true;
		}
		
	}
	
	
	
	//get- und set-Mehoden
	public Date getCovidstart() {
		return covidstart;
	}
	
	public Date getCovidende() {
		return covidende;
	}
	
	public ModelKlasse getP() {
		return this.p;
	}
	
	public Map<String, ModelKlasse> getMap(){
		return this.num;
	}
	
	public Map<String, ModelKlasse> getCovidMap(){
		return this.covid;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getVorname() {
		return this.vorname;
	}
	
	public Date getStart() {
		return start;
	}
	
	public Date getEnde() {
		return ende;
	}
	
	public long getTelefonnummer() {
		return telefonnummer;
	}
	
	
}
