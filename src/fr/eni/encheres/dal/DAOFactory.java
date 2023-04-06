package fr.eni.encheres.dal;

public class DAOFactory {
	public static ArticleVenduDAO getArticleVenduDAO() {
		return new ArticleVenduJDBCImpl();
	}
	
	public static UtilisateurDAO getUtilisateurDAO()
	{
		return new UtilisateurDAOJdbcImpl();
	}
}
