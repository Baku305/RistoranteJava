package com.fra.ristorante;

import java.util.ArrayList;
import java.util.List;

public class Ristorante {

	private String nome;

	private String indirizzo;
	private String città;
	private String CAP;
	private int civico;
	private Portata[] antipasti;
	private Portata[] primi;
	private Portata[] secondi;
	private Portata[] dolci;

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

	public Portata[] getAntipasti() {
		return antipasti;
	}

	public void setAntipasti(Portata[] antipasti) {
		this.antipasti = antipasti;
	}

	public Portata[] getPrimi() {
		return primi;
	}

	public void setPrimi(Portata[] primi) {
		this.primi = primi;
	}

	public Portata[] getSecondi() {
		return secondi;
	}

	public void setSecondi(Portata[] secondi) {
		this.secondi = secondi;
	}

	public Portata[] getDolci() {
		return dolci;
	}

	public void setDolci(Portata[] dolci) {
		this.dolci = dolci;
	}

	public Ristorante() {
	}

	public Ristorante(String nome, String indirizzo, String città, String CAP, int civico, Portata[] antipasti,
			Portata[] primi, Portata[] secondi, Portata[] dolci) {
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.città = città;
		this.CAP = CAP;
		this.civico = civico;
		this.antipasti = antipasti;
		this.primi = primi;
		this.secondi = secondi;
		this.dolci = dolci;
	}

	/*
	 * METODO PER STAMPARE NOME DEL MENU
	 */

	static public String stampaTipoMenu(int indexMenu) {
		switch (indexMenu) {
		case 0:
			return "Antipasti";
		case 1:
			return "Primi";
		case 2:
			return "secondi";
		case 3:
			return "dolci";
		default:
			return "menù";
		}
	}

	/*
	 * METODO INTERNO PER TROVARE SINGOLA PORTATA
	 */

	static private Portata trovaPortata(int codicePortata, Portata[] tipoMenu) {
		Portata portata = null;
		if (codicePortata < tipoMenu.length) {
			portata = tipoMenu[codicePortata];
		}
		return portata;
	}

	/*
	 * METODO INTERNO PER STAMPARE ERRORE IN CASO DI CONTROLLO
	 */

	static private void erroreCodicePortata(int codicePortata, int menuIndex) {
		System.out.println(
				String.format("prodotto con codice %s non trovato in %s", codicePortata, stampaTipoMenu(menuIndex)));
	}

	/*
	 * METODO PER IL TOTALE DELLA SPESA CON OPZIONE PER IL CONTROLLO
	 */

	public double getTotaleSpesa2D(int[][] codiciPortate, boolean controllo) {

		double res = 0.00;

		for (int i = 0; i < codiciPortate.length; i++) {
			switch (i) {
			case 0:
				for (int p : codiciPortate[i]) {
					Portata portata = trovaPortata(p, antipasti);
					if (portata != null) {
						res += portata.getPrezzo();
					} else if (portata == null && controllo) {
						erroreCodicePortata(p, i);
					}
				}
				break;
			case 1:
				for (int p : codiciPortate[i]) {
					Portata portata = trovaPortata(p, primi);
					if (portata != null) {
						res += portata.getPrezzo();
					} else if (portata == null && controllo) {
						erroreCodicePortata(p, i);
					}
				}
				break;
			case 2:
				for (int p : codiciPortate[i]) {
					Portata portata = trovaPortata(p, secondi);
					if (portata != null) {
						res += portata.getPrezzo();
					} else if (portata == null && controllo) {
						erroreCodicePortata(p, i);
					}
				}
				break;
			case 3:
				for (int p : codiciPortate[i]) {
					Portata portata = trovaPortata(p, dolci);
					if (portata != null) {
						res += portata.getPrezzo();
					} else if (portata == null && controllo) {
						erroreCodicePortata(p, i);
					}
				}
				break;
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

	public List<List<Portata>> getPortate2D(int[][] codiciPortate, boolean controllo) {

		List<List<Portata>> listaPortateDinamica = new ArrayList<>();

		for (int i = 0; i < codiciPortate.length; i++) {

			List<Portata> portataDinamica = new ArrayList<>();

			switch (i) {
			case 0:
				for (int p : codiciPortate[i]) {
					Portata portata = trovaPortata(p, antipasti);
					if (portata == null && controllo) {
						erroreCodicePortata(p, i);
					}
					portataDinamica.add(portata);
				}
				;
				listaPortateDinamica.add(portataDinamica);
				break;
			case 1:
				for (int p : codiciPortate[i]) {
					Portata portata = trovaPortata(p, primi);
					if (portata == null && controllo) {
						erroreCodicePortata(p, i);
					}
					portataDinamica.add(portata);
				}
				;
				listaPortateDinamica.add(portataDinamica);
				break;
			case 2:
				for (int p : codiciPortate[i]) {
					Portata portata = trovaPortata(p, secondi);
					if (portata == null && controllo) {
						erroreCodicePortata(p, i);
					}
					portataDinamica.add(portata);
				}
				;
				listaPortateDinamica.add(portataDinamica);
				break;
			case 3:
				for (int p : codiciPortate[i]) {
					Portata portata = trovaPortata(p, dolci);
					if (portata == null && controllo) {
						erroreCodicePortata(p, i);
					}
					portataDinamica.add(portata);
				}
				;
				listaPortateDinamica.add(portataDinamica);
				break;
			}
		}

		return listaPortateDinamica;
	}

	/*
	 * 
	 * CLASSE INNESTATA SERVIZIO RISTORANTE
	 * 
	 */

	public class ServizioRistorante {

		/*
		 * METODO PER IL CALCOLO DELLA SPESA TOTALE CON LO SCONTO, CON OPZIONE PER IL
		 * CONTROLLO CHE POI VIENE PASSATO AL METOTO "GetTotaleSpesa"
		 */

		public double calcolaSpesaConSconto(int[][] codiciPortate, int sconto, boolean controllo) {

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

		public void stampaComanda2D(int[][] codiciPortate) {

			List<List<Portata>> portate = getPortate2D(codiciPortate, true);
			double res = getTotaleSpesa2D(codiciPortate, false);
			double resScontato = calcolaSpesaConSconto(codiciPortate, 10, false);

			for (List<Portata> p : portate) {

				if (p.size() > 0)
					switch (portate.indexOf(p)) {
					case 0:
						System.out.println("ANTIPASTI");
						stampaNomePrezzo(p);
						break;
					case 1:
						System.out.println("PRIMI");
						stampaNomePrezzo(p);
						break;
					case 2:
						System.out.println("SECONDI");
						stampaNomePrezzo(p);
						break;
					case 3:
						System.out.println("DOLCI");
						stampaNomePrezzo(p);
						break;

					}
			}

			System.out.println(String.format("TOTALE \n%s €", res));
			System.out.println(String.format("TOTALE CON SCONTO \n%s €", resScontato));

		}

		/*
		 * METODO PER STAMPARE UN MENU A SCELTA O INTERO MENU (NO PARAMETRI)
		 */
		
		private static void menuLoop(Portata[]... tipoMenu) {
			int j = 0;
			for(Portata[] portata : tipoMenu) {
				int i = 0;
				System.out.println(String.format("%s", Ristorante.stampaTipoMenu(j).toUpperCase()));
				j++;
				for(Portata p : portata) {
					System.out.println("ID: " + i + "\n- " + p.getNome());
					i++;
				}
				System.out.println("");
			}
		}
		
		private static void menuLoop(int id,Portata[] tipoMenu) {
			int i = 0;
			System.out.println(String.format("%s", Ristorante.stampaTipoMenu(id).toUpperCase()));
			for(Portata portata : tipoMenu) {
					System.out.println("ID: " + i + "\n- " + portata.getNome());
					i++;
			}
			System.out.println("");
		}

		public void stampaMenu(int id) {
			switch(id) {
			case 0 : menuLoop(id,antipasti);
			break;
			case 1 : menuLoop(id,primi);
			break;
			case 2 : menuLoop(id,secondi);
			break;
			case 3 : menuLoop(id,dolci);
			break;
			default : stampaMenu() ;
			break;
			}	
	};
	
		public void stampaMenu() {
			menuLoop(antipasti,primi,secondi,dolci) ;
		}
		

	}

}
