package com.intiformation.gestioncompte.jsf.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.intiformation.gestioncompte.jsf.dao.CompteDaoImpl;
import com.intiformation.gestioncompte.jsf.dao.ICompteDAO;
import com.intiformation.gestioncompte.jsf.model.Compte;




@SuppressWarnings("serial")
@ManagedBean(name = "gestionCompte")
@SessionScoped
public class GestionCompteBean implements Serializable{

	//props
	private Compte compte;
	private int filtreClientId = 0;
	
	List<Compte> liste_comptes;
	
	ICompteDAO compteDAO;
	
	
	
	//ctors
	public GestionCompteBean() { //ctor vide pour l'instanciation du managedbean
		compteDAO = new CompteDaoImpl();
	}
	
	
	
	

	//m�thodes
	/**
	 * permet de r�cup la liste des comptes dans la bdd via la dao.
	 * permet aussi d'alimenter la table dans accueil.xhtml pour affichage.
	 */
	public List<Compte> findAllComptesBdd(){
		
		if(filtreClientId > 0)
			liste_comptes = compteDAO.findCompteByIdOwner(filtreClientId);
		else
			liste_comptes = compteDAO.getAll();
		
		return liste_comptes;
	}
	


/////////////////////////////////////////////////////////////////// SECTION  SUPPRESSION \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

	public void supprimerCompte(ActionEvent event) {
		
		//1. r�cup du param pass� dans le composant au click du lien 'supprimer'
		UIParameter cp = (UIParameter) event.getComponent().findComponent("deleteID");
		
		//2. r�cup de la valeur du param => l'id du compte � supprimer
		int compteID = (int) cp.getValue();
		
		//3. suppression du compte dans la bdd via la dao
		//3.1 r�cup du context de JSF
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		
		
		if(compteDAO.supprimer(compteID)) {  //suppression ok
		
			//envoi d'un message vers la vue via le context
			contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
														"Suppression r�ussie", 
														"Le compte a �t� supprim� avec succ�s"));
		
		}else {  //suppression not ok
		
			//envoi d'un message vers la vue via le context
			contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,
														"Echec de la Suppression", 
														"La suppression du compte a �chou�e"));
		}
	}
	
/////////////////////////////////////////////////////////////////// SECTION  MODIFICATION \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

	public void selectionnerCompte(ActionEvent event) {
		
		//1. r�cup du param pass� dans le composant au click du lien 'modifier'
		UIParameter cp = (UIParameter) event.getComponent().findComponent("updateID");
		
		//2. r�cup de la valeur du param => l'id du compte � modifier
		int compteID = (int) cp.getValue();
		
		//3. r�cup du compte dans la bdd par l'id
		Compte compte = compteDAO.getById(compteID);
		
		//4. affectation du compte � modifier � la prop 'compte' du managedbean
		setCompte(compte);
	
	/**
	* dans la page modifier-compte.xhtml => on r�cup�re le compte � modifier via 
	* la prop compte du MB (managedbean)
	*/
	}
	
	

	public void modifierCompte(ActionEvent event) {	//invoqu�e au click du bouton modifier de la page modifier-compte.xhtml
	
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		if(compteDAO.modifier(compte)) {  //modif ok
		
			//envoi d'un message vers la vue via le context
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
													"Modification r�ussie", 
													"Le compte a �t� modifi� avec succ�s");
			contextJSF.addMessage(null,  message);
		
		}else {  //modif not ok
		
			//envoi d'un message vers la vue via le context
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL,
													"Echec de la Modification", 
													"La modification du compte a �chou�e");
			contextJSF.addMessage(null,  message);
		}
	}
	
	
///////////////////////////////////////////////////////////////////////// SECTION  AJOUT \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	public void initialiserCompte(ActionEvent event) {
	
		//instanciation d'un nouveau objet compte vide
		Compte compteAdd = new Compte();
		
		//affectation de l'objet � la prop compte du MB
		setCompte(compteAdd);
	
	}
	
	
	
	public void ajouterCompte(ActionEvent event) {
		
		//r�cup le context de JSF pour l'envoi de messages vers la vue
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		//ajout dans la bdd
		if (compteDAO.ajouter(compte)) {	//ajout ok 
		
			//envoi d'un message vers la vue via le context
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
													"Ajout r�ussie", 
													"Le compte a �t� ajout� avec succ�s");
			contextJSF.addMessage(null,  message);
		
		} else {	//ajout not ok
		
			//envoi d'un message vers la vue via le context
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL,
													"Echec de l'ajout", 
													"L'ajout du compte a �chou�e");
			contextJSF.addMessage(null,  message);
		
		}
	}
	
	
///////////////////////////////////////////////////////////////////////// SECTION  RETRAIT \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

	public void retirer(ActionEvent event) {	//invoqu�e au click du bouton Retirer de la page retrait.xhtml
	
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		if(compteDAO.withdrow(compte.getMontant(), compte.getId_compte())) {  //retrait ok
		
			//envoi d'un message vers la vue via le context
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
													"Retrait r�ussi", 
													"Le compte a �t� d�bit� avec succ�s");
			contextJSF.addMessage(null,  message);
		
		}else {  //retrait not ok
		
			//envoi d'un message vers la vue via le context
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL,
													"Echec du Retrait", 
													"Les fonds sont insuffisants");
			contextJSF.addMessage(null,  message);
		}
	}
	
	
///////////////////////////////////////////////////////////////////////// SECTION  DEP�T \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

	public void deposer(ActionEvent event) {	//invoqu�e au click du bouton D�poser de la page depot.xhtml
	
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		if(compteDAO.deposit(compte.getMontant(), compte.getId_compte())) {  //d�p�t ok
		
			//envoi d'un message vers la vue via le context
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
													"D�p�t r�ussi", 
													"Le compte a �t� cr�dit� avec succ�s");
			contextJSF.addMessage(null,  message);
		
		}else {  //d�p�t not ok
		
			//envoi d'un message vers la vue via le context
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL,
													"Echec du D�p�t", 
													"Le compte n'a pas �t� cr�dit�");
			contextJSF.addMessage(null,  message);
		}
	}
	
	
///////////////////////////////////////////////////////////////////////// SECTION  VIREMENT \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

	public void virement(ActionEvent event) {	//invoqu�e au click du bouton Valider de la page virement.xhtml
	
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		if(compteDAO.transfert(compte.getMontant(), compte.getId_compteE(), compte.getId_compteD())) {  //virement ok
		
			//envoi d'un message vers la vue via le context
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
													"Transaction r�ussie", 
													"Le virement a �t� effectu� avec succ�s");
			contextJSF.addMessage(null,  message);
		
		}else {  //virement not ok
		
			//envoi d'un message vers la vue via le context
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL,
													"Fonds insuffisants", 
													"Le virement n'a pas �t� effectu�");
			contextJSF.addMessage(null,  message);
		}
	}
	
///////////////////////////////////////////////////////////////////////// SECTION  Filtre Client ID \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\


	
	
	
	
	
	//getters|setters
	public Compte getCompte() {
	return compte;
	}
	
	public void setCompte(Compte compte) {
	this.compte = compte;
	}

	
	public int getFiltreClientId() {
		return filtreClientId;
	}

	public void setFiltreClientId(int filtreClientId) {
		this.filtreClientId = filtreClientId;
	}	
		
	
}
