package fr.eni.encheres.bll;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.SQLException;
import java.util.List;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import fr.eni.encheres.bo.BusinessException;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UtilisateurDAO;
import fr.eni.encheres.util.HashMotDePasse;

public class UtilisateurManager {
	//Attributs d'instance
	private static UtilisateurManager utilisateurManager;
	private UtilisateurDAO utilisateurDAO;
	private List<Utilisateur> utilisateurs;
	
	//Constructeur
	private UtilisateurManager() throws BusinessException {
		utilisateurDAO = DAOFactory.getUtilisateurDAO();
		utilisateurs=utilisateurDAO.lister();
	}
	
	//Getter
	public static UtilisateurManager getUtilisateurManager() throws BusinessException {
		if (utilisateurManager==null) {
			utilisateurManager = new UtilisateurManager();
		}
		return utilisateurManager;
	}
	
	//Autres méthodes
	public void creer(Utilisateur utilisateur) throws SQLException, BusinessException {
		if (utilisateur == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatBLL.OBJET_NULL_AJOUT_UTILISATEUR);
		}
		
		try
		{
			controlerUtilisateur(utilisateur);
			utilisateurDAO.creerUtilisateur(utilisateur);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatBLL.AUTRE_ERREUR_AJOUT_UTILISATEUR);
			throw businessException;
		}
		
	}
	
	public void modifier(int index, Utilisateur utilisateur) throws BusinessException {
		//controle des champs passes en parametre
		Utilisateur utilisateurEnCours = null;
		BusinessException businessException = new BusinessException();
		if (utilisateur==null) {
			businessException.ajouterErreur(CodesResultatBLL.OBJET_NULL_MODIF_UTILISATEUR);
			throw businessException;
		}
		
		if (index<0 || index>utilisateurs.size()) {
			businessException.ajouterErreur(CodesResultatBLL.ERREUR_TAILLE_LISTE_MODIF_UTILISATEUR);
			throw businessException;
		}
		
		try
		{
			//recuperer l'utilisateur en cours
			utilisateurEnCours = utilisateurs.get(index);
			if (!utilisateur.equals(utilisateurEnCours)) {
				businessException.ajouterErreur(CodesResultatBLL.ERREUR_EQUALS_MODIF_UTILISATEUR);
				throw businessException;
			}
			
			//validation des donnees
			controlerUtilisateur(utilisateur);
			
			//modification en BDD
			utilisateurDAO.modifierUtilisateur(utilisateur);
			
			//modifier les infos dans la liste
			utilisateurEnCours.setPseudo(utilisateur.getPseudo());
			utilisateurEnCours.setNom(utilisateur.getNom());
			utilisateurEnCours.setPrenom(utilisateur.getPrenom());
			utilisateurEnCours.setEmail(utilisateur.getEmail());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			businessException.ajouterErreur(CodesResultatBLL.AUTRE_ERREUR_MODIF_UTILISATEUR);
			throw businessException;
		}
	}
	
	public void supprimer(Utilisateur utilisateur) throws BusinessException {
		BusinessException businessException = new BusinessException();
		if (utilisateur==null) {
			businessException.ajouterErreur(CodesResultatBLL.OBJET_NULL_SUPPRESSION_UTILISATEUR);
			throw businessException;
		}
		
		try
		{
			utilisateurDAO.supprimerUtilisateur(utilisateur);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			businessException.ajouterErreur(CodesResultatBLL.AUTRE_ERREUR_SUPPRESSION_UTILISATEUR);
			throw businessException;
		}
		
		
	}
	
	public void afficher(Utilisateur utilisateur) {
		
	}
	
	/**
	 * M�thode pour se connecter � l'application. Ajoute les donn�es de l'utilisateur au param�tre d'entr�e.
	 * @param utilisateur
	 * @return Vrai si la connection est faite
	 * @throws BusinessException
	 */
	public void seConnecter(Utilisateur utilisateur) throws BusinessException {
		
		Utilisateur utilisateurBDD = new Utilisateur();
		utilisateurBDD = utilisateurDAO.selectionnerParPseudo(utilisateur);
		
		if(utilisateurBDD == null) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatBLL.UTILISATEUR_INCORNNU);
			throw be;
		}
		
		try {
			if(!HashMotDePasse.validatePassword(utilisateur.getMotDePasse(), utilisateurBDD.getMotDePasse())) {
				BusinessException be = new BusinessException();
				be.ajouterErreur(CodesResultatBLL.MOT_DE_PASSE_UTILISATEUR_INCORRECT);
				throw be;
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    utilisateur = utilisateurBDD;
	    utilisateur.setMotDePasse(null);
		
	}
	
	public void controlerUtilisateur(Utilisateur utilisateur) {
//		boolean valide = true;
//		BusinessException businessException = new BusinessException();
//		
//		if (utilisateur.getNom().trim().isEmpty()) {
//			//businessException.ajouterErreur(CodesResultatBLL.);
//		}
//		
//		if (utilisateur.get) {
//			
//		}
	}
}
