package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.BusinessException;
import fr.eni.encheres.bo.Enchere;

public interface EnchereDAO {
	
	//Création d'une enchère
	public void creerEnchere(Enchere enchere) throws BusinessException;
	
	//Modification d'une enchère
	public void modifierEnchere(Enchere enchere) throws BusinessException;
	
	//Suppression d'une enchère
	public void supprimerEnchere(Enchere enchere) throws BusinessException;
	
}
