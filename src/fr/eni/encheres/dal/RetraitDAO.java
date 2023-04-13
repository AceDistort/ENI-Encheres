package fr.eni.encheres.dal;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.BusinessException;
import fr.eni.encheres.bo.Retrait;

public interface RetraitDAO {
	
	public void creer(Retrait retrait) throws BusinessException;
	
	public Retrait selectionnerParNoArticle(ArticleVendu article) throws BusinessException;
}
