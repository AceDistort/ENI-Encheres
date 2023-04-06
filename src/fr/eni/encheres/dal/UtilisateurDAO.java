package fr.eni.encheres.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.encheres.bo.BusinessException;
import fr.eni.encheres.bo.Utilisateur;

public interface UtilisateurDAO {
	
	//Créer un utilisateur 
	public void creerUtilisateur(Utilisateur utilisateur) throws SQLException, BusinessException;
	
	//Modifier un utilisateur
	public void modifierUtilisateur(Utilisateur utilisateur) throws BusinessException;
	
	//Supprimer un utilisateur
	public void supprimerUtilisateur(Utilisateur utilisateur) throws BusinessException;
	
	//Afficher un utilisateur par ID
	public Utilisateur selectionnerParId(Utilisateur utilisateur) throws BusinessException;
	
	//Lister tous les utilisateurs
	public List<Utilisateur> lister() throws BusinessException;
	
	//Afficher un utilisateur par pseudo
	public Utilisateur selectionnerParPseudo(Utilisateur utilisateur) throws BusinessException; 
}
