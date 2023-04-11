package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.bo.BusinessException;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.dal.CategorieDAO;
import fr.eni.encheres.dal.DAOFactory;

public class CategoriesManager {
	private static CategoriesManager categorieManager;
	private CategorieDAO categorieDAO;
	
	private CategoriesManager() {
		this.categorieDAO = DAOFactory.getCategorieDAO();
	}

	public static CategoriesManager getCategorieManager() {
		if(categorieManager == null) {
			categorieManager = new CategoriesManager();
		}
		return categorieManager;
	}
	
	public List<Categorie> lister() throws BusinessException {
		return categorieDAO.lister();
	}
	
}
