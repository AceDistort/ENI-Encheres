package fr.eni.encheres.servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

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
import fr.eni.encheres.util.HashMotDePasse;

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
		
		if(request.getSession().getAttribute("sessionUtilisateur") == null) {
			((HttpServletResponse) response).sendRedirect("connexion");
		} else {
			request.getRequestDispatcher("/WEB-INF/modifierProfil.jsp").forward(request, response);
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getSession().getAttribute("sessionUtilisateur") == null) {
			((HttpServletResponse) response).sendRedirect("connexion");
		} else {
			try {
				Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("sessionUtilisateur");
				if(request.getParameter("confirmation") == null) {
					BusinessException businessException = new BusinessException();
					throw businessException;
				}
				
				if(!HashMotDePasse.validatePassword(request.getParameter("confirmation"), utilisateur.getMotDePasse())) {
					BusinessException businessException = new BusinessException();
					throw businessException;
				}
				
				utilisateur.setPseudo(request.getParameter("pseudo"));
				utilisateur.setTelephone(request.getParameter("telephone"));
				utilisateur.setRue(request.getParameter("rue"));
				utilisateur.setCodePostal(request.getParameter("codePostal"));
				utilisateur.setVille(request.getParameter("ville"));
				
				UtilisateurManager.getUtilisateurManager().modifier(utilisateur);
				
				((HttpServletResponse) response).sendRedirect("/ENI-Encheres/encheres");
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BusinessException e) {
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
					request.setAttribute("erreurConfirmation", LecteurMessage.getMessageErreur(CodesResultatServlet.MAUVAIS_MOT_DE_PASSE_CONFIRMATION));
					request.getRequestDispatcher("../WEB-INF/modifierProfil.jsp").forward(request, response);
				}
			}
		}
	}

}
