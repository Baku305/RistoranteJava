package com.fra.ristorante;

import java.util.ArrayList;

public class testRistorante {

	public static void main(String[] args) {

		int[][] portate2D = { { 0, 0, 1, 2, 5}, { 0, 1, 1 }, { 0, 2, }, { 0, 3, 1 } };

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

		Portata[] antipasti = { bruschette, antipastoMare, antipastoMontagna};
		Portata[] primi = { spaghetti, penneSugo, tortellini };
		Portata[] secondi = { bistecca, pesce, hamburger };
		Portata[] dolci = { tiramisù, crostata, tortaNonna };
		Ristorante ristorante = new Ristorante("Da Gino", "Via della Pasta", "Roma", "00100", 0, antipasti, primi,
				secondi, dolci);
		System.out.println("**********TOTALE SPESA*************");
		System.out.println(ristorante.getTotaleSpesa2D(portate2D,true)+" €\n");
		System.out.println("**********TOTALE PORTATE*************");
		ArrayList<ArrayList<Portata>> listaPortate = ristorante.getPortate2D(portate2D,true);
		for(ArrayList<Portata> portate : listaPortate) {
			for(Portata p : portate) {
				if(p!=null)
				System.out.println(p.getNome());
			}
		}
		System.out.println("**********SPESA CON SCONTO*************");
		System.out.println(ServizioRistorante.calcolaSpesaConSconto(ristorante, portate2D, 10,true) +" €\n");
		System.out.println("**********INDIRIZZO COMPLETO*************");
		ServizioRistorante.getIndirizzoCompleto(ristorante);
		System.out.println("");
		System.out.println("**********STAMPA COMANDA*************");
		ServizioRistorante.stampaComanda2D(portate2D, ristorante);
	}

}
