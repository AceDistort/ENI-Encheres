package fr.eni.encheres.bll;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.BusinessException;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.RetraitDAO;

/**
 * Classe en charge de gérer les retraits
 */
public class RetraitManager {
	private static RetraitManager retraitManager;
	private RetraitDAO retraitDAO;
	
	/**
	 * Constructeur privé
	 */
	private RetraitManager() {
		retraitDAO = DAOFactory.getRetraitDAO();
	}
	
	/**
	 * Méthode en charge de récupérer l'instance de la classe
	 * @return RetraitManager
	 */
	public static RetraitManager getRetraitManager() {
		if(retraitManager == null) {
			retraitManager = new RetraitManager();
		}
		return retraitManager;
	}
	
	/**
	 * Méthode en charge de créer un nouveau retrait
	 * @param retrait
	 * @throws BusinessException
	 */
	public void creer(Retrait retrait) throws BusinessException {
		retraitDAO.creer(retrait);
	}
	
	/**
	 * Méthode en charge d'afficher un retrait
	 * @param retrait
	 * @throws BusinessException
	 */
	public Retrait afficher(Retrait retrait) throws BusinessException {
		if(retrait == null) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatBLL.OBJET_NULL_AFFICHER_RETRAIT_PAR_ID);
			throw be;
		}
		
		try {
			return retraitDAO.selectionnerParNoArticle(retrait);
		} catch (BusinessException e) {
			e.ajouterErreur(CodesResultatBLL.AUTRE_ERREUR_AFFICHER_RETRAIT_PAR_ID);
			throw e;
		}	
	}
}
