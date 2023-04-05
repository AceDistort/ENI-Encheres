package fr.eni.encheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.BusinessException;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ConnexionServlet
 */
@WebServlet("/connexion")
public class ConnexionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurManager utilisateurManager;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnexionServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// vérification si un utilisateur n'est pas déjà connecté
		if(request.getSession().getAttribute("sessionUtilisateur") == null) {
			request.getRequestDispatcher("WEB-INF/connexion.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("WEB-INF/pageAccueil.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			UtilisateurManager.getUtilisateurManager();
			
			Utilisateur utilisateur = new Utilisateur(request.getParameter("identifiant"),request.getParameter("motDePasse"));
			
			utilisateurManager.seConnecter(utilisateur);
			request.getSession().setAttribute("sessionUtilisateur", utilisateur);
			((HttpServletResponse) response).sendRedirect("/encheres");
		} catch (BusinessException e) {
			request.getSession().setAttribute("erreurConnexion", "Erreur");
			request.getRequestDispatcher("WEB-INF/connexion.jsp").forward(request, response);
		}
			
	}

}
