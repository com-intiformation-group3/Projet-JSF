package com.intiformation.gestioncompte.jsf.dao;


import java.util.List;

import com.intiformation.gestioncompte.jsf.model.Compte;


public interface ICompteDAO extends IGenerale<Compte>{
	
	
	
	
	public boolean setCompteToClient (int pIdClient, int pIdCompte);
	
	public List<Compte> findCompteByIdOwner(int pIdClient);
	
	
	public boolean withdrow (double pMontant, int pIdCompte);
	
	public boolean deposit (double pMontant, int pIdCompte);
	
	public void transfert (double pMontant, int pIdCompte1, int pIdCompte2);
	
	

}
