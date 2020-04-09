package com.intiformation.gestioncompte.jsf.dao;


import java.sql.Connection;

import com.intiformation.gestioncompte.jsf.tool.DBConnection;


/**
 * interface de la dao pour le conseiller <br>
 *
 */
public interface IConseillerDAO {

	//récup de la connexion
	public Connection connection = DBConnection.getInstance();
	
	
	
	//....méthodes de la dao
	
	/**
	 * permet de vérifier si un conseiller existe dans la bdd avec mail et mdp <br>
	 * @param pMail
	 * @param pMdp
	 * @return
	 */
	public boolean isConseillerExists(String pMail, String pMdp);
	
	public String idConseiller(String pMail);
	
	public int GetIdConseiller(String pMail);
	
}
