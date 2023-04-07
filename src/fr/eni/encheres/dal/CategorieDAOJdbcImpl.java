package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.BusinessException;
import fr.eni.encheres.bo.Categorie;

public class CategorieDAOJdbcImpl implements CategorieDAO {
	
	private static final String LISTER = "SELECT no_categorie, libelle FROM CATEGORIES;";

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

}
