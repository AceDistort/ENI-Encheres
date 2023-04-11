package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.BusinessException;

public class ArticleVenduDAOJdbcImpl implements ArticleVenduDAO {
	
	private static final String CREER_VENTE_ARTICLE = "INSERT INTO ARTICLES_VENDUS (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,no_utilisateur,no_categorie) VALUES (?,?,?,?,?,?,?);";

	/**
	 * {@inheritDoc}
	 * @throws BusinessException 
	 */
	@Override
	public void creerVenteArticle(ArticleVendu article) throws BusinessException {
		try {
			Connection cnx = ConnectionProvider.getConnection();
			
			PreparedStatement pstmt = cnx.prepareStatement(CREER_VENTE_ARTICLE,PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, article.getNomArticle());
			pstmt.setString(2, article.getDescription());
			pstmt.setDate(3, article.getDateDebutEncheres());
			pstmt.setDate(4, article.getDateFinEncheres());
			pstmt.setInt(5, article.getPrixInitial());
			pstmt.setInt(6, article.getVend().getNoUtilisateur());
			pstmt.setInt(7, article.getCategorie().getNoCategorie());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rs.next()) {
				article.setNoArticle(rs.getInt(1));
			}
			
		} catch (SQLException e) {
			BusinessException be = new BusinessException();
			throw be;
		}

	}

	@Override
	public List<ArticleVendu> listerVentesDeconnecte(String texte) throws BusinessException {
		List<ArticleVendu> articles = new ArrayList<ArticleVendu>();
		for(ArticleVendu article: listerVentesDeconnecte()) {
			if(article.getNomArticle().contains(texte)) {
				articles.add(article);
			}
		}
		return articles;
	}

	@Override
	public List<ArticleVendu> listerVentesDeconnecte(int noCategorie) throws BusinessException {
		List<ArticleVendu> articles = new ArrayList<ArticleVendu>();
		for(ArticleVendu article: listerVentesDeconnecte()) {
			if(article.getCategorie().getNoCategorie() == noCategorie) {
				articles.add(article);
			}
		}
		return articles;
	}

}
