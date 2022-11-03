package com.fra.ristorante;
import java.util.List;

public class ServizioRistorante {
	
	/*
	 * METODO PER IL CALCOLO DELLA SPESA TOTALE CON LO SCONTO, CON OPZIONE PER IL CONTROLLO CHE POI VIENE PASSATO AL METOTO 
	 * "GetTotaleSpesa"
	 */

	public static double calcolaSpesaConSconto(Ristorante ristorante, int[][] codiciPortate, int sconto, boolean controllo) {

		double totaleSpesa = ristorante.getTotaleSpesa2D(codiciPortate,controllo);

		double totaleSconto = (totaleSpesa / 100) * sconto;

		double totale = totaleSpesa - totaleSconto;

		return totale;
	}
	
	/*
	 * METODO PER STAMPARE L'INDIRIZZO COMPLETO DEL RISTORANTE
	 */

	public static void getIndirizzoCompleto(Ristorante ristorante) {
		System.out.println(String.format("Ristorante %s \nIndirizzo : %s %s %s %s", ristorante.getNome(),
				ristorante.getIndirizzo(), ristorante.getCivico(), ristorante.getCittà(), ristorante.getCAP()));
	}
	
	
	/*
	 * METODO INTERNO PER STAMPARE IL NOME DEL IL PREZZO DELLA PORTATA, RICEVE LA LISTA DELLE PORTATE E STAMPA IL NOME ED
	 * IL PREZZO DI OGNI SINGOLA PORTATA
	 */

	static private void stampaNomePrezzo(List<Portata> portata) {
		for (int i = 0; i < portata.size(); i++) {
			if (portata.get(i) != null) {
				String pNome = portata.get(i).getNome();
				double pCosto = portata.get(i).getPrezzo();
				System.out.println("- " + pNome + " " + pCosto + " €");
			}
		};
	}
	
	/*
	 * METODO PER STAMPARE LA COMANDA PER INTERNO
	 */

	public static void stampaComanda2D(int[][] codiciPortate, Ristorante ristorante) {

		List<List<Portata>> portate = ristorante.getPortate2D(codiciPortate,true);
		double res = ristorante.getTotaleSpesa2D(codiciPortate,false);
		double resScontato = calcolaSpesaConSconto(ristorante, codiciPortate, 10, false);

		for (List<Portata> p : portate) {

			switch (portate.indexOf(p)) {
			case 0:
				System.out.println("ANTIPASTI");
				stampaNomePrezzo(p);
				break;
			case 1 :
				System.out.println("PRIMI");
				stampaNomePrezzo(p);
				break;
			case 2 :
				System.out.println("SECONDI");
				stampaNomePrezzo(p);
				break;
			case 3 :
				System.out.println("DOLCI");
				stampaNomePrezzo(p);
				break;

			}
		}
		
		System.out.println(String.format("TOTALE \n%s €", res));
		System.out.println(String.format("TOTALE CON SCONTO \n%s €", resScontato));

	}

}
