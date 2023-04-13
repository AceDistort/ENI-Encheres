package fr.eni.encheres.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.ArticleVenduManager;
import fr.eni.encheres.bll.CategoriesManager;
import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bll.RetraitManager;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.BusinessException;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class JeanJacquesServlet
 */
@WebServlet("/vente")
public class AfficherVenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AfficherVenteServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur sessionUtilisateur = (Utilisateur) request.getSession().getAttribute("sessionUtilisateur");
		if(sessionUtilisateur == null) {
			((HttpServletResponse) response).sendRedirect("encheres");
		}
		else {
			ArticleVendu article = new ArticleVendu();
			article.setNoArticle(Integer.parseInt(request.getParameter("id")));
			Utilisateur utilisateur = new Utilisateur();
			utilisateur.setNoUtilisateur(article.getVend().getNoUtilisateur());
			Enchere enchere = new Enchere();
			enchere.setUtilisateur(utilisateur);
			enchere.setArticle(article);
			Retrait retrait = new Retrait();
			retrait.setConcerne(article);
		
			try {
				Utilisateur utilisateurProfil = UtilisateurManager.getUtilisateurManager().afficher(utilisateur);
				request.setAttribute("utilisateurProfil", utilisateurProfil);
				
				ArticleVendu articleProfil = ArticleVenduManager.getArticleVenduManager().afficherArticle(article);
				request.setAttribute("articleProfil", articleProfil);
				
				Retrait retraitProfil = RetraitManager.getRetraitManager().afficher(retrait);
				request.setAttribute("retraitProfil", retraitProfil);
				
				Categorie categorieProfil = CategoriesManager.getCategorieManager().afficherCategorie(articleProfil.getCategorie());
				request.setAttribute("categorieProfil", categorieProfil);
				
				Enchere enchereProfil = EnchereManager.getEnchereManager().afficherEnchere(enchere);
				request.setAttribute("EnchereProfil", enchereProfil);
				
				request.getRequestDispatcher("/WEB-INF/afficherVente.jsp").forward(request, response);
				
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur sessionUtilisateur = (Utilisateur) request.getSession().getAttribute("sessionUtilisateur");
		if(sessionUtilisateur == null) {
			((HttpServletResponse) response).sendRedirect("encheres");
		}
		else {
			
		}
	}

}
