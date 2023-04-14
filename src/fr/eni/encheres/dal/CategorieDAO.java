package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.BusinessException;
import fr.eni.encheres.bo.Categorie;

public interface CategorieDAO {

	/**
	 * Méthode pour lister les catégories
	 * @return List<Categorie>
	 * @throws BusinessException
	 */
	public List<Categorie> lister() throws BusinessException;
	
	/**
	 * Méthode pour afficher une catégorie par ID
	 * @param categorie
	 * @return Categorie
	 * @throws BusinessException
	 */
	public Categorie afficherCategorieParID(Categorie categorie) throws BusinessException;
}
