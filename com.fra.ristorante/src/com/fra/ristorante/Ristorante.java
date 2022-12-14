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
		switch(indexMenu) {
		case 0 : return "Antipasti";
		case 1 : return "Primi";
		case 2 : return "secondi";
		case 3 : return "dolci";
		default : return "menù";
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
		System.out.println(String.format("prodotto con codice %s non trovato in %s", codicePortata,stampaTipoMenu(menuIndex)));
	}
	

	/*
	 * METODO PER IL TOTALE DELLA SPESA CON OPZIONE PER IL CONTROLLO
	 */

	public double getTotaleSpesa2D(int[][] codiciPortate,boolean controllo) {

		double res = 0.00;

		for (int i = 0; i < codiciPortate.length; i++) {
			switch (i) {
			case 0:
				for (int p : codiciPortate[i]) {
					Portata portata = trovaPortata(p, antipasti);
					if(portata!=null) {						
						res += portata.getPrezzo();
					} else if (portata == null && controllo) {
						erroreCodicePortata(p,i);
					} 
				}
				break;
			case 1:
				for (int p : codiciPortate[i]) {
					Portata portata = trovaPortata(p, primi);
					if(portata!=null){						
						res += portata.getPrezzo();
					}else if (portata == null && controllo) {
						erroreCodicePortata(p,i);
					}
				}
				break;
			case 2:
				for (int p : codiciPortate[i]) {
					Portata portata = trovaPortata(p, secondi);
					if(portata!=null){						
						res += portata.getPrezzo();
					}else if (portata == null && controllo) {
						erroreCodicePortata(p,i);
					}
				}
				break;
			case 3:
				for (int p : codiciPortate[i]) {
					Portata portata = trovaPortata(p, dolci);
					if(portata!=null){						
						res += portata.getPrezzo();
					}else if (portata == null && controllo) {
						erroreCodicePortata(p,i);
					}
				}
				break;
			}
		}

		return res;
	}

	/*
	 * METODO PER TROVARE LA PORTATA IN MODO DINAMICO, RICEVE UN ARRAY 2D CON I CODICI DELLE PORTATE E RESTITUISCE UN ARRAY 2D
	 * CON TUTTE LE PORTATE CORRISPONDENTI RISPETTO ALLA POSIZIONE (INDICE) DELL'ARRAY SOTTOSTANTE, CON OPZIONE PER IL CONTROLLO 
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
					if(portata == null && controllo) {
						erroreCodicePortata(p,i);
					}
					portataDinamica.add(portata);
				}
				;
				listaPortateDinamica.add(portataDinamica);
				break;
			case 1:
				for (int p : codiciPortate[i]) {
					Portata portata = trovaPortata(p, primi);
					if(portata == null && controllo) {
						erroreCodicePortata(p,i);
					}
					portataDinamica.add(portata);
				}
				;
				listaPortateDinamica.add(portataDinamica);
				break;
			case 2:
				for (int p : codiciPortate[i]) {
					Portata portata = trovaPortata(p, secondi);
					if(portata == null && controllo) {
						erroreCodicePortata(p,i);
					}
					portataDinamica.add(portata);
				}
				;
				listaPortateDinamica.add(portataDinamica);
				break;
			case 3:
				for (int p : codiciPortate[i]) {
					Portata portata = trovaPortata(p, dolci);
					if(portata == null && controllo) {
						erroreCodicePortata(p,i);
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

}
