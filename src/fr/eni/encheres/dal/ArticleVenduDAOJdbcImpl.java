package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.BusinessException;
import fr.eni.encheres.bo.Utilisateur;

public class ArticleVenduDAOJdbcImpl implements ArticleVenduDAO {
	
	private static final String CREER_VENTE_ARTICLE = "INSERT INTO ARTICLES_VENDUS (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,no_utilisateur,no_categorie) VALUES (?,?,?,?,?,?,?);";
	private static final String LISTER_VENTES_ARTICLE = "SELECT no_article, nom_article, description, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie " + 
														"FROM ARTICLES_VENDUS " + 
														"INNER JOIN UTILISATEURS ON (UTILISATEURS.no_utilisateur = ARTICLES_VENDUS.no_utilisateur);";
	//private static final String LISTER_VENTES_ARTICLES = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente FROM ARTICLES_VENDUS;";

	/**
	 * {@inheritDoc}
	 * @throws BusinessException 
	 */
	@Override
	public void creerVenteArticle(ArticleVendu article) throws BusinessException {
		if (article == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.OBJET_NULL_AJOUT_ARTICLE);
			throw businessException;
		}
		try {
			Connection cnx = ConnectionProvider.getConnection();
			
			PreparedStatement pstmt = cnx.prepareStatement(CREER_VENTE_ARTICLE);
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
			
		} catch(Exception e){
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.AUTRE_ERREUR_AJOUT_ARTICLE);
			throw businessException;
		}
	}

	@Override
	public List<ArticleVendu> listerVentesDeconnecte() throws BusinessException {
		List<ArticleVendu> articles = new ArrayList<>();
		ArticleVendu article = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(LISTER_VENTES_ARTICLE);
			
			
			while (rs.next()) {
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
				article = new ArticleVendu();
				article.setNoArticle(rs.getInt("no_article"));
				article.setNomArticle(rs.getString("nom_article"));
				article.setDescription(rs.getString("description"));
				article.setDateDebutEncheres(rs.getDate("date_devut_encheres"));
				article.setDateFinEncheres(rs.getDate("date_fin_encheres"));
				article.setPrixInitial(rs.getInt("prix_initial"));
				article.setPrixVente(rs.getInt("prix_vente"));
				article.setVend(utilisateur);
			}
			articles.add(article);
		}
		catch(Exception e){
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.AUTRE_ERREUR_LISTER_ARTICLE);
			throw businessException;
		}
		return articles;
	}
	
	

}
