# École des Loustics

## Description
École des Loustics est une application éducative Android conçue pour aider les enfants à développer leurs compétences en mathématiques et en culture générale de manière ludique. L'application propose différents mini-jeux incluant des exercices d'additions, des tables de multiplication, des questions de culture générale, ainsi qu'un jeu de Chifoumi (Pierre-Papier-Ciseaux).

## Fonctionnalités

- **Gestion des utilisateurs** : Création et sélection de profils utilisateurs pour suivre les progrès individuels
- **Exercices d'additions** : Mini-jeux pour pratiquer les additions
- **Tables de multiplication** : Apprentissage et pratique des tables de multiplication
- **Culture générale** : Questions variées pour développer la connaissance générale des enfants
- **Jeu de Chifoumi** : Version numérique du classique Pierre-Papier-Ciseaux
- **Système de score** : Suivi des performances pour motiver les enfants

## Technologies utilisées

- Java pour Android
- Room Database pour la persistance des données
- Architecture MVC (Model-View-Controller)
- Shared Preferences pour les paramètres utilisateur
- Composants Android standard (Activities, Layouts XML, etc.)

## Prérequis

- Android Studio Arctic Fox (2020.3.1) ou supérieur
- SDK Android minimum : API 24 (Android 7.0 Nougat)
- SDK Android cible : API 35
- Gradle 7.0+
- JDK 11

## Installation

1. Clonez ce dépôt sur votre machine locale :
```bash
git clone https://github.com/votre-compte/ecole-des-loustics.git
```

2. Ouvrez le projet dans Android Studio :
   - Lancez Android Studio
   - Sélectionnez "Open an existing Android Studio project"
   - Naviguez jusqu'au dossier cloné et sélectionnez-le

3. Synchronisez le projet avec Gradle :
   - Cliquez sur "Sync Project with Gradle Files"

4. Exécutez l'application :
   - Connectez un appareil Android ou démarrez un émulateur
   - Cliquez sur "Run" dans Android Studio

## Structure du projet

```
app/
├── src/main/
│   ├── java/fr/iut/ecoledesloustics/
│   │   ├── chifoumiData/       # Classes pour le jeu Chifoumi
│   │   ├── db/                 # Classes de base de données (Room)
│   │   │   ├── AppDatabase.java
│   │   │   ├── DatabaseClient.java
│   │   │   ├── User.java
│   │   │   ├── UserDAO.java
│   │   │   ├── Question.java
│   │   │   └── QuestionDao.java
│   │   ├── maths/              # Classes pour les exercices de mathématiques
│   │   ├── ui/                 # Composants d'interface utilisateur
│   │   └── ...                 # Activities principales
│   └── res/
│       ├── layout/             # Fichiers de mise en page XML
│       ├── drawable/           # Images et icônes
│       ├── values/             # Chaînes, couleurs, styles
│       └── ...
└── build.gradle                # Configuration de build de l'application
```

## Licence

Ce projet est sous licence MIT - voir le fichier LICENSE pour plus de détails.