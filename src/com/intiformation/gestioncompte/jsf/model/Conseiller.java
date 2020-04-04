package com.intiformation.gestioncompte.jsf.model;


public class Conseiller{

		//props
		private int idConseiller;
		private String nom;
		private String mail;
		private String motDePasse;
		
		
		
		//ctors
		public Conseiller(int idConseiller, String nom, String mail, String motDePasse) {
			this.idConseiller = idConseiller;
			this.nom = nom;
			this.mail = mail;
			this.motDePasse = motDePasse;
		}

		public Conseiller(String nom, String mail, String motDePasse) {
			this.nom = nom;
			this.mail = mail;
			this.motDePasse = motDePasse;
		}
		
		public Conseiller(String nom) {
			this.nom = nom;
		}
		
		public Conseiller() {
		}
		
		
		
		
		
		//getters|setters
		public int getIdConseiller() {
			return idConseiller;
		}
		public void setIdConseiller(int idConseiller) {
			this.idConseiller = idConseiller;
		}


		public String getNom() {
			return nom;
		}
		public void setNom(String nom) {
			this.nom = nom;
		}


		public String getMail() {
			return mail;
		}
		public void setMail(String mail) {
			this.mail = mail;
		}

		
		public String getMotDePasse() {
			return motDePasse;
		}
		public void setMotDePasse(String motDePasse) {
			this.motDePasse = motDePasse;
		}
	
}
