<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isErrorPage="false" errorPage="Erreur.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>	
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Nouvelle vente</title>
</head>
<body>
	<header>
		<h1 class="title">ENI Enchères</h1>
	</header>
	<h2>Nouvelle vente</h2>
	<form method="POST" action="vendre">
		<div>
			<label for="nomArticle">Article</label>
			<input id="nomArticle" name="nomArticle" type="text"/>
		</div>
		<div>
			<label for="description">Description</label>
			<input id="description" name="description" type="text"/>
		</div>
		<div>
			<label>Catégorie</label>
			<select id="categorie" name="categorie">
				<c:forEach var="categorie" items="${requestScope['categories']}">
					<option value="${categorie.getNoCategorie()}">${categorie.getLibelle()}</option>
				</c:forEach>
			</select>
		</div>
		<div>
			<label for="prixInitial">Mise à prix</label>
			<input id="prixInitial" name="prixInitial" type="number"/>
		</div>
		<div>
			<label for="dateDebutEncheres">Début de l'enchère</label>
			<input id="dateDebutEncheres" name="dateDebutEncheres" type="date"/>
		</div>
		<div>
			<label for="dateFinEncheres">Fin de l'enchère</label>
			<input id="dateFinEncheres" name="dateFinEncheres" type="date"/>
		</div>
		<div>
			<p>Retrait</p>
			<div>
				<label for="rue">Rue</label>
				<input id="rue" name="rue" type="text" value="${sessionScope['sessionUtilisateur'].getRue()}"/>
			</div>
			<div>
				<label for="codePostal">Code postal</label>
				<input id="codePostal" name="codePostal" type="text" value="${sessionScope['sessionUtilisateur'].getCodePostal()}"/>
			</div>
			<div>
				<label for="ville">Ville</label>
				<input id="ville" name="ville" type="text" value="${sessionScope['sessionUtilisateur'].getVille()}"/>
			</div>
		</div>
		<input type="submit" value="Enregistrer">
	</form>
</body>
</html>