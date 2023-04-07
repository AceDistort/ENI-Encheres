package fr.eni.encheres.dal;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.BusinessException;

public interface ArticleVenduDAO {
	
	/**
	 * Méthode pour créer une nouvelle vente.
	 * @param article
	 * @throws BusinessException 
	 */
	public void creerVenteArticle(ArticleVendu article) throws BusinessException;
}
