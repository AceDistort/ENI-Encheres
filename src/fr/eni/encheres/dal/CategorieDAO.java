package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.BusinessException;
import fr.eni.encheres.bo.Categorie;

public interface CategorieDAO {
	public List<Categorie> lister() throws BusinessException;
	
	public Categorie afficherCategorieParID(Categorie categorie) throws BusinessException;
}
