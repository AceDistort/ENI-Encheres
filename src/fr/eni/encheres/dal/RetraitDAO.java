package fr.eni.encheres.dal;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.BusinessException;
import fr.eni.encheres.bo.Retrait;

/**
 * Interface pour la DAO Retrait
 *
 */
public interface RetraitDAO {
	
	/**
	 * Méthode pour créer un nouveau retrait
	 * @param retrait
	 * @throws BusinessException
	 */
	public void creer(Retrait retrait) throws BusinessException;
	
	/**
	 * Méthode pour afficher un retrait par numéro d'article
	 * @param retrait
	 * @return Retrait
	 * @throws BusinessException
	 */
	public Retrait selectionnerParNoArticle(Retrait retrait) throws BusinessException;
}
