package com.intiformation.gestioncompte.jsf.model;


public class CompteCourant extends Compte{
	
	//ctors
	public CompteCourant(int id_compte, String type_compte, int num_compte, double solde, double decouvert, int client_id) {
		super();
		this.id_compte = id_compte;
		this.type_compte = "courant";
		this.num_compte = num_compte;
		this.solde = solde;
		this.decouvert = decouvert;
		this.client_id = client_id;
	}
	
	public CompteCourant(int id_compte, String type_compte, int num_compte, double solde, double decouvert) {
		super();
		this.id_compte = id_compte;
		this.type_compte = "courant";
		this.num_compte = num_compte;
		this.solde = solde;
		this.decouvert = decouvert;
	}
	

	public CompteCourant(String type_compte, int num_compte, double solde, double decouvert) {
		super();
		this.type_compte = "courant";
		this.num_compte = num_compte;
		this.solde = solde;
		this.decouvert = decouvert;
	}
	


}
