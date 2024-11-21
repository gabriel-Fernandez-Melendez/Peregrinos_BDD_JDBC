package controlador;

import java.time.LocalDate;

import Modelo.Carnet;
import Modelo.Peregrino;

public class CarnetController {
	//el nuevo carnet de momento  no hace falta por que meteremos dentro de las DAO
	public static Carnet NuevoCarnet(Peregrino p) {
		Carnet ret =new Carnet();
		ret.setId(0L);//esta a 0 pero es autocalculado en la base de datos, este dato es irrelevante para la creacion del objeto
		ret.setParada(p.getParadas().get(0));
		ret.setFecha_creacion(LocalDate.now());
		//el carnet inicia con los valores de distancia y de vip a cero
		ret.setDistancia(0.0f);
		ret.setN_vips(0);
		return ret;
	}
}
