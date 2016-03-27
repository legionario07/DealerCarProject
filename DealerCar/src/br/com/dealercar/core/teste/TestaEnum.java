package br.com.dealercar.core.teste;

public class TestaEnum {

	private enum TipoEnum{
		HUM("um"),
		DOIS("dois"),
		TRES("tres");
		
		String descricao;
		
		private TipoEnum(String descricao){
			this.descricao = descricao;
		}
		
		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}
	}
	
	public static void main(String[] args) {
		
		//System.out.println(TipoEnum.DOIS.getDescricao());
		TipoEnum.HUM.setDescricao("Agora Mudei");
		System.out.println(TipoEnum.HUM.getDescricao());
		
	}
	
}
