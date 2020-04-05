package com.intiformation.gestioncompte.jsf.model;


public class CompteEpargne extends Compte{

	//ctors
	public CompteEpargne(int id_compte, String type_compte, int num_compte, double solde, double taux, int client_id) {
		super();
		this.id_compte = id_compte;
		this.type_compte = "epargne";
		this.num_compte = num_compte;
		this.solde = solde;
		this.taux = taux;
		this.client_id = client_id;
	}

	public CompteEpargne(int id_compte, String type_compte, int num_compte, double solde, double taux) {
		super();
		this.id_compte = id_compte;
		this.type_compte = "epargne";
		this.num_compte = num_compte;
		this.solde = solde;
		this.taux = taux;
	}
	
	public CompteEpargne(String type_compte, int num_compte, double solde, double taux) {
		super();
		this.type_compte = "epargne";
		this.num_compte = num_compte;
		this.solde = solde;
		this.taux = taux;
	}
	
	public CompteEpargne() {
		
	}
}
