package com.intiformation.gestioncompte.jsf.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.intiformation.gestioncompte.jsf.dao.ClientDaoImpl;
import com.intiformation.gestioncompte.jsf.dao.IClientDAO;
import com.intiformation.gestioncompte.jsf.model.Client;



/**
 * managedbean pour la gestion des clients
 *
 */
@SuppressWarnings("serial")
@ManagedBean(name = "gestionClient")
@SessionScoped
public class GestionClientBean implements Serializable{

	//props
	private Client client;
	
	List<Client> liste_clients;
	
	IClientDAO clientDAO;
	
	
	
	//ctors
	public GestionClientBean() { //ctor vide pour l'instanciation du managedbean
		
		clientDAO = new ClientDaoImpl();  //on en profite pour l'instancier � l'appel
	}
	
	
	
	
	//m�thodes
	/**
	 * permet de r�cup la liste des clients dans la bdd via la dao.
	 * permet aussi d'alimenter la table dans accueil.xhtml pour affichage.
	 */
	public List<Client> findAllClientsBdd(){
		
		liste_clients = clientDAO.getAll();
		
		return liste_clients;
	}

/////////////////////////////////////////////////////////////////// SECTION  SUPPRESSION \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	/**
	 * invoqu�e au click sur le lien 'supprimer' de accueil.xhtml <br/>
	 * au click, l'�v�nement javax.faces.event.ActionEvent se d�clenche <br/>
	 * l'�v�nement encapsule toutes les infos concernant le composant <br/>
	 * permet de supprimer un client dans la bdd via la dao <br/>
	 */
	public void supprimerClient(ActionEvent event) {
		//1. r�cup du param pass� dans le composant au click du lien 'supprimer'
		UIParameter cp = (UIParameter) event.getComponent().findComponent("deleteID");
		
		//2. r�cup de la valeur du param => l'id du client � supprimer
		int clientID = (int) cp.getValue();
		
		//3. suppression du client dans la bdd via la dao
		//3.1r�cup du context de JSF
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		
		
		if(clientDAO.supprimer(clientID)) {  //suppression ok
			
			//envoi d'un message vers la vue via le context
			contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					 									"Suppression r�ussie", 
					 									"Le client a �t� supprim� avec succ�s"));
			
		}else {  //suppression not ok
			
			//envoi d'un message vers la vue via le context
			contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,
														"Echec de la Suppression", 
													 	"La suppression du client a �chou�e"));
			
		}
	}

/////////////////////////////////////////////////////////////////// SECTION  MODIFICATION \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	/**
	 * invoqu�e au click sur le lien 'modifier' de accueil.xhtml <br/>
	 * au click, l'�v�nement javax.faces.event.ActionEvent se d�clenche <br/>
	 * l'�v�nement encapsule toutes les infos concernant le composant <br/>
	 * permet de r�cup�rer les infos du client � modifier � partir de la bdd, 
	 * et lier ces infos � la page modifier-client.xhtml <br/>
	 */
	public void selectionnerClient(ActionEvent event) {
		//1. r�cup du param pass� dans le composant au click du lien 'modifier'
		UIParameter cp = (UIParameter) event.getComponent().findComponent("updateID");
		
		//2. r�cup de la valeur du param => l'id du client � modifier
		int clientID = (int) cp.getValue();
		
		//3. r�cup du client dans la bdd par l'id
		Client client = clientDAO.getById(clientID);
		
		//4. affectation du client � modifier � la prop 'client' du managedbean
		setClient(client);
		
		/**
		 * dans la page modifier-client.xhtml => on r�cup�re le client � modifier via 
		 * la prop client du MB (managedbean)
		 */
	}
	

	/**
	 * invoqu�e au click du bouton modifier de la page modifier-client.xhtml <br/>
	 * permet de modifier le client dans la bdd
	 */
	public void modifierClient(ActionEvent event) {
		
		/**
		 * la prop client du MB encapsule les infos du client � modifier dans la bdd
		 */
		
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		if(clientDAO.modifier(client)) {  //modif ok
			
			//envoi d'un message vers la vue via le context
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
														 "Modification r�ussie", 
														 "Le client a �t� modifi� avec succ�s");
			contextJSF.addMessage(null,  message);
		
		}else {  //modif not ok

			//envoi d'un message vers la vue via le context
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL,
														 "Echec de la Modification", 
														 "La modification du client a �chou�e");
			contextJSF.addMessage(null,  message);
			
		}
		
	}
	
	
///////////////////////////////////////////////////////////////////////// SECTION  AJOUT \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	
	/**
	 * permet d'instancier un objet de type client � vide pour pouvoir r�cup les valeurs 
	 * saisies dans le formulaire d'ajout de la page ajouter-client.xhtml <br/>
	 * cela permet d'�viter un nullPointerException <br/>
	 */
	public void initialiserClient(ActionEvent event) {
		
		//instanciation d'un nouveau objet client vide
		Client clientAdd = new Client();
		
		//affectation de l'objet � la prop client du MB
		setClient(clientAdd);
	
	}
	
	
	/**
	 * invoqu�e au click du bouton Ajouter de la page ajouter_livre.xhtml <br/>
	 * permet d'ajouter un nouveau livre dans la bdd <br/>
	 * @param event
	 */
	public void ajouterClient(ActionEvent event) {
		//r�cup le context de JSF pour l'envoi de messages vers la vue
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		//ajout dans la bdd
		if (clientDAO.ajouter(client)) {	//ajout ok 
			
			//envoi d'un message vers la vue via le context
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
														 "Ajout r�ussie", 
														 "Le client a �t� ajout� avec succ�s");
			contextJSF.addMessage(null,  message);
			
		} else {	//ajout not ok

			//envoi d'un message vers la vue via le context
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					 								"Echec de l'ajout", 
					 								"L'ajout du client a �chou�e");
			contextJSF.addMessage(null,  message);
			
		}
	}
	
	
	
	
	
	
	
	//getters|setters
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}
