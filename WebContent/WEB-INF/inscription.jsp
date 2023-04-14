<%@ page language="java" contentType="text/html; charset=UTF-8"
	isErrorPage="false" errorPage="Erreur.jsp"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="icon" type="image/x-icon" href="images/favicon.png">
	<title>Inscription</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
	<link href="styles/global-style.css" rel="stylesheet">
	<script type="text/javascript" src="js/utils.js"></script>
</head>
<body>
	<section class="mx-auto p-2" style="width: 80%; max-width: 500px; margin-top: 50px">
		<img src="images/logoENIEncheres.png" style="margin-bottom: 50px; width: 230px">
		<form method="post" action="inscription">
			<div class="mb-3">
			  <label class="form-label">Pseudo <span class="red">*</span></label>
			  <input required type="text" name="pseudo" pattern="\b\w{4,30}\b" class="form-control">
			</div>
			
			<div class="row mb-3">
			  <div class="col">
			    <label class="form-label">Nom <span class="red">*</span></label>
			  	<input required type="text" name="nom" pattern="^[a-zA-ZÀ-ÖØ-öø-ÿ]+(?:[\s-'][a-zA-ZÀ-ÖØ-öø-ÿ]+)*$" class="form-control">
			  </div>
			  <div class="col">
			    <label class="form-label">Prénom <span class="red">*</span> </label>
			  	<input required type="text" name="prenom" pattern="^[a-zA-ZÀ-ÖØ-öø-ÿ]+(?:[\s-'][a-zA-ZÀ-ÖØ-öø-ÿ]+)*$" class="form-control">
			  </div>
			</div>
			
			<div class="mb-3">
			  <label class="form-label">Email <span class="red">*</span> </label>
			  <input required type="email" name="email" class="form-control">
			</div>
			<c:if test="${not empty requestScope['erreurEmail']}">
				<p class="error-text">${requestScope['erreurEmail']}</p>
			</c:if>
			
			<div class="mb-3">
			  <label class="form-label">Téléphone </label>
			  <input type="text" name="telephone" pattern="^(?:(?:\+|00)33|0)\s*[1-9](?:[\s.-]*\d{​​​2}​​​){​​​4}​​​$" class="form-control">
			</div>
			
			<div class="mb-3">
			  <label class="form-label">Rue <span class="red">*</span> </label>
			  <input required type="text" name="rue" class="form-control">
			</div>
			
			<div class="row mb-3">
			  <div class="col">
			    <label class="form-label">Ville <span class="red">*</span> </label>
			  	<input required type="text" name="ville" pattern="^[a-zA-ZÀ-ÖØ-öø-ÿ]+(?:[\s-']+[a-zA-ZÀ-ÖØ-öø-ÿ]+)*$" class="form-control">
			  </div>
			  <div class="col">
			    <label class="form-label">Code Postal <span class="red">*</span></label>
			  	<input required type="text" name="codePostal" pattern="^[0-9]{5}$" class="form-control">
			  	<c:if test="${not empty requestScope['erreurCodePostal']}">
					<p class="error-text">${requestScope['erreurCodePostal']}</p>
				</c:if>
			  </div>
			</div>
			
			<div class="mx-auto" style="width: 95%; height: 1px; background-color: #ebebeb; margin-bottom: 20px"></div>
			
			<div class="row mb-3">
			  <div class="col">
			    <label class="form-label">Mot de passe <span class="red">*</span> </label>
			  	<input required type="password" name="motDePasse" pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{12,30}$" class="form-control">
			  </div>
			  <div class="col">
			    <label class="form-label">Confirmation <span class="red">*</span> </label>
			  	<input required type="password" name="confirmation" pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{12,30}$" class="form-control">
			  </div>
			</div>
			<p class="info-text">** 12 caractères minimum, au moins une majuscule, au moins une minuscule, au moins un chiffre, au moins un caractère spécial<p>
			<c:if test="${not empty requestScope['erreurMotDePasse']}">
				<p class="error-text">${requestScope['erreurMotDePasse']}</p>
			</c:if>

			<!-- Boutons -->
			<p style="margin-top: 30px; margin-bottom: 5px"" class="info-text"><span class="red">*</span> Champs obligatoires</p>
			<button class="btn btn-secondary" style="margin: 20px 0; border: none" onclick="retourAction()" type="button">Retour</button>
			<button class="btn btn-primary orange-background" style="margin: 20px 0; border: none" type="submit">Inscription</button>
		</form>		
	</section>
</body>
</html>