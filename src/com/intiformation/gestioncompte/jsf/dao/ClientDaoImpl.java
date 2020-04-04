package com.intiformation.gestioncompte.jsf.dao;


import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intiformation.gestioncompte.jsf.model.Client;



@SuppressWarnings("static-access")
public class ClientDaoImpl implements IClientDAO{

	
	
	public List<Client> getAll() {

		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			//1. requête préparée
			ps = this.connection.prepareStatement("SELECT * FROM clients");
			
			//2. exécution + récup du résultat
			rs = ps.executeQuery();
			
			List<Client> listeClients = new ArrayList<>();
			Client client = null;
			
			while(rs.next()) {
				client = new Client(rs.getInt(1), 
									rs.getString(2), 
									rs.getString(3), 
									rs.getString(4), 
									rs.getString(5), 
									rs.getString(6), 
									rs.getString(7),
									rs.getInt(8));
				
				listeClients.add(client);
			
			}//end while
			return listeClients;
			
		} catch (SQLException e){
			
			System.out.println("... Erreur lors de l'exécution de la requête getAllClients de la dao ...");
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
	}//end getAllClients()

	
	
	public boolean ajouter(Client client) {
		PreparedStatement ps = null;
		
		try {
			
			//1. requête préparée
			ps = this.connection.prepareStatement("INSERT INTO clients(nom, prenom, adresse, code_postal, ville, telephone, conseiller_id) VALUES(?, ?, ?, ?, ?, ?, ?)");
			
			ps.setString(1, client.getNom());
			ps.setString(2, client.getPrenom());
			ps.setString(3, client.getAdresse());
			ps.setString(4, client.getCode_postal());
			ps.setString(5, client.getVille());
			ps.setString(6, client.getTelephone());
			ps.setInt(7, client.getConseiller_id());
			
			//2. exécution + récup du résultat
			ps.executeUpdate();
			
			return true;
			
		} catch (SQLException e){
			
			System.out.println("... Erreur lors de l'exécution de la requête ajouterClient() de la dao ...");
			e.printStackTrace();
			
		}finally {
			try {
				if(ps != null) ps.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	

	public boolean modifier(Client client) {

		PreparedStatement ps = null;
		
		try {
			
			//1. requête préparée
			ps = this.connection.prepareStatement("UPDATE clients SET nom=?, prenom=?, adresse=?, code_postal=?, ville=?, telephone=?, conseiller_id=? WHERE id_client=?");
			
			ps.setString(1, client.getNom());
			ps.setString(2, client.getPrenom());
			ps.setString(3, client.getAdresse());
			ps.setString(4, client.getCode_postal());
			ps.setString(5, client.getVille());
			ps.setString(6, client.getTelephone());
			ps.setInt(7, client.getConseiller_id());
			ps.setInt(8, client.getId_client());
			
			//2. exécution + récup du résultat
			ps.executeUpdate();
			
			return true;
			
		} catch (SQLException e){
			
			System.out.println("... Erreur lors de l'exécution de la requête modifierClient() de la dao ...");
			e.printStackTrace();
			
		}finally {
			try {
				if(ps != null) ps.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	
	
	public boolean supprimer(int pIdClient) {

		PreparedStatement ps = null;
		
		try {
			
			//1. requête préparée
			ps = this.connection.prepareStatement("DELETE FROM clients WHERE id_client=?");
			
			ps.setInt(1, pIdClient);
			
			//2. exécution + récup du résultat
			ps.executeUpdate();
			
			return true;
			
		} catch (SQLException e){
			
			System.out.println("... Erreur lors de l'exécution de la requête supprimerClient() de la dao ...");
			e.printStackTrace();
			
		}finally {
			try {
				if(ps!= null) ps.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	
	
	public Client getById(int pIdClient) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			//1. requête préparée
			ps = this.connection.prepareStatement("SELECT * FROM clients WHERE id_client=?");
			
			ps.setInt(1, pIdClient);
			
			//2. exécution + récup du résultat
			rs = ps.executeQuery();
			
			Client client = null;
			
			rs.next();
			client = new Client(rs.getInt(1), 
								rs.getString(2), 
								rs.getString(3), 
								rs.getString(4), 
								rs.getString(5), 
								rs.getString(6), 
								rs.getString(7));
				
			return client;
			
		} catch (SQLException e){
			
			System.out.println("... Erreur lors de l'exécution de la requête getClientById de la dao ...");
			e.printStackTrace();
			
		}finally {
			try {
				if(rs!= null) rs.close();
				if(ps!= null) ps.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return null;
		
	}



	@Override
	public List<Client> findClientByIdConseiller(int pIdConseiller) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			//1. requête préparée
			ps = this.connection.prepareStatement("SELECT * FROM clients WHERE conseiller_id=" + pIdConseiller);
			
			//2. exécution + récup du résultat
			rs = ps.executeQuery();
			
			
			//conteneurs des donénes à récupérer
			List<Client> listeClients = new ArrayList<>();
			Client client = null;
			
			while(rs.next()) {
				client = new Client(rs.getInt(1), 
									rs.getString(2), 
									rs.getString(3), 
									rs.getString(4), 
									rs.getString(5), 
									rs.getString(6), 
									rs.getString(7),
									rs.getInt(8));
				
				listeClients.add(client);
			
			}//end while
			return listeClients;
			
			
		} catch (SQLException e){
			
			System.out.println("... Erreur lors de l'exécution de la requête findClientByIdConseiller() de la dao ...");
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
	
	
	
}
