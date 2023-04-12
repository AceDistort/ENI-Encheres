package fr.eni.encheres.bll;

/**
 * Les codes disponibles sont entre 20000 et 29999
 */
public abstract class CodesResultatBLL {
	
	/**
	 * Echec général quand tentative d'ajouter un objet null
	 */
	public static final int OBJET_NULL_AJOUT_UTILISATEUR=20000;
	
	/**
	 * Echec général quand erreur non gérée à l'insertion 
	 */
	public static final int AUTRE_ERREUR_AJOUT_UTILISATEUR=20001;
	
	/**
	 * Echec général quand tentative de modification un objet null
	 */
	public static final int OBJET_NULL_MODIF_UTILISATEUR=20002;
	
	/**
	 * Echec général quand erreur non gérée à la modification
	 */
	public static final int AUTRE_ERREUR_MODIF_UTILISATEUR=20003;
	
	/**
	 * Echec général quand tentative de suppression un object null
	 */
	public static final int OBJET_NULL_SUPPRESSION_UTILISATEUR=20004;
	/**
	 * Echec général quand erreur non gérée à la suppression
	 */
	public static final int AUTRE_ERREUR_SUPPRESSION_UTILISATEUR=20005;
	
	/**
	 * Erreur règle de gestion pseudo en alphanumérique obligatoirement
	 */
	public static final int REGLE_PSEUDO_UTILISATEUR=20006;
	
	/**
	 * Erreur règle de gestion mot de passe crypté
	 */
	public static final int REGLE_MOTDEPASSE_UTILISATEUR=20007;
	
	/**
	 * Erreur règle de gestion mot de passe crypté
	 */
	public static final int ERREUR_TAILLE_LISTE_MODIF_UTILISATEUR=20008;
	
	/**
	 * Erreur règle de gestion mot de passe crypté
	 */
	public static final int ERREUR_EQUALS_MODIF_UTILISATEUR=20009;
	
	/**
	 * Erreur methode SHA
	 */
	public static final int NOSUCHALGORITHM = 20010;
	
	/**
	 * Erreur si le mot de passe est incorrect lors de la connection
	 */
	public static final int MOT_DE_PASSE_UTILISATEUR_INCORRECT = 20011;
	
	/**
	 * Echec général quand tentative affichage par ID un objet null
	 */
	public static final int OBJET_NULL_AFFICHER_UTILISATEUR_PAR_ID=20012;
	/**
	 * Echec général quand erreur non gérée à affichage par ID
	 */
	public static final int AUTRE_ERREUR_AFFICHER_UTILISATEUR_PAR_ID=20013;
	/**
	 * Echec général quand erreur non gérée à affichage tous les utilisateurs
	 */
	public static final int AUTRE_ERREUR_AFFICHER_TOUS_LES_UTILISATEURS=20014;
	/**
	 * Echec général quand tentative affichage un objet null par pseudo
	 */
	public static final int OBJET_NULL_AFFICHER_UTILISATEUR_PAR_PSEUDO=20015;
	/**
	 * Echec général quand erreur non gérée à affichage par pseudo
	 */
	public static final int AUTRE_ERREUR_AFFICHER_UTILISATEUR_PAR_PSEUDO=20016;
	/**
	 * Echec afficher selectionnerParId
	 */
	public static final int ERREUR_AFFICHER_SELECTIONNER_PAR_ID=20017;
	/**
	 * Erreur si l'utilisateur renseigne une adresse mail non valide
	 */
	public static final int EMAIL_UTILISATEUR_NON_VALIDE = 20018;
	
	/**
	 * Erreur si l'utilisateur renseigne un mot de passe non conforme aux règles imposées pour celui-ci.
	 */
	public static final int MOT_DE_PASSE_UTILISATEUR_NON_VALIDE = 20019;
	
	/**
	 * Erreur si l'utilisateur renseigne un mot de passe non valide
	 */
	public static final int CODE_POSTAL_UTILISATEUR_NON_VALIDE = 20020;
	
	/**
	 * Erreur si pseudo utilisateur est null
	 */
	public static final int PSEUDO_UTILISATEUR_INEXISTANT = 20021;
	
	/**
	 * Erreur si nom utilisateur est null
	 */
	public static final int NOM_UTILISATEUR_INEXISTANT = 20022;
	
	/**
	 * Erreur si prenom utilisateur est null
	 */
	public static final int PRENOM_UTILISATEUR_INEXISTANT = 20023;
	
	/**
	 * Erreur si rue utilisateur est null
	 */
	public static final int RUE_UTILISATEUR_INEXISTANT = 20024;
	
	/**
	 * Erreur si ville utilisateur est null
	 */
	public static final int VILLE_UTILISATEUR_INEXISTANT = 20025;
	
	/**
	 * Erreur si credit utilisateur est null
	 */
	public static final int CREDIT_UTILISATEUR_INCORRECT = 20026;
	
	/**
	 * Erreur si admin utilisateur est null
	 */
	public static final int STATUT_ADMIN_UTILISATEUR_INEXISTANT = 20027;
	
	/**
	 * Echec général quand tentative d'ajouter un objet null
	 * + Autre erreur non gérée lors de l'ajout d'un objet
	 */
	public static final int OBJET_NULL_AJOUT_ENCHERE = 20028;
	public static final int AUTRE_ERREUR_AJOUT_ENCHERE = 20029;
	
	/**
	 * Echec général quand tentative de modif d'un objet null
	 * + Autre erreur non gérée lors de la modif d'un objet
	 */
	public static final int OBJET_NULL_MODIF_ENCHERE = 20030;
	public static final int AUTRE_ERREUR_MODIF_ENCHERE = 20031;
	
	/**
	 * Echec général quand tentative de suppression d'un objet null
	 * + Autre erreur non gérée lors de la suppression d'un objet
	 */
	public static final int OBJET_NULL_SUPP_ENCHERE = 20032;
	public static final int AUTRE_ERREUR_SUPP_ENCHERE = 20033;
}
