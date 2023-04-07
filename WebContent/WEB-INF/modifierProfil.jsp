<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isErrorPage="false" errorPage="Erreur.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Inscription</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
	<link href=".././styles/global-style.css" rel="stylesheet">
	<link href=".././styles/page-inscription.css" rel="stylesheet">
</head>
<body>
	<header>
		<h1 class="title">ENI Enchères</h1>
	</header>
	
	<section class="centered" style="margin: 0 50px">
		<form method="post" action="modifier">
			<input hidden name="noUtilisateur" value="${requestScope['profil'].getNoUtilisateur()}">
			<div class="input-label-box">
				<label>Pseudo <span class="red">*</span> </label>
				<input required type="text" name="pseudo" pattern="\b\w{4,30}\b" value="${requestScope['profil'].getPseudo()}">
			</div>
			<input hidden name="nomUtilisateur" value="${requestScope['profil'].getNom()}">
			<input hidden name="prenomUtilisateur" value="${requestScope['profil'].getPrenom()}">
			<input hidden name="emailUtilisateur" value="${requestScope['profil'].getEmail()}">						
			<div class="input-label-box">
				<label>Téléphone </label>
				<input type="text" name="telephone" pattern="^(?:(?:\+|00)33|0)\s*[1-9](?:[\s.-]*\d{​​​2}​​​){​​​4}​​​$" value="${requestScope['profil'].getTelephone()}">
			</div>
			
			<div class="input-label-box">
				<label>Rue <span class="red">*</span> </label>
				<input required type="text" name="rue" pattern="^\d+\s+[a-zA-Z]+(?:\s+[a-zA-Z]+)*$" value="${requestScope['profil'].getRue()}">
			</div>
			
			<div class="input-label-box">
				<label>Code Postal <span class="red">*</span> </label>
				<input required type="text" name="codePostal" input="^\d{​​​​​​​​​​​​​​​​​​5}​​​​​​​​​​​​​​​​​​(?:[\s-]\w{​​​​​​​​​​​​​​​​​​2}​​​​​​​​​​​​​​​​​​)?$" value="${requestScope['profil'].getCodePostal()}">
			</div>
			<c:if test="${not empty requestScope['erreurCodePostal']}">
				<p class="error-text">${requestScope['erreurCodePostal']}</p>
			</c:if>
			
			<div class="input-label-box">
				<label>Ville <span class="red">*</span> </label>
				<input required type="text" name="ville" pattern="^[a-zA-ZÀ-ÖØ-öø-ÿ]+(?:[\s-']+[a-zA-ZÀ-ÖØ-öø-ÿ]+)*$" value="${requestScope['profil'].getVille()}">
			</div>
			
			<div class="input-label-box">
				<label>Mot de passe <span class="red">*</span> </label>
				<input required type="password" name="motDePasse" pattern="^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[^\w\s]).{​​​​​​​​​​​​​​​​​​​​​​​​12,50}​​​​​​​​​​​​​​​​​​​​​​​​$">
			</div>
			<p class="info-text">** 12 caractères minimum, au moins une majuscule, au moins une minuscule, au moins un chiffre, au moins un caractère spécial<p>
			<c:if test="${not empty requestScope['erreurMotDePasse']}">
				<p class="error-text">${requestScope['erreurMotDePasse']}</p>
			</c:if>
			
			<div class="input-label-box">
				<label>Confirmation <span class="red">*</span> </label>
				<input required type="password" name="confirmation" pattern="^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[^\w\s]).{​​​​​​​​​​​​​​​​​​​​​​​​12,50}​​​​​​​​​​​​​​​​​​​​​​​​$">
			</div>
			<c:if test="${not empty requestScope['erreurMotDePasse']}">
				<p class="error-text">${requestScope['erreurMotDePasse']}</p>
			</c:if>
			
			<div class="input-label-box">
				<input class="button green-background" style="border: none" type="submit" value="Enregistrer" >
			</div>
			<div class="input-label-box">
				<input class="button" style="border: none" type="button" value="Supprimer mon compte">
			</div>
			<div class="input-label-box">
				<input class="button" style="border: none" type="submit" value="Retour" >
			</div>
		</form>
		
	</section>
</body>
</html>