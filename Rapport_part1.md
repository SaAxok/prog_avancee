# Rapport Général - Partie 1

>[Retour au README](./README.md)


## Table des matières

- [Rapport Général - Partie 1](#rapport-général---partie-1)
  - [Table des matières](#table-des-matières)
  - [Définitions](#définitions)
    - [Thread](#thread)
    - [Sémaphore](#sémaphore)
    - [Ressource critique](#ressource-critique)
    - [Section critique](#section-critique)
    - [Programmation parallèle](#programmation-parallèle)
    - [Programmation partagée](#programmation-partagée)
    - [Parallélisme de tâches](#parallélisme-de-tâches)
  - [TP1 : Dossier de conception du TP Thread](#tp1--dossier-de-conception-du-tp-thread)
    - [Exercice 1](#exercice-1)
    - [Exercice 2](#exercice-2)
  - [TP2 : Affichage synchronisé](#tp2--affichage-synchronisé)
    - [Sémaphore](#sémaphore-1)
      - [Outils de Synchronisation](#outils-de-synchronisation)
      - [Implémentation](#implémentation)
      - [Exemple : Analogie avec un Phare](#exemple--analogie-avec-un-phare)
      - [Procédé](#procédé)
    - [Conclusion](#conclusion)
  - [TP3 : Boîte aux lettres](#tp3--boîte-aux-lettres)
    - [Analyse et Résolution](#analyse-et-résolution)
    - [Ce que j'ai appris :](#ce-que-jai-appris-)
    - [Conclusion](#conclusion-1)

## Définitions

### Thread
Un **thread** est la plus petite unité d'exécution dans un programme. Il représente un flux d'instructions qui peut s'exécuter indépendamment tout en partageant des ressources comme la mémoire et les fichiers avec d'autres threads du même processus.

### Sémaphore
Un **sémaphore** est une variable utilisée pour gérer l'accès concurrent à une ressource partagée. Il peut être binaire (1 ou 0) ou comptant (avec une valeur entière), et ses opérations principales sont :
- **`wait`** : Réduit la valeur du sémaphore et bloque si elle devient négative.
- **`signal`** : Augmente la valeur et réveille un thread bloqué si nécessaire.

### Ressource critique
Une **ressource critique** est un élément (mémoire, fichier, périphérique) partagé entre plusieurs threads ou processus, dont l'accès concurrent peut entraîner des incohérences si non contrôlé.

### Section critique
Une **section critique** est une portion du code où une ressource critique est accédée. Il est essentiel d'assurer qu'une seule entité (thread ou processus) l'exécute à la fois pour éviter des erreurs ou des incohérences.

### Programmation parallèle
La **programmation parallèle** consiste à exécuter plusieurs tâches en simultané sur des processeurs multiples, permettant d'accélérer l'exécution d'un programme en divisant les calculs ou tâches.

### Programmation partagée
La **programmation partagée** implique l'utilisation d'une mémoire unique accessible par plusieurs threads ou processus. Elle nécessite des mécanismes de synchronisation pour éviter les conflits.

### Parallélisme de tâches
Le **parallélisme de tâches** correspond à l'exécution simultanée de tâches différentes. Contrairement au parallélisme de données, chaque tâche effectue une opération distincte et indépendante.

## TP1 : Dossier de conception du TP Thread

### Exercice 1

**Implémentation :**
La classe `UnMobile` représente un mobile qui se déplace dans une interface graphique en utilisant un thread. Sa méthode `run()` gère le mouvement par incréments successifs, suivis de redessins avec `repaint()`.

**Ce que j'ai compris :**
Le fonctionnement d'un thread inclut :
- La gestion des boucles d'exécution (dans `run`).
- L'utilisation de `sleep` pour réguler la vitesse.
- L'importance de redessiner régulièrement les objets graphiques pour simuler le mouvement.

### Exercice 2

**Implémentation :**
Un mobile est ajouté à une fenêtre avec `add()` dans la classe `UneFenetre`, et un thread est lancé pour animer le mobile.

**Ce que j'ai appris :**
La manipulation de fenêtres avec `JFrame`, notamment :
- La gestion de la visibilité (`setVisible`).
- L'ajout de composants dynamiques (comme `UnMobile`).
- L'utilisation de threads pour animer les composants.

## TP2 : Affichage synchronisé

### Sémaphore

#### Outils de Synchronisation
Les sémaphores sont utilisés pour gérer l'accès sécurisé aux ressources partagées. Ils empêchent plusieurs threads d'accéder simultanément à une **section critique**, ce qui pourrait entraîner des conditions de course.

#### Implémentation

1. **Initialisation** : Choisir la valeur de départ en fonction des besoins (1 pour exclusivité, >1 pour ressources multiples).
2. **Interblocages** : Prévenir les deadlocks en respectant l'ordre des verrous.
3. **Performance** : Minimiser les sections critiques pour limiter les délais d'accès.

#### Exemple : Analogie avec un Phare
Le phare régule l'entrée des bateaux dans un port.
- **Vert (1)** : Accès autorisé à un bateau.
- **Rouge (0)** : Attente obligatoire pour les suivants.
Cela illustre les concepts de `wait` (rouge) et `signal` (vert).

#### Procédé
Un sémaphore unique contrôle l'accès au deuxième tiers d'une fenêtre, divisée en six sections. Un thread appelle `sem.syncWait()` pour entrer dans la section critique et `sem.syncSignal()` en sortant, assurant qu'un seul mobile y passe à la fois.

### Conclusion
Les sémaphores simplifient la synchronisation des threads mais nécessitent une implémentation soignée pour éviter les interblocages et préserver les performances.

## TP3 : Boîte aux lettres

### Analyse et Résolution

La **BAL (Boîte aux Lettres)** est une file circulaire utilisée pour un modèle producteur-consommateur. Elle s'appuie sur la classe **`BlockingQueue`** pour synchroniser automatiquement l'accès.

Dans ce TP, j’ai structuré la solution autour de la classe **BAL**, qui encapsule l’interface **`BlockingQueue`** et simplifie son utilisation. La **BAL** agit comme un point central de communication entre les threads producteurs et consommateurs. Elle masque les détails complexes de synchronisation, en utilisant une instance concrète de **`ArrayBlockingQueue`** comme tampon circulaire.

Cette organisation permet une séparation claire des responsabilités : les producteurs et consommateurs interagissent uniquement avec la **BAL** via des méthodes dédiées (déposer et retirer), tandis que la gestion des accès concurrents est entièrement confiée à l’implémentation sous-jacente de la **`BlockingQueue`**. Cette approche favorise la modularité, la lisibilité, la portabilité et la maintenance du code.

### Ce que j'ai appris :
1. **Gestion simplifiée des threads** :
   Les méthodes comme `offer` (ajout) et `poll` (retrait) évitent les blocages indéfinis.

2. **Arrêt propre** :
   Un marqueur spécial signale la fin de la production, permettant aux threads de se terminer correctement.

3. **Parallèle avec la boulangerie** :
   - **Dépôt** : Un boulanger place un pain dans un panier (lettre dans la BAL).
   - **Retrait** : Un client retire un pain, ou attend si le panier est vide.
   - **Marqueur de fermeture** : Indique la fin de l'activité.

### Conclusion

Ce TP illustre les avantages des outils de synchronisation modernes, comme **`BlockingQueue`**, pour gérer efficacement la concurrence, prévenir les blocages et simplifier le code.