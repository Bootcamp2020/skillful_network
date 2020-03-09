# Réseau Habile - Plateforme de formations et de recrutement 

[![Build Status](https://travis-ci.org/danglotb/skillful_network.svg?branch=master)](https://travis-ci.org/danglotb/skillful_network)

## Scénario

1. Le candidat s’identifie / s’inscrit avec son email.
1. Le candidat complète son profil avec son parcours, ses qualifications et ses compétences. Il renseigne également un objectif professionnel.
1. Le candidat lance et complète une simulation.
1. Le candidat obtient, en fin de simulation, une proposition : une formation complémentaire ou spécialisante, pour une offre d’emploi donnée en adéquation avec son profil et le résultat de la simulation.
1. L’application renvoie vers une page de candidature à un couple formation ; offre d’emploi
1. Le candidat peut exprimer son intérêt pour le couple (formation ; carrière) afin de monter en compétence / qualifications et obtenir un poste. 

## Product Backlog

Les scénarios sont listés dans l'ordre de leurs priorités, _i.e._ le premier est le plus prioritaire et le dernier est le moins prioritaire. 

| ID | Scénario | Status | Équipe |
| --- | --- | --- | --- |
| 1 | En tant qu'utilisateur, je peux m'enregistrer dans la plateforme à la première connexion. Pour cela, je dois fournir un email ou un numéro de mobile valide  | TBD | NA |
| 2 | En tant qu'utilisateur, je peux me connecter à mon compte personnel en saisissant dans un premier temps l'email ou le numéro de mobile que j'ai utilisé lors de ma première connexion, puis dans un second temps le code temporaire que le serveur enverra à l'un ou l'autre| TBD | NA |
| 3 | En tant qu'utilisateur et une fois connecté, je peux accèder à mon dashboard personnel. Dans un premier temps ce dashboard affichera toutes les informations concernant l'utilisateur. | TBD | NA |
| 4 | En tant qu'utilisateur et une fois connecté, je peux accèder à la liste de tous les utilisateurs, ainsi qu'à leur page personnel. Cette page personnel affichera toutes les informations concernant l'utilisateurs | TBD | NA |
| 5 | Le serveur charge et sauvegarde les utilisateurs depuis une base de données. Cette base de données peut être de différentes natures (_e.g._ fichiers JSON ou CSV) et peut être configurée au lancement du serveur (_e.g._ options sur la ligne de commande ou fichiers de configuration). | TBD | NA |
| w | x | y | z |

## API

| API | Description | Méthode |
| --- | ----------- | --- |
| `/user/<id>`| récupération de l’**utilisateur** avec l’id <id> | GET |
| `/users`| récupération de la **liste des utilisateurs** | GET |
| `/offer/<id>` | récupération de l’**offre d’emploi** avec l’id <id> | GET |
| `/offers` | récupération de la **liste des offres d’emploi** | GET |
| `/training/<id>` | récupération de **la formation** avec l’id <id> | GET |
| `/trainings` | récupération de la **liste des formations** | GET |
| `/login` | Tentative d’identification d’un utilisateur. Si l’utilisateur existe, le serveur renvoie l’id de l’utilisateur, sinon il en créer un nouveau. Lors de cette étape, le serveur génère également un code unique temporaire et l’envoie soit par email soit par mobile. | POST |
| `/token` | authentification de l’utilisateur. La requête doit contenir un code, qui doit correspondre au code généré par le serveur. Si le code et l’id de l'utilisateur correspondent, l’authentification est réussi | POST |
| `/offer` | création d’une nouvelle offre d’emploi | POST |
| `/training` | création d’une nouvelle formation | POST |
| `/user/<id>` | modification des informations de l’utilisateur avec l’id <id> | PUT |
| `/offer/<id>` | modification des informations de l’offre d’emploi avec l’id <id> | PUT |
| `/training/<id>` | modification des informations de la formation avec l’id <id> | PUT |

## Hierarchie

- skillful_network/
  - fr.uca.cdr.skillful_network_server/
    - src/main/java/
      - fr.uca.cdr.skillful_network
        - controller
        - model
          - entities
          - repository
        - security
        - Application.java
     - pom.xml
  - fr.uca.cdr.skillful_network_client/
    - src/
      - app/
        - user/ 
      - app.component.ts/html
    - index.html

