package com.SergiCesfBBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Mantenimiento {	
	Alumno al;
	
	public boolean insertar(String a, String b, int c){	
		boolean bok = comprobacion(b);
		Connection conn;
		
		if(bok == false)  {
			
			try {
				conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO prueva VALUES (?,?,?,?)");
				pstmt.setString(1, null);
				pstmt.setString(2, a);
				pstmt.setString(3, b);
				pstmt.setInt(4,c);
				pstmt.execute();
				pstmt.close();
				
				
				conn.close();			
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		return bok;
	}
			
	public void eliminar(String del){
		Connection conn;
		try {
			conn = getConnection();
//			Statement statement = conn.createStatement();
//			System.out.println(statement.executeUpdate("DELETE FROM prueva  WHERE dni='"+del+"'"));  
//			statement.executeUpdate("DELETE FROM prueva  WHERE dni="+"'del'");
//			statement.close();	
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM prueva  WHERE dni=(?)");
			pstmt.setString(1, del);		
			pstmt.execute();
			pstmt.close();
			
			conn.close();			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void modificar(String mod,String nombre, int edad) {
		Connection conn;
		try {
			conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement("UPDATE prueva SET nombre=?, edad = ? WHERE dni = ?");
			pstmt.setString(1, nombre);
			pstmt.setInt(2, edad);
			pstmt.setString(3,mod );
			pstmt.execute();
			pstmt.close();			
			conn.close();			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Alumno> consultar() {
		Connection conn;
		List<Alumno>alumnos = new ArrayList<>();
		try {
			conn = getConnection();	
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM prueva");
			ResultSet personas = pstmt.executeQuery();
			while (personas.next()){
				Alumno al = new Alumno();
				al.setNombre(personas.getString("nombre"));
				al.setDni(personas.getString("dni"));
				al.setEdad(personas.getInt("edad"));
				
				alumnos.add(al);
				
			}
			pstmt.close();	
			
			
			
			
			conn.close();			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alumnos;
	}
	
	public boolean comprobacion(String dni) {
		boolean bok = false;
		Connection conn;
		try {
			conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM prueva where dni = ?");
			pstmt.setString(1, dni);
			ResultSet personas = pstmt.executeQuery();
			bok = personas.next();
			pstmt.close();			

			conn.close();			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bok;
	}
	
	
	
	
	
	
	
	 public Connection getConnection() throws ClassNotFoundException{
		//Aquí viene el código que falta
		Connection databaseConnection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Define the data source for the driver
			String sourceURL = "jdbc:mysql://localhost/personas";
			// Create a connection through the DriverManager
			databaseConnection = DriverManager.getConnection(sourceURL, "root", "");
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return databaseConnection;
		} 
			
		
		
		
		
		
		
		
				
		
		
	
	
	
	
	
	
	
}
