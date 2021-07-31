package logico;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class mainPruebas {

	public static void main(String[] args) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
		Date fecha;
		try {
			fecha = formato.parse("23/11/2015");
			System.out.print(formato.format(fecha));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
