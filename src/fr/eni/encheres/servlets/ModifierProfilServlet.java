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
 * Servlet implementation class ModifierProfilServlet
 */
@WebServlet("/profil/modifier")
public class ModifierProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierProfilServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur sessionUtilisateur = (Utilisateur) request.getSession().getAttribute("sessionUtilisateur");
		int idUtilisateur = -1;
		Utilisateur profil = new Utilisateur();
		try {
			//idUtilisateur = Integer.parseInt(request.getParameter("id"));
			idUtilisateur = Integer.parseInt(request.getParameter("noUtilisateur"));
		} catch (NumberFormatException e) {
			//Normalement il ne faut rien faire
		}
		
		//Admin + modif de compte utilisateur
		if(sessionUtilisateur != null && sessionUtilisateur.isAdministrateur() && idUtilisateur > 0) {
			profil.setNoUtilisateur(idUtilisateur);
			try {
				profil = UtilisateurManager.getUtilisateurManager().afficher(profil);
				request.setAttribute("profil", profil);
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getRequestDispatcher("/WEB-INF/modifierProfil.jsp").forward(request, response);
		} else { //Modif compte perso
			profil = sessionUtilisateur;
			request.setAttribute("profil", profil);
			request.getRequestDispatcher("/WEB-INF/modifierProfil.jsp").forward(request, response);
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
			utilisateur.setNoUtilisateur(((Utilisateur) request.getSession().getAttribute("sessionUtilisateur")).getNoUtilisateur());
			utilisateur.setPseudo(request.getParameter("pseudo"));
			utilisateur.setNom(request.getParameter("nomUtilisateur"));
			utilisateur.setPrenom(request.getParameter("prenomUtilisateur"));
			utilisateur.setEmail(request.getParameter("emailUtilisateur"));
			utilisateur.setTelephone(request.getParameter("telephone"));
			utilisateur.setRue(request.getParameter("rue"));
			utilisateur.setCodePostal(request.getParameter("codePostal"));
			utilisateur.setVille(request.getParameter("ville"));
			if (!request.getParameter("motDePasse").equals(request.getParameter("confirmation"))) {
				businessException.ajouterErreur(CodesResultatServlet.MOTS_DE_PASSE_NON_IDENTIQUES);
			}
			else
			{
				utilisateur.setMotDePasse(request.getParameter("motDePasse"));
			}
			
			UtilisateurManager.getUtilisateurManager().modifier(utilisateur);
			if (utilisateur.getNoUtilisateur() == ((Utilisateur) request.getSession().getAttribute("sessionUtilisateur")).getNoUtilisateur()) {
				((Utilisateur) request.getSession().getAttribute("sessionUtilisateur")).setPseudo(request.getParameter("pseudo"));
				((Utilisateur) request.getSession().getAttribute("sessionUtilisateur")).setTelephone(request.getParameter("telephone"));
				((Utilisateur) request.getSession().getAttribute("sessionUtilisateur")).setRue(request.getParameter("rue"));
				((Utilisateur) request.getSession().getAttribute("sessionUtilisateur")).setCodePostal(request.getParameter("codePostal"));
				((Utilisateur) request.getSession().getAttribute("sessionUtilisateur")).setVille(request.getParameter("ville"));
				((Utilisateur) request.getSession().getAttribute("sessionUtilisateur")).setMotDePasse(request.getParameter("motDePasse"));
				request.getSession().setAttribute("sessionUtilisateur", utilisateur);
			}
			((HttpServletResponse) response).sendRedirect("/ENI-Encheres/encheres");
		}
		catch (BusinessException e) {
			if(e.getListeCodesErreur().contains(CodesResultatBLL.CODE_POSTAL_UTILISATEUR_NON_VALIDE)) {
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
	}

}
