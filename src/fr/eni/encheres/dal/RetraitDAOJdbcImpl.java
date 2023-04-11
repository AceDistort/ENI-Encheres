package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import fr.eni.encheres.bo.BusinessException;
import fr.eni.encheres.bo.Retrait;

public class RetraitDAOJdbcImpl implements RetraitDAO {

	private static final String CREER = "INSERT INTO RETRAITS (no_article,rue,code_postal,ville) VALUES (?,?,?,?);";
	
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

}
