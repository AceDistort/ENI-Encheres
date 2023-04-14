<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Détail Vente</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
	<link href="styles/global-style.css" rel="stylesheet">
	<link href="styles/page-inscription.css" rel="stylesheet">
	<script type="text/javascript" src="js/utils.js"></script>
</head>
<body>

	<section class="mx-auto p-2" style="width: 80%; max-width: 450px; margin-top: 50px">
		<img src="images/logoENIEncheres.png" style="margin-bottom: 50px; width: 230px">

		<img src="images/article-exemple.png" style="width: 80%; max-width: 200px; margin: 10px; border-radius: 10px; object-fit: cover">
		<div>
			<p name="nom_article" style="font-size: xx-large; font-weight: 600">${requestScope['articleProfil'].getNomArticle()}</p>
			<p name="nom_categorie" style="color: #363636;">${requestScope['categorieProfil'].getLibelle()}</p>
			
			<p name="description_article" style="color: grey;">${requestScope['articleProfil'].getDescription()}</p>
			
			<p>Vendu par <span name="utilisateur_vendeur"><a href="profil?id=${requestScope['utilisateurProfil'].getNoUtilisateur()}">${requestScope['utilisateurProfil'].getPseudo()}</a></span></p>
		
			<p>Fin le <span name="date_fin_encheres">${requestScope['articleProfil'].getDateFinEncheres()}</span></p>
			
			<!-- Placement d'enchère  -->
			<div class="d-flex flex-wrap flex-row align-items-center" style="width: 100%; border-radius: 10px">
				<div style="flex: 1; min-width: 200px">
					<p style="margin: 0; font-size: 45px; font-weight: 600" name="prix_vente">${requestScope['articleProfil'].getPrixVente()}</p>
					<p>points</p>
				</div>
				<div style="flex: 3; min-width: 200px; margin-top: 20px">
					<form method="post" action="vente">
						<input hidden name="noArticle" value="${requestScope['articleProfil'].getNoArticle()}">
						<input class="form-control" type="number" min="${requestScope['articleProfil'].getPrixVente()+1}" name="montantEnchere" value="${requestScope['articleProfil'].getPrixVente()+1}">
						<button class="btn btn-primary orange-background" style="margin: 20px 0; border: none" type="submit">Placer l'offre</button>
					</form>
				</div>
			</div>
			
			<button class="btn btn-secondary" style="margin: 20px 0; border: none" onclick="retourAction()">Retour</button>
			
		</div>
	</section>
</body>
</html>