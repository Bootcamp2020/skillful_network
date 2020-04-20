# Réseau Habile - Plateforme de formations et de recrutement 

| Master status | Develop status |
| --- | --- |
| [![Build Status](https://travis-ci.org/Bootcamp2020/skillful_network.svg?branch=master)](https://travis-ci.org/Bootcamp2020/skillful_network) | [![Build Status](https://travis-ci.org/Bootcamp2020/skillful_network.svg?branch=develop)](https://travis-ci.org/Bootcamp2020/skillful_network)

Ce projet a été réalisé durant le Bootcamp en 2020. C'est une plateforme qui permet de dynamiser les acteurs sociaux autour de la recherche d'opportunité et/ou de reconversion professionnelle.

Du 9 mars 2020 au 16 avril 2020, 26 dévelopeurs ont participés à son élaborations.

## Scénario

1. Le candidat s’identifie / s’inscrit avec son email.
1. Le candidat complète son profil avec son parcours, ses qualifications et ses compétences. Il renseigne également un objectif professionnel.
1. Le candidat lance et complète une simulation.
1. Le candidat obtient, en fin de simulation, une proposition : une formation complémentaire ou spécialisante, pour une offre d’emploi donnée en adéquation avec son profil et le résultat de la simulation.
1. L’application renvoie vers une page de candidature à un couple formation ; offre d’emploi
1. Le candidat peut exprimer son intérêt pour le couple (formation ; carrière) afin de monter en compétence / qualifications et obtenir un poste.

## Source de la plateforme

Dans ce projet, on trouve de sous-projets : 

1. le `frontend` dans le dossier `skillful_network_client`, developpé en Angular 9 et en basant sur material.
2. le `backend` dans le dossier `skillful_network_server`, developpé en Java avec Spring-boot.

## Build

1. Cloner le dépôt
```sh
git clone https://github.com/Bootcamp2020/skillful_network.git
```

2. Build et lancer le frontend :
```sh
cd skillful_network/skillful_network_client/
npm install
ng serve
```

3. Build et lancer le backend (depuis `skillful_network`) :

```sh
mvn clean package spring-boot:run -DskipTests -Dspring-boot.run.profiles=test -f skillful_network_server/pom.xml 
```

4. Constater le résultat dans votre navigateur web en vous connectant à `localhost:4200`.

Le serveur est chargé avec une base de données basique permetant l'utilisateur de la plateforme. Un example d'identifiants valides est le couple suivant `john@uca.fr`;`12345678`.

## Contribution

Les contributions à se projet se font par pull-request. L'intégration continue doit rester vert.
