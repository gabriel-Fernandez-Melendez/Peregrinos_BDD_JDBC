package Modelo;

import java.time.LocalDate;
import java.util.List;

public class Estancia {
	//campos de la clase con las especificaciones del esquema
	private Long id;
	private LocalDate fecha;
	private boolean vip;
	
	//conexion con la clase peregrino
	private Peregrino peregrino;
	//conexion con la clase peregrino
	private Parada parada;
	
	//constructor por defecto
	public Estancia() {	
	}
	//constructor usando todos los argumentos
	public Estancia(Long id, LocalDate fecha, boolean vip) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.vip = vip;
	}
	//nuevo constructor con los argumentos luego de las conexiones
	public Estancia(Long id, LocalDate fecha, boolean vip, Peregrino peregrino, Parada parada) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.vip = vip;
		this.peregrino = peregrino;
		this.parada = parada;
	}
	//getter y setters
	public Long getId() {
		return id;
	}	
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public boolean isVip() {
		return vip;
	}
	public void setVip(boolean vip) {
		this.vip = vip;
	}
	
	public Peregrino getPeregrino() {
		return peregrino;
	}
	public void setPeregrino(Peregrino peregrino) {
		this.peregrino = peregrino;
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
		return "id: " + id + ", fecha: " + fecha + ", vip: " + vip + ", id dle peregrino "+ peregrino.getId();
	}
}
