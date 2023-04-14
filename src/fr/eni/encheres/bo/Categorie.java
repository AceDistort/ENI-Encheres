package fr.eni.encheres.bo;

import java.io.Serializable;

/**
 * Classe en charge de gérer les catégories
 */
public class Categorie implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int noCategorie;
	private String libelle;
	
	public Categorie() {}

	/**
	 * Constructeur pour la création d'une catégorie
	 * @param libelle
	 */
	public Categorie(String libelle) {
		this.setLibelle(libelle);
	}
	
	public int getNoCategorie() {
		return noCategorie;
	}
	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
}
