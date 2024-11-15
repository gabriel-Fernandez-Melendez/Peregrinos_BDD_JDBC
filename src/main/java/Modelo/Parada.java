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
	
	//conexion con la clase carnet
	private Carnet carnet;
	//conexion con la clase estancia
	private Estancia estancia;
	//campo de conexion con el objeto peregrino(por medio de una coleccion)
	private List<Peregrino> peregrinos;
	
	//constructor por defecto
	public Parada() {	
	}
	
	//constructor usando todos los argumentos
	public Parada(Long id, String nombre, char region, String responsable_parada) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.region = region;
		this.responsable_parada = responsable_parada;
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
	//metodo to string(por defecto aun sin ser modificado)
	@Override
	public String toString() {
		return getId()+" "+getNombre()+" "+getRegion()+" "+getResponsable_parada();
	}	
}
