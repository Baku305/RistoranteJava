package com.fra.ristorante;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fra.ristorante.Ristorante.ServizioRistorante;

public class CreaComanda {

	static Scanner scanner = new Scanner(System.in);

//	static public void chiediComanda(){
//		String res = "";
//		System.out.println("Vuoi creare una nuova comanda?");
//		if(res.contains("si")) {
//			creaComanda();
//		}
//		
//	}

	static private void richiestaPortata(Portata[][] portate, int indicePortate, List<Integer> tipoMenu) {
		try {
			String res = "";
			int ordine = 0;
			boolean loop = true;
			if(indicePortate == 0) {				
				System.out.println(String.format("Vuoi degli %s", Ristorante.stampaTipoMenu(indicePortate)));
			} else {
				System.out.println(String.format("Vuoi dei %s", Ristorante.stampaTipoMenu(indicePortate)));
			}
			res = scanner.nextLine();
			if (res.contains("si")) {
				while (loop) {
					System.out.println(String.format("Scegli altri %s", Ristorante.stampaTipoMenu(indicePortate)));
					ServizioRistorante.stampaMenu(portate[indicePortate]);
					ordine = scanner.nextInt();
					scanner.nextLine();
					if (ordine < portate[indicePortate].length) {
						tipoMenu.add(ordine);
						System.out.println("ne vuoi un altro?");
						res = scanner.nextLine();
						if (res.contains("no")) {
							loop = false;
						}
					} else {
						System.out.println(String.format("Non abbiamo questi %s, scegli meglio",
								Ristorante.stampaTipoMenu(indicePortate)));
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static public int[][] creaComanda(Portata[][] portate, Ristorante ristorante) {
		
		List<Integer> antipasti = new ArrayList<Integer>();
		List<Integer> primi = new ArrayList<Integer>();
		List<Integer> secondi = new ArrayList<Integer>();
		List<Integer> dolci = new ArrayList<Integer>();

		richiestaPortata(portate, 0, antipasti);

		richiestaPortata(portate, 1, primi);

		richiestaPortata(portate, 2, secondi);

		richiestaPortata(portate, 3, dolci);

		int[] antipastiArray = antipasti.stream().mapToInt(Integer::intValue).toArray();
		int[] primiArray = primi.stream().mapToInt(Integer::intValue).toArray();
		int[] secondiArray = secondi.stream().mapToInt(Integer::intValue).toArray();
		int[] dolciArray = dolci.stream().mapToInt(Integer::intValue).toArray();

		int[][] comandaArray = { antipastiArray, primiArray, secondiArray, dolciArray };

		Ristorante.ServizioRistorante servizioRistorante = ristorante.new ServizioRistorante();
		servizioRistorante.stampaComanda2D(comandaArray);
		return comandaArray;

	}
}
