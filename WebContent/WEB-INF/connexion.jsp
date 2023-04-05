<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Connexion</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
	<link href="styles/global-style.css" rel="stylesheet">
</head>
<body>
	<header>
		<h1 class="title">ENI Ench�res</h1>
	</header>
	
	<section class="centered">
		<form method="post" action="/connexion">
			<div class="input-label-box">
				<label>Identifiant : </label>
				<input type="text" name="pseudo">
			</div>
			
			<div class="input-label-box">
				<label>Mot de passe : </label>
				<input type="password" name="motDePasse">
			</div>
			<div class="input-label-box">
				<input class="button" type="submit" value="Connexion" >
			</div>
		</form>
		
	</section>
</body>
</html>