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
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.BusinessException;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;
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
			try {
				ArticleVendu article = new ArticleVendu();
				article.setNoArticle(Integer.parseInt(request.getParameter("id")));
				article = ArticleVenduManager.getArticleVenduManager().afficherArticle(article);
				
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setNoUtilisateur(article.getVend().getNoUtilisateur());
				utilisateur = UtilisateurManager.getUtilisateurManager().afficher(utilisateur);
				
				Categorie categorie = new Categorie();
				categorie.setNoCategorie(article.getCategorie().getNoCategorie());
				categorie = CategoriesManager.getCategorieManager().afficherCategorie(categorie);
		
				request.setAttribute("utilisateurProfil", utilisateur);
				request.setAttribute("articleProfil", article);
				request.setAttribute("categorieProfil", categorie);
				
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
			try {
				ArticleVendu article = new ArticleVendu();
				article.setNoArticle(Integer.parseInt(request.getParameter("noArticle")));
				article = ArticleVenduManager.getArticleVenduManager().afficherArticle(article);
				
				Enchere enchere = new Enchere();
				enchere.setArticle(article);
				enchere.setUtilisateur(sessionUtilisateur);
				enchere.setMontantEnchere(Integer.parseInt(request.getParameter("montantEnchere")));
				EnchereManager.getEnchereManager().encherir(enchere);
				
				((HttpServletResponse) response).sendRedirect("encheres");
			} catch (BusinessException e) {
				((HttpServletResponse) response).sendRedirect("encheres?id=" + request.getParameter("noArticle"));
			}
		}
	}

}
