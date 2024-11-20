package controlador;

import java.io.CharConversionException;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import BDD.Peregrino_BDD;
import DAO.CredencialesUsuarioDAO;
import Modelo.Carnet;
import Modelo.CredencialesUsuario;
import Modelo.Estancia;
import Modelo.Parada;
import Modelo.Peregrino;
import Modelo.Usuarios;
import utilidades.Utilidades;
import vista.Menus;

public class PeregrinoController {
	public static Connection c=null;
	public static Peregrino_BDD con=Peregrino_BDD.Conex_BDD(c);
	
	public static Peregrino NuevoPeregrino() {
		boolean val =false;
		Peregrino p = new Peregrino();
		CredencialesUsuario cred=new CredencialesUsuario();
		do {		
		System.out.println("ingrese su nombre para almecenarla en el sistema");
		String nombre=Utilidades.LeerCadena();
		System.out.println("ingrese su contraseña para almecenarla en el sistema");
		String contraseña =Utilidades.LeerCadena();		
		cred.setNombre(nombre);
		cred.setClave(contraseña);
		cred.setTipo_usuario(Usuarios.Peregrino);
		//creamos el objetoDAO QUE TIENE SINGLETON PARA EJECUTAR LA INSERCION
		
		CredencialesUsuarioDAO credenciales=CredencialesUsuarioDAO.Conexion_CredencialesUsuario(con);
		val=credenciales.insertarConID(cred);
		if(val) {//este if tiene que tener true si el valor de las credenciales no existia hasta ahora
		System.out.println("las credenciales son validas");	
		System.out.println("Se han ingresado a la base de datos!");	
		p.setNombre(nombre);
		}
		else {
			System.out.println("introduzca unas credenciales validas");
			val=false;
		}	
		} while (!val);
		val=true;
		do {
			HashMap<String, String> paises = new HashMap<String, String>();
			ArrayList<String> claves = new ArrayList<String>();
			paises=SeleccionDePais();
			claves=Ordenarlista(paises);
			int i =1;
			for(String pais:claves) {
				//de esta forma esta ordenado de manera alfabetica y no se ve la clave si no solo el nombre del pais
				System.out.println(i+" - "+paises.get(pais));
				i++;
			}
			System.out.println("ingrese el numero del pais al que pertenece: ");
			int pais_num=Utilidades.LeerNumero();
			//le quitamos 1 para pillar el que metio por pantalla el usuario
			String valor_pais =claves.get((pais_num)-1);
			System.out.println("es su pais el "+paises.get(valor_pais));
			val=Utilidades.leerBoolean();
			if(val) {
				p.setNacionalidad(valor_pais);
			}
			else {
				val=false;
			}
			} while (!val);
		val=false;
		do {		
			ArrayList<Parada> paradas = new ArrayList<Parada>();//primer metodo que debe usar DAO
			//paradas=Controlador_Parada.ListaDeParadas(); metodo de datos que hay que haces
			int z=1;
			for(Parada par:paradas) {
				System.out.println(z+" -"+par.toString());
			}
			System.out.println("ingrese el numero de la parada en la que esta: ");
			int parada_num=utilidades.Utilidades.LeerNumero();
			Parada valor_parada=paradas.get((parada_num)-1);
			System.out.println("es su parada la: "+valor_parada.toString());
			val=Utilidades.leerBoolean();
			if(val) {
				//recordar que cada peregrino puede pasar por varias paradas, por eso es una coleccion de objetos y no uno solo
				ArrayList<Parada> paradas_ = new ArrayList<Parada>();
				paradas_.add(valor_parada);
				p.setParadas(paradas_);
			}
			else {
				val=false;
			}
		} while (!val);
		Carnet carnet=new Carnet();
		carnet=CarnetController.NuevoCarnet(p);//hay que tocar este metodo
		p.setCarnet_peregrino(carnet);
		//hania codigo relativo a estancias aqui, creo que no sera necesario en el futuro pero dejo el comentatario en caso de que me sea necesario
		System.out.println("se a añadido al peregrino con: "+p.getId()+" "+p.getNombre()+" "+p.getNacionalidad()+" "+carnet.getFecha_creacion()+" "+p.getParadas().get(0).getNombre()+" "+p.getParadas().get(0).getRegion());
		Menus.MenuLogin(cred,p);//tengo que crear aun el carnet
		return p;
	}
	public static HashMap<String, String> SeleccionDePais() {
		HashMap<String, String> paises = new HashMap<String, String>();
		System.out.println("Escoja su pais de la siguiente lista: ");
		try {
			DocumentBuilder builder =DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document documento = builder.parse(new File("FicherosPeregrino/paises.xml"));
			documento.getDocumentElement().normalize();
			NodeList empleados = documento.getElementsByTagName("pais");
			for (int i = 0; i < empleados.getLength(); i++) {
				 Node emple = empleados.item(i);
				 if(emple.getNodeType() == Node.ELEMENT_NODE){
					 Element elemento = (Element) emple;//Obtenemos los elementos del nodo
					 if(emple.getNodeType() == Node.ELEMENT_NODE){
						 //System.out.print("id: "+extraer_datos_pais("id",elemento));
						 //System.out.println("pais: "+extraer_datos_pais("nombre", elemento));
						 paises.put(extraer_datos_pais("id",elemento),extraer_datos_pais("nombre", elemento));
					 //System.out.println("----------------------------------");
					 }
				 }
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return paises;	
	}
	private static String extraer_datos_pais(String etiqueta, Element elem){
		 NodeList nodo =
		elem.getElementsByTagName(etiqueta).item(0).getChildNodes();
		 Node valorNodo = (Node) nodo.item(0);
		 return valorNodo.getNodeValue();
		 }
	public static ArrayList<String> Ordenarlista(HashMap<String, String> pa){
		ArrayList<String> clave = new ArrayList<String>(pa.keySet());
		Collections.sort(clave);
		return clave;
	}
}
