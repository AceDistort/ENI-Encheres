package fr.eni.encheres.bll;

import fr.eni.encheres.bo.BusinessException;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.dal.EnchereDAO;

public class EnchereManager {
	//Attributs d'instances
	private static EnchereManager enchereManager;
	private EnchereDAO enchereDAO;
	
	//Getter
	public static EnchereManager getEnchereManager() throws BusinessException {
		if (enchereManager==null) {
			enchereManager = new EnchereManager();
		}
		return enchereManager;
	}
	
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
}
