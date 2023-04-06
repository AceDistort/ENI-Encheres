<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isErrorPage="false" errorPage="Erreur.jsp"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<title>Profil de ${ requestScope['profil'].getPseudo() }</title>
</head>
<body>
	<h2>${requestScope['profil'].getPseudo()}</h2>
	<div>
		<div class="row">
			<p class="col-4">Nom</p>
			<p class="col-8">${requestScope['profil'].getNom()}</p>
		</div>
		<div class="row">
			<p class="col-4">Prénom</p>
			<p class="col-8">${requestScope['profil'].getPrenom()}</p>
		</div>
		<c:if test="${requestScope['profil'].equals(sessionScope['sessionUtilisateur']) || sessionScope['sessionUtilisateur'].isAdministrateur()}">
		<div class="row">
			<p class="col-4">Email</p>
			<p class="col-8">${requestScope['profil'].getEmail()}</p>
		</div>
		<div class="row">
			<p class="col-4">Téléphone</p>
			<p class="col-8">${requestScope['profil'].getTelephone()}</p>
		</div>
		<div class="row">
			<p class="col-4">Rue</p>
			<p class="col-8">${requestScope['profil'].getRue()}</p>
		</div>
		<div class="row">
			<p class="col-4">Code postal</p>
			<p class="col-8">${requestScope['profil'].getCodePostal()}</p>
		</div>
		<div class="row">
			<p class="col-4">Ville</p>
			<p class="col-8">${requestScope['profil'].getVille()}</p>
		</div>
		</c:if>
	</div>
	<c:if test="${requestScope['profil'].equals(sessionScope['sessionUtilisateur']) || sessionScope['sessionUtilisateur'].isAdministrateur()}">
		<a href="profil/modifier">Modifier</a>
	</c:if>
</body>
</html>