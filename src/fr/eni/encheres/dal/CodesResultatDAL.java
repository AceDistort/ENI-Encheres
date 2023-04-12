package fr.eni.encheres.dal;

/**
 * Les codes disponibles sont entre 10000 et 19999
 */
public abstract class CodesResultatDAL {
	
	/**
	 * Echec général quand tentative d'ajouter un objet null
	 */
	public static final int OBJET_NULL_AJOUT_UTILISATEUR=10000;
	
	/**
	 * Echec général quand erreur non gérée à l'insertion 
	 */
	public static final int AUTRE_ERREUR_AJOUT_UTILISATEUR=10001;
	
	/**
	 * Echec général quand tentative de modification un objet null
	 */
	public static final int OBJET_NULL_MODIF_UTILISATEUR=10002;
	
	/**
	 * Echec général quand erreur non gérée à la modification
	 */
	public static final int AUTRE_ERREUR_MODIF_UTILISATEUR=10003;
	
	/**
	 * Echec général quand tentative de suppression un object null
	 */
	public static final int OBJET_NULL_SUPPRESSION_UTILISATEUR=10004;
	/**
	 * Echec général quand erreur non gérée à la suppression
	 */
	public static final int AUTRE_ERREUR_SUPPRESSION_UTILISATEUR=10005;
	
	/**
	 * Echec général quand tentative affichage par ID un objet null
	 */
	public static final int OBJET_NULL_AFFICHER_UTILISATEUR_PAR_ID=10006;
	/**
	 * Echec général quand erreur non gérée à affichage par ID
	 */
	public static final int AUTRE_ERREUR_AFFICHER_UTILISATEUR_PAR_ID=10007;
	/**
	 * Echec général quand erreur non gérée à affichage tous les utilisateurs
	 */
	public static final int AUTRE_ERREUR_AFFICHER_TOUS_LES_UTILISATEURS=10008;
	/**
	 * Echec général quand tentative affichage un objet null par pseudo
	 */
	public static final int OBJET_NULL_AFFICHER_UTILISATEUR_PAR_PSEUDO=10009;
	/**
	 * Echec général quand erreur non gérée à affichage par pseudo
	 */
	public static final int AUTRE_ERREUR_AFFICHER_UTILISATEUR_PAR_PSEUDO=10010;
	
	/**
	 * Echec méthode lister cat�gories
	 */
	public static final int ERREUR_LISTER_CATEGORIES=10011;
	
	/**
	 * Echec méthode creer retrait
	 */
	public static final int ERREUR_CREER_RETRAIT=10012;
	
	/**
	 * Echec général quand tentative d'ajouter un objet null
	 */
	public static final int OBJET_NULL_AJOUT_ARTICLE=10013;
	
	/**
	 * Echec général quand erreur non gérée à l'insertion 
	 */
	public static final int AUTRE_ERREUR_AJOUT_ARTICLE=10014;
	
	/**
	 * Autre erreur quand affichage de tous les utilisateurs
	 */
	public static final int AUTRE_ERREUR_LISTER_ARTICLE=10015;
	
	/**
	 * Echec général quand tentative d'ajouter un objet null
	 * + Autre erreur non gérée à l'ajout d'une enchere
	 */
	public static final int OBJET_NULL_AJOUT_ENCHERE = 10016;
	public static final int AUTRE_ERREUR_AJOUT_ENCHERE = 10017;
	
	/**
	 * Echec général quand tentative de modif d'un objet null
	 * + Autre erreur non gérée à la modif d'une enchere
	 */
	public static final int OBJET_NULL_MODIF_ENCHERE = 10018;
	public static final int AUTRE_ERREUR_MODIF_ENCHERE = 10019;
	
	/**
	 * Echec général quand tentative de suppression d'un objet null
	 * + Autre erreur non gérée à la suppression d'une enchere
	 */
	public static final int OBJET_NULL_SUPP_ENCHERE = 10020;
	public static final int AUTRE_ERREUR_SUPP_ENCHERE = 10021;
	
	
	
}
