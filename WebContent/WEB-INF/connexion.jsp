<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	isErrorPage="false" errorPage="Erreur.jsp"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="icon" type="image/x-icon" href="images/favicon.png">
	<title>Connexion</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
	<link href="styles/global-style.css" rel="stylesheet">
</head>
<body>
	<header>
		<h1 class="title">ENI-Ench�res</h1>
	</header>
	
	<section class="centered">
		<form method="post" action="connexion">
			<div class="mb-3">
			  <label class="form-label">Login (adresse mail ou pseudo)</label>
			  <input required type="text" name="identifiant" class="form-control">
			</div>
			<div class="mb-3">
			  <label class="form-label">Mot de passe</label>
			  <input required type="password" name="motDePasse" class="form-control">
			</div>

			<button class="btn btn-primary orange-background" style="margin: 20px 0; border: none" type="submit">Connexion</button>
		</form>
		<c:if test="${not empty requestScope['erreurConnexion']}">
			<p class="error-text">${requestScope['erreurConnexion']}</p>
		</c:if>
		
	</section>
</body>
</html>