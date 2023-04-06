package fr.eni.encheres.test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.BusinessException;
import fr.eni.encheres.util.HashMotDePasse;

/**
 * Servlet implementation class HashMDP
 */
@WebServlet("/HashMDP")
public class HashMDP extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HashMDP() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {

			System.out.println(HashMotDePasse.generateStorngPasswordHash("motdepasse"));
			System.out.println(HashMotDePasse.generateStorngPasswordHash("password"));
			System.out.println(HashMotDePasse.generateStorngPasswordHash("mdp123"));
			System.out.println(HashMotDePasse.generateStorngPasswordHash("test123"));
			System.out.println(HashMotDePasse.generateStorngPasswordHash("pass123"));
			System.out.println(HashMotDePasse.generateStorngPasswordHash("testmdp"));
			System.out.println(HashMotDePasse.generateStorngPasswordHash("testpass"));
			System.out.println(HashMotDePasse.generateStorngPasswordHash("test1234"));
			System.out.println(HashMotDePasse.generateStorngPasswordHash("admin"));
			
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
