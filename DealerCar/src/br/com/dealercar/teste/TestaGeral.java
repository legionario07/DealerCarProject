package br.com.dealercar.teste;

import java.io.File;
import java.text.ParseException;

import br.com.dealercar.domain.itensopcionais.Opcional;

public class TestaGeral {

	public static void main(String[] args) throws ParseException {
		
		StringBuffer pasta = new StringBuffer();
		pasta.append("WebContent\\resources\\images\\");
		pasta.append("teste");
		File diretorio = new File(pasta.toString()); 
		
		if (!diretorio.exists()) {
			   diretorio.mkdir(); 
			   System.out.println("Criado com sucesso");
			   System.out.println(diretorio.getPath());
		}else{
			System.out.println("Ja existe");
		}
		
	Opcional opcional = new Opcional();
	opcional.getItens().get(1).getNumeroPatrimonio();
		
	}
}
