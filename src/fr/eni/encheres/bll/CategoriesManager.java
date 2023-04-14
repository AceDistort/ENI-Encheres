package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.bo.BusinessException;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.dal.CategorieDAO;
import fr.eni.encheres.dal.DAOFactory;

/**
 * Classe en charge de gérer les catégories
 */
public class CategoriesManager {
	private static CategoriesManager categorieManager;
	private CategorieDAO categorieDAO;
	
	private CategoriesManager() {
		this.categorieDAO = DAOFactory.getCategorieDAO();
	}

	/**
	 * Méthode en charge de récupérer l'instance de la classe
	 * @return CategoriesManager
	 */
	public static CategoriesManager getCategorieManager() {
		if(categorieManager == null) {
			categorieManager = new CategoriesManager();
		}
		return categorieManager;
	}
	
	/**
	 * Méthode en charge de lister les catégories
	 * @return List<Categorie>
	 * @throws BusinessException
	 */
	public List<Categorie> lister() throws BusinessException {
		return categorieDAO.lister();
	}
	
	/**
	 * Méthode en charge de récupérer une catégorie par son ID
	 * @param categorie
	 * @return Categorie
	 * @throws BusinessException
	 */
	public Categorie afficherCategorie(Categorie categorie) throws BusinessException {
		BusinessException businessException = new BusinessException();
		if(categorie == null) {
			businessException.ajouterErreur(CodesResultatBLL.OBJET_NULL_AFFICHER_CATEGORIE_PAR_ID);
			throw businessException;
		}
		
		try {
			return categorieDAO.afficherCategorieParID(categorie);
		} catch(Exception e) {
			e.printStackTrace();
			businessException.ajouterErreur(CodesResultatBLL.AUTRE_ERREUR_AFFICHER_CATEGORIE_PAR_ID);
			throw businessException;
		}
	}
	
}
