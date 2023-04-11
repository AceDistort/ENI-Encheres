package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.BusinessException;

public interface ArticleVenduDAO {
	
	/**
	 * Méthode pour créer une nouvelle vente.
	 * @param article
	 * @throws BusinessException 
	 */
	public void creerVenteArticle(ArticleVendu article) throws BusinessException;
	
	/**
	 * Méthode pour lister toutes les ventes en mode déconnecté
	 * @return une liste d'articleVendu
	 * @throws BusinessException 
	 */
	public List<ArticleVendu> listerVentesDeconnecte() throws BusinessException;
	
	public List<ArticleVendu> listerVentesDeconnecte(String texte) throws BusinessException;
	
	public List<ArticleVendu> listerVentesDeconnecte(int noCategorie) throws BusinessException;
}
