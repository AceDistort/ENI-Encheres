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
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;

public class ArticleVenduDAOJdbcImpl implements ArticleVenduDAO {
	
	private static final String CREER_VENTE_ARTICLE = "INSERT INTO ARTICLES_VENDUS (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie) VALUES (?,?,?,?,?,?,?,?);";
	private static final String LISTER_VENTES_ARTICLE = "SELECT a.no_article, a.nom_article, a.description, a.date_debut_encheres, a.date_fin_encheres, a.prix_initial, a.prix_vente, u.no_utilisateur, c.no_categorie, c.libelle, u.pseudo FROM ARTICLES_VENDUS a INNER JOIN UTILISATEURS u ON (u.no_utilisateur = a.no_utilisateur) INNER JOIN CATEGORIES c ON (c.no_categorie = a.no_categorie);";
	private static final String AFFICHER_ARTICLE_PAR_ID = "SELECT a.no_article, a.nom_article, a.description, a.date_debut_encheres, a.date_fin_encheres, a.prix_initial, a.prix_vente,\r\n" + 
			"u.no_utilisateur, u.pseudo, u.nom, u.prenom, u.email, u.telephone, u.rue as u_rue, u.code_postal as u_code_postal, u.ville as u_ville, u.mot_de_passe, u.credit, u.administrateur,\r\n" + 
			"r.rue as r_rue, r.code_postal as r_code_postal, r.ville as r_ville,\r\n" + 
			"c.no_categorie, c.libelle\r\n" + 
			"FROM ARTICLES_VENDUS a\r\n" + 
			"INNER JOIN UTILISATEURS u ON (u.no_utilisateur = a.no_utilisateur)\r\n" + 
			"INNER JOIN CATEGORIES c ON (c.no_categorie = a.no_categorie)\r\n" + 
			"LEFT OUTER JOIN RETRAITS r ON (r.no_article = a.no_article)\r\n" + 
			"WHERE a.no_article=?;";
	private static final String LISTER_ENCHERES_OUVERTES = "SELECT a.no_article, a.nom_article, a.description, a.date_debut_encheres, a.date_fin_encheres, a.prix_initial, a.prix_vente, u.no_utilisateur, c.no_categorie, c.libelle, u.pseudo FROM ARTICLES_VENDUS a INNER JOIN UTILISATEURS u ON (u.no_utilisateur = a.no_utilisateur) INNER JOIN CATEGORIES c ON (c.no_categorie = a.no_categorie) WHERE date_debut_encheres <= GETDATE() AND GETDATE() < date_fin_encheres;";
	private static final String LISTER_MES_ENCHERES_EN_COURS = "SELECT a.no_article, a.nom_article, a.description, a.date_debut_encheres, a.date_fin_encheres, a.prix_initial, a.prix_vente, u.no_utilisateur, c.no_categorie, c.libelle, u.pseudo FROM ARTICLES_VENDUS a INNER JOIN UTILISATEURS u ON (u.no_utilisateur = a.no_utilisateur) INNER JOIN CATEGORIES c ON (c.no_categorie = a.no_categorie) WHERE a.no_article IN (SELECT a.no_article FROM ENCHERES e INNER JOIN ARTICLES_VENDUS a ON e.no_article = a.no_article WHERE date_debut_encheres <= GETDATE() AND GETDATE() < date_fin_encheres AND e.no_utilisateur = ?);";
	private static final String LISTER_MES_ENCHERES_REMPORTEES = "SELECT a.no_article, a.nom_article, a.description, a.date_debut_encheres, a.date_fin_encheres, a.prix_initial, a.prix_vente, u.no_utilisateur, c.no_categorie, c.libelle, u.pseudo FROM ARTICLES_VENDUS a INNER JOIN UTILISATEURS u ON (u.no_utilisateur = a.no_utilisateur) INNER JOIN CATEGORIES c ON (c.no_categorie = a.no_categorie) INNER JOIN ENCHERES e ON (e.no_article = a.no_article AND e.no_utilisateur = u.no_utilisateur) INNER JOIN (SELECT no_article, MAX(date_enchere) AS date_enchere_max FROM ENCHERES WHERE no_utilisateur = ? GROUP BY no_article) dmax ON e.no_article = dmax.no_article AND e.date_enchere = dmax.date_enchere_max WHERE e.no_utilisateur = ?;";
	private static final String LISTER_MES_VENTES_EN_COURS = "SELECT a.no_article, a.nom_article, a.description, a.date_debut_encheres, a.date_fin_encheres, a.prix_initial, a.prix_vente, u.no_utilisateur, c.no_categorie, c.libelle, u.pseudo FROM ARTICLES_VENDUS a INNER JOIN UTILISATEURS u ON (u.no_utilisateur = a.no_utilisateur) INNER JOIN CATEGORIES c ON (c.no_categorie = a.no_categorie) WHERE date_debut_encheres <= GETDATE() AND GETDATE() <= date_fin_encheres AND u.no_utilisateur = ?;";
	private static final String LISTER_VENTES_NON_DEBUTEES = "SELECT a.no_article, a.nom_article, a.description, a.date_debut_encheres, a.date_fin_encheres, a.prix_initial, a.prix_vente, u.no_utilisateur, c.no_categorie, c.libelle, u.pseudo FROM ARTICLES_VENDUS a INNER JOIN UTILISATEURS u ON (u.no_utilisateur = a.no_utilisateur) INNER JOIN CATEGORIES c ON (c.no_categorie = a.no_categorie) WHERE date_debut_encheres > GETDATE();";
	private static final String LISTER_VENTES_TERMINEES = "SELECT a.no_article, a.nom_article, a.description, a.date_debut_encheres, a.date_fin_encheres, a.prix_initial, a.prix_vente, u.no_utilisateur, c.no_categorie, c.libelle, u.pseudo FROM ARTICLES_VENDUS a INNER JOIN UTILISATEURS u ON (u.no_utilisateur = a.no_utilisateur) INNER JOIN CATEGORIES c ON (c.no_categorie = a.no_categorie) WHERE GETDATE() > date_fin_encheres;";
	
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
			pstmt.setInt(6, article.getPrixVente());
			pstmt.setInt(7, article.getVend().getNoUtilisateur());
			pstmt.setInt(8, article.getCategorie().getNoCategorie());
			
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
	public List<ArticleVendu> listerMesEncheresEnCours(Utilisateur utilisateur) throws BusinessException {
		List<ArticleVendu> articles = new ArrayList<>();

		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(LISTER_MES_ENCHERES_EN_COURS);
			
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
	public List<ArticleVendu> listerMesEncheresRemportees(Utilisateur utilisateur) throws BusinessException {
		List<ArticleVendu> articles = new ArrayList<>();

		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(LISTER_MES_ENCHERES_REMPORTEES);
			
			pstmt.setInt(1, utilisateur.getNoUtilisateur());
			pstmt.setInt(2, utilisateur.getNoUtilisateur());
			
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
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setTelephone(rs.getString("telephone"));
				utilisateur.setRue(rs.getString("u_rue"));
				utilisateur.setCodePostal(rs.getString("u_code_postal"));
				utilisateur.setVille(rs.getString("u_ville"));
				utilisateur.setCredit(rs.getInt("credit"));
				
				Categorie categorie = new Categorie();
				categorie.setNoCategorie(rs.getInt("no_categorie"));
				categorie.setLibelle(rs.getString("libelle"));
				
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
