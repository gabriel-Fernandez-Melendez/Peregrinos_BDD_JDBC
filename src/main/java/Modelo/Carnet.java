package Modelo;

import java.time.LocalDate;

public class Carnet {
	//campos de la clase con las especificaciones del esquema
	private Long id;
	private LocalDate fecha_creacion;
	private double distancia;
	private int n_vips;
	
	//conexion con la parada(falta por meter en el constructor)
	private Parada parada;
	
	//constructor por defecto
	public Carnet() {
		
	}
	//constructor usando todos los argumentos
	public Carnet(Long id, LocalDate fecha_creacion, double distancia, int n_vips) {
		super();
		this.id = id;
		this.fecha_creacion = fecha_creacion;
		this.distancia = distancia;
		this.n_vips = n_vips;
	}
	//getter y setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getFecha_creacion() {
		return fecha_creacion;
	}
	public void setFecha_creacion(LocalDate fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	public double getDistancia() {
		return distancia;
	}
	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}
	public int getN_vips() {
		return n_vips;
	}
	public void setN_vips(int n_vips) {
		this.n_vips = n_vips;
	}
	public Parada getParada() {
		return parada;
	}
	public void setParada(Parada parada) {
		this.parada = parada;
	}
	//metodo to string(por defecto aun sin ser modificado)
	@Override
	public String toString() {
		return "Carnet [id=" + id + ", fecha_creacion=" + fecha_creacion + ", distancia=" + distancia + ", n_vips="
				+ n_vips + "]";
	}
	
	
	
	
}
