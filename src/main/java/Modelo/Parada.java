package Modelo;

import java.io.Serializable;
import java.util.List;

public class Parada implements Serializable{//NOTA habra que hacer otro constructor en el futuro
	
	private static final long serialVersionUID = 1L;
	//campos de la clase con las especificaciones del esquema
	private Long id;
	private String nombre;
	private char region;
	private String responsable_parada;
	//un nuevo private para llergar el long id de la tabla credenciales incluida en esta base de datos
	private long id_credenciales;
	//conexion con la clase carnet
	private Carnet carnet;
	//conexion con la clase estancia
	private Estancia estancia;
	//campo de conexion con el objeto peregrino(por medio de una collation)
	private List<Peregrino> peregrinos;
	
	//constructor por defecto
	public Parada() {	
	}
	
	//constructor usando todos los argumentos
		public Parada(Long id, String nombre, char region, String responsable_parada, long id_credenciales, Carnet carnet,
			Estancia estancia, List<Peregrino> peregrinos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.region = region;
		this.responsable_parada = responsable_parada;
		this.id_credenciales = id_credenciales;
		this.carnet = carnet;
		this.estancia = estancia;
		this.peregrinos = peregrinos;
	}
	//getter y setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public char getRegion() {
		return region;
	}
	public void setRegion(char region) {
		this.region = region;
	}
	public String getResponsable_parada() {
		return responsable_parada;
	}
	public void setResponsable_parada(String responsable_parada) {
		this.responsable_parada = responsable_parada;
	}
	
	public long getId_credenciales() {
		return id_credenciales;
	}

	public void setId_credenciales(long id_credenciales) {
		this.id_credenciales = id_credenciales;
	}

	public Carnet getCarnet() {
		return carnet;
	}

	public void setCarnet(Carnet carnet) {
		this.carnet = carnet;
	}

	public Estancia getEstancia() {
		return estancia;
	}

	public void setEstancia(Estancia estancia) {
		this.estancia = estancia;
	}

	public List<Peregrino> getPeregrinos() {
		return peregrinos;
	}

	public void setPeregrinos(List<Peregrino> peregrinos) {
		this.peregrinos = peregrinos;
	}
	

	//metodo to string(por defecto aun sin ser modificado)
	@Override
	public String toString() {
		return getId()+" "+getNombre()+" "+getRegion()+" "+getResponsable_parada()+"id de las credenciales: "+ getId_credenciales();
	}	
}
