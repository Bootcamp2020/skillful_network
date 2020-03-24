# Réseau Habile - Plateforme de formations et de recrutement 

| Master status | Develop status |
| --- | --- |
| [![Build Status](https://travis-ci.org/danglotb/skillful_network.svg?branch=master)](https://travis-ci.org/danglotb/skillful_network) | [![Build Status](https://travis-ci.org/danglotb/skillful_network.svg?branch=develop)](https://travis-ci.org/danglotb/skillful_network) 

## Scénario

1. Le candidat s’identifie / s’inscrit avec son email.
1. Le candidat complète son profil avec son parcours, ses qualifications et ses compétences. Il renseigne également un objectif professionnel.
1. Le candidat lance et complète une simulation.
1. Le candidat obtient, en fin de simulation, une proposition : une formation complémentaire ou spécialisante, pour une offre d’emploi donnée en adéquation avec son profil et le résultat de la simulation.
1. L’application renvoie vers une page de candidature à un couple formation ; offre d’emploi
1. Le candidat peut exprimer son intérêt pour le couple (formation ; carrière) afin de monter en compétence / qualifications et obtenir un poste. 

## Product Backlog

Le Product Backlog, la liste des l'API et d'autres informations sont disponibles [ici](https://docs.google.com/spreadsheets/d/1lbNliISNaqo5MqHFgB9sjYDznmNMU6qPSyGo6n5AKzo/edit?usp=sharing).

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

