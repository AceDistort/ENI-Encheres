package fr.eni.encheres.dal;

import fr.eni.encheres.bo.BusinessException;
import fr.eni.encheres.bo.Retrait;

public interface RetraitDAO {
	public void creer(Retrait retrait) throws BusinessException;
}
