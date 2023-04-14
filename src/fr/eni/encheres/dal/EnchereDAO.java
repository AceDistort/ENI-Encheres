package fr.eni.encheres.dal;

import fr.eni.encheres.bo.BusinessException;
import fr.eni.encheres.bo.Enchere;

/**
 * Interface pour les méthodes de la DAO Enchere
 */
public interface EnchereDAO {
	
	/**
	 * Méthode pour créer une nouvelle enchère
	 * @param enchere
	 * @throws BusinessException
	 */
	public void creerEnchere(Enchere enchere) throws BusinessException;
	
	/**
	 * Méthode pour modifier une enchère par ID
	 * @param enchere
	 * @throws BusinessException
	 */
	public void modifierEnchere(Enchere enchere) throws BusinessException;
	
	/**
	 * Méthode pour supprimer une enchère par ID
	 * @param enchere
	 * @throws BusinessException
	 */
	public void supprimerEnchere(Enchere enchere) throws BusinessException;
	
	/**
	 * Méthode pour afficher une enchère par ID utilisateur et numéro d'article
	 * @param enchere
	 * @return Enchere
	 * @throws BusinessException
	 */
	public Enchere afficherParUtilEtArt(Enchere enchere) throws BusinessException;
	
	/**
	 * Méthode pour enchérir
	 * @param enchere
	 * @throws BusinessException
	 */
	public void encherir(Enchere enchere) throws BusinessException;
}
