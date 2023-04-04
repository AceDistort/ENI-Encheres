package fr.eni.encheres.dal;

public class DAOFactory {
	public static ArticleVenduDAO getArticleVenduDAO() {
		return new ArticleVenduJDBCImpl();
	}
}
