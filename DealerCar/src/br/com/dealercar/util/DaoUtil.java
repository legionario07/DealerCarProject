package br.com.dealercar.util;

public class DaoUtil {
	
	/**
	 * Metodo verifica se a Classe que chamou o metodo DAO
	 * tem origem de em outro DAO. Devido a isso o ResultSet, PreparedStament
	 * n�o deve ser fechado. Apenas no m�todo chamador.
	 * @return -1 = significa que a n�o foi chamado por nenhum m�todo
	 * 
	 */
	public static int isCallFromDao(){
		
		Throwable thr = new Throwable();
	    thr.fillInStackTrace ();
	    StackTraceElement[] ste = thr.getStackTrace();
	    
	    return ste[2].getClassName().lastIndexOf("DAO");
	}

}
