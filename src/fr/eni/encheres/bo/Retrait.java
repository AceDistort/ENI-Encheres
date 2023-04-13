package fr.eni.encheres.bo;

import java.io.Serializable;

public class Retrait implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String rue;
	private String codePostal;
	private String ville;
	private ArticleVendu concerne;

	public Retrait() {}
	
	public Retrait(String rue, String codePostal, String ville, ArticleVendu concerne) {
		this.setRue(rue);
		this.setCodePostal(codePostal);
		this.setVille(ville);
		this.setConcerne(concerne);
	}

	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public ArticleVendu getConcerne() {
		return concerne;
	}
	public void setConcerne(ArticleVendu concerne) {
		this.concerne = concerne;
	}
}
