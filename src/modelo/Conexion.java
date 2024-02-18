package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	
	//Metodo para conectarse a una base de datos SQLite
		public static Connection conectar(String bd, String url) {
//			<--Parametros-->
//			bd -> Nombre de la base de datos 
//			url -> ubicacion de la base de datos
			
			String direccion ="jdbc:sqlite:/"+url+"/"+bd+".db";
			
			Connection con = null;
			try {
				con = DriverManager.getConnection(direccion);
//				System.out.println("Conexion exitosa");
				return con;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
			
		}

}
