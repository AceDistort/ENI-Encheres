package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.BusinessException;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.ArticleVenduDAO;
import fr.eni.encheres.dal.DAOFactory;

public class ArticleVenduManager {
	private static ArticleVenduManager articleVenduManager;
	private ArticleVenduDAO articleVenduDAO;
	
	private ArticleVenduManager() {
		this.articleVenduDAO = DAOFactory.getArticleVenduDAO();
	}

	public static ArticleVenduManager getCategorieManager() {
		if(articleVenduManager == null) {
			articleVenduManager = new ArticleVenduManager();
		}
		return articleVenduManager;
	}
	
	public void nouvelleVente(ArticleVendu article) throws BusinessException {
		if(article == null) {
			BusinessException be = new BusinessException();
			throw be;
		}
		articleVenduDAO.creerVenteArticle(article);
	}
	
	public List<ArticleVendu> listerVentesDeconnecte() throws BusinessException {
		return articleVenduDAO.listerVentesDeconnecte();
	}
	
	public List<ArticleVendu> listerEncheresOuvertes() throws BusinessException {
		return articleVenduDAO.listerEncheresOuvertes();
	}
	
	public List<ArticleVendu> listerVentesNonDebutees() throws BusinessException {
		return articleVenduDAO.listerVentesNonDebutees();
	}
	
	public List<ArticleVendu> listerVentesTerminees() throws BusinessException {
		return articleVenduDAO.listerVentesTerminees();
	}
	
	public List<ArticleVendu> listerMesVentesEnCours(Utilisateur utilisateur) throws BusinessException {
		if(utilisateur == null) {
			BusinessException be = new BusinessException();
			//TODO
			throw be;
		}
		return articleVenduDAO.listerMesVentesEnCours(utilisateur);
	}
}
