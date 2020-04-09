package com.intiformation.gestioncompte.jsf.model;



public class Client {
	//props
	private int id_client;
	private String nom;
	private String prenom;
	private String adresse;
	private String code_postal;
	private String ville;
	private String telephone;
	private int conseiller_id;
	
	
	
	//ctors
	public Client(int id_client, String nom, String prenom, String adresse, String code_postal, String ville, String telephone, int conseiller_id) {
		this.id_client = id_client;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.code_postal = code_postal;
		this.ville = ville;
		this.telephone = telephone;
		this.conseiller_id = conseiller_id;
	}


	public Client(int id_client, String nom, String prenom, String adresse, String code_postal, String ville, String telephone) {
		this.id_client = id_client;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.code_postal = code_postal;
		this.ville = ville;
		this.telephone = telephone;
	}


	public Client(String nom, String prenom, String adresse, String code_postal, String ville, String telephone, int conseiller_id) {
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.code_postal = code_postal;
		this.ville = ville;
		this.telephone = telephone;
		this.conseiller_id = conseiller_id;
	}
	public Client(String nom, String prenom, String adresse, String code_postal, String ville, String telephone) {
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.code_postal = code_postal;
		this.ville = ville;
		this.telephone = telephone;
	}
	public Client() {
		
	}
	







	//getters|setters
	public int getId_client() {
		return id_client;
	}
	public void setId_client(int id_client) {
		this.id_client = id_client;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getCode_postal() {
		return code_postal;
	}
	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public int getConseiller_id() {
		return conseiller_id;
	}
	public void setConseiller_id(int conseiller_id) {
		this.conseiller_id = conseiller_id;
	}
	
	
}
