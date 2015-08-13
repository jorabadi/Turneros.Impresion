package turneros.Impresion.post;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import turneros.Common.entidades.Reserva;

public class ImpresionTurnosPost {
	public static void imprimirReserva(Reserva reserva) {
		System.out.println("Se imprimio la reserva del servicio :"+reserva.getServicio()+" del turno:"+reserva.getTurno());
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String hora = sdf.format(cal.getTime());
		String template = 
				"EMPRESA\n"+
				"\n"+
				"\n"+
				"Turno\n"+
				"\n"+
				"\n"+
				"${turno}\n"+
				"\n"+
				"${servicio}\n"+
				"\n"+
				"Hora:	"+hora;
		PostPrinterDriver.print(template, reserva);
	}
}
