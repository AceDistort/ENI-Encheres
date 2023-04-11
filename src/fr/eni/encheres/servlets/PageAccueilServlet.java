package fr.eni.encheres.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.ArticleVenduManager;
import fr.eni.encheres.bll.CategoriesManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.BusinessException;
import fr.eni.encheres.bo.Categorie;

/**
 * Servlet implementation class ServletPageAccueil
 */
@WebServlet("/encheres")
public class PageAccueilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PageAccueilServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Categorie> categories = CategoriesManager.getCategorieManager().lister();
			request.setAttribute("categories", categories);
			
			List<ArticleVendu> articles = ArticleVenduManager.getCategorieManager().listerVentesDeconnecte();
			List<ArticleVendu> articlesFiltres = new ArrayList<ArticleVendu>(articles);

			for(ArticleVendu article: articles) {
				if(request.getParameter("texte") != null) {
					System.out.println(article.getNomArticle());
					System.out.println(request.getParameter("texte"));
					System.out.println(!article.getNomArticle().contains(request.getParameter("texte")));
					if(!article.getNomArticle().contains(request.getParameter("texte"))) {
						articlesFiltres.remove(article);
					}
				}
				if(request.getParameter("categorie") != null && request.getParameter("categorie") != "") {
					if(article.getCategorie().getNoCategorie() != Integer.parseInt(request.getParameter("categorie"))) {
						articlesFiltres.remove(article);
					}
				}
			}
			
			request.setAttribute("articles", articlesFiltres);
		
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("WEB-INF/pageAccueil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> listeParametres = new ArrayList<String>();
		
		if(!"".equals(request.getParameter("texte"))) {
			listeParametres.add(String.format("texte=%s", request.getParameter("texte")));
		}
		if(request.getParameter("categorie") != null && request.getParameter("categorie") != "") {
			listeParametres.add(String.format("categorie=%s", request.getParameter("categorie")));
		}
		String parametres = String.join("&", listeParametres);
		if(!parametres.equals("")) {
			parametres = String.format("?%s", parametres);
		}
		((HttpServletResponse) response).sendRedirect(String.format("encheres%s", parametres));
	}

}
