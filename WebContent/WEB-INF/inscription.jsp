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
</head>
<body>
	<header>
		<h1 class="title">ENI Enchères</h1>
	</header>
	
	<section class="centered">
		<form method="post" action="inscription">
			<div class="input-label-box">
				<label>Pseudo : </label>
				<input required type="text" name="pseudo" placeholder="">
			</div>
			
			<div class="input-label-box">
				<label>Nom : </label>
				<input required type="text" name="nom">
			</div>
			
			<div class="input-label-box">
				<label>Prénom : </label>
				<input required type="text" name="prenom">
			</div>
			
			<div class="input-label-box">
				<label>Email : </label>
				<input required type="text" name="email">
			</div>
			<p class="error-text"><%=session.getAttribute("erreurEmail") %></p>
			
			<div class="input-label-box">
				<label>Téléphone : </label>
				<input required type="text" name="telephone">
			</div>
			
			<div class="input-label-box">
				<label>Rue : </label>
				<input required type="text" name="rue">
			</div>
			
			<div class="input-label-box">
				<label>Code Postal : </label>
				<input required type="text" name="codePostal">
			</div>
			<p class="error-text"><%=session.getAttribute("erreurCodePostal") %></p>
			
			<div class="input-label-box">
				<label>Ville : </label>
				<input required type="text" name="ville">
			</div>
			
			<div class="input-label-box">
				<label>Mot de passe : </label>
				<input required type="password" name="motDePasse">
			</div>
			<p class="error-text"><%=session.getAttribute("erreurMotDePasse") %></p>
			
			<div class="input-label-box">
				<label>Confirmation : </label>
				<input required type="password" name="confirmation">
			</div>
			<p class="error-text"><%=session.getAttribute("erreurMotDePasse") %></p>
			
			<div class="input-label-box">
				<input class="button" type="submit" value="Créer" >
			</div>
			
			<div class="input-label-box">
				<input class="button" type="submit" value="Annuler" >
			</div>
		</form>
		
	</section>
</body>
</html>