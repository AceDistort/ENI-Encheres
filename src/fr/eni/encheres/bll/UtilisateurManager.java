package fr.eni.encheres.bll;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.List;

import fr.eni.encheres.bo.BusinessException;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UtilisateurDAO;

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
	
	public void supprimer(Utilisateur utilisateur) {
		
	}
	
	public void afficher(Utilisateur utilisateur) {
		
	}
	
	/**
	 * M�thode pour se connecter � l'application. Ajoute les donn�es de l'utilisateur au param�tre d'entr�e.
	 * @param utilisateur
	 * @return Vrai si la connection est faite
	 */
	public void seConnecter(Utilisateur utilisateur) throws BusinessException {
		
		Utilisateur utilisateurBDD = null;
		utilisateurBDD = utilisateurDAO.selectionnerParPseudo(utilisateur);
		String salt = getSalt();
		utilisateur.setMotDePasse(get_SHA_256_SecurePassword(utilisateur.getMotDePasse(), salt));
	    
	    if(!utilisateurBDD.getMotDePasse().equals(utilisateur.getMotDePasse())) {
	    	BusinessException be = new BusinessException();
	    	be.ajouterErreur(CodesResultatBLL.MOT_DE_PASSE_UTILISATEUR_INCORRECT);
	    	throw be;
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
		
	}
	
	//GESTION DE LA SECURITE DES MOTS DE PASSE
	private static String getSalt() throws BusinessException {
		try {
			SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        	byte[] salt = new byte[16];
        	sr.nextBytes(salt);
            return salt.toString();
		} catch (NoSuchAlgorithmException e) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatBLL.NOSUCHALGORITHM);
			throw be;
		}
    }
	private static String get_SHA_256_SecurePassword(String passwordToHash,
            String salt) throws BusinessException {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            BusinessException be = new BusinessException();
            be.ajouterErreur(CodesResultatBLL.NOSUCHALGORITHM);
            throw be;
        }
        return generatedPassword;
    }
}
