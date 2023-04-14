package fr.eni.encheres.dal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.BusinessException;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;

public class EnchereDAOJdbcImpl implements EnchereDAO {
	//Attributs d'instances
	private static final String CREER="INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES (?,?,?,?);";
	private static final String MODIFIER="UPDATE ENCHERES SET montant_enchere=? WHERE no_utilisateur=? AND no_article=?;";
	private static final String SUPPRIMER="DELETE FROM ENCHERE WHERE no_utilisateur=? AND no_article=?;";
	private static final String AFFICHER_PAR_UTIL_ET_ARTICLE = "SELECT no_utilisateur, no_article, date_enchere, montant_enchere FROM ENCHERES WHERE no_utilisateur=? AND no_article=?;";
	private static final String ENCHERIR_PROCEDURE="{ CALL dbo.ajout_enchere(?, ?, ?) }";

	@Override
	public void creerEnchere(Enchere enchere) throws BusinessException {
		if (enchere == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.OBJET_NULL_AJOUT_ENCHERE);
			throw businessException;
		}
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(CREER);
			pstmt.setInt(1, enchere.getUtilisateur().getNoUtilisateur());
			pstmt.setInt(2, enchere.getArticle().getNoArticle());
			pstmt.setDate(3, enchere.getDateEnchere());
			pstmt.setInt(4, enchere.getMontantEnchere());
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.AUTRE_ERREUR_AJOUT_ENCHERE);
			throw businessException;
		}
	}
	
	@Override
	public void encherir(Enchere enchere) throws BusinessException {
		if (enchere == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.OBJET_NULL_ENCHERIR_ENCHERE);
			throw businessException;
		}
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			CallableStatement callstmt = cnx.prepareCall(ENCHERIR_PROCEDURE);
			callstmt.setInt(1, enchere.getArticle().getNoArticle());
			callstmt.setInt(2, enchere.getUtilisateur().getNoUtilisateur());
			callstmt.setInt(3, enchere.getMontantEnchere());
			callstmt.execute();
			
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.AUTRE_ERREUR_ENCHERIR_ENCHERE);
			throw businessException;
		}
	}

	@Override
	public void modifierEnchere(Enchere enchere) throws BusinessException {
		if (enchere == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.OBJET_NULL_MODIF_ENCHERE);
			throw businessException;
		}
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(MODIFIER);
			pstmt.setInt(1, enchere.getMontantEnchere());
			pstmt.setInt(2, enchere.getUtilisateur().getNoUtilisateur());
			pstmt.setInt(3, enchere.getArticle().getNoArticle());
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.AUTRE_ERREUR_MODIF_ENCHERE);
			throw businessException;
		}
	}

	@Override
	public void supprimerEnchere(Enchere enchere) throws BusinessException {
		if (enchere == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.OBJET_NULL_SUPP_ENCHERE);
			throw businessException;
		}
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(SUPPRIMER);
			pstmt.setInt(1, enchere.getUtilisateur().getNoUtilisateur());
			pstmt.setInt(2, enchere.getArticle().getNoArticle());
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.AUTRE_ERREUR_SUPP_ENCHERE);
			throw businessException;
		}
	}

	@Override
	public Enchere afficherParUtilEtArt(Enchere enchere) throws BusinessException {
		BusinessException businessException = new BusinessException();
		if (enchere == null) {
			businessException.ajouterErreur(CodesResultatDAL.OBJET_NULL_AFFICHER_ENCHERE_PAR_IDUTIL_ET_IDART);
			throw businessException;
		}
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(AFFICHER_PAR_UTIL_ET_ARTICLE);
			pstmt.setInt(1, enchere.getUtilisateur().getNoUtilisateur());
			pstmt.setInt(2, enchere.getArticle().getNoArticle());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
				
				ArticleVendu article = new ArticleVendu();
				article.setNoArticle(rs.getInt("no_article"));
				
				enchere.setUtilisateur(utilisateur);
				enchere.setArticle(article);
				enchere.setDateEnchere(rs.getDate("date_enchere"));
				enchere.setMontantEnchere(rs.getInt("montant_enchere"));
			}
		} catch(Exception e){
			businessException.ajouterErreur(CodesResultatDAL.AUTRE_ERREUR_AFFICHAGE_ENCHERE_PAR_IDUTIL_ET_IDART);
			throw businessException;
		}
		return enchere;
	}
}
