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
	
	
	
	

	//méthodes
	/**
	 * permet de récup la liste des comptes dans la bdd via la dao.
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
		
		//1. récup du param passé dans le composant au click du lien 'supprimer'
		UIParameter cp = (UIParameter) event.getComponent().findComponent("deleteID");
		
		//2. récup de la valeur du param => l'id du compte à supprimer
		int compteID = (int) cp.getValue();
		
		//3. suppression du compte dans la bdd via la dao
		//3.1 récup du context de JSF
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		
		
		if(compteDAO.supprimer(compteID)) {  //suppression ok
		
			//envoi d'un message vers la vue via le context
			contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
														"Suppression réussie", 
														"Le compte a été supprimé avec succès"));
		
		}else {  //suppression not ok
		
			//envoi d'un message vers la vue via le context
			contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,
														"Echec de la Suppression", 
														"La suppression du compte a échouée"));
		}
	}
	
/////////////////////////////////////////////////////////////////// SECTION  MODIFICATION \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

	public void selectionnerCompte(ActionEvent event) {
		
		//1. récup du param passé dans le composant au click du lien 'modifier'
		UIParameter cp = (UIParameter) event.getComponent().findComponent("updateID");
		
		//2. récup de la valeur du param => l'id du compte à modifier
		int compteID = (int) cp.getValue();
		
		//3. récup du compte dans la bdd par l'id
		Compte compte = compteDAO.getById(compteID);
		
		//4. affectation du compte à modifier à la prop 'compte' du managedbean
		setCompte(compte);
	
	/**
	* dans la page modifier-compte.xhtml => on récupère le compte à modifier via 
	* la prop compte du MB (managedbean)
	*/
	}
	
	

	public void modifierCompte(ActionEvent event) {	//invoquée au click du bouton modifier de la page modifier-compte.xhtml
	
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		if(compteDAO.modifier(compte)) {  //modif ok
		
			//envoi d'un message vers la vue via le context
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
													"Modification réussie", 
													"Le compte a été modifié avec succès");
			contextJSF.addMessage(null,  message);
		
		}else {  //modif not ok
		
			//envoi d'un message vers la vue via le context
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL,
													"Echec de la Modification", 
													"La modification du compte a échouée");
			contextJSF.addMessage(null,  message);
		}
	}
	
	
///////////////////////////////////////////////////////////////////////// SECTION  AJOUT \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	public void initialiserCompte(ActionEvent event) {
	
		//instanciation d'un nouveau objet compte vide
		Compte compteAdd = new Compte();
		
		//affectation de l'objet à la prop compte du MB
		setCompte(compteAdd);
	
	}
	
	
	
	public void ajouterCompte(ActionEvent event) {
		
		//récup le context de JSF pour l'envoi de messages vers la vue
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		//ajout dans la bdd
		if (compteDAO.ajouter(compte)) {	//ajout ok 
		
			//envoi d'un message vers la vue via le context
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
													"Ajout réussie", 
													"Le compte a été ajouté avec succès");
			contextJSF.addMessage(null,  message);
		
		} else {	//ajout not ok
		
			//envoi d'un message vers la vue via le context
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL,
													"Echec de l'ajout", 
													"L'ajout du compte a échouée");
			contextJSF.addMessage(null,  message);
		
		}
	}
	
	
///////////////////////////////////////////////////////////////////////// SECTION  RETRAIT \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

	public void retirer(ActionEvent event) {	//invoquée au click du bouton Retirer de la page retrait.xhtml
	
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		if(compteDAO.withdrow(compte.getMontant(), compte.getId_compte())) {  //retrait ok
		
			//envoi d'un message vers la vue via le context
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
													"Retrait réussi", 
													"Le compte a été débité avec succès");
			contextJSF.addMessage(null,  message);
		
		}else {  //retrait not ok
		
			//envoi d'un message vers la vue via le context
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL,
													"Echec du Retrait", 
													"Les fonds sont insuffisants");
			contextJSF.addMessage(null,  message);
		}
	}
	
	
///////////////////////////////////////////////////////////////////////// SECTION  DEPÔT \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

	public void deposer(ActionEvent event) {	//invoquée au click du bouton Déposer de la page depot.xhtml
	
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		if(compteDAO.deposit(compte.getMontant(), compte.getId_compte())) {  //dépôt ok
		
			//envoi d'un message vers la vue via le context
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
													"Dépôt réussi", 
													"Le compte a été crédité avec succès");
			contextJSF.addMessage(null,  message);
		
		}else {  //dépôt not ok
		
			//envoi d'un message vers la vue via le context
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL,
													"Echec du Dépôt", 
													"Le compte n'a pas été crédité");
			contextJSF.addMessage(null,  message);
		}
	}
	
	
///////////////////////////////////////////////////////////////////////// SECTION  VIREMENT \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

	public void virement(ActionEvent event) {	//invoquée au click du bouton Valider de la page virement.xhtml
	
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		if(compteDAO.transfert(compte.getMontant(), compte.getId_compteE(), compte.getId_compteD())) {  //virement ok
		
			//envoi d'un message vers la vue via le context
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
													"Transaction réussie", 
													"Le virement a été effectué avec succès");
			contextJSF.addMessage(null,  message);
		
		}else {  //virement not ok
		
			//envoi d'un message vers la vue via le context
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL,
													"Fonds insuffisants", 
													"Le virement n'a pas été effectué");
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
