# 🚀 Gestion de Projets — Application Spring Boot & SpringDoc OpenAPI

> **Module** : Architecture des Systèmes d'Information (ASI 4CCE13) — ESPRIT  
> **Auteur** : Amine Mezni (`amine.mezni@esprit.tn`)  
> **Branche** : `gestion-projet`  
> **Référence de cours** : Chapitre 6 — *Documentation API REST avec Spring Doc (OpenAPI Specification)*  

---

## 📌 Présentation du Projet
Ce projet implémente l'application de démonstration du **Chapitre 6 - Spring Doc** pour la gestion de projets en entreprise.
Il expose des Web Services REST CRUD documentés avec les annotations OpenAPI v3 (**SpringDoc UI**) :
- `@Tag(name = "Gestion Projet")`
- `@Operation(description = "...")`
- Personnalisation du serveur sur le port `8089` avec le chemin de contexte `/gestionprojets`.

---

## 🏗️ Architecture des Entités

| Entité | Description | Relations JPA |
| :--- | :--- | :--- |
| **`Projet`** | Représente un projet informatique (sujet, dates début/fin). | `@OneToOne` avec `ProjetDetail`, `@ManyToOne` avec `Entreprise`, `@ManyToMany` avec `Equipe`. |
| **`ProjetDetail`** | Détails techniques (description, technologie, coût). | Clé étrangère 1-1 liée à `Projet`. |
| **`Entreprise`** | Entreprise cliente ou porteuse du projet. | `@OneToMany(mappedBy="entreprise")` vers `Projet`. |
| **`Equipe`** | Équipe de développement intervenant sur les projets. | `@ManyToMany(mappedBy="equipes")` vers `Projet`. |

---

## ⚙️ Configuration & Ports

- **Port HTTP** : `8089`
- **Context-Path** : `/gestionprojets`
- **Base de données** : H2 en mémoire (`jdbc:h2:mem:gestionprojetsdb`)
- **Console H2** : `http://localhost:8089/gestionprojets/h2-console`
- **Swagger UI** : 👉 **`http://localhost:8089/gestionprojets/swagger-ui/index.html`**
- **OpenAPI JSON Spec** : `http://localhost:8089/gestionprojets/v3/api-docs`

---

## 🧪 Endpoints REST Principaux (Slide 10)

| Méthode | URI | Description |
| :--- | :--- | :--- |
| `GET` | `/gestionprojets/projet/retrieve-all-projets` | Récupérer tous les projets de la base de données |
| `GET` | `/gestionprojets/projet/retrieve-projet/{id}` | Récupérer un projet par son ID |
| `POST` | `/gestionprojets/projet/add-projet` | Ajouter un nouveau projet |
| `PUT` | `/gestionprojets/projet/update-projet` | Mettre à jour un projet existant |
| `DELETE` | `/gestionprojets/projet/remove-projet/{id}` | Supprimer un projet par son ID |

*Des contrôleurs CRUD équivalents sont disponibles pour `Entreprise`, `Equipe` et `ProjetDetail`.*

---

## 🚀 Démarrage

```bash
# Avec Maven
mvn spring-boot:run
```
