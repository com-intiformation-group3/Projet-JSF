package com.intiformation.gestioncompte.jsf.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("static-access")
public class ConseillerDaoImpl implements IConseillerDAO {

	/**
	 * permet de v�rifier si un conseiller existe dans la bdd avec mail et mdp <br>
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

			// 2. requ�te pr�par�e
			ps = this.connection.prepareStatement(isExistsReq);

			// 2.1 passage de aprams � la requ�te pr�par�e
			ps.setString(1, pMail);
			ps.setString(2, pMdp);

			// 3. ex�cution + r�cup du r�sultat
			rs = ps.executeQuery();

			// 4. lecture du r�sultat
			rs.next();
			int verifIsExists = rs.getInt(1);

			// 5. renvoi du r�sultat
			return (verifIsExists == 1) ? true : false;

			
			
		} catch (SQLException e) {
			
			System.out.println("... Erreur d'ex�cution de la requ�te isConseillerExists ...");
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


			// 2. ex�cution + r�cup du r�sultat
			rs=ps.executeQuery();
			
			// 3. lecture du r�sultat
			rs.next();
			
			// 4. r�cup du nom
			String nomConseiller = rs.getString(1);
			return nomConseiller;


		} catch (SQLException e) {
			
			System.out.println("... Erreur d'ex�cution de la requ�te idConseiller(pMail) ...");
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


			// 2. ex�cution + r�cup du r�sultat
			rs=ps.executeQuery();
			
			// 3. lecture du r�sultat
			rs.next();
			
			// 4. r�cup du nom
			int idConseiller = rs.getInt(1);
			return idConseiller;


		} catch (SQLException e) {
			
			System.out.println("... Erreur d'ex�cution de la requ�te idConseiller(pMail) ...");
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
