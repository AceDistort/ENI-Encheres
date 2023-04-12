<%@page import="fr.eni.encheres.bo.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	isErrorPage="false" errorPage="Erreur.jsp"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="fr">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="icon" type="image/x-icon" href="images/favicon.png">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
	<link href="styles/global-style.css" rel="stylesheet">
	<link href="styles/page-accueil-style.css" rel="stylesheet">
	<title>Accueil</title>
</head>
<body>
	<header>
		<img style="margin: 30px" src="images/logoENIEncheres.png">
		<ul class="header-nav">
			<c:if test="${empty sessionScope['sessionUtilisateur']}">
				<li><a class="no-text-decoration text" href="connexion">Se connecter</a></li>
				<li class="button orange-background"><a href="inscription">S'inscrire</a></li>
			</c:if>
			<c:if test="${not empty sessionScope['sessionUtilisateur']}">
				<li><a class="no-text-decoration text" href="">Enchères</a></li>
				<li><a class="no-text-decoration text" href="vendre">Vendre un article</a></li>
				<li><a class="no-text-decoration text" href="profil">Mon profil</a></li>
				<li><a class="no-text-decoration text" href="deconnexion">Déconnexion</a></li>
			</c:if>
		</ul>
	</header>
	<body>
		<h2 style="text-align: center; margin-bottom: 50px">Liste des enchères</h2>
		
		<div style="display: flex; flex-direction: row; flex-wrap: wrap">
			<!-- Section filtres et recherche -->
			<section class="mx-5" style="min-width: 300px; flex: 1">
				<form method="POST" action="encheres">
					<div class="mb-3">
						<label class="form-label" for="texte">Le nom de l'article contient</label>
  						<input class="form-control" id="texte" name="texte" type="text" value="${param.texte}">
					</div>
					<div>
						<label class="form-label" for="categorie">Catégorie</label>
						<select id="categorie" name="categorie" class="form-select">
							<option selected>Toutes</option>
							<c:forEach var="categorie" items="${requestScope['categories']}">
								<option ${param.categorie == categorie.getNoCategorie() ? "selected" : ""} value="${categorie.getNoCategorie()}">${categorie.getLibelle()}</option>
							</c:forEach>
						</select>
					</div>
					<button class="btn btn-primary blue-background" style="margin-top: 20px; border: none" type="submit">Rechercher</button>
				</form>
			</section>
			
			<!-- Section filtres et recherche -->
			<section class="mx-5" style="flex: 2">
				<div class="card-group">
					<c:forEach var="article" items="${requestScope['articles']}">
						<div class="card mb-3 mx-2" style="max-width: 500px; min-width: 400px;">
						  <div class="row g-0">
						    <div class="col-md-4">
						      <img src="images/article-exemple.png" class="img-fluid rounded-start" alt="...">
						    </div>
						    <div class="col-md-8">
						      <div class="card-body">
						        <h5 class="card-title">${article.getNomArticle()}</h5>
						        <p class="card-text">${article.getPrixVente()} points</p>
						        <p class="card-text"><small class="text-body-secondary">Fin le ${article.getDateFinEncheres()}</small></p>
						        <p class="card-text">Vendu par ${article.getVend().getPseudo()}</p>
						      </div>
						    </div>
						  </div>
						</div>
					</c:forEach>
			</section>
		</div>
	</body>
</body>
</html>