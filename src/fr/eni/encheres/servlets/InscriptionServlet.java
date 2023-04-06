package fr.eni.encheres.servlets;

import java.io.IOException;
import java.sql.SQLException;

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
 * Servlet implementation class InscriptionServlet
 */
@WebServlet("/inscription")
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InscriptionServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("sessionUtilisateur") == null) {
			request.getRequestDispatcher("WEB-INF/inscription.jsp").forward(request, response);
		}
		else {
			((HttpServletResponse) response).sendRedirect("encheres");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			BusinessException businessException = new BusinessException();
			Utilisateur utilisateur = new Utilisateur();
			utilisateur.setPseudo(request.getParameter("pseudo"));
			utilisateur.setNom(request.getParameter("nom"));
			utilisateur.setPrenom(request.getParameter("prenom"));
			utilisateur.setEmail(request.getParameter("email"));
			utilisateur.setTelephone(request.getParameter("telephone"));
			utilisateur.setRue(request.getParameter("rue"));
			utilisateur.setCodePostal(request.getParameter("codePostal"));
			utilisateur.setVille(request.getParameter("ville"));
			if (!request.getParameter("motDePasse").equals(request.getParameter("confirmation"))) {
				businessException.ajouterErreur(CodesResultatServlet.MOTS_DE_PASSE_NON_IDENTIQUES);
			}
			
			UtilisateurManager.getUtilisateurManager().creer(utilisateur);
		}
		catch (BusinessException e) {
			if(e.getListeCodesErreur().contains(CodesResultatBLL.EMAIL_UTILISATEUR_NON_VALIDE)) {
				request.setAttribute("erreurEmail", LecteurMessage.getMessageErreur(CodesResultatBLL.EMAIL_UTILISATEUR_NON_VALIDE));
				request.getRequestDispatcher("WEB-INF/inscription.jsp").forward(request, response);
			} else if(e.getListeCodesErreur().contains(CodesResultatBLL.CODE_POSTAL_UTILISATEUR_NON_VALIDE)) {
				request.setAttribute("erreurCodePostal", LecteurMessage.getMessageErreur(CodesResultatBLL.CODE_POSTAL_UTILISATEUR_NON_VALIDE));
				request.getRequestDispatcher("WEB-INF/inscription.jsp").forward(request, response);
			} else if(e.getListeCodesErreur().contains(CodesResultatBLL.MOT_DE_PASSE_UTILISATEUR_NON_VALIDE)) {
				request.setAttribute("erreurMotDePasse", LecteurMessage.getMessageErreur(CodesResultatBLL.MOT_DE_PASSE_UTILISATEUR_NON_VALIDE));
				request.getRequestDispatcher("WEB-INF/inscription.jsp").forward(request, response);
			} else if(e.getListeCodesErreur().contains(CodesResultatServlet.MOTS_DE_PASSE_NON_IDENTIQUES)) {
				request.setAttribute("erreurMotDePasse", LecteurMessage.getMessageErreur(CodesResultatServlet.MOTS_DE_PASSE_NON_IDENTIQUES));
				request.getRequestDispatcher("WEB-INF/inscription.jsp").forward(request, response);
			} else {
				//TODO
				((HttpServletResponse) response).sendError(1);
			}
		}
		catch (SQLException e) {
			
		}
	}

}
