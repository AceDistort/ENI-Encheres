package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.BusinessException;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.ArticleVenduDAO;
import fr.eni.encheres.dal.DAOFactory;

/**
 * Classe en charge de gérer les articles vendus
 */
public class ArticleVenduManager {
	private static ArticleVenduManager articleVenduManager;
	private ArticleVenduDAO articleVenduDAO;
	
	/**
	 * Constructeur privé
	 */
	private ArticleVenduManager() {
		this.articleVenduDAO = DAOFactory.getArticleVenduDAO();
	}

	/**
	 * Méthode en charge de récupérer l'instance de la classe
	 * @return ArticleVenduManager
	 */
	public static ArticleVenduManager getArticleVenduManager() {
		if(articleVenduManager == null) {
			articleVenduManager = new ArticleVenduManager();
		}
		return articleVenduManager;
	}
	
	/**
	 * Méthode en charge de créer une nouvelle vente
	 * @param article
	 * @throws BusinessException
	 */
	public void nouvelleVente(ArticleVendu article) throws BusinessException {
		if(article == null) {
			BusinessException be = new BusinessException();
			throw be;
		}
		articleVenduDAO.creerVenteArticle(article);
	}
	
	/**
	 * Méthode en charge de lister les articles vendus
	 * @return List<ArticleVendu>
	 * @throws BusinessException
	 */
	public List<ArticleVendu> listerVentesDeconnecte() throws BusinessException {
		return articleVenduDAO.listerVentesDeconnecte();
	}
	
	/**
	 * Méthode en charge de lister les encheres ouvertes
	 * @return List<ArticleVendu>
	 * @throws BusinessException
	 */
	public List<ArticleVendu> listerEncheresOuvertes() throws BusinessException {
		return articleVenduDAO.listerEncheresOuvertes();
	}
	
	/**
	 * Méthode en charge de lister les ventes non débutées
	 * @return List<ArticleVendu>
	 * @throws BusinessException
	 */
	public List<ArticleVendu> listerVentesNonDebutees() throws BusinessException {
		return articleVenduDAO.listerVentesNonDebutees();
	}
	
	/**
	 * Méthode en charge de lister les ventes terminées
	 * @return List<ArticleVendu>
	 * @throws BusinessException
	 */
	public List<ArticleVendu> listerVentesTerminees() throws BusinessException {
		return articleVenduDAO.listerVentesTerminees();
	}
	
	/**
	 * Méthode en charge de lister les ventes en cours
	 * @return List<ArticleVendu>
	 * @throws BusinessException
	 */
	public List<ArticleVendu> listerMesVentesEnCours(Utilisateur utilisateur) throws BusinessException {
		if(utilisateur == null) {
			BusinessException be = new BusinessException();
			//TODO
			throw be;
		}
		return articleVenduDAO.listerMesVentesEnCours(utilisateur);
	}
	
	/**
	 * Méthode en charge de lister mes enchères en cours
	 * @return List<ArticleVendu>
	 * @throws BusinessException
	 */
	public List<ArticleVendu> listerMesEncheresEnCours(Utilisateur utilisateur) throws BusinessException {
		if(utilisateur == null) {
			BusinessException be = new BusinessException();
			//TODO
			throw be;
		}
		return articleVenduDAO.listerMesEncheresEnCours(utilisateur);
	}
	
	/**
	 * Méthode en charge de lister mes enchères remportées
	 * @return List<ArticleVendu>
	 * @throws BusinessException
	 */
	public List<ArticleVendu> listerMesEncheresRemportees(Utilisateur utilisateur) throws BusinessException {
		if(utilisateur == null) {
			BusinessException be = new BusinessException();
			//TODO
			throw be;
		}
		return articleVenduDAO.listerMesEncheresRemportees(utilisateur);
	}
	
	/*
	 * Méthode en charge d'afficher un article par son ID
	 * @return List<ArticleVendu>
	 * @throws BusinessException
	 */
	public ArticleVendu afficherArticle(ArticleVendu article) throws BusinessException {
		BusinessException businessException = new BusinessException();
		if(article == null) {
			businessException.ajouterErreur(CodesResultatBLL.OBJET_NULL_AFFICHER_ARTICLE_PAR_ID);
			throw businessException;
		}
		
		try {
			return articleVenduDAO.afficherArticleParID(article);
		} catch(Exception e) {
			e.printStackTrace();
			businessException.ajouterErreur(CodesResultatBLL.AUTRE_ERREUR_AFFICHER_ARTICLE_PAR_ID);
			throw businessException;
		}
	}
}
