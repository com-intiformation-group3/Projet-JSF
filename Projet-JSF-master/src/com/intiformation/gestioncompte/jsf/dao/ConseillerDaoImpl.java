package com.intiformation.gestioncompte.jsf.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("static-access")
public class ConseillerDaoImpl implements IConseillerDAO {

	/**
	 * permet de vérifier si un conseiller existe dans la bdd avec mail et mdp <br>
	 * 
	 * @param pMail
	 * @param pMdp
	 * @return
	 */
	public boolean isConseillerExists(String pMail, String pMdp) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			// 1. requete SQL
			String isExistsReq = "SELECT COUNT(id_conseiller) FROM conseiller WHERE mail=? AND mot_de_passe=?";

			// 2. requête préparée
			ps = this.connection.prepareStatement(isExistsReq);

			// 2.1 passage de aprams à la requête préparée
			ps.setString(1, pMail);
			ps.setString(2, pMdp);

			// 3. exécution + récup du résultat
			rs = ps.executeQuery();

			// 4. lecture du résultat
			rs.next();
			int verifIsExists = rs.getInt(1);

			// 5. renvoi du résultat
			return (verifIsExists == 1) ? true : false;

			
			
		} catch (SQLException e) {
			
			System.out.println("... Erreur d'exécution de la requête isConseillerExists ...");
			e.printStackTrace();
			
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}// end isConseillerExists()

	
	public String idConseiller(String pMail) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			// 1. requete SQL
			ps = this.connection.prepareStatement("SELECT nom FROM conseiller WHERE mail=" + pMail);


			// 2. exécution + récup du résultat
			rs=ps.executeQuery();
			
			// 3. lecture du résultat
			rs.next();
			
			// 4. récup du nom
			String nomConseiller = rs.getString(1);
			return nomConseiller;


		} catch (SQLException e) {
			
			System.out.println("... Erreur d'exécution de la requête idConseiller(pMail) ...");
			e.printStackTrace();
			
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	@Override
	public int GetIdConseiller(String pMail) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			// 1. requete SQL
			ps = this.connection.prepareStatement("SELECT id_conseiller FROM conseiller WHERE mail=?" );
			ps.setString(1, pMail);


			// 2. exécution + récup du résultat
			rs=ps.executeQuery();
			
			// 3. lecture du résultat
			rs.next();
			
			// 4. récup du nom
			int idConseiller = rs.getInt(1);
			return idConseiller;


		} catch (SQLException e) {
			
			System.out.println("... Erreur d'exécution de la requête idConseiller(pMail) ...");
			e.printStackTrace();
			
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

}
