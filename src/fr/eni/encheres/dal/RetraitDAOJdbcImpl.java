package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.BusinessException;
import fr.eni.encheres.bo.Retrait;

public class RetraitDAOJdbcImpl implements RetraitDAO {

	private static final String CREER = "INSERT INTO RETRAITS (no_article,rue,code_postal,ville) VALUES (?,?,?,?);";
	private static final String AFFICHER_PAR_ID_ARTICLE = "SELECT rue, code_postal, ville FROM RETRAITS WHERE no_article=?;";
	
	@Override
	public void creer(Retrait retrait) throws BusinessException {
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(CREER);
			
			pstmt.setInt(1, retrait.getConcerne().getNoArticle());
			pstmt.setString(2, retrait.getRue());
			pstmt.setString(3, retrait.getCodePostal());
			pstmt.setString(4, retrait.getVille());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.ERREUR_CREER_RETRAIT);
			throw be;
		}
	}

	@Override
	public Retrait selectionnerParNoArticle(ArticleVendu article) throws BusinessException {
		Retrait retrait = null;
		if (article == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.OBJET_NULL_AFFICHAGE_RETRAIT);
			throw businessException;
		}
		try(Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(AFFICHER_PAR_ID_ARTICLE);
			pstmt.setInt(1, article.getNoArticle());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				retrait = new Retrait();
				retrait.setConcerne(article);
				retrait.setRue(rs.getString("rue"));
				retrait.setCodePostal(rs.getString("code_postal"));
				retrait.setVille(rs.getString("ville"));
			}
		} catch(Exception e){
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.AUTRE_ERREUR_AFFICHAGE_RETRAIT);
			throw businessException;
		}
		return retrait;
	}

}
