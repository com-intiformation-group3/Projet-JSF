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
		
		clientDAO = new ClientDaoImpl();  //on en profite pour l'instancier à l'appel
	}
	
	
	
	
	//méthodes
	/**
	 * permet de récup la liste des clients dans la bdd via la dao.
	 * permet aussi d'alimenter la table dans accueil.xhtml pour affichage.
	 */
	public List<Client> findAllClientsBdd(){
		
		liste_clients = clientDAO.getAll();
		
		return liste_clients;
	}

/////////////////////////////////////////////////////////////////// SECTION  SUPPRESSION \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	/**
	 * invoquée au click sur le lien 'supprimer' de accueil.xhtml <br/>
	 * au click, l'événement javax.faces.event.ActionEvent se déclenche <br/>
	 * l'évènement encapsule toutes les infos concernant le composant <br/>
	 * permet de supprimer un client dans la bdd via la dao <br/>
	 */
	public void supprimerClient(ActionEvent event) {
		//1. récup du param passé dans le composant au click du lien 'supprimer'
		UIParameter cp = (UIParameter) event.getComponent().findComponent("deleteID");
		
		//2. récup de la valeur du param => l'id du client à supprimer
		int clientID = (int) cp.getValue();
		
		//3. suppression du client dans la bdd via la dao
		//3.1récup du context de JSF
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		
		
		if(clientDAO.supprimer(clientID)) {  //suppression ok
			
			//envoi d'un message vers la vue via le context
			contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					 									"Suppression réussie", 
					 									"Le client a été supprimé avec succès"));
			
		}else {  //suppression not ok
			
			//envoi d'un message vers la vue via le context
			contextJSF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,
														"Echec de la Suppression", 
													 	"La suppression du client a échouée"));
			
		}
	}

/////////////////////////////////////////////////////////////////// SECTION  MODIFICATION \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	/**
	 * invoquée au click sur le lien 'modifier' de accueil.xhtml <br/>
	 * au click, l'événement javax.faces.event.ActionEvent se déclenche <br/>
	 * l'évènement encapsule toutes les infos concernant le composant <br/>
	 * permet de récupérer les infos du client à modifier à partir de la bdd, 
	 * et lier ces infos à la page modifier-client.xhtml <br/>
	 */
	public void selectionnerClient(ActionEvent event) {
		//1. récup du param passé dans le composant au click du lien 'modifier'
		UIParameter cp = (UIParameter) event.getComponent().findComponent("updateID");
		
		//2. récup de la valeur du param => l'id du client à modifier
		int clientID = (int) cp.getValue();
		
		//3. récup du client dans la bdd par l'id
		Client client = clientDAO.getById(clientID);
		
		//4. affectation du client à modifier à la prop 'client' du managedbean
		setClient(client);
		
		/**
		 * dans la page modifier-client.xhtml => on récupère le client à modifier via 
		 * la prop client du MB (managedbean)
		 */
	}
	

	/**
	 * invoquée au click du bouton modifier de la page modifier-client.xhtml <br/>
	 * permet de modifier le client dans la bdd
	 */
	public void modifierClient(ActionEvent event) {
		
		/**
		 * la prop client du MB encapsule les infos du client à modifier dans la bdd
		 */
		
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		if(clientDAO.modifier(client)) {  //modif ok
			
			//envoi d'un message vers la vue via le context
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
														 "Modification réussie", 
														 "Le client a été modifié avec succès");
			contextJSF.addMessage(null,  message);
		
		}else {  //modif not ok

			//envoi d'un message vers la vue via le context
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL,
														 "Echec de la Modification", 
														 "La modification du client a échouée");
			contextJSF.addMessage(null,  message);
			
		}
		
	}
	
	
///////////////////////////////////////////////////////////////////////// SECTION  AJOUT \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	
	/**
	 * permet d'instancier un objet de type client à vide pour pouvoir récup les valeurs 
	 * saisies dans le formulaire d'ajout de la page ajouter-client.xhtml <br/>
	 * cela permet d'éviter un nullPointerException <br/>
	 */
	public void initialiserClient(ActionEvent event) {
		
		//instanciation d'un nouveau objet client vide
		Client clientAdd = new Client();
		
		//affectation de l'objet à la prop client du MB
		setClient(clientAdd);
	
	}
	
	
	/**
	 * invoquée au click du bouton Ajouter de la page ajouter_livre.xhtml <br/>
	 * permet d'ajouter un nouveau livre dans la bdd <br/>
	 * @param event
	 */
	public void ajouterClient(ActionEvent event) {
		//récup le context de JSF pour l'envoi de messages vers la vue
		FacesContext contextJSF = FacesContext.getCurrentInstance();
		
		//ajout dans la bdd
		if (clientDAO.ajouter(client)) {	//ajout ok 
			
			//envoi d'un message vers la vue via le context
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
														 "Ajout réussie", 
														 "Le client a été ajouté avec succès");
			contextJSF.addMessage(null,  message);
			
		} else {	//ajout not ok

			//envoi d'un message vers la vue via le context
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL,
					 								"Echec de l'ajout", 
					 								"L'ajout du client a échouée");
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
