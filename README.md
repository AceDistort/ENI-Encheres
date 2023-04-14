# ENI-Encheres

Projet réalisé dans le cadre de la formation JAVA EE de l'ENI. Le projet a duré deux semaines (du 3 au 14 avril 2023).

## Contributions

Projet en collaboration avec @HugoBrunetENI et @Tin0GuiGui.

## Description du projet

Le problème levé est qu'encore aujourd'hui beaucoup d'objets encore en état de marche sont jetés.
Le but a donc été de réaliser un site d'enchères pour permettre aux utilisateurs de proposer des objets à la vente. D'autres utilisateurs peuvent enchérir sur cette vente afin d'espérer acquérir l'objet.
Le système n'utilise aucun fonds réels, tout fonctionne grâce à un système par points :
* lorsqu'un utilisateur vend un objet il acquiert des points;
* lorsqu'il achète un article, il utilise ses points.

À son inscription, l'utilisateur acquiert 100 points sans payer quoi que ce soit.

De nombreuses fonctionnalités sont fonctionnelles mais certaines ont des problèmes, dont la possibilité d'enchérir.

## Base de données

### Création de la base sous SQL Server 2019

```SQL
-- Script de creation de la base de donnees ENCHERES
--   type :      SQL Server 2012
--   à :         2019-11-23 18:00:51 CET

USE ENCHERES;
GO

-- nettoyage de la base
DROP TABLE IF EXISTS RETRAITS;
DROP TABLE IF EXISTS ENCHERES;
DROP TABLE IF EXISTS ARTICLES_VENDUS;
DROP TABLE IF EXISTS CATEGORIES;
DROP TABLE IF EXISTS UTILISATEURS;

CREATE TABLE CATEGORIES (
    no_categorie   INTEGER IDENTITY(1,1) NOT NULL,
    libelle        VARCHAR(30) NOT NULL
)

ALTER TABLE CATEGORIES ADD CONSTRAINT PK_CATEGORIES PRIMARY KEY (no_categorie)

CREATE TABLE ENCHERES (
    no_utilisateur   INTEGER NOT NULL,
    no_article       INTEGER NOT NULL,
    date_enchere     DATETIME NOT NULL,
    montant_enchere  INTEGER NOT NULL
)

ALTER TABLE ENCHERES ADD CONSTRAINT PK_ENCHERES PRIMARY KEY (no_utilisateur, no_article, date_enchere)

CREATE TABLE RETRAITS (
	no_article         INTEGER NOT NULL,
    rue              VARCHAR(30) NOT NULL,
    code_postal      VARCHAR(15) NOT NULL,
    ville            VARCHAR(30) NOT NULL
)

ALTER TABLE RETRAITS ADD CONSTRAINT PK_RETRAITS PRIMARY KEY  (no_article)

CREATE TABLE UTILISATEURS (
    no_utilisateur   INTEGER IDENTITY(1,1) NOT NULL,
    pseudo           VARCHAR(30) NOT NULL,
    nom              VARCHAR(30) NOT NULL,
    prenom           VARCHAR(30) NOT NULL,
    email            VARCHAR(254) NOT NULL,
    telephone        VARCHAR(15),
    rue              VARCHAR(30) NOT NULL,
    code_postal      VARCHAR(10) NOT NULL,
    ville            VARCHAR(30) NOT NULL,
    mot_de_passe     VARCHAR(255) NOT NULL,
    credit           INTEGER DEFAULT 100 NOT NULL,
    administrateur   BIT DEFAULT 0 NOT NULL
)

ALTER TABLE UTILISATEURS ADD CONSTRAINT PK_UTILISATEUR PRIMARY KEY (no_utilisateur)


CREATE TABLE ARTICLES_VENDUS (
    no_article                    INTEGER IDENTITY(1,1) NOT NULL,
    nom_article                   VARCHAR(30) NOT NULL,
    description                   VARCHAR(300) NOT NULL,
	date_debut_encheres           DATE NOT NULL,
    date_fin_encheres             DATE NOT NULL,
    prix_initial                  INTEGER NOT NULL,
    prix_vente                    INTEGER,
    no_utilisateur                INTEGER,
    no_categorie                  INTEGER NOT NULL
)

ALTER TABLE ARTICLES_VENDUS ADD CONSTRAINT PK_ARTICLES_VENDUS PRIMARY KEY (no_article)

ALTER TABLE ENCHERES
    ADD CONSTRAINT FK_ENCHERES_UTILISATEURS FOREIGN KEY ( no_utilisateur ) REFERENCES UTILISATEURS ( no_utilisateur )
ON DELETE NO ACTION 
    ON UPDATE no action 

ALTER TABLE ENCHERES
    ADD CONSTRAINT FK_ENCHERES_ARTICLES_VENDUS FOREIGN KEY ( no_article ) REFERENCES ARTICLES_VENDUS ( no_article )
ON DELETE NO ACTION 
    ON UPDATE no action 

ALTER TABLE RETRAITS
    ADD CONSTRAINT FK_RETRAITS_ARTICLES_VENDUS FOREIGN KEY ( no_article ) REFERENCES ARTICLES_VENDUS ( no_article )
ON DELETE NO ACTION 
    ON UPDATE no action 

ALTER TABLE ARTICLES_VENDUS
    ADD CONSTRAINT FK_ARTICLES_VENDUS_CATEGORIES FOREIGN KEY ( no_categorie ) REFERENCES CATEGORIES ( no_categorie )
ON DELETE NO ACTION 
    ON UPDATE no action 

ALTER TABLE ARTICLES_VENDUS
    ADD CONSTRAINT FK_VENTES_UTILISATEURS FOREIGN KEY ( no_utilisateur ) REFERENCES UTILISATEURS ( no_utilisateur )
ON DELETE NO ACTION 
    ON UPDATE no action 

-- CONTRAINTES D'INTEGRITE

ALTER TABLE UTILISATEURS ADD CONSTRAINT UN_UTILISATEURS_CREDIT_POSITIF UNIQUE(pseudo)
ALTER TABLE UTILISATEURS ADD CONSTRAINT CK_UTILISATEURS_CREDIT_POSITIF CHECK(credit >= 0)

ALTER TABLE ARTICLES_VENDUS ADD CONSTRAINT CK_COHERENCE_DATES CHECK(date_debut_encheres < date_fin_encheres)
ALTER TABLE ARTICLES_VENDUS ADD CONSTRAINT CK_COHERENCE_PRIX CHECK(prix_initial <= prix_vente)
ALTER TABLE ARTICLES_VENDUS ADD CONSTRAINT CK_PRIX_INITIAL_POSITIF CHECK(prix_initial >= 0)
ALTER TABLE ARTICLES_VENDUS ADD CONSTRAINT CK_PRIX_VENTE_POSITIF CHECK(prix_vente > 0)


-- PROCEDURES
DROP PROCEDURE IF EXISTS ajout_enchere;
GO

 -- Procédure d'ajout d'une enchère, vérifier si la vente n'est pas terminée, débiter du crédit sur le compte (si l'utilisateur en a assez) et rendre le crédit à l'utilisateur précédent. Penser à update la valeur de vente de l'article
CREATE PROCEDURE ajout_enchere(
	@IdArticle integer,
	@IdEncherisseur integer,
	@MontantEnchere integer)
AS
BEGIN
	SET NOCOUNT ON
	BEGIN TRANSACTION placer_enchere;
	BEGIN TRY

		DECLARE @IdDernierEncherisseur integer
		DECLARE @MontantDerniereEnchere integer
		DECLARE @DateDerniereEnchere Date
		SELECT TOP 1 @IdDernierEncherisseur = no_utilisateur, @DateDerniereEnchere = date_enchere, @MontantDerniereEnchere = montant_enchere FROM ENCHERES WHERE no_article = @IdArticle ORDER BY date_enchere DESC

		DECLARE @DateFinVente Date
		DECLARE @IdVendeur int
		SELECT @DateFinVente = date_fin_encheres, @IdVendeur = no_utilisateur FROM ARTICLES_VENDUS WHERE no_article = @IdArticle

		-- si la vente est terminée
		IF DATEDIFF(day, @DateFinVente, GETDATE()) > 0 THROW 50001, 'La vente est terminée, impossible d''ajouter une enchère.', 1;

		-- si l'utilisateur essaie de poser une enchère sur sa propre vente
		IF @IdEncherisseur = @IdVendeur THROW 50004, 'Impossible d''enchérir sur sa propre vente', 1;

		-- si l'utilisateur essaie de surrenchérir sur lui-même 
		IF @IdEncherisseur = @IdDernierEncherisseur THROW 50005, 'Impossible de surenchérir sur sa propre proposition', 1;

		-- si le montant n'est pas supérieur à la dernière enchère
		IF @MontantEnchere < ISNULL(@MontantDerniereEnchere, 0) THROW 50002, 'Le montant ne dépasse pas celui de la dernière enchère, impossible d''ajouter l''enchère.', 1;

		DECLARE @CreditUtilisateur integer
		SELECT @CreditUtilisateur = credit FROM UTILISATEURS WHERE no_utilisateur = @IdEncherisseur
		-- si l'utilisateur n'a pas assez de crédit
		IF @CreditUtilisateur < @MontantEnchere THROW 50003, 'Crédit insuffisant, impossible de placer une enchère.', 1;

		-- débit des crédits sur le compte de l'enchérisseur
		UPDATE UTILISATEURS SET credit = credit - @MontantEnchere WHERE no_utilisateur = @IdEncherisseur

		-- crédit des points de l'ancien enchérisseur
		UPDATE UTILISATEURS SET credit = credit + @MontantDerniereEnchere WHERE no_utilisateur = @IdDernierEncherisseur

		-- insert de l'enchère
		INSERT INTO ENCHERES(no_utilisateur, no_article, date_enchere, montant_enchere) VALUES (@IdEncherisseur, @IdArticle, GETDATE(), @MontantEnchere)

		-- mise à jour du champ prix_vente
		UPDATE ARTICLES_VENDUS SET prix_vente = @MontantEnchere WHERE no_article = @IdArticle

		COMMIT TRAN placer_enchere;
	END TRY

	BEGIN CATCH
		ROLLBACK TRAN placer_enchere;
		THROW;
	END CATCH
END;

USE ENCHERES
GO
GRANT EXECUTE ON dbo.ajout_enchere TO utilisateurBDD;

```

### Insertion d'un jeu de données de test
```SQL
USE ENCHERES;
GO

BEGIN TRAN insertion_jeu_donnees;

DELETE ENCHERES;
DELETE ARTICLES_VENDUS;
DELETE CATEGORIES;
DELETE UTILISATEURS;
DELETE RETRAITS;

INSERT INTO CATEGORIES (libelle) VALUES ('Informatique'), ('Ameublement'), ('Vêtement'), ('Sport&Loisirs');

INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur)
VALUES 
('alice', 'Dubois', 'Alice', 'alice@mail.com', '0654789214', 'rue des Lilas', '75020', 'Paris', '1000:1b590fa8dd7dc0f01136f5ecaf57f46c:41e93e40c47b0edecd9a25f4c7bf7ffec6f85875d125cb72031f3a14068d09e84680510e6dc4acc22423eb8148fc0f4f0b6f354401bbddc5c1f602c6f9e6f790', 200, 0),--'motdepasse'
('bob', 'Martin', 'Bob', 'bob@mail.com', '0612354789', 'rue de la Paix', '69002', 'Lyon', '1000:73fb5fa5bf3780e2cef16ba1bff7569c:31ae8dda1ae4f170f46f52c3efe21cd4ccc313c841aa7e044caedf447de3d51fd656564c3138b2137ede7ab9f8db6e79e3eacf7ff1f33a40e80860bd755fc2d1', 150, 0),--'password'
('carla', 'Dupont', 'Carla', 'carla@mail.com', '0745632189', 'avenue du Mont-Blanc', '74000', 'Annecy', '1000:eed60179d372a9b79846fd675f5b91ff:fe1cd90dbb721a0edb893735736118cf90743f0c78f19476cb22a5f28528f0f6f3be8fa7a8e8e7c4c00a4891d54e66d3d6ad13a5ba3d852e71d3eb34a82b3430', 500, 0),--'mdp123'
('david', 'Lefevre', 'David', 'david@mail.com', '0625478913', 'rue des Acacias', '75017', 'Paris', '1000:18649831de63e3ba8dc8492169c5d001:9174d7b2847c4d693cc831c5688a39cdf55a814f0747a9577bf004eee7185c0d306c04b92878ead66b937c585d44f2ef58142300f8bd698cbfb40f0646b65c6d', 0, 0),--'test123'
('emilie', 'Rousseau', 'Emilie', 'emilie@mail.com', '0658963472', 'rue de la République', '13001', 'Marseille', '1000:9ef5a0c515e6bb577415e0dbdd9cab48:48cac04a46458c0f44c7d1cfc7960c5b3cae15498c421319b8cd5c75b7fbde0f7987c60c19e676960cabcf321eb9273a70ee6fb7e2a7b627b379f6aa0029693d', 50, 0),--'pass123'
('fanny', 'Bouchard', 'Fanny', 'fanny@mail.com', '0678954321', 'rue de la Gare', '21000', 'Dijon', '1000:586b9ad7b4483909dcb6919d1caf0851:2b7251fff6e98007995ba3b909f107fc83d64acec56ec3f9da4c5f78368c844182eb2b94f9b25d4a6c40cb47ba89d09212a90516adaf7ada5841b3d9e2d18941', 100, 0),--'testmdp'
('greg', 'Thibault', 'Gregory', 'greg@mail.com', '0654789231', 'rue du Stade', '69008', 'Lyon', '1000:f3db7706a10ecb56eb7938fdad5d8bc6:fa661db2b2e347075b0b4756341c2d0bd2a73724bb34701529a43b6360b5528ef330aa83542cf87ff77d59f9c5bd3818c4f7eeaa70a9c34a43e364857a7aaade', 0, 0),--'testpass'
('henri', 'Leclerc', 'Henri', 'henri@mail.com', '0689547321', 'rue du Château', '75014', 'Paris', '1000:18b3b455bfe94c7dca1083b2d7737ca3:46ee01f98510b7de2862354e244da34918388a778e7290a98e0a9677296ac4c10c1d6b80771f983ba132b163a2c4de2ff03d2966ef7e33e9ceb2d407cefbf285', 0, 0),--'test1234'
('admin','admin','admin','','','','','','1000:940614cd844f107eb677e9186b70ac3f:7fe64d266966e194a8bafb3ab9745d3bd51e397680a92427dce2b8f8b64058ba67213c7192d02ac604873928b68c9ba6ff9a9ffb0585b4dec83336b9f2bfb64a',99999999,1);--'admin'

INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie)
VALUES ('Ordinateur portable HP', 'Ordinateur portable HP modèle X8X55EA avec processeur Intel Core i5 et disque dur SSD de 256 Go', '2023-04-01', '2023-04-13', 0, NULL, 1, 1),
('Canapé d''angle', 'Canapé d''angle en cuir noir avec pouf assorti', '2023-03-31', '2023-04-25', 700, 800, 2, 2),
('Chemise en lin', 'Chemise en lin beige pour homme, taille M', '2023-04-02', '2023-04-20', 30, NULL, 3, 4),
('Tapis de course', 'Tapis de course pliable avec écran LCD et capteurs de rythme cardiaque', '2023-03-30', '2023-04-07',50,NULL,1,4);

INSERT INTO ENCHERES (no_article, no_utilisateur, date_enchere, montant_enchere)
VALUES (1, 1, '2023-04-10', 20);

COMMIT TRAN insertion_jeu_donnees;


```
