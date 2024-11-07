package controller;

import java.io.*;

public class SteamController {

	public void readFile(String caminho, String nome, String ano, String mes, Double mediaDesejada) throws IOException {
		dirExists(caminho);

		File arquivo = new File(caminho, nome);
		String[] linhaVetor;

		if (arquivo.exists() && arquivo.isFile()) {
			FileInputStream fluxo = new FileInputStream(arquivo);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			
			linha = buffer.readLine();
			while (linha != null) {
				linhaVetor = linha.split(",");
				Double media = Double.parseDouble(linhaVetor[3]);

				if (linha.contains(ano) && linha.contains(mes) && media >= mediaDesejada) {
					linhaVetor = linha.split(",");
					System.out.println(linhaVetor[0] + " | " + linhaVetor[3]);
				}
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		} else {
			throw new IOException("Arquivo não existe");
		}

	}

	public void dirExists(String caminho) throws IOException {
		File diretorio = new File(caminho);

		if (!(diretorio.exists() && diretorio.isDirectory())) {
			throw new IOException("Diretório não existe");
		}
	}
	
	public void generateFile(String caminho, String nomeArquivo, String ano, String mes) throws IOException {
		dirExists(caminho);

		File arquivoSteam = new File("C:\\temp","SteamCharts.csv");
		File arquivoGravado = new File(caminho, nomeArquivo);
		StringBuffer stringBuffer = new StringBuffer();
		String[] linhaVetor;

		if (arquivoSteam.exists() && arquivoSteam.isFile()) {
			FileInputStream fluxo = new FileInputStream(arquivoSteam);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			
			linha = buffer.readLine();
			while (linha != null) {
				if (linha.contains(ano) && linha.contains(mes)) {
					linhaVetor = linha.split(",");
					stringBuffer.append(linhaVetor[0] + " ; " + linhaVetor[3] + "\n");
				}
				linha = buffer.readLine();
			}
			
			buffer.close();
			leitor.close();
			fluxo.close();
		
		} else {
			throw new IOException("Arquivo inválido");
		}

		FileWriter fileWriter = new FileWriter(arquivoGravado);
		PrintWriter printWriter = new PrintWriter(fileWriter);
		
		printWriter.write(stringBuffer.toString());
		
		printWriter.flush();
		printWriter.close();
		fileWriter.close();
		
		System.out.println("\n" + "Arquivo gravado");
	}
}