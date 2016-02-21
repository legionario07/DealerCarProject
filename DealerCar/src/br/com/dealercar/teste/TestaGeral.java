package br.com.dealercar.teste;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.dealercar.dao.ReservaDAO;
import br.com.dealercar.domain.Reserva;

public class TestaGeral {

	/**
	 * @param args
	 * @throws ParseException
	 */
	/**
	 * @param args
	 * @throws ParseException
	 */
	/**
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {

		/*
		 * StringBuffer pasta = new StringBuffer();
		 * pasta.append("WebContent\\resources\\images\\");
		 * pasta.append("teste"); File diretorio = new File(pasta.toString());
		 * 
		 * if (!diretorio.exists()) { diretorio.mkdir(); System.out.println(
		 * "Criado com sucesso"); System.out.println(diretorio.getPath());
		 * }else{ System.out.println("Ja existe"); }
		 * 
		 * Opcional opcional = new Opcional();
		 * opcional.getItens().get(1).getNumeroPatrimonio();
		 */

		List<Reserva> lista = new ArrayList<Reserva>(); //lista que recebe todos os itens do BD
		List<String> listaString = new ArrayList<String>(); //Lista que ira receber apenas os nomes
		lista = new ReservaDAO().listarTodos();
		//passando apenas os nomes para a lista de String
		for (Reserva r : lista) {
			listaString.add(r.getModelo().getNome());
		}
		//Criando uma collections com apenas os distintos
		Set<String> reservasDistintas = new HashSet<String>(listaString);
		//criando uma lista que ira transforrmar a collection em uma lista de String
		List<String> listaDistinta = new ArrayList<String>();

		//HashMap que ira receber <Nome, qde>
		HashMap<String, Integer> grafico = new HashMap<String, Integer>();
		
		for(String s: reservasDistintas){
			listaDistinta.add(s);
		}

		System.out.println("\n\n" + grafico.keySet());
		
		Collections.sort(listaDistinta);
		Collections.sort(listaString);
        for (int i =0; i < listaDistinta.size(); i++)
        {
            int count = Collections.frequency(listaString, listaDistinta.get(i));
            grafico.put(listaDistinta.get(i), count);
            System.out.println(listaDistinta.get(i)+" recebeu "+ count + "votos ");  
        }

        System.out.println(grafico.entrySet());
        
       System.out.println(grafico.size());
	}
}
