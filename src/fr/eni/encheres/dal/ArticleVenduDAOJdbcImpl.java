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
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Utilisateur;

public class ArticleVenduDAOJdbcImpl implements ArticleVenduDAO {
	
	private static final String CREER_VENTE_ARTICLE = "INSERT INTO ARTICLES_VENDUS (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,no_utilisateur,no_categorie) VALUES (?,?,?,?,?,?,?);";
	private static final String LISTER_VENTES_ARTICLE = "SELECT a.no_article, a.nom_article, a.description, a.date_debut_encheres, a.date_fin_encheres, a.prix_initial, a.prix_vente, u.no_utilisateur, c.no_categorie, c.libelle, u.pseudo FROM ARTICLES_VENDUS a INNER JOIN UTILISATEURS u ON (u.no_utilisateur = a.no_utilisateur) INNER JOIN CATEGORIES c ON (c.no_categorie = a.no_categorie);";
	private static final String AFFICHER_ARTICLE_PAR_ID = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie FROM ARTICLES_VENDUS WHERE no_article=?;";
	private static final String LISTER_ENCHERES_OUVERTES = "SELECT a.no_article, a.nom_article, a.description, a.date_debut_encheres, a.date_fin_encheres, a.prix_initial, a.prix_vente, u.no_utilisateur, c.no_categorie, c.libelle, u.pseudo FROM ARTICLES_VENDUS a INNER JOIN UTILISATEURS u ON (u.no_utilisateur = a.no_utilisateur) INNER JOIN CATEGORIES c ON (c.no_categorie = a.no_categorie) WHERE date_debut_encheres <= GETDATE() AND GETDATE() < date_fin_encheres;";
	
	
	private static final String LISTER_MES_VENTES_EN_COURS = "SELECT a.no_article, a.nom_article, a.description, a.date_debut_encheres, a.date_fin_encheres, a.prix_initial, a.prix_vente, u.no_utilisateur, c.no_categorie, c.libelle, u.pseudo FROM ARTICLES_VENDUS a INNER JOIN UTILISATEURS u ON (u.no_utilisateur = a.no_utilisateur) INNER JOIN CATEGORIES c ON (c.no_categorie = a.no_categorie) WHERE date_debut_encheres <= GETDATE() AND GETDATE() <= date_fin_encheres AND u.no_utilisateur = ?;";
	private static final String LISTER_VENTES_NON_DEBUTEES = "SELECT a.no_article, a.nom_article, a.description, a.date_debut_encheres, a.date_fin_encheres, a.prix_initial, a.prix_vente, u.no_utilisateur, c.no_categorie, c.libelle, u.pseudo FROM ARTICLES_VENDUS a INNER JOIN UTILISATEURS u ON (u.no_utilisateur = a.no_utilisateur) INNER JOIN CATEGORIES c ON (c.no_categorie = a.no_categorie) WHERE date_debut_encheres > GETDATE();";
	private static final String LISTER_VENTES_TERMINEES = "SELECT a.no_article, a.nom_article, a.description, a.date_debut_encheres, a.date_fin_encheres, a.prix_initial, a.prix_vente, u.no_utilisateur, c.no_categorie, c.libelle, u.pseudo FROM ARTICLES_VENDUS a INNER JOIN UTILISATEURS u ON (u.no_utilisateur = a.no_utilisateur) INNER JOIN CATEGORIES c ON (c.no_categorie = a.no_categorie) WHERE GETDATE() >= date_fin_encheres;";
	
	private ArticleVendu creerArticleVenduDepuisResultSet(ResultSet rs) throws SQLException {
		ArticleVendu article = null;
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
		utilisateur.setPseudo(rs.getString("pseudo"));
		
		Categorie categorie = new Categorie();
		categorie.setLibelle(rs.getString("libelle"));
		categorie.setNoCategorie(rs.getInt("no_categorie"));
		
		article = new ArticleVendu();
		article.setNoArticle(rs.getInt("no_article"));
		article.setNomArticle(rs.getString("nom_article"));
		article.setDescription(rs.getString("description"));
		article.setDateDebutEncheres(rs.getDate("date_debut_encheres"));
		article.setDateFinEncheres(rs.getDate("date_fin_encheres"));
		article.setPrixInitial(rs.getInt("prix_initial"));
		article.setPrixVente(rs.getInt("prix_vente"));
		article.setVend(utilisateur);
		article.setCategorie(categorie);
		return article;
	}
	
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
			e.printStackTrace();
			BusinessException be = new BusinessException();
			throw be;
		}

	}


	@Override
	public List<ArticleVendu> listerVentesDeconnecte() throws BusinessException {
		List<ArticleVendu> articles = new ArrayList<>();
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(LISTER_VENTES_ARTICLE);
			
			
			while (rs.next()) {
				ArticleVendu article = creerArticleVenduDepuisResultSet(rs);
				articles.add(article);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.AUTRE_ERREUR_LISTER_ARTICLE);
			throw businessException;
		}
		return articles;
	}


	@Override
	public List<ArticleVendu> listerEncheresOuvertes() throws BusinessException {
		List<ArticleVendu> articles = new ArrayList<>();

		try {
			Connection cnx = ConnectionProvider.getConnection();
			Statement stmt = cnx.createStatement();
			
			ResultSet rs = stmt.executeQuery(LISTER_ENCHERES_OUVERTES);
			
			while(rs.next()) {
				ArticleVendu article = creerArticleVenduDepuisResultSet(rs);
				articles.add(article);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return articles;
	}


	@Override
	public List<ArticleVendu> listerMesEncheresEnCours() throws BusinessException {
		List<ArticleVendu> articles = new ArrayList<>();
		// TODO Auto-generated method stub
		return articles;
	}


	@Override
	public List<ArticleVendu> listerMesEncheresRemportees() throws BusinessException {
		List<ArticleVendu> articles = new ArrayList<>();
		// TODO Auto-generated method stub
		return articles;
	}


	@Override
	public List<ArticleVendu> listerMesVentesEnCours(Utilisateur utilisateur) throws BusinessException {
		List<ArticleVendu> articles = new ArrayList<>();

		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(LISTER_MES_VENTES_EN_COURS);
			
			pstmt.setInt(1, utilisateur.getNoUtilisateur());
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ArticleVendu article = creerArticleVenduDepuisResultSet(rs);
				articles.add(article);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return articles;
	}


	@Override
	public List<ArticleVendu> listerVentesNonDebutees() throws BusinessException {
		List<ArticleVendu> articles = new ArrayList<>();

		try {
			Connection cnx = ConnectionProvider.getConnection();
			Statement stmt = cnx.createStatement();
			
			ResultSet rs = stmt.executeQuery(LISTER_VENTES_NON_DEBUTEES);
			
			while(rs.next()) {
				ArticleVendu article = creerArticleVenduDepuisResultSet(rs);
				articles.add(article);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return articles;
	}


	@Override
	public List<ArticleVendu> listerVentesTerminees() throws BusinessException {
		List<ArticleVendu> articles = new ArrayList<>();

		try {
			Connection cnx = ConnectionProvider.getConnection();
			Statement stmt = cnx.createStatement();
			
			ResultSet rs = stmt.executeQuery(LISTER_VENTES_TERMINEES);
			
			while(rs.next()) {
				ArticleVendu article = creerArticleVenduDepuisResultSet(rs);
				articles.add(article);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return articles;
	}
	
	public ArticleVendu afficherArticleParID(ArticleVendu article) throws BusinessException {
		if (article == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.OBJET_NULL_AFFICHER_ARTICLE);
			throw businessException;
		}
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(AFFICHER_ARTICLE_PAR_ID);
			pstmt.setInt(1, article.getNoArticle());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
				
				Categorie categorie = new Categorie();
				categorie.setNoCategorie(rs.getInt("no_categorie"));
				
				article.setNoArticle(rs.getInt("no_article"));
				article.setNomArticle(rs.getString("nom_article"));
				article.setDescription(rs.getString("description"));
				article.setDateDebutEncheres(rs.getDate("date_debut_encheres"));
				article.setDateFinEncheres(rs.getDate("date_fin_encheres"));
				article.setPrixInitial(rs.getInt("prix_initial"));
				article.setPrixVente(rs.getInt("prix_vente"));
				article.setVend(utilisateur);
				article.setCategorie(categorie);
			}
		} catch(Exception e){
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.AUTRE_ERREUR_AFFICHER_ARTICLE);
			throw businessException;
		}
		return article;
	}
	
	

}
