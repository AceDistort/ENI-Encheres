package fr.eni.encheres.bll;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

import fr.eni.encheres.bo.BusinessException;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.util.HashMotDePasse;
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
			utilisateur.setMotDePasse(HashMotDePasse.generateStorngPasswordHash(utilisateur.getMotDePasse()));
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
	
	public void modifier(Utilisateur utilisateur) throws BusinessException {
		//controle des champs passes en parametre
		BusinessException businessException = new BusinessException();
		if (utilisateur==null) {
			businessException.ajouterErreur(CodesResultatBLL.OBJET_NULL_MODIF_UTILISATEUR);
			throw businessException;
		}
		
		try
		{	
			//validation des donnees
			controlerUtilisateur(utilisateur);
			
			//modification en BDD
			utilisateurDAO.modifierUtilisateur(utilisateur);
			
			//modifier les infos dans la liste
			utilisateur.setPseudo(utilisateur.getPseudo());
			utilisateur.setNom(utilisateur.getNom());
			utilisateur.setPrenom(utilisateur.getPrenom());
			utilisateur.setEmail(utilisateur.getEmail());
			utilisateur.setTelephone(utilisateur.getTelephone());
			utilisateur.setRue(utilisateur.getRue());
			utilisateur.setCodePostal(utilisateur.getCodePostal());
			utilisateur.setVille(utilisateur.getVille());
			utilisateur.setMotDePasse(utilisateur.getMotDePasse());
			
			//TODO
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
	
	public Utilisateur afficher(Utilisateur utilisateur) throws BusinessException {
		if(utilisateur == null) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatBLL.OBJET_NULL_AFFICHER_UTILISATEUR_PAR_ID);
			throw be;
		}
		try {
			return utilisateurDAO.selectionnerParId(utilisateur);
		} catch (BusinessException e) {
			e.ajouterErreur(CodesResultatBLL.ERREUR_AFFICHER_SELECTIONNER_PAR_ID);
			throw e;
		}	
	}
	
	/**
	 * M�thode pour se connecter � l'application. Ajoute les donn�es de l'utilisateur au param�tre d'entr�e.
	 * @param utilisateur
	 * @return Vrai si la connection est faite
	 * @throws BusinessException
	 */
	public Utilisateur seConnecter(Utilisateur utilisateur) throws BusinessException {
		
		Utilisateur utilisateurBDD = new Utilisateur();
		utilisateurBDD = utilisateurDAO.selectionnerParPseudo(utilisateur);
		
		if(utilisateurBDD == null) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatBLL.OBJET_NULL_AFFICHER_UTILISATEUR_PAR_ID);
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
	    return utilisateur;
		
	}
	
	public void controlerUtilisateur(Utilisateur utilisateur) {
		String RegexEmail = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
		        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
		String RegexMotDePasse = "^(?:(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])|" +
	            "(?=.*\\d)(?=.*[^A-Za-z0-9])(?=.*[a-z])|" +
	            "(?=.*[^A-Za-z0-9])(?=.*[A-Z])(?=.*[a-z])|" +
	            "(?=.*\\d)(?=.*[A-Z])(?=.*[^A-Za-z0-9]))(?!.*(.)\\1{2,})" +
	            "[A-Za-z0-9!~<>,;:_=?*+#.\"&§%°()\\|\\[\\]\\-\\$\\^\\@\\/]" +
	            "{12,32}$";
		String RegexCodePostal = "^[0-9]{5}(?:-[0-9]{4})?$";
		BusinessException businessException = new BusinessException();
		
		if (!patternMatches(utilisateur.getEmail(), RegexEmail)) {
			businessException.ajouterErreur(CodesResultatBLL.EMAIL_UTILISATEUR_NON_VALIDE);
		}
		
		if(!patternMatches(utilisateur.getMotDePasse(), RegexMotDePasse)){
			businessException.ajouterErreur(CodesResultatBLL.MOT_DE_PASSE_UTILISATEUR_NON_VALIDE);
		}
		
		if (!patternMatches(utilisateur.getCodePostal(), RegexCodePostal)) {
			businessException.ajouterErreur(CodesResultatBLL.CODE_POSTAL_UTILISATEUR_NON_VALIDE);
		}
		
		if (utilisateur.getPseudo() == null) {
			businessException.ajouterErreur(CodesResultatBLL.PSEUDO_UTILISATEUR_INEXISTANT);
		}
		if (utilisateur.getNom() == null) {
			businessException.ajouterErreur(CodesResultatBLL.NOM_UTILISATEUR_INEXISTANT);
		}
		if (utilisateur.getPrenom() == null) {
			businessException.ajouterErreur(CodesResultatBLL.PRENOM_UTILISATEUR_INEXISTANT);
		}
		if (utilisateur.getRue() == null) {
			businessException.ajouterErreur(CodesResultatBLL.RUE_UTILISATEUR_INEXISTANT);
		}
		if (utilisateur.getVille() == null) {
			businessException.ajouterErreur(CodesResultatBLL.VILLE_UTILISATEUR_INEXISTANT);
		}
		if (utilisateur.getCredit() == 0) {
			businessException.ajouterErreur(CodesResultatBLL.CREDIT_UTILISATEUR_INCORRECT);
		}
//		if (utilisateur.isAdministrateur() == false) {
//			businessException.ajouterErreur(CodesResultatBLL.STATUT_ADMIN_UTILISATEUR_INEXISTANT);
//		}
	}
	
	public static boolean patternMatches(String chaineDeCaracteres, String pattern) {
	    return Pattern.compile(pattern)
	      .matcher(chaineDeCaracteres)
	      .matches();
	}
}
