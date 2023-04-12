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
	<main>
		<h2 style="text-align: center; margin-bottom: 50px">Liste des enchères</h2>
		
		<div style="display: flex; flex-direction: row; flex-wrap: wrap">
			<!-- Section filtres et recherche -->
			<section class="mx-3" style="min-width: 300px; flex: 1">
				<form method="POST" action="encheres">
					<div class="mb-3">
						<label class="form-label" for="texte">Le nom de l'article contient</label>
  						<input class="form-control" id="texte" name="texte" type="text" value="${param.texte}">
					</div>
					<div>
						<label class="form-label" for="categorie">Catégorie</label>
						<select id="categorie" name="categorie" class="form-select">
							<option value="">Toutes</option>
							<c:forEach var="categorie" items="${requestScope['categories']}">
								<option ${param.categorie == categorie.getNoCategorie() ? "selected" : ""} value="${categorie.getNoCategorie()}">${categorie.getLibelle()}</option>
							</c:forEach>
						</select>
					</div>
					<div>
					<c:if test="${not empty sessionScope['sessionUtilisateur']}">
						<div>
							<input id="achats" name="gestionBtn" type="radio" ${not empty paramValues.achats or empty paramValues.mesVentes ? "checked" : ""}/>
							<label for="achats">Achats</label>
							<div>
								<div>
									<input id="achats0" name="achats" type="checkbox" value="0"/>
									<label for="achats">enchères ouvertes</label>
								</div>
								<div>
									<input id="achats1" name="achats" type="checkbox" value="1"/>
									<label for="achats">mes enchères en cours</label>
								</div>
								<div>
									<input id="achats2" name="achats" type="checkbox" value="2"/>
									<label for="achats">mes enchères remportées</label>
								</div>
							</div>
						</div>
				
						<div>
							<input id="mesVentes" name="gestionBtn" type="radio" ${not empty paramValues.mesVentes ? "checked" : ""}/>
							<label for="mesVente">Mes ventes</label>
							<div>
								<div>
									<input id="mesVente0" name="mesVentes" type="checkbox" value="0"/>
									<label for="mesVentes">mes ventes en cours</label>
								</div>
								<div>
									<input id="mesVente1" name="mesVentes" type="checkbox" value="1"/>
									<label for="mesVentes">ventes non débutées</label>
								</div>
								<div>
									<input id="mesVente2" name="mesVentes" type="checkbox" value="2"/>
									<label for="mesVentes">ventes terminées</label>
								</div>
							</div>
						</div>
					</c:if>
					</div>
					<button class="btn btn-primary blue-background" style="margin-top: 20px; border: none" type="submit">Rechercher</button>
				</form>
			</section>
			
			<!-- Section filtres et recherche -->
			<section class="mx-3" style="flex: 2">
				<div class="card-group">
					<c:forEach var="article" items="${requestScope['articles']}">
						<div class="card mb-3 d-flex flex-row flex-nowrap" style="max-width: 400px; min-width: 350px;">
						    <div class="flex-1">
						      <img src="images/article-exemple.png" class="img-fluid rounded-start" style="height: 100%; object-fit: cover">
						    </div>
						    <div class="flex-2">
						      <div class="card-body">
						        <h5 class="card-title">${article.getNomArticle()}</h5>
						        <p class="card-text">${article.getPrixVente()} points</p>
						        <p class="card-text"><small class="text-body-secondary">Fin le ${article.getDateFinEncheres()}</small></p>
						        <p class="card-text">Vendu par ${article.getVend().getPseudo()}</p>
						      </div>
						    </div>
						</div>
					</c:forEach>
				</div>
			</section>
		</div>
	</main>
	<script>
		const achatsBtn = document.getElementById("achats");
		const mesVentesBtn = document.getElementById("mesVentes");
		
		if(achatsBtn != null && mesVentesBtn != null) {
			const achats = document.querySelectorAll("input[name='achats']");
			const mesVentes = document.querySelectorAll("input[name='mesVentes']");
			
			function gestionBtn () {
				[...mesVentes].forEach( i => {
					achatsBtn.checked ? i.checked = false : "";
					i.disabled = achatsBtn.checked;
				});
				[...achats].forEach( i => {
					achatsBtn.checked ? "" : i.checked = false;
					i.disabled = !achatsBtn.checked;
				});
			}
			
			gestionBtn();
			
			achatsBtn.addEventListener("click",gestionBtn);
			mesVentesBtn.addEventListener("click",gestionBtn);
			
		}
	</script>
</body>
</html>