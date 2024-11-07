package view;

import controller.SteamController;

public class Main {
	
	public static void main(String[] args) {

		SteamController cont = new SteamController();
		String caminho = "C:\\temp";
		String nome = "SteamCharts.csv";
		String arquivo = "gravado.csv";

		try {
			cont.readFile(caminho, nome, "2018", "December", 150000.0);
			cont.generateFile(caminho, arquivo, "2018", "December");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
