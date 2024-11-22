package controlador;

import java.io.CharConversionException;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import BDD.Peregrino_BDD;
import DAO.CarnetDAO;
import DAO.CredencialesUsuarioDAO;
import DAO.ParadaDAO;
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
		Collection<CredencialesUsuario> lista=new ArrayList<CredencialesUsuario>();
		lista=credenciales.buscarTodos();
		val=CredencialesUsuarioController.ValidarCredencialesNuevas(lista, cred);
		if(val) {
			val=credenciales.insertarConID(cred);
		}
		else {
			//esto para que si no son validas las ingrese de nuevo
			val=false;
		}
		if(val) {//este if tiene que tener true si el valor de las credenciales no existia hasta ahora y que las ingrese en caso de no existir
		System.out.println("las credenciales son validas");	
		System.out.println("Se han ingresado a la base de datos!");	
		p.setNombre(nombre);
		}
		else {
			System.out.println("introduzca unas credenciales validas");
			val=false;
		}	
		} while (!val);
		val=false;
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
		val=true;
		do {		
			ArrayList<Parada> paradas = new ArrayList<Parada>();
			ParadaDAO parada=ParadaDAO.Conexion_Parada(con);
			//cast a arraylist para poder usar el metodo get() para la seleccion de la parada
			paradas=(ArrayList<Parada>)parada.buscarTodos();
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
		carnet=CarnetController.NuevoCarnet(p);//este metodo devuelve un objeto con los datos necesarios de un  nuevo carnet
		CarnetDAO carnetBDD= CarnetDAO.Conexion_Peregrino(con);
		long num=carnetBDD.insertarSinID(carnet);
		System.out.println("el id del carnet es: "+num);
		p.setCarnet_peregrino(carnet);
		//hania codigo relativo a estancias aqui, creo que no sera necesario en el futuro pero dejo el comentatario en caso de que me sea necesario
		System.out.println("se a añadido al peregrino con: "+p.getId()+" "+p.getNombre()+" "+p.getNacionalidad()+" "+carnet.getFecha_creacion()+" "+p.getParadas().get(0).getNombre()+" "+p.getParadas().get(0).getRegion());
		Menus.MenuLogin(cred,p);//tengo que crear aun el carnet
		//IMPORTANTE TENGO QUE SELLAR EL CODIGO DE QUE CREO EL CARNET EN ESA PARADA--
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
	public static void ExportarXml(Peregrino p) {
		 
		try {
			DocumentBuilderFactory fabricaConstructorDocumento = DocumentBuilderFactory.newInstance();
			DocumentBuilder constructorDocumento = fabricaConstructorDocumento.newDocumentBuilder();
			DOMImplementation implementacion = constructorDocumento.getDOMImplementation();
			Document documento = implementacion.createDocument(null, "carnet", null);
            Element carnet = documento.getDocumentElement();
            //creamos los elementos
            Element id, fechaexp, expedidoen,peregrino,nombre,nacionalidad,hoy,distanciatotal,paradas,parada,orden,nombre_parada,region,estancias,estancia,id_estancia,fecha_estancia,parada_estancia,vip;
            //y el contenido de los elementos(misma cantidad de texto que elementos, aunque lo mas probable no los use todos)
            Text id_val, fechaexp_val, expedidoen_val,peregrino_val,nombre_val,nacionalidad_val,hoy_val,distanciatotal_val,paradas_val,parada_val,orden_val,nombre_val_val,region_val,estancias_val,estancia_val,id_estancia_val,fecha_estancia_val,parada_estancia_val,vip_val;
            //creamos los elementos en el orden del ejemplo de la tarea
            //elemento id
            id =documento.createElement("id");
            carnet.appendChild(id);
            id_val = documento.createTextNode(p.getId().toString());
            id.appendChild(id_val);
            
            
            expedidoen=documento.createElement("fechaexp");
            carnet.appendChild(expedidoen);
            fechaexp_val=documento.createTextNode(p.getCarnet_peregrino().getFecha_creacion().toString());
            expedidoen.appendChild(fechaexp_val);
            
            peregrino=documento.createElement("peregrino");
            carnet.appendChild(peregrino);
            nombre=documento.createElement("nombre");
            peregrino.appendChild(nombre);
            nombre_val = documento.createTextNode(p.getNombre().toString());
            nombre.appendChild(nombre_val);
            nacionalidad=documento.createElement("nacionalidad");
            peregrino.appendChild(nacionalidad);
            nacionalidad_val = documento.createTextNode(p.getNacionalidad());
            nacionalidad.appendChild(nacionalidad_val);
            
            hoy=documento.createElement("hoy");
            carnet.appendChild(hoy);
            hoy_val=documento.createTextNode(LocalDate.now().toString());
            hoy.appendChild(hoy_val);
            
            distanciatotal=documento.createElement("distanciatotal");
            carnet.appendChild(distanciatotal);
            //en relacion con el programa anterior hubo que modificar este campo para que se convirtiera en String 
            distanciatotal_val=documento.createTextNode(String.valueOf(p.getCarnet_peregrino().getDistancia()));
            distanciatotal.appendChild(distanciatotal_val);
            
            
            paradas=documento.createElement("paradas");
            carnet.appendChild(paradas);
            parada=documento.createElement("parada");
            paradas.appendChild(parada);
            orden=documento.createElement("orden");
            parada.appendChild(orden);
            //de momento no hace falta pero para el futuro habria que hacer un  bucle for que recorra y haga varios nodos parada en funcion de las paradas dentro de la lista(se implementara mas adelante)
            orden_val=documento.createTextNode(p.getParadas().get(0).getId().toString());
            orden.appendChild(orden_val);
            nombre_parada=documento.createElement("nombre");
            parada.appendChild(nombre_parada);
            nombre_val_val=documento.createTextNode(p.getParadas().get(0).getNombre());
            nombre_parada.appendChild(nombre_val_val);
            region=documento.createElement("region");
            parada.appendChild(region);
            region_val=documento.createTextNode(String.valueOf(p.getParadas().get(0).getRegion())); //casteo de un  char a string
            region.appendChild(region_val);
            
            estancias=documento.createElement("estancias");
            carnet.appendChild(estancias);
            //aqui hariamos lo mismo que se implementara en el futuro para que pueda haber mas estancias,se recorrera la lista de peregrino con un  bucle for (aun por implementar)
            estancia=documento.createElement("estancia");
            estancias.appendChild(estancia);
            id_estancia=documento.createElement("id");
            estancia.appendChild(id_estancia);
            id_estancia_val=documento.createTextNode(p.getEstancias().get(0).getId().toString());
            id_estancia.appendChild(id_estancia_val);
            fecha_estancia=documento.createElement("fecha");
            estancia.appendChild(fecha_estancia);
            fecha_estancia_val=documento.createTextNode(p.getEstancias().get(0).getFecha().toString());
            fecha_estancia.appendChild(fecha_estancia_val);
            //para el vip hay que hacer un if para confirmar si el booleano esta como true(no hace falta poner el "==")
            if(p.getEstancias().get(0).isVip()) {
            	vip=documento.createElement("vip");
            	estancia.appendChild(vip);
            }
            
            Source fuente = new DOMSource(documento);
            File fichero = new File("FicherosPeregrino/"+p.getNombre()+".xml");
            Result resultado = new StreamResult(fichero);
            TransformerFactory fabricaTransformador = TransformerFactory.newInstance();
            Transformer transformador = fabricaTransformador.newTransformer();
            transformador.transform(fuente, resultado);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         

	}
}
