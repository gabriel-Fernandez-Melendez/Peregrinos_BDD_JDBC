package utilidades;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Utilidades {

	public static boolean leerBoolean() {
		boolean ret;
		Scanner in;
		char resp;
		do {
			System.out.println("Pulse s para Sí o n para No");
			in = new Scanner(System.in, "ISO-8859-1");
			in.reset();
			resp = in.nextLine().charAt(0);
			if (resp != 's' && resp != 'S' && resp != 'n' && resp != 'N') {
				System.out.println("Valor introducido incorrecto.");
			}
		} while (resp != 's' && resp != 'S' && resp != 'n' && resp != 'N');
		if (resp == 's' || resp == 'S') {
			ret = true;
		} else {
			ret = false;
		}
		return ret;
	}
	
	public static String LeerCadena() {
		boolean val = false;
		Scanner scan=new Scanner(System.in);
		String linea;
		do {			
			 linea =scan.nextLine();
			 if(Pattern.matches("[a-zA-Z]+",linea)) {
				 val=true;
			 }
			 else {
				 System.out.println("no puede incluir numeros en este campo,porfavor rellene denuevo el campo correctamente ");
			 }
		}while(!val);		
		scan.close();		
		return linea;
		
	}
}
