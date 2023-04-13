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
</head>
<body>
	<header>
		<h1 class="title">ENI Enchères</h1>
	</header>
	
	<section class="centered" style="margin: 0 50px">
		<form method="post" action="encherir">
			<input hidden name="noArticle" value="${requestScope['articleProfil'].getNoArticle()}">
			<div class="input-label-box">
				<label>Nom article : </label>
				<label name="nom_article">${requestScope['articleProfil'].getNomArticle()}</label>
			</div>
			
			<div class="input-label-box">
				<label>Description : </label>
				<label name="description_article">${requestScope['articleProfil'].getDescription()}</label>
			</div>
			
			<input hidden name="noCategorie" value="${requestScope['categorieProfil'].getNoCategorie()}">
			<div class="input-label-box">
				<label>Catégorie : </label>
				<label name="nom_categorie">${requestScope['categorieProfil'].getLibelle()}</label>
			</div>
			
			<div hidden class="input-label-box">
				<label>Meilleure offre : </label>
				<label name="max_enchere">${requestScope['enchereProfil'].getMaxEnchere()}</label><!-- TODO !!!! -->
			</div>
			
			<div class="input-label-box">
				<label>Mise à prix : </label>
				<label name="prix_initial">${requestScope['articleProfil'].getPrixInitial()} points</label>
			</div>
			
			<div class="input-label-box">
				<label>Fin de l'enchere : </label>
				<label name="date_fin_encheres">${requestScope['articleProfil'].getDateFinEncheres()}</label>
			</div>
			
			<div hidden class="input-label-box">
				<label>Retrait : </label>
				<label name="rue_retrait">${requestScope['retraitProfil'].getRue()}</label>
				<label name="codePostal_retrait">${requestScope['retraitProfil'].getCodePostal()}</label>
				<label name="ville_retrait">${requestScope['retraitProfil'].getVille()}</label>
			</div>
			
			<div class="input-label-box">
				<label>Vendeur : </label>
				<label name="utilisateur_vendeur">${requestScope['utilisateurProfil'].getPseudo()}</label>
			</div>
			
			<div class="input-label-box">
				<label>Ma proposition : </label>
				<label name="valeur">${requestScope['enchereProfil'].getMontantEnchere()}</label>
			</div>
			
			<div class="input-label-box">
				<label>Ma proposition : </label>
				<label name="valeur">${requestScope['enchereProfil'].getMontantEnchere()}</label>
			</div>
			<input class="button green-background" style="border: none" type="submit" value="Enchérir" >
		</form>
	</section>
</body>
</html>