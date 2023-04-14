package fr.eni.encheres.dal;

/**
 * Classe en charge de gérer les DAO
 */
public class DAOFactory {

	/**
	 * Méthode en charge de récupérer l'instance de la classe ArticleVenduDAOJdbcImpl
	 * @return ArticleVenduDAO
	 */
	public static ArticleVenduDAO getArticleVenduDAO() {
		return new ArticleVenduDAOJdbcImpl();
	}
	
	/**
	 * Méthode en charge de récupérer l'instance de la classe UtilisateurDAOJdbcImpl
	 * @return UtilisateurDAO
	 */
	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOJdbcImpl();
	}
	
	/**
	 * Méthode en charge de récupérer l'instance de la classe CategorieDAOJdbcImpl
	 * @return CategorieDAO
	 */
	public static CategorieDAO getCategorieDAO() {
		return new CategorieDAOJdbcImpl();
	}
	
	/**
	 * Méthode en charge de récupérer l'instance de la classe RetraitDAOJdbcImpl
	 * @return RetraitDAO
	 */
	public static RetraitDAO getRetraitDAO() {
		return new RetraitDAOJdbcImpl();
	}
	
	/**
	 * Méthode en charge de récupérer l'instance de la classe EnchereDAOJdbcImpl
	 * @return EnchereDAO
	 */
	public static EnchereDAO getEnchereDAO() {
		return new EnchereDAOJdbcImpl();
	}
	
}
