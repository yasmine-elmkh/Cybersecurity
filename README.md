---------------------------------------Sécurisation d’une Application Spring Boot----------------------------------------

  Réalisé par:
             Yasmine El mkhantar  et  Hasnae Nid-Haddou (https://github.com/hasnae-bit)


🛡️ Objectif du projet
Ce projet vise à renforcer la sécurité d'une application Java Spring Boot en intégrant :

Une authentification sécurisée basée sur les rôles.
Un module de détection et prévention d’intrusions (IDS/IPS).
Une journalisation centralisée des événements critiques.
Un dashboard interactif pour l’analyse comportementale.

🗂️ Projet de base
Projet original : Cybersecurity_VictorElias
voici le lien du projet de base : https://github.com/250428-PracticeProjects/Cybersecurity_VictorElias

L'application est une solution de surveillance du trafic réseau (Java Full Stack) utilisant :

Backend : Spring Boot + PostgreSQL
Frontend : React (structure initiale non implémentée)
Objectif initial : exposer des API REST pour la capture, l’analyse et la détection de vulnérabilités réseau.

🔧 Améliorations apportées

1. 🔐 Authentification sécurisée
Implémentation de Spring Security.
Gestion des rôles (USER, ADMIN) avec contrôle d’accès aux routes API.
Activation de CORS & CSRF avec configuration fine.
Seuls les utilisateurs authentifiés peuvent accéder aux API selon leur rôle.

2. 🛡️ Module IDS/IPS intégré

Utilisation de Pcap4J pour la capture réseau en temps réel.
Détection d’anomalies via des règles de signatures (fichier emerging-all.rules).
Création des entités Anomaly, AnomalyService pour journaliser chaque intrusion.
Blocage automatique d’IP suspectes via iptables avec le composant IpBlocker.

Résultat :

Intrusions détectées en temps réel et stockées.
API REST pour consulter les anomalies : GET /api/anomalies
Protection proactive via blocage IP automatisé.

3. 📊 Journalisation centralisée & Dashboard

Création de l’entité SecurityEvent pour tracer les incidents critiques.
Enregistrement automatique des événements : échecs de login, alertes IDS...
Envoi d’emails d’alerte dès qu’un événement critique est détecté.
Développement d’un dashboard React avec vues :
Vue d’ensemble (KPIs de sécurité)
Liste des anomalies avec bouton de blocage IP
Logs filtrables par période/type
Statistiques visuelles (graphiques, heatmaps)

💻 Stack Technique

Backend : Java, Spring Boot, Spring Security, Spring Data JPA
Base de données : PostgreSQL
Frontend : React.js (dashboard admin)

📈 Fonctionnalités clés

✅ Authentification utilisateur (JWT possible)
✅ Rôles différenciés : USER, ADMIN
✅ IDS/IPS avec détection & blocage d’IP
✅ Centralisation des logs critiques
✅ Dashboard de supervision
✅ API REST sécurisées


