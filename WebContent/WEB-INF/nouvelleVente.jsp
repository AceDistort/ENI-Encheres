<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isErrorPage="false" errorPage="Erreur.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>	
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" type="image/x-icon" href="images/favicon.png">
<link href="styles/global-style.css" rel="stylesheet">
<script type="text/javascript" src="js/utils.js"></script>
<title>Nouvelle vente</title>
</head>
<body>

	<main class="mx-auto p-2" style="width: 80%; max-width: 700px; margin-top: 50px">
		<img src="images/logoENIEncheres.png" style="margin-bottom: 50px; width: 230px; margin-left: 40px">
		<p style="font-size: xx-large; font-weight: 600; margin-left: 40px">Créer une vente</p>
		<form method="post" action="vendre">
		
			<section class="d-flex flex-row flex-wrap justify-content-between" style="margin: 20px">
				<div style="flex: 2; margin: 0 20px; min-width: 250px">
					<div class="mb-3">
						<label class="form-label">Article <span class="red">*</span></label>
						<input id="nomArticle" name="nomArticle" type="text" required class="form-control">
					</div>
					<div class="mb-3">
						<label class="form-label">Description <span class="red">*</span></label>
						<textarea class="form-control" id="description" name="description" type="text" required rows="3"></textarea>
					</div>
					<div class="mb-3">
						<label class="form-label">Catégorie <span class="red">*</span></label>
						<select class="form-select" id="categorie" name="categorie" required>
							<c:forEach var="categorie" items="${requestScope['categories']}">
								<option value="${categorie.getNoCategorie()}">${categorie.getLibelle()}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				
				<div style="flex: 1; margin: 0 20px; min-width: 250px">
					<div class="mb-3">
						<label class="form-label">Prix de départ <span class="red">*</span></label>
						<div class="input-group mb-3 ombreInputs" style="border-radius: 5px">
							<input name="prixInitial" min="0" type="number" class="form-control" aria-describedby="prixAddon" style="box-shadow: none">
							<span class="input-group-text" id="prixAddon">points</span>
						</div>
					</div>
					<div class="mb-3">
						<label class="form-label">Début de l'enchère<span class="red">*</span></label>
						<input id="dateDebutEncheres" name="dateDebutEncheres" type="date" required class="form-control">
					</div>
					<div class="mb-3">
						<label class="form-label">Fin de l'enchère<span class="red">*</span></label>
						<input id="dateFinEncheres" name="dateFinEncheres" type="date" required class="form-control">
					</div>
				</div>
				
			</section>
				
			<section style="margin: 20px">
				<div style="margin: 0 20px; min-width: 250px">
					<p style="font-size: larger; font-weight: 600">Retrait</p>
				
					<div class="mb-3">
						<label class="form-label">Rue<span class="red">*</span></label>
						<input id="rue" name="rue" type="text" required value="${sessionScope['sessionUtilisateur'].getRue()}" class="form-control">
					</div>
					
					<div class="row mb-3">
					  <div class="col">
					    <label class="form-label">Ville <span class="red">*</span> </label>
					  	<input id="ville" name="ville" type="text" required value="${sessionScope['sessionUtilisateur'].getVille()}" pattern="^[a-zA-ZÀ-ÖØ-öø-ÿ]+(?:[\s-']+[a-zA-ZÀ-ÖØ-öø-ÿ]+)*$" class="form-control">
					  </div>
					  <div class="col">
					    <label class="form-label">Code Postal <span class="red">*</span></label>
					  	<input id="codePostal" name="codePostal" type="text" required pattern="[0-9]{5}" value="${sessionScope['sessionUtilisateur'].getCodePostal()}" class="form-control">
					  	<c:if test="${not empty requestScope['erreurCodePostal']}">
							<p class="error-text">${requestScope['erreurCodePostal']}</p>
						</c:if>
					  </div>
					</div>
				</div>
			</section>
			
			<section style="margin-left: 40px">
				<button class="btn btn-secondary" style="margin: 20px 0; border: none" onclick="retourAction()" type="button">Retour</button>
				<button class="btn btn-primary orange-background" style="margin: 20px 0; border: none" type="submit">Créer</button>
			</section>
			
		</form>		
	</main>
</body>
</html>