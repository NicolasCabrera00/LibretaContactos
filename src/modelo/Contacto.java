package modelo;

import java.sql.Connection;

public class Contacto {

	String nombre = "";
	int numero;	
	
	public Contacto(String nombre, int numero) {
		this.nombre = nombre;
		this.numero = numero;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	public int getNumero() {
		return this.numero;
	}

}
