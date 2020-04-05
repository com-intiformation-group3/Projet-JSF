package com.intiformation.gestioncompte.jsf.model;



public class Compte{

	//props
	protected int id_compte;
	protected String type_compte;
	protected int num_compte;
	protected double solde;
	protected double taux;
	protected double decouvert;
	protected int client_id;
	
	//props pour les retraits/dépôts/virements
	protected double montant; //montant de la transaction
	protected int id_compteE; //compte emetteur
	protected int id_compteD; //compte déstinataire
	
	
	
	
	
	//ctor
	public Compte() {
	}

	
	
	

	//getters|setters
	public int getId_compte() {
		return id_compte;
	}


	public void setId_compte(int id_compte) {
		this.id_compte = id_compte;
	}


	public String getType_compte() {
		return type_compte;
	}


	public void setType_compte(String type_compte) {
		this.type_compte = type_compte;
	}


	public int getNum_compte() {
		return num_compte;
	}


	public void setNum_compte(int num_compte) {
		this.num_compte = num_compte;
	}


	public double getSolde() {
		return solde;
	}


	public void setSolde(double solde) {
		this.solde = solde;
	}


	public double getTaux() {
		return taux;
	}


	public void setTaux(double taux) {
		this.taux = taux;
	}


	public double getDecouvert() {
		return decouvert;
	}


	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
	}


	public int getClient_id() {
		return client_id;
	}


	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}
	
	
	
	
	
	
	

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public int getId_compteE() {
		return id_compteE;
	}

	public void setId_compteE(int id_compteE) {
		this.id_compteE = id_compteE;
	}

	public int getId_compteD() {
		return id_compteD;
	}
	
	public void setId_compteD(int id_compteD) {
		this.id_compteD = id_compteD;
	}
	
	
}
