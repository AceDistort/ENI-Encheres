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
	
	
}
