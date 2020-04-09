package com.intiformation.gestioncompte.jsf.dao;

import java.util.List;

import com.intiformation.gestioncompte.jsf.model.Client;


public interface IClientDAO extends IGenerale<Client>{
	
	public List<Client> findClientByIdConseiller(int pIdConseiller);

}
