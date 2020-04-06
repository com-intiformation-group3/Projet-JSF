package com.intiformation.gestioncompte.jsf.dao;


import java.sql.Connection;
import java.util.List;

import com.intiformation.gestioncompte.jsf.tool.DBConnection;



public interface IGenerale<T> {
	
	
	public Connection connection = DBConnection.getInstance();
	
	
	//------méthodes de la dao--------
	
	//public List<T> getAll();

	public boolean ajouter(T t);
		
	public boolean modifier(T t);
		
	public boolean supprimer(int id);
		
	public T getById(int id);

	
	
}
