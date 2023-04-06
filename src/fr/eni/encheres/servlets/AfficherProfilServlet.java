package fr.eni.encheres.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.BusinessException;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class AfficherProfilServlet
 */
@WebServlet("/profil")
public class AfficherProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AfficherProfilServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur profil = new Utilisateur();
		if(request.getParameter("id") == null) {
			if(request.getSession().getAttribute("sessionUtilisateur") == null) {
				((HttpServletResponse) response).sendRedirect("connexion");
			} else {
				profil = (Utilisateur) request.getSession().getAttribute("sessionUtilisateur");
				request.setAttribute("profil", profil);
				request.getRequestDispatcher("WEB-INF/pageProfil.jsp").forward(request, response);
			}
		} else {
			try {
				profil.setNoUtilisateur(Integer.parseInt(request.getParameter("id")));
				profil = UtilisateurManager.getUtilisateurManager().afficher(profil);
				request.setAttribute("profil", profil);
				request.getRequestDispatcher("WEB-INF/pageProfil.jsp").forward(request, response);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
