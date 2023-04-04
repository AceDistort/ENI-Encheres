package fr.eni.encheres.dal;

import fr.eni.encheres.bo.ArticleVendu;

public interface ArticleVenduDAO {
	
	/**
	 * M�thode pour cr�er une nouvelle vente.
	 * @param article
	 */
	public void creerVenteArticle(ArticleVendu article);
}
