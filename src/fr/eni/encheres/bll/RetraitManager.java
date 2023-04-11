package fr.eni.encheres.bll;

import fr.eni.encheres.bo.BusinessException;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.RetraitDAO;

public class RetraitManager {
	private static RetraitManager retraitManager;
	private RetraitDAO retraitDAO;
	
	private RetraitManager() {
		retraitDAO = DAOFactory.getRetraitDAO();
	}
	
	public static RetraitManager getRetraitManager() {
		if(retraitManager == null) {
			retraitManager = new RetraitManager();
		}
		return retraitManager;
	}
	
	public void creer(Retrait retrait) throws BusinessException {
		retraitDAO.creer(retrait);
	}
}
