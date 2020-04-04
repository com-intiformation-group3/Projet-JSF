package com.intiformation.gestioncompte.jsf.tool;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	//infos de la connexion à la bdd
	private static final String DB_URL = "jdbc:mysql://localhost:3306/db_jdbc_jsf";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "root";
	private static final String MYSQL_JDBC_DRIVER = "com.mysql.jdbc.Driver";
	
	//l'objet connexion à retourner
	private static Connection connection;
	
	
	private DBConnection() {
	}
	
	
	public static Connection getInstance() {
		
		if(connection == null) {
			try {
				
				
				Class.forName(MYSQL_JDBC_DRIVER);
				
				connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				
			} catch (SQLException | ClassNotFoundException e) {
				System.out.println("... erreur lors de la récupération de la connection vers la bdd ...");
				e.printStackTrace();
			}
		}
		return connection;
	}

	
	
	
}