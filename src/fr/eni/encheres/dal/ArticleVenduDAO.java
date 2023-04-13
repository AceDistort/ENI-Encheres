package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.BusinessException;
import fr.eni.encheres.bo.Utilisateur;

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
	
	public List<ArticleVendu> listerEncheresOuvertes() throws BusinessException;
	
	public List<ArticleVendu> listerMesEncheresEnCours() throws BusinessException;
	
	public List<ArticleVendu> listerMesEncheresRemportees() throws BusinessException;
	
	public List<ArticleVendu> listerMesVentesEnCours(Utilisateur utilisateur) throws BusinessException;
	
	public List<ArticleVendu> listerVentesNonDebutees() throws BusinessException;
	
	public List<ArticleVendu> listerVentesTerminees() throws BusinessException;
}
