package br.com.dealercar.core.teste;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
	
	public static String verificaPlaca(String s){
		StringBuffer retorno = new StringBuffer();
		
		if(!s.matches("\\w{3}-\\d{4}")){
			retorno.append("Placa Invalida");
		}
		
		
		return retorno.toString();
		
		
	}
	
	public static Date getPrimeiroDiaDoMesAtual(String ano, String mes, String dia) {
		Calendar c = Calendar.getInstance();
		
		c.set(Integer.parseInt(ano), Integer.parseInt(mes), Integer.parseInt(dia));
		
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		d = c.getTime();
		
		String data =sdf.format(d.getTime()); 
		try {
			return sdf.parse(data);
		} catch (ParseException e) {
			return null;
		}
		
	}
	
	public static Date getUltimoDiaDoMesAtual(String ano, String mes, String dia) {
		Calendar c = Calendar.getInstance();
		
		c.set(Integer.parseInt(ano), Integer.parseInt(mes), Integer.parseInt(dia));
		
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		d = c.getTime();
		
		String data =sdf.format(d.getTime()); 
		try {
			return sdf.parse(data);
		} catch (ParseException e) {
			return null;
		}
		
	}
	
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
/*
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
       */
		
		/*
		int dataAno = Calendar.YEAR;
		int dataAno2 = Calendar.getInstance().get(Calendar.YEAR);
		int dataMes = Calendar.getInstance().get(Calendar.MONTH);
		int dia = 1;
		System.out.println(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
		System.out.println(dataAno);
		System.out.println(dataAno2);
		System.out.println(dataMes);
		
		Calendar.getInstance().set(dataAno2, 1, 1);
		System.out.println(Calendar.getInstance().getTime());
		Calendar c = Calendar.getInstance();
		*/
		/*
		String mes = "01";
		String dia = "01";
		String ano = "2016";
		
		//String data= dia+"/"+mes+"/"+ano;
		
		Date data2 = getPrimeiroDiaDoMesAtual(ano, mes, dia);
		System.out.println(data2);
		Date data3 = getUltimoDiaDoMesAtual(ano, mes, dia);
		System.out.println(data3);
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd _ hh:mm:ss");

		Calendar c = Calendar.getInstance();
		String s = sdf.format(c.getTime());
		System.out.println(s.replace("/", "").replace(" ", "").replace(":", ""));
		*/
		
		
		
	}
}
