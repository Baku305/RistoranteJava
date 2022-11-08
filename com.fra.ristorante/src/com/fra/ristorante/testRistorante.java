package com.fra.ristorante;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class testRistorante {

	public static void main(String[] args) throws Exception {

		int[][] portate2D = { { 0, 0, 1, 2, 5 }, { 0, 1, 2, 5 }, { 0, 2 }, { 0, 1 } };
		int[][] portate2D2 = { { 0, 0, 1, 2, 5 }, { 0, 1, 2, 5 }, { 0, 2 }, { 0, 1 }, {0,0} };

		Portata bruschette = new Portata("bruschette", 1.0);
		Portata antipastoMare = new Portata("antipasto di mare", 12.0);
		Portata antipastoMontagna = new Portata("antipasto di montagna", 10.0);
		Portata spaghetti = new Portata("spaghetti", 5.0);
		Portata penneSugo = new Portata("penne al sugo", 7.0);
		Portata tortellini = new Portata("tortellini", 6.0);
		Portata bistecca = new Portata("bistecca", 8.0);
		Portata pesce = new Portata("pesce", 14.0);
		Portata hamburger = new Portata("hamburger", 6.0);
		Portata tiramisù = new Portata("tiramisù", 4.0);
		Portata crostata = new Portata("crostata", 4.0);
		Portata tortaNonna = new Portata("torta della nonna", 4.0);

		List<Portata> antipasti = new ArrayList<Portata>();
		List<Portata> primi= new ArrayList<Portata>();;
		List<Portata> secondi= new ArrayList<Portata>();;
		List<Portata> dolci= new ArrayList<Portata>();;
		
		HashMap<Integer, List<Portata>> menu = new HashMap<>();
		
		Collections.addAll(antipasti,bruschette,antipastoMare,antipastoMontagna);
		Collections.addAll(primi,spaghetti, penneSugo, tortellini);
		Collections.addAll(secondi, bistecca, pesce, hamburger);
		Collections.addAll(dolci, tiramisù, crostata, tortaNonna);
		menu.put(0, antipasti);
		menu.put(1, primi);
		menu.put(2, secondi);
		menu.put(3, dolci);
		
		/*Ristorante ristorante2 = new Ristorante("Da Gino", "Via della Pasta", "Roma", "00100", 0, antipasti, primi,
				secondi, dolci);*/
		
		Ristorante ristorante = new Ristorante("Da Gino", "Via della Pasta", "Roma", "00100", 0, menu);
		Ristorante.ServizioRistorante servizioRistorante = ristorante.new ServizioRistorante();

		System.out.println("**********************************TOTALE SPESA***************************");
		System.out.println(ristorante.getTotaleSpesa2D(portate2D, true) + " €\n");

		System.out.println("**********************************TOTALE PORTATE*************************");
		List<List<Portata>> listaPortate = ristorante.getPortate2D(portate2D, true);
		int i = 0;
		for (List<Portata> portate : listaPortate) {
			System.out.println(String.format("%s", Ristorante.nomeMenu.stampaTipoMenu(i).toUpperCase()));
			for (Portata p : portate) {
			if (p != null)
					System.out.println(String.format("- %s", p.getNome()));
			}
			System.out.println("");
			i++;
		}		

		System.out.println("**********************************SPESA CON SCONTO***********************");
		System.out.println(servizioRistorante.calcolaSpesaConSconto(portate2D, 10, true) + " €\n");
		System.out.println("**********************************INDIRIZZO COMPLETO*********************");
		servizioRistorante.getIndirizzoCompleto();
		System.out.println("");
		System.out.println("**********************************STAMPA COMANDA*************************");
		servizioRistorante.stampaComanda2D(portate2D);
		System.out.println("");
		System.out.println("**********************************STAMPA MENU A SCELTA*******************");
		servizioRistorante.stampaMenu(1);
		System.out.println("");
		System.out.println("**********************************STAMPA INTERO MENU*********************");
		servizioRistorante.stampaMenu();
		System.out.println("**********************************CREAZIONE COMANDA CON SCANNER*********************");
		CreaComanda.creaComanda(ristorante);
		System.out.println("**********************************MENU AGGIORNATO CON NUOVA SEZIONE*************************");
		ristorante.addtoMenu(primi);
		System.out.println(ristorante.getMenu());
		servizioRistorante.stampaMenu();
		System.out.println("**********************************TOTALE PORTATE CON NUOVA SEZIONE*************************");
		List<List<Portata>> listaPortate2 = ristorante.getPortate2D(portate2D2, true);
		int j = 0;
		for (List<Portata> portate : listaPortate2) {
			System.out.println(String.format("%s", Ristorante.nomeMenu.stampaTipoMenu(j).toUpperCase()));
			for (Portata p : portate) {
			if (p != null)
					System.out.println(String.format("- %s", p.getNome()));
			}
			System.out.println("");
			j++;
		}
		System.out.println("**********************************AGGIUNGO UNA PORTATA AL MENU*************************");
		ristorante.addPortataToMenu("nuova portata",2.0,4);
		System.out.println("**********************************STAMPA MENU AGGIORNATO A SCELTA*************************");
		servizioRistorante.stampaMenu(4);
	}

}
