package fr.eni.encheres.dal;

import fr.eni.encheres.bo.ArticleVendu;

public interface ArticleVenduDAO {
	
	/**
	 * Méthode pour créer une nouvelle vente.
	 * @param article
	 */
	public void creerVenteArticle(ArticleVendu article);
}
