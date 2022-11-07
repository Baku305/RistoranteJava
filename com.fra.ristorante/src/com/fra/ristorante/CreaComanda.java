package com.fra.ristorante;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fra.ristorante.Ristorante.ServizioRistorante;

public class CreaComanda {

	static Scanner scanner = new Scanner(System.in);

	static private void richiestaPortata(Ristorante ristorante, int indicePortate, List<Integer> tipoMenu,ServizioRistorante servizioRistorante) {
		try {
			String res = "";
			int ordine = 0;
			boolean loop = true;
			if(indicePortate == 0) {				
				System.out.println(String.format("Vuoi degli %s", Ristorante.nomeMenu.stampaTipoMenu(indicePortate)));
			} else {
				System.out.println(String.format("Vuoi dei %s", Ristorante.nomeMenu.stampaTipoMenu(indicePortate)));
			}
			res = scanner.nextLine();
			if (res.contains("si")) {
				while (loop) {
					System.out.println(String.format("Scegli altri %s", Ristorante.nomeMenu.stampaTipoMenu(indicePortate)));
					servizioRistorante.stampaMenu(indicePortate);
					ordine = scanner.nextInt();
					scanner.nextLine();
					if (ordine < ristorante.getMenu().get(indicePortate).size()) {
						tipoMenu.add(ordine);
						System.out.println("ne vuoi un altro?");
						res = scanner.nextLine();
						if (res.contains("no")) {
							loop = false;
						}
					} else {
						System.out.println(String.format("Non abbiamo questi %s, scegli meglio",
								Ristorante.nomeMenu.stampaTipoMenu(indicePortate)));
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static public int[][] creaComanda(Ristorante ristorante) {
		
		List<Integer> antipasti = new ArrayList<Integer>();
		List<Integer> primi = new ArrayList<Integer>();
		List<Integer> secondi = new ArrayList<Integer>();
		List<Integer> dolci = new ArrayList<Integer>();
		Ristorante.ServizioRistorante servizioRistorante = ristorante.new ServizioRistorante();

		richiestaPortata(ristorante, 0, antipasti,servizioRistorante);

		richiestaPortata(ristorante, 1, primi,servizioRistorante);

		richiestaPortata(ristorante, 2, secondi,servizioRistorante);

		richiestaPortata(ristorante, 3, dolci,servizioRistorante);

		int[] antipastiArray = antipasti.stream().mapToInt(Integer::intValue).toArray();
		int[] primiArray = primi.stream().mapToInt(Integer::intValue).toArray();
		int[] secondiArray = secondi.stream().mapToInt(Integer::intValue).toArray();
		int[] dolciArray = dolci.stream().mapToInt(Integer::intValue).toArray();

		int[][] comandaArray = { antipastiArray, primiArray, secondiArray, dolciArray };

		servizioRistorante.stampaComanda2D(comandaArray);
		return comandaArray;

	}
}
