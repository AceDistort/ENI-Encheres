package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.BusinessException;
import fr.eni.encheres.bo.Categorie;

public class CategorieDAOJdbcImpl implements CategorieDAO {
	
	private static final String LISTER = "SELECT no_categorie, libelle FROM CATEGORIES;";
	private static final String AFFICHER_PAR_ID = "SELECT no_categorie, libelle FROM CATEGORIES WHERE no_categorie=?;";

	@Override
	public List<Categorie> lister() throws BusinessException {
		List<Categorie> categories = new ArrayList<Categorie>();
		
		try {
			Connection cnx = ConnectionProvider.getConnection();
			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(LISTER);
			while(rs.next()) {
				Categorie c = new Categorie(rs.getString("libelle"));
				c.setNoCategorie(rs.getInt("no_categorie"));
				categories.add(c);
			}
		} catch (SQLException e) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.ERREUR_LISTER_CATEGORIES);
			throw be;
		}
		
		return categories;
	}

	@Override
	public Categorie afficherCategorieParID(Categorie categorie) throws BusinessException {
		BusinessException businessException = new BusinessException();
		if (categorie == null) {
			businessException.ajouterErreur(CodesResultatDAL.OBJET_NULL_AFFICHER_CATEGORIE);
			throw businessException;
		}
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(AFFICHER_PAR_ID);
			pstmt.setInt(1, categorie.getNoCategorie());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				categorie.setNoCategorie(rs.getInt("no_categorie"));
				categorie.setLibelle(rs.getString("libelle"));
			}
		} catch(Exception e){
			businessException.ajouterErreur(CodesResultatDAL.AUTRE_ERREUR_AFFICHER_CATEGORIE);
			throw businessException;
		}
		
		return categorie;
	}

}
