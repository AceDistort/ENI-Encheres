package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Enchere;

public interface EnchereDAO {
	
	//Création d'une enchère
	public void creerEnchere(Enchere enchere);
	
	//Modification d'une enchère
	public void modifierEnchere(Enchere enchere);
	
	//Suppression d'une enchère
	public void supprimerEnchere(Enchere enchere);
	
	//Lister toutes les enchères
	public List<Enchere> listerEncheres();
	
}
