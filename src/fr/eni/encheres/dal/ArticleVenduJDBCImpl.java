package fr.eni.encheres.dal;

import fr.eni.encheres.bo.ArticleVendu;

public class ArticleVenduJDBCImpl implements ArticleVenduDAO {
	
	private static final String CREER_VENTE_ARTICLE = "INSERT INTO ARTICLES_VENDUS (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,no_utilisateur,no_categorie) VALUES (?,?,?,?,?,?,?);";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void creerVenteArticle(ArticleVendu article) {
		// TODO Auto-generated method stub

	}

}
