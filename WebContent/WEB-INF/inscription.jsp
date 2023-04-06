<%@ page language="java" contentType="text/html; charset=UTF-8"
	isErrorPage="false" errorPage="Erreur.jsp"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Inscription</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
	<link href="styles/global-style.css" rel="stylesheet">
	<link href="styles/page-inscription.css" rel="stylesheet">
</head>
<body>
	<header>
		<h1 class="title">ENI Enchères</h1>
	</header>
	
	<section class="centered" style="margin: 0 50px">
		<form method="post" action="inscription">
			<div class="input-label-box">
				<label>Pseudo <span class="red">*</span> </label>
				<input required type="text" name="pseudo" pattern="\b\w{4,30}\b">
			</div>
			
			<div class="input-label-box">
				<label>Nom <span class="red">*</span> </label>
				<input required type="text" name="nom" pattern="^[a-zA-ZÀ-ÖØ-öø-ÿ]+(?:[\s-'][a-zA-ZÀ-ÖØ-öø-ÿ]+)*$">
			</div>
			
			<div class="input-label-box">
				<label>Prénom <span class="red">*</span> </label>
				<input required type="text" name="prenom" pattern="^[a-zA-ZÀ-ÖØ-öø-ÿ]+(?:[\s-'][a-zA-ZÀ-ÖØ-öø-ÿ]+)*$">
			</div>
			
			<div class="input-label-box">
				<label>Email <span class="red">*</span> </label>
				<input required type="text" name="email" pattern="\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}\b">
			</div>
			<c:if test="${not empty requestScope['erreurEmail']}">
				<p class="error-text">${requestScope['erreurEmail']}</p>
			</c:if>
						
			<div class="input-label-box">
				<label>Téléphone </label>
				<input type="text" name="telephone" pattern="^(?:(?:\+|00)33|0)\s*[1-9](?:[\s.-]*\d{2}){4}$">
			</div>
			
			<div class="input-label-box">
				<label>Rue <span class="red">*</span> </label>
				<input required type="text" name="rue" pattern="^\d+\s+[a-zA-Z]+(?:\s+[a-zA-Z]+)*$">
			</div>
			
			<div class="input-label-box">
				<label>Code Postal <span class="red">*</span> </label>
				<input required type="text" name="codePostal" input="^\d{​5}​(?:[\s-]\w{​2}​)?$">
			</div>
			<c:if test="${not empty requestScope['erreurCodePostal']}">
				<p class="error-text">${requestScope['erreurCodePostal']}</p>
			</c:if>
			
			<div class="input-label-box">
				<label>Ville <span class="red">*</span> </label>
				<input required type="text" name="ville" pattern="^[a-zA-ZÀ-ÖØ-öø-ÿ]+(?:[\s-']+[a-zA-ZÀ-ÖØ-öø-ÿ]+)*$">
			</div>
			
			<div class="input-label-box">
				<label>Mot de passe <span class="red">*</span> </label>
				<input required type="password" name="motDePasse" pattern="^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[^\w\s]).{12,50}$">
			</div>
			<p class="info-text">** 12 caractères minimum, au moins une majuscule, au moins une minuscule, au moins un chiffre, au moins un caractère spécial<p>
			<c:if test="${not empty requestScope['erreurMotDePasse']}">
				<p class="error-text">${requestScope['erreurMotDePasse']}</p>
			</c:if>
			
			<div class="input-label-box">
				<label>Confirmation <span class="red">*</span> </label>
				<input required type="password" name="confirmation" pattern="^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[^\w\s]).{12,50}$">
			</div>
			<c:if test="${not empty requestScope['erreurMotDePasse']}">
				<p class="error-text">${requestScope['erreurMotDePasse']}</p>
			</c:if>
			
			<div class="input-label-box">
				<input class="button green-background" style="border: none" type="submit" value="Créer" >
			</div>
			
			<div class="input-label-box">
				<input class="button" style="border: none" type="submit" value="Annuler" >
			</div>
		</form>
		
	</section>
</body>
</html>