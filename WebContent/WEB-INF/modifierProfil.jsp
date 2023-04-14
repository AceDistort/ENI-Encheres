<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isErrorPage="false" errorPage="Erreur.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="icon" type="image/x-icon" href="images/favicon.png">
	<title>Inscription</title>
	<link rel="icon" type="image/x-icon" href="images/favicon.png">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
	<link href=".././styles/global-style.css" rel="stylesheet">
	<link href=".././styles/page-inscription.css" rel="stylesheet">
	<script type="text/javascript" src="../js/utils.js"></script>
</head>
<body>
	<section class="mx-auto p-2" style="width: 80%; max-width: 500px; margin-top: 100px">
		<img src="../images/logoENIEncheres.png" style="margin-bottom: 50px; width: 230px">
		<form method="post" action="modifier">
			
			<div class="mb-3">
			  <label class="form-label">Pseudo <span class="red">*</span></label>
			  <input required type="text" name="pseudo" pattern="\b\w{4,30}\b" value="${sessionScope['sessionUtilisateur'].getPseudo()}" class="form-control">
			</div>
			
			<div class="mb-3">
			  <label class="form-label">Téléphone</label>
			  <input type="text" name="telephone" pattern="^(?:(?:\+|00)33|0)\s*[1-9](?:[\s.-]*\d{​​​2}​​​){​​​4}​​​$" value="${sessionScope['sessionUtilisateur'].getTelephone()}" class="form-control">
			</div>
			
			<div class="mb-3">
			  <label class="form-label">Rue <span class="red">*</span></label>
			  <input required type="text" name="rue" value="${sessionScope['sessionUtilisateur'].getRue()}" class="form-control">
			</div>
			
			<div class="mb-3">
			  <label class="form-label">Code postal <span class="red">*</span></label>
			  <input required type="text" name="codePostal" input="^\d{​​​​​​​​​​​​​​​​​​5}​​​​​​​​​​​​​​​​​​(?:[\s-]\w{​​​​​​​​​​​​​​​​​​2}​​​​​​​​​​​​​​​​​​)?$" value="${sessionScope['sessionUtilisateur'].getCodePostal()}" class="form-control">
			</div>
			<c:if test="${not empty requestScope['erreurCodePostal']}">
				<p class="error-text">${sessionScope['sessionUtilisateur']}</p>
			</c:if>
			
			<div class="mb-3">
			  <label class="form-label">Ville <span class="red">*</span></label>
			  <input required type="text" name="ville" pattern="^[a-zA-ZÀ-ÖØ-öø-ÿ]+(?:[\s-']+[a-zA-ZÀ-ÖØ-öø-ÿ]+)*$" value="${sessionScope['sessionUtilisateur'].getVille()}" class="form-control">
			</div>
			
			<div class="mx-auto" style="width: 95%; height: 1px; background-color: #d9d9d9; margin-bottom: 20px"></div>
			
		  <div class="col">
		    <label class="form-label">Confirmer votre mot de passe <span class="red">*</span> </label>
		  	<input required type="password" name="confirmation" pattern="^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[^\w\s]).{​​​​​​​​​​​​​​​​​​​​​​​​12,50}​​​​​​​​​​​​​​​​​​​​​​​​$" class="form-control">
		  </div>
			
			<button class="btn btn-secondary" style="margin: 20px 0; border: none" onclick="retourAction()" type="button">Retour</button>
			<button disabled="disabled" class="btn btn-secondary" style="margin: 20px 0; border: none" type="button">Modifier le mot de passe</button>
			<button class="btn btn-primary orange-background" style="margin: 20px 0; border: none" type="submit">Enregistrer</button>
		</form>
		
	</section>
	
</body>
</html>