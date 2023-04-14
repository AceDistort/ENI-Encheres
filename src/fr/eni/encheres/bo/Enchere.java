package fr.eni.encheres.bo;

import java.io.Serializable;
import java.sql.Date;

/**
 * Classe en charge de gérer les enchères
 */
public class Enchere implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//Attributs d'instance
	private Date dateEnchere;
	private int montantEnchere;
	private Utilisateur utilisateur;
	private ArticleVendu article;
	
	//Constructeurs
	public Enchere() {
	}
	
	/**
	 * Constructeur pour la création d'une enchère
	 * @param utilisateur
	 * @param article
	 * @param dateEnchere
	 * @param montantEnchere
	 */
	public Enchere(Utilisateur utilisateur, ArticleVendu article, Date dateEnchere, int montantEnchere) {
		setDateEnchere(dateEnchere);
		setMontantEnchere(montantEnchere);
		setUtilisateur(utilisateur);
		setArticle(article);
	}

	//Getter et Setter
	public Date getDateEnchere() {
		return dateEnchere;
	}
	public void setDateEnchere(Date dateEnchere) {
		this.dateEnchere = dateEnchere;
	}
	public int getMontantEnchere() {
		return montantEnchere;
	}
	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public ArticleVendu getArticle() {
		return article;
	}
	public void setArticle(ArticleVendu article) {
		this.article = article;
	}
}
