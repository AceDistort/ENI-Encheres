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
	 * @return List<ArticleVendu>
	 * @throws BusinessException 
	 */
	public List<ArticleVendu> listerVentesDeconnecte() throws BusinessException;
	
	/**
	 * Méthode pour afficher un article par ID
	 * @param article
	 * @return List<ArticleVendu>
	 * @throws BusinessException
	 */
	public ArticleVendu afficherArticleParID(ArticleVendu article) throws BusinessException;
	public List<ArticleVendu> listerEncheresOuvertes() throws BusinessException;
	
	/**
	 * Méthode pour lister les ventes en cours
	 * @param utilisateur
	 * @return List<ArticleVendu>
	 * @throws BusinessException
	 */
	public List<ArticleVendu> listerMesEncheresEnCours(Utilisateur utilisateur) throws BusinessException;
	
	/**
	 * Méthode pour lister les ventes remportées
	 * @param utilisateur
	 * @return List<ArticleVendu>
	 * @throws BusinessException
	 */
	public List<ArticleVendu> listerMesEncheresRemportees(Utilisateur utilisateur) throws BusinessException;
	

	/**
	 * Méthode pour lister les ventes en cours
	 * @param utilisateur
	 * @return List<ArticleVendu>
	 * @throws BusinessException
	 */
	public List<ArticleVendu> listerMesVentesEnCours(Utilisateur utilisateur) throws BusinessException;
	
	/**
	 * Méthode pour lister les ventes non débutées
	 * @param utilisateur
	 * @return List<ArticleVendu>
	 * @throws BusinessException
	 */
	public List<ArticleVendu> listerVentesNonDebutees() throws BusinessException;
	
	/**
	 * Méthode pour lister les ventes terminées
	 * @param utilisateur
	 * @return List<ArticleVendu>
	 * @throws BusinessException
	 */
	public List<ArticleVendu> listerVentesTerminees() throws BusinessException;
}
