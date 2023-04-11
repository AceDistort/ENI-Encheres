<%@page import="fr.eni.encheres.bo.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	isErrorPage="false" errorPage="Erreur.jsp"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="fr">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
	<link href="styles/global-style.css" rel="stylesheet">
	<link href="styles/page-accueil-style.css" rel="stylesheet">
	<title>Accueil</title>
</head>
<body>
	<header>
		<h1 class="title">ENI-Enchères</h1>
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
		
	</body>
</body>
</html>