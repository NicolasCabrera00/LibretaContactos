package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import javax.swing.JOptionPane;
import modelo.Contacto;

//		El objetivo de esta clase es tener todos los metodos estaticos necesarios
//		para poder crear, eliminar y editar un contacto de manera correcta


public class Control {

	
	//Retorna true si ya existe un contacto con el mismo nombre que el pasado como parametro
	public static boolean mismoNombre(Contacto cont, Connection con, String tabla) {
						//			<--Parametros-->
						//			con -> conexion a la base de datos
						//			tabla -> tabla donde se encuentra el contacto
						//			cont -> contacto que se verifica
			
		//Obtengo los valores del contacto y armo la sentencia sql (para SQLite)
		String nombre = cont.getNombre();
		String sql = "SELECT * FROM "+tabla+" WHERE nombre ='"+nombre+"'";
		
		//Ejecuto la sentencia sql.
		try {
			PreparedStatement pdst = con.prepareStatement(sql);
			ResultSet resultado = pdst.executeQuery();
			//Si se encuentra al menos un registro, retorna true
			if(resultado.next()) {
				pdst.close();
				resultado.close();

				return true;
				}else {	
					pdst.close();
					resultado.close();

					return false;
				}	
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		

	}
	//Retorna true si ya existe un contacto con el mismo telefono que el pasado como parametro
	private static boolean mismoNumero(Contacto cont, Connection con, String tabla) {
			//			<--Parametros-->
			//			con -> conexion a la base de datos
			//			tabla -> tabla donde se encuentra el contacto
			//			cont -> contacto que se verifica	
		
		//Obtengo los valores del contacto y armo la sentencia sql (para SQLite)
		int telefono = cont.getNumero();
		String sql = "SELECT * FROM "+tabla+" WHERE telefono ="+telefono;
				
		//Ejecuto la sentencia sql.
		try {
			PreparedStatement pdst = con.prepareStatement(sql);
			ResultSet resultado = pdst.executeQuery();
					
			//Si se encuentra al menos un registro, retorna true
			if (resultado.next()) {
				pdst.close();
				resultado.close();

				return true;
						}	
			else {
				pdst.close();
				resultado.close();

				return false;
			}
			} catch (SQLException e) {
					e.printStackTrace();
					return false;	
			}
		

	}
		
	//Retorna true si ya existe el contacto pasado como parametro 
	private static boolean mismoContacto(Contacto cont, Connection con, String tabla) {
			//			<--Parametros-->
			//			con -> conexion a la base de datos
			//			tabla -> tabla donde se encuentra el contacto
			//			cont -> contacto que se verifica
		
		//Si el contacto tiene el mismo nombre y el mismo numero retorna true
		if (mismoNombre(cont, con, tabla) == true && mismoNumero(cont, con, tabla)) {
			return true;
		}
		//false si no se encuentra la concidencia en algun de los dos casos
		return false;
		}
	
	//Retorna true si la cadena es un numero. Por ejemplo "1212414" -> retorna true
	private static boolean esNumero(String nombre) {
		try {
			//Si la cadena son solo numeros			
			int n = Integer.parseInt(nombre);
			return true;
		} catch (Exception e) {
			//Si la cadena no contiene numeros o es una convinacion de numeros y letras
			return false;
		}
		
	}
	
	//Metodo que devuelve true si la cadena contiene caracteres especiales
	//Considera caracteres especiales a *,.?¡¿"'/\$%|°¬=+<>{}[]^`~
	private static boolean hasCharEsp(String nombre) {
		
		//Comparo cada letra de la cadena nombre con cada caracter no permitido
		//En caso de hallar concidencia retorna false
		
		String noPermitidos = "*,.?¡¿\"'/\\$%|°¬=+<>{}[]^`~";
		for (int i = 0; i < nombre.length(); i++) {

			for (int j = 0; j < noPermitidos.length(); j++) {
				if (noPermitidos.charAt(j) == nombre.charAt(i) ) {
					return true;
				}
			}
			
		}
		return false;
	}
	
	


	public static ResultSet mostrarContactos(Connection conexion, String tabla) {
//		<--Parametros-->
//		conexion ->conexion a la base de datos donde se encuentra la tabla a mostrar
//		tabla -> nombre de la tabla 
		String sql = "SELECT nombre, telefono FROM "+tabla;
		try {

			PreparedStatement pdst = conexion.prepareStatement(sql);
			ResultSet rs = pdst.executeQuery();
			return rs;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	
	//Envia la sentencia sql para insertar un contacto en la bd y tabla pasados como parametros
	public static void agregarContacto(Connection con, Contacto contacto, String tabla) {
//		<--Parametros-->
//		con -> conexion a la base de datos
//		tabla -> tabla donde se guardara un nuevo contacto
//		contacto -> contacto nuevo el cual se guardara en la bd
		
		//Obtengo los valores del contacto y armop la sentencia sql (para SQLite)
		String nombre = contacto.getNombre();
		int telefono = contacto.getNumero(); 
		String sql = "INSERT INTO "+tabla+" (nombre, telefono) VALUES (? , ?)";
		try {
			//preparo la sentencia y le paso los parametros guardados
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, nombre);
			pstm.setInt(2, telefono);
			
			pstm.executeUpdate();
			pstm.close();
	
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}
	

	//Metodo para corroborar que los datos pasados como parametro sean validos para crear un contacto
	//retorna true -> formato valido
	public static boolean formatoValido(Contacto cont, Connection con, String tabla) {
		
		String nombre = cont.getNombre();
		int telefono = cont.getNumero();
		
		//Si el contacto ingresado ya existe
		if (mismoContacto(cont, con, tabla)) {
			return false;
		}
		//Si ya existe un contacto registrado con el numero ingresado
		if(mismoNumero(cont, con, tabla)) {
			return false;
		}
		//Si el nombre esta conformado solo por numeros, vacio o contiene caracteres especiales
		if (esNumero(nombre)|| nombre.isEmpty() || hasCharEsp(nombre)) {
			return false;
		}
		//Si se cumplen todas los requisitos para un contacto
		return true;
	
	}
	
	

















}
	

	


















