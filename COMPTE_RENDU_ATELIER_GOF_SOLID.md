# 📘 Compte Rendu d'Atelier — Améliorer la conception d'AutoLoc avec GoF et SOLID
> **Module** : Architecture des Systèmes d'Information (ASI 4CCE13) — ESPRIT  
> **Auteur** : Amine Mezni (`amine.mezni@esprit.tn`)  
> **Projet** : AutoLoc (Plateforme de location de véhicules multi-agences)  
> **Date** : 28 Octobre 2026  

---

## 📑 Sommaire
1. [Partie A — Identifier les problèmes de conception](#1-partie-a--identifier-les-probl%C3%A8mes-de-conception)
2. [Partie B — Appliquer SRP et DIP](#2-partie-b--appliquer-srp-et-dip)
3. [Partie C — GoF Strategy et principe OCP](#3-partie-c--gof-strategy-et-principe-ocp)
4. [Partie D — Reconnaître Factory, ISP et LSP](#4-partie-d--reconna%C3%AEtre-factory-isp-et-lsp)
5. [Synthèse globale des principes SOLID et patrons GoF](#5-synth%C3%A8se-globale-des-principes-solid-et-patrons-gof)

---

## 1. Partie A — Identifier les problèmes de conception

### Question 1 : Identifiez les différentes responsabilités prises en charge par `VehiculeService`
Dans la version initiale non refactorisée, la classe `VehiculeService` enfreint le principe de responsabilité unique en concentrant **4 responsabilités distinctes** :
1. **Orchestration métier** : coordination du cycle de vie des véhicules (`ajouter`, `calculerTarif`).
2. **Validation des données d'entrée** : vérification des contraintes d'intégrité (`tarifJournalier <= 0`, `nombreJours <= 0`).
3. **Journalisation / Audit** : écriture sur la sortie standard (`System.out.println("Ajout du véhicule")`).
4. **Calcul financier / Algorithmique tarifaire** : application de formules de calcul monétaire (`tarifJournalier * nombreJours`).

### Question 2 : Pourquoi la validation, la journalisation et la gestion des véhicules pourraient-elles évoluer indépendamment ?
- **La validation** évolue selon les règles légales et contraintes métier (ex: format du numéro de matricule tunisien, seuils minimaux/maximaux de prix). Si une règle de validation change, le code de gestion du véhicule ne devrait pas être altéré.
- **La journalisation** dépend de l'infrastructure technique (ex: remplacer `System.out.println` par un framework professionnel comme SLF4J/Logback, écrire dans un fichier tournant, ou envoyer les traces vers un serveur distant Elasticsearch/Logstash). Cela ne concerne en rien la logique fonctionnelle de location.
- **La gestion des véhicules** évolue selon les besoins des agences (ex: changement de statut disponible/loué, association à une succursale).  
*Mélanger ces aspects au même endroit multiplie les risques de régression lors de la maintenance.*

### Question 3 : Quel principe SOLID pourrait améliorer cette conception ?
Le **Single Responsibility Principle (SRP)** :  
> *« Une classe ne doit avoir qu'une seule et unique raison de changer. »*  
En appliquant SRP, chaque aspect (validation, traçabilité, tarification, persistance) est confié à un composant spécialisé.

### Question 4 : Proposez une répartition plus claire des responsabilités
| Composant proposé | Responsabilité unique |
| :--- | :--- |
| **`VehiculeService`** | Coordonner les opérations sur les véhicules et déléguer aux spécialistes. |
| **`VehiculeValidator`** | Vérifier la conformité des données du véhicule avant toute opération. |
| **`JournalisationService`** | Centraliser l'enregistrement des événements et des actions métier. |
| **`TarificationService`** | Gérer l'algorithmique et le calcul des devis de location. |

---

## 2. Partie B — Appliquer SRP et DIP

### B.1. Principe de Responsabilité Unique (SRP)
Dans notre solution, la méthode `ajouterVehicule()` a été refactorisée pour déléguer chaque étape :

```java
@Service
@RequiredArgsConstructor
public class VehiculeServiceImpl implements IVehiculeService {

    private final VehiculeRepository vehiculeRepository;
    private final VehiculeValidator vehiculeValidator;         // SRP : Validation
    private final JournalisationService journalisationService; // SRP : Logs
    private final TarificationService tarificationService;     // SRP : Calculs

    @Override
    public Vehicule ajouterVehicule(Vehicule vehicule) {
        // 1. Validation métier isolée
        vehiculeValidator.valider(vehicule);

        // 2. Journalisation centralisée
        journalisationService.journaliserAction("AJOUT", "Vehicule", vehicule.getImmatriculation());

        // 3. Persistance
        return vehiculeRepository.save(vehicule);
    }
}
```

### B.2. Principe d'Inversion des Dépendances (DIP)

#### 1. Quel est le rôle de `VehiculeRepository` dans cette architecture ?
`VehiculeRepository` est une interface qui matérialise le **patron Repository**. Elle fournit une abstraction de haut niveau pour accéder aux données de l'entité `Vehicule` (CRUD, requêtes dérivées) sans exposer la mécanique technique sous-jacente (Hibernate, requêtes SQL, JDBC).

#### 2. Pourquoi le service dépend-il d'un contrat plutôt que de créer lui-même son mécanisme de persistance ?
- **Couplage faible (Loose Coupling)** : Le service n'a pas à connaître le SGBD utilisé (PostgreSQL, MySQL, base H2 en mémoire).
- **Testabilité unitaire** : Il devient trivial de tester `VehiculeServiceImpl` en lui injectant un *Mock* (`Mockito.mock(VehiculeRepository.class)`) sans avoir besoin de démarrer une base de données réelle.
- **Interchangeabilité** : Si l'architecture migre vers un stockage NoSQL ou une API externe, la couche métier ne subit aucune modification.

#### 3. Expliquez comment Spring injecte le repository dans le service
1. Au démarrage, Spring scanne les packages (`@EnableJpaRepositories`).
2. Spring Data JPA génère dynamiquement au runtime une **classe proxy implémentant `VehiculeRepository`**.
3. Grâce à l'annotation Lombok `@RequiredArgsConstructor`, un constructeur prenant `VehiculeRepository` en paramètre est généré sur `VehiculeServiceImpl`.
4. Le conteneur IoC de Spring résout la dépendance et **injecte l'instance de ce proxy dans le constructeur** du service (*Constructor-based Dependency Injection*).

#### 4. Quel est le lien entre DIP et l'Injection de Dépendances (DI) ?
- **DIP (Dependency Inversion Principle)** est le **principe architectural de conception** : *« Les modules de haut niveau ne doivent pas dépendre des modules de bas niveau ; les deux doivent dépendre d'abstractions »*.
- **DI (Dependency Injection)** est le **mécanisme technique** qui concrétise ce principe : au lieu que `VehiculeService` instancie lui-même sa persistance (`new ...`), c'est un agent externe (le framework Spring) qui lui transmet l'abstraction déjà configurée.

---

## 3. Partie C — GoF Strategy et principe OCP

### Architecture de la solution implémentée
Nous avons mis en place le patron **GoF Strategy** couplé à la découverte automatique de Spring :

1. **Abstraction** : `StrategieTarification` avec la méthode `double calculer(double tarifJournalier, int jours)`.
2. **Stratégies concrètes** annotées en composants Spring :
   - `TarificationStandard` (`@Component("STANDARD")`) : $tarif 	imes jours$
   - `TarificationWeekend` (`@Component("WEEKEND")`) : $-10\%$
   - `TarificationLongueDuree` (`@Component("LONGUE_DUREE")`) : $-20\%$
   - `TarificationEntreprise` (`@Component("ENTREPRISE")`) : $-15\%$ *(Étape 4)*
3. **Contexte d'exécution** : `TarificationService` reçoit automatiquement par injection une `Map<String, StrategieTarification>` contenant toutes les stratégies découvertes dans le contexte Spring.

```java
@Service
@RequiredArgsConstructor
public class TarificationService {

    private final Map<String, StrategieTarification> strategies;

    public double calculerPrix(String typePolitique, double tarifJournalier, int jours) {
        StrategieTarification strategie = strategies.get(typePolitique.toUpperCase());
        if (strategie == null) {
            throw new IllegalArgumentException("Type de tarif inconnu : " + typePolitique);
        }
        return strategie.calculer(tarifJournalier, jours);
    }
}
```

### Réponses aux questions de conception

#### 1. Quel problème le patron Strategy permet-il de résoudre ?
Il élimine les blocs conditionnels rigides et extensibles à l'infini (`if-else if` ou `switch-case`) qui polluent le code lorsque plusieurs variantes d'un même algorithme coexistent. Il encapsule chaque algorithme dans une classe interchangeable.

#### 2. Pourquoi est-il préférable d'isoler chaque formule dans une classe différente ?
- **Indépendance des calculs** : modifier les conditions du tarif Week-end ne risque pas d'altérer la formule Longue Durée.
- **Tests unitaires dédiés** : chaque stratégie possède sa propre classe de tests ciblée.
- **Lisibilité** : chaque classe fait moins de 25 lignes et ne contient qu'une seule formule explicite.

#### 3. Comment cette conception applique-t-elle le principe OCP (Open/Closed Principle) ?
Le système est :
- **Ouvert à l'extension (Open)** : Pour ajouter la politique `TarificationEntreprise` (Étape 4), il a suffi de créer une nouvelle classe implémentant `StrategieTarification` avec `@Component("ENTREPRISE")`.
- **Fermé à la modification (Closed)** : La classe `TarificationService` n'a **pas été modifiée d'une seule virgule** ! Spring injecte automatiquement la nouvelle stratégie dans la `Map`.

#### 4. Que doit faire le service si le type de tarif demandé n'existe pas ?
Le service doit lever une exception métier explicite (`IllegalArgumentException`), en rejetant la saisie avec un message clair listant les stratégies valides disponibles (`getPolitiquesDisponibles()`), évitant tout calcul erroné silencieux.

---

## 4. Partie D — Reconnaître Factory, ISP et LSP

### Situation 1 — Factory (Création de factures PDF / Excel)
- **Problème résolu par une `FactureFactory`** :  
  La création de documents (PDF via iText, Excel via Apache POI) implique des initialisations lourdes, des dépendances techniques et des configurations de styles. La Factory masque cette complexité d'instanciation au reste de l'application.
- **Signature de la méthode exposée** :  
  `public DocumentFacture creerFacture(TypeDocument format, Contrat contrat)`
- **Avantage de la centralisation** :  
  Point unique d'instanciation, respect du principe OCP (l'ajout d'une facture XML ou Factur-X ne modifiera pas les contrôleurs ou les services appelants).

### Situation 2 — ISP (Interface Segregation Principle)
- **Principe concerné** :  
  L'**Interface Segregation Principle (ISP)** : *« Aucun client ne doit être contraint de dépendre d'interfaces qu'il n'utilise pas. »*
- **Proposition de découpage de `GestionVehicule`** :  
  Au lieu d'une interface monolithique ("interface grasse"), on la sépare en contrats cohérents et spécialisés :
  1. `VehiculeConsultationService` : `consulter(Long id)`, `listerTous()`
  2. `VehiculeOperationService` : `ajouter(...)`, `modifier(...)`, `supprimer(...)`
  3. `VehiculeRapportService` : `exporterRapport()`
  4. `VehiculeNotificationService` : `envoyerNotification()`

### Situation 3 — LSP (Liskov Substitution Principle)
- **Pourquoi lever `UnsupportedOperationException` viole LSP ?** :  
  Le **Liskov Substitution Principle (LSP)** exige que tout sous-type `S` puisse remplacer son type de base `T` sans altérer la cohérence du système. Si un appelant reçoit une référence de `Vehicule` et appelle une méthode normale, lever une `UnsupportedOperationException` est une rupture brutale du contrat promis. Le polymorphisme ne fonctionne plus de manière transparente.
- **Solution pour respecter le contrat** :  
  1. **Privilégier la composition à l'héritage** (*"Favor composition over inheritance"*).
  2. Découper la hiérarchie pour que la classe dérivée n'hérite que des contrats qu'elle implémente réellement et légitimement.

---

## 5. Synthèse globale des principes SOLID et patrons GoF

| Principe / Patron | Application concrète dans notre projet AutoLoc |
| :--- | :--- |
| **SRP** (*Single Responsibility*) | Séparation de `VehiculeService`, `VehiculeValidator` et `JournalisationService`. |
| **OCP** (*Open/Closed*) | Ajout de `TarificationEntreprise` sans retoucher `TarificationService`. |
| **LSP** (*Liskov Substitution*) | Respect des contrats d'interfaces sans lever d'exceptions inattendues. |
| **ISP** (*Interface Segregation*) | Interfaces spécialisées et ciblées (`StrategieTarification`, `JournalisationService`). |
| **DIP** (*Dependency Inversion*) | Dépendance vers les abstractions (`VehiculeRepository`, `StrategieTarification`) via injection Spring. |
| **GoF Strategy** | Algorithmes de calcul de prix interchangeables injectés dans une `Map`. |
| **GoF Factory** | Encapsulation de la génération de documents (factures PDF / Excel). |

---

###  Points d'accès pour tester la tarification (Swagger & Postman)
- `GET /api/tarification/politiques` : Liste les stratégies disponibles (`STANDARD`, `WEEKEND`, `LONGUE_DUREE`, `ENTREPRISE`).
- `GET /api/tarification/calculer?politique=WEEKEND&tarifJournalier=120&jours=5` : Calcule le tarif remisé.
- `GET /api/tarification/vehicule/1?politique=ENTREPRISE&jours=7` : Calcule la location pour le véhicule #1.
