package fr.eni.encheres.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.encheres.bo.BusinessException;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Interface pour la DAO Utilisateur
 *
 */
public interface UtilisateurDAO {
	
	/**
	 * Méthode pour créer un nouvel utilisateur
	 * @param utilisateur
	 * @throws SQLException
	 * @throws BusinessException
	 */ 
	public void creerUtilisateur(Utilisateur utilisateur) throws SQLException, BusinessException;
	
	/**
	 * Méthode pour modifier un utilisateur
	 * @param utilisateur
	 * @throws BusinessException
	 */
	public void modifierUtilisateur(Utilisateur utilisateur) throws BusinessException;
	
	/**
	 * Méthode pour supprimer un utilisateur
	 * @param utilisateur
	 * @throws BusinessException
	 */
	public void supprimerUtilisateur(Utilisateur utilisateur) throws BusinessException;
	
	/**
	 * Méthode pour afficher un utilisateur par ID
	 * @param utilisateur
	 * @return Utilisateur
	 * @throws BusinessException
	 */
	public Utilisateur selectionnerParId(Utilisateur utilisateur) throws BusinessException;
	
	/**
	 * Méthode pour lister les utilisateurs
	 * @return List<Utilisateur>
	 * @throws BusinessException
	 */
	public List<Utilisateur> lister() throws BusinessException;
	
	/**
	 * Méthode pour afficher un utilisateur par pseudo
	 * @param utilisateur
	 * @return Utilisateur
	 * @throws BusinessException
	 */
	public Utilisateur selectionnerParPseudo(Utilisateur utilisateur) throws BusinessException; 
}
