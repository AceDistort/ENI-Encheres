package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.BusinessException;
import fr.eni.encheres.bo.Utilisateur;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {
	
	//Attributs d'instance
	private static final String AJOUTER = "INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES (?,?,?,?,?,?,?,?,?,?,?);";
	private static final String MODIFIER = "UPDATE UTILISATEURS SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe=?, credit=?, administrateur=? WHERE no_utilisateur=?;";
	private static final String SUPPRIMER = "DELETE FROM UTILISATEURS WHERE no_utilisateur=?;";
	private static final String AFFICHER_UTILISATEUR_PAR_ID = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS WHERE no_utilisateur=?;";
	private static final String AFFICHER_TOUS_LES_UTILISATEURS = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS;";
	private static final String AFFICHER_UTILISATEUR_PAR_PSEUDO = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS WHERE pseudo=?;";

	@Override
	public void creerUtilisateur(Utilisateur utilisateur) throws SQLException, BusinessException {
		if (utilisateur == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.OBJET_NULL_AJOUT_UTILISATEUR);
			throw businessException;
		}
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(AJOUTER, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2, utilisateur.getNom());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5, utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getRue());
			pstmt.setString(7, utilisateur.getCodePostal());
			pstmt.setString(8, utilisateur.getVille());
			pstmt.setString(9, utilisateur.getMotDePasse());
			pstmt.setInt(10, utilisateur.getCredit());
			pstmt.setBoolean(11, utilisateur.isAdministrateur());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next())
			{
				utilisateur.setNoUtilisateur(rs.getInt(1));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.AUTRE_ERREUR_AJOUT_UTILISATEUR);
			throw businessException;
		}
	}

	@Override
	public void modifierUtilisateur(Utilisateur utilisateur) throws BusinessException {
		if (utilisateur == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.OBJET_NULL_MODIF_UTILISATEUR);
			throw businessException;
		}
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(MODIFIER);
			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2, utilisateur.getNom());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5, utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getRue());
			pstmt.setString(7, utilisateur.getCodePostal());
			pstmt.setString(8, utilisateur.getVille());
			pstmt.setString(9, utilisateur.getMotDePasse());
			pstmt.setInt(10, utilisateur.getCredit());
			pstmt.setBoolean(11, utilisateur.isAdministrateur());
			pstmt.setInt(12, utilisateur.getNoUtilisateur());
			pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.AUTRE_ERREUR_MODIF_UTILISATEUR);
			throw businessException;
		}
	}

	@Override
	public void supprimerUtilisateur(Utilisateur utilisateur) throws BusinessException {
		if (utilisateur == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.OBJET_NULL_SUPPRESSION_UTILISATEUR);
			throw businessException;
		}
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SUPPRIMER);
			pstmt.setInt(1, utilisateur.getNoUtilisateur());
			pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.AUTRE_ERREUR_SUPPRESSION_UTILISATEUR);
			throw businessException;
		}
	}

	@Override
	public Utilisateur selectionnerParId(Utilisateur utilisateur) throws BusinessException {
		Utilisateur unUtilisateur = null; 
		if (utilisateur == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.OBJET_NULL_AFFICHER_UTILISATEUR_PAR_ID);
			throw businessException;
		}
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(AFFICHER_UTILISATEUR_PAR_ID);
			pstmt.setInt(1, utilisateur.getNoUtilisateur());
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				unUtilisateur = new Utilisateur();
				unUtilisateur.setPseudo(rs.getString("pseudo"));
				unUtilisateur.setNom(rs.getString("nom"));
				unUtilisateur.setPrenom(rs.getString("prenom"));
				unUtilisateur.setEmail(rs.getString("email"));
				unUtilisateur.setTelephone(rs.getString("telephone"));
				unUtilisateur.setRue(rs.getString("rue"));
				unUtilisateur.setCodePostal(rs.getString("code_postal"));
				unUtilisateur.setVille(rs.getString("ville"));
				unUtilisateur.setMotDePasse(rs.getString("mot_de_passe"));
				unUtilisateur.setCredit(rs.getInt("credit"));
				unUtilisateur.setAdministrateur(rs.getBoolean("administrateur"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.AUTRE_ERREUR_AFFICHER_UTILISATEUR_PAR_ID);
			throw businessException;
		}
		
		return unUtilisateur;
	}

	@Override
	public List<Utilisateur> lister() throws BusinessException {
		Utilisateur utilisateur = null;
		List<Utilisateur> utilisateurs = new ArrayList<>();;
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			Statement stmt = cnx.createStatement();
			ResultSet rs=stmt.executeQuery(AFFICHER_TOUS_LES_UTILISATEURS);
			
			while (rs.next()) {
				utilisateur = new Utilisateur();
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setTelephone(rs.getString("telephone"));
				utilisateur.setRue(rs.getString("rue"));
				utilisateur.setCodePostal(rs.getString("code_postal"));
				utilisateur.setVille(rs.getString("ville"));
				utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
				utilisateur.setCredit(rs.getInt("credit"));
				utilisateur.setAdministrateur(rs.getBoolean("administrateur"));
				
				utilisateurs.add(utilisateur);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.AUTRE_ERREUR_AFFICHER_TOUS_LES_UTILISATEURS);
			throw businessException;
		}
		
		return utilisateurs;
	}

	@Override
	public Utilisateur selectionnerParPseudo(Utilisateur utilisateur) throws BusinessException {
		Utilisateur unUtilisateur = null;
		if (utilisateur == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.OBJET_NULL_AFFICHER_UTILISATEUR_PAR_PSEUDO);
			throw businessException;
		}
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(AFFICHER_UTILISATEUR_PAR_PSEUDO);
			pstmt.setString(1, utilisateur.getPseudo());
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				unUtilisateur = new Utilisateur();
				unUtilisateur.setPseudo(rs.getString("pseudo"));
				unUtilisateur.setNom(rs.getString("nom"));
				unUtilisateur.setPrenom(rs.getString("prenom"));
				unUtilisateur.setEmail(rs.getString("email"));
				unUtilisateur.setTelephone(rs.getString("telephone"));
				unUtilisateur.setRue(rs.getString("rue"));
				unUtilisateur.setCodePostal(rs.getString("code_postal"));
				unUtilisateur.setVille(rs.getString("ville"));
				unUtilisateur.setMotDePasse(rs.getString("mot_de_passe"));
				unUtilisateur.setCredit(rs.getInt("credit"));
				unUtilisateur.setAdministrateur(rs.getBoolean("administrateur"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.AUTRE_ERREUR_AFFICHER_UTILISATEUR_PAR_PSEUDO);
			throw businessException;
		}
		return unUtilisateur;
	}
}
