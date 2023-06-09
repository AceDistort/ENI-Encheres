package fr.eni.encheres.servlets;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.ArticleVenduManager;
import fr.eni.encheres.bll.CategoriesManager;
import fr.eni.encheres.bll.RetraitManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.BusinessException;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.messages.LecteurMessage;

/**
 * Servlet implementation class NouvelleVenteServlet
 */
@WebServlet("/vendre")
public class NouvelleVenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NouvelleVenteServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Redirection vers page connexion si utilisateur non connect�
		if(request.getSession().getAttribute("sessionUtilisateur") == null) {
			((HttpServletResponse) response).sendRedirect("connexion");
		} else {
			try {
				List<Categorie> categories = CategoriesManager.getCategorieManager().lister();
				request.setAttribute("categories", categories);
			} catch (BusinessException e) {
			}
			request.getRequestDispatcher("WEB-INF/nouvelleVente.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// NOM ARTICLE
			if(request.getParameter("nomArticle") == null) {
			}
			String nomArticle = request.getParameter("nomArticle");
			
			// DESCRIPTION
			if(request.getParameter("description") == null) {
			}
			String description = request.getParameter("description");
			
			// CATEGORIE
			if(request.getParameter("categorie") == null) {
			}
			Categorie categorie = new Categorie();
			categorie.setNoCategorie(Integer.parseInt(request.getParameter("categorie")));
			
			// PRIX INITIAL
			if(request.getParameter("prixInitial") == null) {
			}
			int prixInitial = Integer.parseInt(request.getParameter("prixInitial"));
			
			// DATE DEBUT ENCHERE
			if(request.getParameter("dateDebutEncheres") == null) {
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date d = sdf.parse(request.getParameter("dateDebutEncheres"));
			Date dateDebutEncheres = new Date(d.getTime());
			
			// DATE FIN ENCHERE
			if(request.getParameter("dateFinEncheres") == null) {
			}
			d = sdf.parse(request.getParameter("dateFinEncheres"));
			Date dateFinEncheres = new Date(d.getTime());
			
			// RUE
			if(request.getParameter("rue") == null) {
			}
			String rue = request.getParameter("rue");
			
			// CODE POSTAL
			if(request.getParameter("codePostal") == null) {
			}
			String codePostal = request.getParameter("codePostal");
			
			// VILLE
			if(request.getParameter("ville") == null) {
			}
			String ville = request.getParameter("ville");
			
			Utilisateur vend = (Utilisateur) request.getSession().getAttribute("sessionUtilisateur");
			ArticleVendu article = new ArticleVendu(nomArticle, description, dateDebutEncheres, dateFinEncheres, vend, categorie);
			article.setPrixInitial(prixInitial);
			article.setPrixVente(prixInitial);
			
			ArticleVenduManager.getArticleVenduManager().nouvelleVente(article);
			
			Retrait retrait = new Retrait(rue,codePostal,ville,article);
			
			RetraitManager.getRetraitManager().creer(retrait);
			
			((HttpServletResponse) response).sendRedirect("encheres");
			
		} catch (NumberFormatException e) {
		} catch (ParseException e) {
		} catch (BusinessException e) {
		}
		
	}

}
