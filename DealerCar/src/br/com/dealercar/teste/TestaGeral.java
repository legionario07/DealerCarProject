package br.com.dealercar.teste;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.dealercar.util.DataUtil;

public class TestaGeral {

	public static void main(String[] args) throws ParseException {
		
		/*
		ClienteBean cli = new ClienteBean();
		
		//System.out.println(cli.validaDataNascimento());
		

		//colocando formato string para armazenar no banco de dados
		SimpleDateFormat stf = new SimpleDateFormat("dd/MM/yyyy");
		String dataAnterior = "11/12/2015";
		Date segundaData = stf.parse(dataAnterior);
		Date atual = DataUtil.pegarDataAtualDoSistema();
		
		System.out.println(segundaData);
		System.out.println(atual);
		
		System.out.println(DataUtil.devolverDataEmDias(segundaData));
		*/
		
		/*
		TaxasAdicionais taxas = new TaxasAdicionais();
		TaxaCombustivel c = new TaxaCombustivel();
		
		System.out.println(c.isFoiCobrado());
		
		System.out.println(c);
		
		if(c.getDescricao() == null){
			System.out.println("Entrou aki por o codigo eh null");
		}
		
		*/
			
		/*
		List<String> lista = new ArrayList<String>();
		
		lista = new Pneu().getPosicoesPneu();
		

		
		for(String s : lista){
			System.out.println(s);
		}

		System.out.println("\n\n");
		
		PosicaoPneu[] posicoes = PosicaoPneu.values();
		for(PosicaoPneu p : posicoes){
			System.out.println(p);
		}
		*/
		
		/*
		Devolucao devolucao = new Devolucao();
		Retirada retirada = new Retirada(3);
		
		retirada = new RetiradaDAO().pesquisarPorID(retirada);
		
		devolucao.setRetirada(retirada);
		
		devolucao.setDataDevolucao(DataUtil.pegarDataAtualDoSistema());
		devolucao.setQtdeDiarias(DataUtil.devolverDataEmDias(devolucao.getRetirada().getDataRetirada()));
		
		List<TaxasAdicionais> taxas = new ArrayList<TaxasAdicionais>();
		List<TaxasAdicionais> taxas2 = new ArrayList<TaxasAdicionais>();
		
		taxas = new TaxasAdicionaisDAO().listarTodos();
		
		TaxasAdicionais taxa = new TaxasAdicionais();
		taxa.setDescricao("Lavagem");
		taxa = new TaxasAdicionaisDAO().pesquisarPorTaxa(taxa.getDescricao());
		taxa.setFoiCobrado(true);
		taxas2.add(taxa);
		devolucao.setTaxasAdicionais(taxas2);
		
		System.out.println(devolucao.calcularValorTaxasAdicionais(devolucao, taxas));
		
		devolucao.setValorFinal(devolucao.calcularValorFinal(devolucao, taxas));
		
		*/
		
		Date date = DataUtil.pegarDataAtualDoSistema();
		System.out.println(date);
		
		String data2 = "25/12/2015";
		SimpleDateFormat stf = new SimpleDateFormat("dd/MM/yyyy");
		
		Date dataSegunda = stf.parse(data2);
		System.out.println(dataSegunda);
		
		System.out.println(DataUtil.compararDatas(date, dataSegunda));
		
		
	}
}
