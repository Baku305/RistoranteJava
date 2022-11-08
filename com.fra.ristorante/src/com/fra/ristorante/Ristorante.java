package com.fra.ristorante;

import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Ristorante {

	private String nome;

	private String indirizzo;
	private String città;
	private String CAP;
	private int civico;
	private List<Portata> antipasti;
	private List<Portata> primi;
	private List<Portata> secondi;
	private List<Portata> dolci;
	private HashMap<Integer, List<Portata>> menu = new HashMap<>();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getCittà() {
		return città;
	}

	public void setCittà(String città) {
		this.città = città;
	}

	public String getCAP() {
		return CAP;
	}

	public void setCAP(String cAP) {
		CAP = cAP;
	}

	public int getCivico() {
		return civico;
	}

	public void setCivico(int civico) {
		this.civico = civico;
	}

	public List<Portata> getAntipasti() {
		return antipasti;
	}

	public void setAntipasti(List<Portata> antipasti) {
		this.antipasti = antipasti;
	}

	public List<Portata> getPrimi() {
		return primi;
	}

	public void setPrimi(List<Portata> primi) {
		this.primi = primi;
	}

	public List<Portata> getSecondi() {
		return secondi;
	}

	public void setSecondi(List<Portata> secondi) {
		this.secondi = secondi;
	}

	public List<Portata> getDolci() {
		return dolci;
	}

	public void setDolci(List<Portata> dolci) {
		this.dolci = dolci;
	}

	public HashMap<Integer, List<Portata>> getMenu() {
		return menu;
	}

	public Ristorante() {
	}
	
	


	public Ristorante(String nome, String indirizzo, String città, String CAP, int civico, List<Portata> antipasti,
			List<Portata> primi, List<Portata> secondi, List<Portata> dolci) {
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.città = città;
		this.CAP = CAP;
		this.civico = civico;
		this.antipasti = antipasti;
		this.primi = primi;
		this.secondi = secondi;
		this.dolci = dolci;
		this.menu = addToMenu();
	}
	
	public Ristorante(String nome, String indirizzo, String città, String CAP, int civico, HashMap<Integer, List<Portata>> menu) {
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.città = città;
		this.CAP = CAP;
		this.civico = civico;
		this.antipasti = menu.get(0);
		this.primi = menu.get(1);
		this.secondi = menu.get(2);
		this.dolci = menu.get(3);
		this.menu = menu;
	}
	
	/*
	 * METODO INTERNO PER SETTARE IL MENU
	 */

	
	private HashMap<Integer, List<Portata>> addToMenu() {
		menu.put(0, antipasti);
		menu.put(1, primi);
		menu.put(2, secondi);
		menu.put(3, dolci);
		return menu;
	}
	
	/*
	 * METODO PER AGGIUNGERE UNA LISTA DI PORTATE AL MENU
	 */
	
	public void addtoMenu(List<Portata> portata) {
		int size = menu.size();
		menu.put(size, portata);
	}
	
	/*
	 * METODO AGGIUNGERE PORTATA AD UN MENU A SCELTA
	 */
	
	public void addPortataToMenu(Portata portata, Integer idMenu) {
		menu.get(idMenu).add(portata);
	}
	
	public void addPortataToMenu(String nomePortata, double prezzoPortata, Integer idMenu) {
		Portata portata = new Portata(nomePortata,prezzoPortata);
		menu.get(idMenu).add(portata);
	}
	
	/*
	 * METODO PER STAMPARE NOME DEL MENU
	 */

	static public enum nomeMenu {
		ANTIPASTI, PRIMI, SECONDI, DOLCI;

		public static String stampaTipoMenu(int indexMenu) {
			String res = "";
			switch (indexMenu) {
			case 0:
				res = nomeMenu.ANTIPASTI.toString();
				break;
			case 1:
				res = nomeMenu.PRIMI.toString();
				break;
			case 2:
				res = nomeMenu.SECONDI.toString();
				break;
			case 3:
				res = nomeMenu.DOLCI.toString();
				break;
			default:
				res = "FUORI MENU";
			}
			return res;
		}

	}

	/*
	 * METODO INTERNO PER TROVARE SINGOLA PORTATA
	 */

	private Portata trovaPortata(Integer codicePortata, Integer idMenu) {
		Portata portata = null;
		if (codicePortata < menu.get(idMenu).size()) {
			portata = menu.get(idMenu).get(codicePortata);
		}
		return portata;
	}

	/*
	 * METODO INTERNO PER STAMPARE ERRORE IN CASO DI CONTROLLO
	 */

	static private String erroreCodicePortata (int codicePortata, int menuIndex) throws NullPointerException {
		return (
				"prodotto con codice " + codicePortata + " non trovato in " + nomeMenu.stampaTipoMenu(menuIndex));
	}

	/*
	 * METODO PER IL TOTALE DELLA SPESA CON OPZIONE PER IL CONTROLLO
	 */

	public double getTotaleSpesa2D(int[][] codiciPortate, boolean controllo) throws Exception {

		double res = 0.00;

		for (int i = 0; i < codiciPortate.length; i++) {
			for (int p : codiciPortate[i]) {
				Portata portata = trovaPortata(p, i);
				if (portata != null) {
					res += portata.getPrezzo();
				} else if (portata == null && controllo) {
					System.out.println(new NullPointerException(erroreCodicePortata(p, i)));
				}
			}
		}

		return res;
	}

	/*
	 * METODO PER TROVARE LA PORTATA IN MODO DINAMICO, RICEVE UN ARRAY 2D CON I
	 * CODICI DELLE PORTATE E RESTITUISCE UN ARRAY 2D CON TUTTE LE PORTATE
	 * CORRISPONDENTI RISPETTO ALLA POSIZIONE (INDICE) DELL'ARRAY SOTTOSTANTE, CON
	 * OPZIONE PER IL CONTROLLO
	 * 
	 */

	public List<List<Portata>> getPortate2D(int[][] codiciPortate, boolean controllo) throws Exception {

		List<List<Portata>> listaPortateDinamica = new ArrayList<>();

		for (int i = 0; i < codiciPortate.length; i++) {

			List<Portata> portataDinamica = new ArrayList<>();

			for (int p : codiciPortate[i]) {
				Portata portata = trovaPortata(p, i);
				if (portata == null && controllo) {
					System.out.println(new NullPointerException(erroreCodicePortata(p, i)));
				}
				portataDinamica.add(portata);
			}
			;
			listaPortateDinamica.add(portataDinamica);
		}

		return listaPortateDinamica;
	}

	/**
	 * !
	 * ***************************************************CLASSE INNESTATA SERVIZIO RISTORANTE**************************************************************
	 * 
	 */

	public class ServizioRistorante {

		/*
		 * METODO PER IL CALCOLO DELLA SPESA TOTALE CON LO SCONTO, CON OPZIONE PER IL
		 * CONTROLLO CHE POI VIENE PASSATO AL METOTO "GetTotaleSpesa"
		 */

		public double calcolaSpesaConSconto(int[][] codiciPortate, int sconto, boolean controllo) throws Exception {

			double totaleSpesa = getTotaleSpesa2D(codiciPortate, controllo);

			double totaleSconto = (totaleSpesa / 100) * sconto;

			double totale = totaleSpesa - totaleSconto;

			return totale;
		}

		/*
		 * METODO PER STAMPARE L'INDIRIZZO COMPLETO DEL RISTORANTE
		 */

		public void getIndirizzoCompleto() {
			System.out.println(
					String.format("Ristorante %s \nIndirizzo : %s %s %s %s", nome, indirizzo, civico, città, CAP));
		}

		/*
		 * METODO INTERNO PER STAMPARE IL NOME ED IL PREZZO DELLA PORTATA, RICEVE LA
		 * LISTA DELLE PORTATE E STAMPA IL NOME ED IL PREZZO DI OGNI SINGOLA PORTATA
		 */

		static private void stampaNomePrezzo(List<Portata> portata) {
			for (int i = 0; i < portata.size(); i++) {
				if (portata.get(i) != null) {
					String pNome = portata.get(i).getNome();
					double pCosto = portata.get(i).getPrezzo();
					System.out.println("- " + pNome + " " + pCosto + " €");
				}
			}
			;
		}

		/*
		 * METODO PER STAMPARE LA COMANDA PER INTERNO
		 */

		public void stampaComanda2D(int[][] codiciPortate) throws Exception {

			List<List<Portata>> portate = getPortate2D(codiciPortate, true);
			double res = getTotaleSpesa2D(codiciPortate, false);
			double resScontato = calcolaSpesaConSconto(codiciPortate, 10, false);
			Currency cur = Currency.getInstance("EUR");
			for (List<Portata> p : portate) {
				if (p.size() > 0)
				System.out.println(nomeMenu.stampaTipoMenu(portate.indexOf(p)));
				stampaNomePrezzo(p);
			}

			System.out.println(String.format("TOTALE \n%s %s", res,cur.getSymbol()));
			System.out.println(String.format("TOTALE CON SCONTO \n%s €", resScontato));

		}

		/*
		 * METODO PER STAMPARE UN MENU A SCELTA O INTERO MENU (NO PARAMETRI)
		 */

		public void stampaMenu(int id) {
			Iterator<Portata> iterator = menu.get(id).iterator();
			System.out.println(nomeMenu.stampaTipoMenu(id));
			int i = 0;
			while (iterator.hasNext()) {
				System.out.println("ID: " + i + "\n- " + iterator.next().getNome());
				i++;
			}
			System.out.println("");
		}
		public void stampaMenu() {
			for (int i = 0; i < menu.size(); i++) {
				stampaMenu(i);
			}
		}

	}

}
