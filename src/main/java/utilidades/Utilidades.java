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
			 linea =scan.next();
			 if(Pattern.matches("[a-zA-Z]+",linea)) {
				 val=true;
			 }
			 else {
				 System.out.println("no puede incluir numeros en este campo,porfavor rellene denuevo el campo correctamente ");
			 }
		}while(!val);		

		return linea;		
	}
	
	public static int LeerNumero() {
		int num=0;
		Scanner scan=new Scanner(System.in);
		//en este caso no declaro una variable booleana ya que se comprueba en la condicion del while
		while(!scan.hasNextInt()) {
			num = scan.nextInt();
			if(!scan.hasNextInt()) {
				System.out.println("engrese un valor que solo contenga numeros");
			}
		}	
		return num;
	}
}
