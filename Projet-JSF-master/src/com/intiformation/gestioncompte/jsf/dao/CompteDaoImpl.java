package com.intiformation.gestioncompte.jsf.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.intiformation.gestioncompte.jsf.model.Compte;
import com.intiformation.gestioncompte.jsf.model.CompteCourant;
import com.intiformation.gestioncompte.jsf.model.CompteEpargne;

@SuppressWarnings("static-access")
public class CompteDaoImpl implements ICompteDAO{

	
	public List<Compte> getAll(int pIdCons) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			//1. requête préparée
			ps = this.connection.prepareStatement("SELECT comptes.* FROM comptes, clients WHERE id_client = client_id AND conseiller_id = ? ");
			ps.setInt(1, pIdCons);
			
			//2. exécution + récup du résultat
			rs = ps.executeQuery();
			
			List<Compte> listeComptes = new ArrayList<>();
			Compte compte = null;
			
			while(rs.next()) {
								int id_compte = rs.getInt(1);
								String type_compte = rs.getString(2);
								int num_compte = rs.getInt(3);
								double solde = rs.getDouble(4);
								double taux = rs.getDouble(5);
								double decouvert = rs.getDouble(6);
								int client_id = rs.getInt(7);
				
				//ajout des données dans l'objet compte
				if(taux == 0)
					compte = new CompteCourant(id_compte, type_compte, num_compte, solde, decouvert, client_id);
				else
					compte = new CompteEpargne(id_compte, type_compte, num_compte, solde, taux, client_id);
				
				//4.4 ajout de l'objet dans la liste des comptes
				listeComptes.add(compte);
			
			}//end while
			return listeComptes;
			
		} catch (SQLException e){
			
			System.out.println("... Erreur lors de l'exécution de la requête getAllComptes de la dao ...");
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

	
	public boolean ajouter(Compte compte) {
		
		PreparedStatement ps = null;
		
		try {
			
			//1. requête préparée
			ps = this.connection.prepareStatement("INSERT INTO comptes(type_compte, num_compte, solde, taux, decouvert, client_id) values(?,?,?,?,?,?)");
			
			ps.setString(1, compte.getType_compte());
			ps.setInt(2, compte.getNum_compte());
			ps.setDouble(3, compte.getSolde());
			ps.setDouble(4, compte.getTaux());
			ps.setDouble(5, compte.getDecouvert());
			ps.setDouble(6, compte.getClient_id());
			
			//2. exécution + récup du résultat
			ps.executeUpdate();
			
			return true;
			
		} catch (SQLException e){
			
			System.out.println("... Erreur lors de l'exécution de la requête ajouterCompte() de la dao ...");
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

	public boolean modifier(Compte compte) {

		PreparedStatement ps = null;
		
		try {
			
			//1. requête préparée
			ps = this.connection.prepareStatement("UPDATE comptes SET type_compte=?, num_compte=?, solde=?, taux=?, decouvert=?, client_id=? where id_compte = ?");
			
			ps.setString(1, compte.getType_compte());
			ps.setInt(2, compte.getNum_compte());
			ps.setDouble(3, compte.getSolde());
			ps.setDouble(4, compte.getTaux());
			ps.setDouble(5, compte.getDecouvert());
			ps.setInt(6, compte.getClient_id());
			ps.setInt(7, compte.getId_compte());
			
			//2. exécution + récup du résultat
			ps.executeUpdate();
			
			return true;
			
		} catch (SQLException e){
			
			System.out.println("... Erreur lors de l'exécution de la requête modifierCompte() de la dao ...");
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

	public boolean supprimer(int id) {

		PreparedStatement ps = null;
		
		try {
			
			//1. requête préparée
			ps = this.connection.prepareStatement("DELETE FROM comptes WHERE id_compte=?");
			
			ps.setInt(1, id);
			
			//2. exécution + récup du résultat
			ps.executeUpdate();
			
			return true;
			
		} catch (SQLException e){
			
			System.out.println("... Erreur lors de l'exécution de la requête supprimerCompte() de la dao ...");
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

	public Compte getById(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			//1. requête préparée
			ps = this.connection.prepareStatement("SELECT * FROM comptes WHERE id_compte = " + id);
			
			
			//2. exécution + récup du résultat
			rs = ps.executeQuery();
			
			Compte compte = null;
			
				while(rs.next()) {
									int id_compte = rs.getInt(1);
									String type_compte = rs.getString(2);
									int num_compte = rs.getInt(3);
									double solde = rs.getDouble(4);
									double taux = rs.getDouble(5);
									double decouvert = rs.getDouble(6);
									int client_id = rs.getInt(7);
				
				//4.3 ajout des données dans l'objet compte
				if(taux == 0)
					compte = new CompteCourant(id_compte, type_compte, num_compte, solde, decouvert, client_id);
				else
					compte = new CompteEpargne(id_compte, type_compte, num_compte, solde, taux, client_id);		
			}
				
			return compte;
			
		} catch (SQLException e){
			
			System.out.println("... Erreur lors de l'exécution de la requête getCompteById() de la dao ...");
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

	
	public boolean setCompteToClient(int pIdClient, int pIdCompte) {
		PreparedStatement ps = null;
		
		try {
			
			//1. requête préparée
			ps = this.connection.prepareStatement("UPDATE comptes set client_id = ? where id_compte = ?");
			
			//2 passage des paramètres à la requête ps avec les méthodes setXXX()
			ps.setInt(1, pIdClient);
			ps.setInt(2, pIdCompte);
			
			ps.executeUpdate();
			
			return true;
			
		} catch (SQLException e){
			
			System.out.println("... Erreur lors de l'exécution de la requête setCompteToClient() de la dao ...");
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


	public List<Compte> findCompteByIdOwner(int pIdClient) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			//1. requête préparée
			ps = this.connection.prepareStatement("SELECT * FROM comptes WHERE client_id=" + pIdClient);
			
			//2. exécution + récup du résultat
			rs = ps.executeQuery();
			
			
			//conteneurs des donénes à récupérer
			List<Compte> listeComptes = new ArrayList<>();
			Compte compte = null;
			
			while(rs.next()) {
								int id_compte = rs.getInt(1);
								String type_compte = rs.getString(2);
								int num_compte = rs.getInt(3);
								double solde = rs.getDouble(4);
								double taux = rs.getDouble(5);
								double decouvert = rs.getDouble(6);
								int client_id = rs.getInt(7);
				
				//ajout des données dans l'objet compte
				if(taux == 0)
					compte = new CompteCourant(id_compte, type_compte, num_compte, solde, decouvert, client_id);
				else
					compte = new CompteEpargne(id_compte, type_compte, num_compte, solde, taux, client_id);
				
				//4.4 ajout de l'objet dans la liste des comptes
				listeComptes.add(compte);
			
			}//end while
			return listeComptes;
			
			
			
			
		} catch (SQLException e){
			
			System.out.println("... Erreur lors de l'exécution de la requête findCompteByIdOwner() de la dao ...");
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

	
	public boolean withdrow(double pMontant, int pIdCompte) {
		
		//demande de récupérer le compte
		Compte compteById = getById(pIdCompte);
		double solde = compteById.getSolde();
		
		if(compteById.getDecouvert() != 0) {	//dans le cas d'un compte courant
			if( (solde - pMontant) >= -compteById.getDecouvert() ) {
				//déduction montant au solde du compte
				compteById.setSolde(solde - pMontant);
				modifier(compteById);
				return true;
			}
			return false;
		}else {		//dans le cas d'un compte epargne
			if(solde >= pMontant) {
				//déduction montant au solde du compte
				compteById.setSolde(solde - pMontant);
				modifier(compteById);
				return true;
			}
			return false;
		}
	}

	public boolean deposit(double pMontant, int pIdCompte) {
		//demande de récupérer le compte concerné par le dépôt d'argent
		Compte compte = getById(pIdCompte);
		//demande d'ajout du montant au solde du compte
		double nouveauSolde = compte.getSolde() + pMontant;
		System.out.println("|===============================================================|\n| \t=> INFOS : Le Dépôt a été effectué avec succès\t\t|\n|===============================================================|\n\tmise à jour du nouveau solde ...\n");
		
		PreparedStatement ps = null;
		
		try {			
			
			//1. requête préparée
			ps = this.connection.prepareStatement("UPDATE comptes SET solde=? WHERE id_compte=?");
			
				//2.1 passage de paramètres à la requête ps avec les méthodes setXXX()
				ps.setDouble(1, nouveauSolde);
				ps.setInt(2, pIdCompte);
			

			ps.executeUpdate();
			
			return true;
			
		} catch (SQLException e){
			
			System.out.println("... Erreur lors de l'exécution de la requête deposit() de la dao ...");
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

	
	public boolean transfert(double pMontant, int pIdCompte1, int pIdCompte2) {
		
		if(withdrow(pMontant, pIdCompte1) && deposit(pMontant, pIdCompte2))
			return true;
		else
			return false;
		
	}
	
	
	
	

}

