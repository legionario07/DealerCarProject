package br.com.dealercar.viewhelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.dealercar.util.DataUtil;

public class ViewHelper {

	public static int validarData(Date data) {
		int i;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String minData = "01/01/1900";
		Date dataMin = null;
		try {
			dataMin = sdf.parse(minData);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		i = DataUtil.compararDatas(dataMin, data);

		return i;

	}


}
