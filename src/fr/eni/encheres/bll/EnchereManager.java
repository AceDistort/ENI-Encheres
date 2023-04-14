package fr.eni.encheres.bll;

import fr.eni.encheres.bo.BusinessException;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.EnchereDAO;

/**
 * Classe en charge de gérer les enchères
 */
public class EnchereManager {
	//Attributs d'instances
	private static EnchereManager enchereManager;
	private EnchereDAO enchereDAO;
	
	//Constructeur
	public EnchereManager() {
		enchereDAO = DAOFactory.getEnchereDAO();
	}
	
	//Getter
	public static EnchereManager getEnchereManager() throws BusinessException {
		if (enchereManager==null) {
			enchereManager = new EnchereManager();
		}
		return enchereManager;
	}
	
	/**
	 * Méthode en charge de créer une nouvelle enchère
	 * @param enchere
	 * @throws BusinessException
	 */
	public void creer (Enchere enchere) throws BusinessException {
		if (enchere == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatBLL.OBJET_NULL_AJOUT_ENCHERE);
		}
		
		try {
			enchereDAO.creerEnchere(enchere);
			
		} catch(Exception e) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatBLL.AUTRE_ERREUR_AJOUT_ENCHERE);
			throw businessException;
		}	
	}
	
	/**
	 * Méthode en charge d'enchérir sur une enchère
	 * @param enchere
	 * @throws BusinessException
	 */
	public void encherir(Enchere enchere) throws BusinessException {
		if (enchere == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatBLL.OBJET_NULL_ENCHERIR_ENCHERE);
			throw businessException;
		}
		
		try {
			enchereDAO.encherir(enchere);
			
		} catch(Exception e) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatBLL.AUTRE_ERREUR_ENCHERIR_ENCHERE);
			throw businessException;
		}	
	}
	
	/**
	 * Méthode en charge de modifier une enchère par son ID
	 * @param enchere
	 * @return Enchere
	 * @throws BusinessException
	 */
	public void modifier (Enchere enchere) throws BusinessException {
		if (enchere == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatBLL.OBJET_NULL_MODIF_ENCHERE);
		}
		
		try {
			enchereDAO.modifierEnchere(enchere);
		} catch(Exception e) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatBLL.AUTRE_ERREUR_MODIF_ENCHERE);
			throw businessException;
		}	
	}
	
	/**
	 * Méthode en charge de supprimer une enchère par son ID
	 * @param enchere
	 * @return Enchere
	 * @throws BusinessException
	 */
	public void supprimer (Enchere enchere) throws BusinessException {
		if (enchere == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatBLL.OBJET_NULL_SUPP_ENCHERE);
		}
		
		try {
			enchereDAO.supprimerEnchere(enchere);
		} catch(Exception e) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatBLL.AUTRE_ERREUR_SUPP_ENCHERE);
			throw businessException;
		}	
	}
	
	/**
	 * Méthode en charge d'afficher une enchère par son ID
	 * @param enchere
	 * @return Enchere
	 * @throws BusinessException
	 */
	public Enchere afficherEnchere(Enchere enchere) throws BusinessException {
		BusinessException businessException = new BusinessException();
		if(enchere == null) {
			businessException.ajouterErreur(CodesResultatBLL.OBJET_NULL_AFFICHER_ENCHERE_PAR_IDS);
			throw businessException;
		}
		
		try {
			return enchereDAO.afficherParUtilEtArt(enchere);
		} catch(Exception e) {
			e.printStackTrace();
			businessException.ajouterErreur(CodesResultatBLL.AUTRE_ERREUR_AFFICHER_ENCHERE_PAR_IDS);
			throw businessException;
		}
	}
}
