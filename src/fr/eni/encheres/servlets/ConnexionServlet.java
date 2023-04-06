package fr.eni.encheres.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.CodesResultatBLL;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.BusinessException;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.messages.LecteurMessage;

/**
 * Servlet implementation class ConnexionServlet
 */
@WebServlet("/connexion")
public class ConnexionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
			Utilisateur utilisateur = new Utilisateur();
			utilisateur.setPseudo(request.getParameter("identifiant"));
			utilisateur.setMotDePasse(request.getParameter("motDePasse"));
			UtilisateurManager.getUtilisateurManager().seConnecter(utilisateur);
			
			request.getSession().setAttribute("sessionUtilisateur", utilisateur);
			
			((HttpServletResponse) response).sendRedirect("encheres");
			
		} catch (BusinessException e) {
			if(e.getListeCodesErreur().contains(CodesResultatBLL.MOT_DE_PASSE_UTILISATEUR_INCORRECT)) {
				request.setAttribute("erreurConnexion", LecteurMessage.getMessageErreur(CodesResultatBLL.MOT_DE_PASSE_UTILISATEUR_INCORRECT));
				request.getRequestDispatcher("WEB-INF/connexion.jsp").forward(request, response);
			} else if(e.getListeCodesErreur().contains(CodesResultatBLL.UTILISATEUR_INCORNNU)) {
				request.setAttribute("erreurConnexion", LecteurMessage.getMessageErreur(CodesResultatBLL.UTILISATEUR_INCORNNU));
				request.getRequestDispatcher("WEB-INF/connexion.jsp").forward(request, response);
			} else {
				//TODO
				((HttpServletResponse) response).sendError(1);
			}
		}
	}

}
