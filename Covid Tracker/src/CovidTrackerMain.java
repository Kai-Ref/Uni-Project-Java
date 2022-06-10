import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controller.ControllerKlasse;
import view.ViewKlasse;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * @author Kai Reffert
 * @version 1.0
 *	Diese Klasse enthält die Main-Methode des Covid-Trackers.
 */

public class CovidTrackerMain extends JFrame{
	
	private static ControllerKlasse ck;
	private static ViewKlasse view;

	
	/**
	 * main Methode Covid-Tracker
	 * @param args Kommandozeilenparameter
	 */
	public static void main (String []args) {
		
		ck = new ControllerKlasse();
		view = new ViewKlasse();
		
		view.setController(ck);
		ck.setView(view);
		
		view.setVisible(true);
		view.setTitle("Covid Tracker");
		view.setSize(500,450);
		view.setResizable(true);
		view.setLocation(800,300);

	}
	
}
