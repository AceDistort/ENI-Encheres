package fr.eni.encheres.bll;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.BusinessException;
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
}
