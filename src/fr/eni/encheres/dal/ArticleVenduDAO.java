package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.BusinessException;

public interface ArticleVenduDAO {
	
	/**
	 * M�thode pour cr�er une nouvelle vente.
	 * @param article
	 * @throws BusinessException 
	 */
	public void creerVenteArticle(ArticleVendu article) throws BusinessException;
	
	public List<ArticleVendu> listerVentesDeconnecte(String texte) throws BusinessException;
	
	public List<ArticleVendu> listerVentesDeconnecte(int noCategorie) throws BusinessException;
}
