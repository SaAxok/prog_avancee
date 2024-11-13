# Programmation parallèle à mémoire partagée

> Gravier Kylian INF3-FA
> - Rapport rédigé avec l'aide d'intelligence artificielle (Mistral)
> - Les TP ont été réalisés avec l'aide de Github Copilot


## Table des matières

- [TP1 : Dossier de conception du TP Thread](#tp1--dossier-de-conception-du-tp-thread)
    - [Exercice 1](#exercice-1)
    - [Exercice 2](#exercice-2)

- [TP2 : Affichage synchronisé](#tp2--affichage-synchronisé)
    - [Sémpahore](#sémpahore)
        - [Des Outils de Synchronisation](#des-outils-de-synchronisation)
        - [Subtilités de l'Implémentation des Sémaphores](#subtilités-de-limplémentation-des-sémaphores)
        - [Exemple : Analogie avec un Phare](#exemple--analogie-avec-un-phare)
        - [Conclusion](#conclusion)
        - [Procédé](#procédé)

## TP1 : Dossier de conception du TP Thread

Thread : processus propre qui possède un environnement, un état et un nom.  
Les threads possèdent une seule mémoire partagée

### Exercice 1

**Implémentation :**
La classe `UnMobile` représente un mobile qui se déplace dans une interface graphique en utilisant un thread. Le mouvement est simulé par une boucle qui fait appel à la méthode `repaint()` pour redessiner le mobile à intervalles réguliers.

**Ce que j'ai compris :**
La méthode `run()` contient une boucle de déplacement du mobile en incrémentant sonDebutDessin par sonPas à chaque itération, puis appelle `repaint()` pour redessiner le mobile à sa nouvelle position. La méthode `sleep()` permet de contrôler la vitesse du mouvement.

## Exercice 2

**Implémentation :**
J'ai ajouté `UnMobile` à `UneFenetre` avec la méthode `add()` et créé un thread pour animer le mobile en appelant `start()` sur le thread du mobile.

**Ce que j'ai appris :**
La classe JFrame permet de créer une fenêtre avec `setVisible()` et `setSize()` pour définir ses dimensions. `add()` permet d'ajouter des composants (ici UnMobile) dans cette fenêtre.


## TP2 : Affichage synchronisé

L'objectif de ce TP est d'instancier un sémaphore qui va contrôler une section critique d'une interface graphique utilisateur (IGU). 
**Les sections critiques** sont les boucles qui gèrent la partie centrale de la fenêtre, entre un tiers et deux tiers de sa largeur. 
**La ressource critique** dans ce cas est l'objet JPanel, qui représente la fenêtre elle-même.

### Sémpahore

#### Des Outils de Synchronisation

Les sémaphores sont des outils de synchronisation utilisés pour contrôler l'accès à des ressources partagées dans des systèmes complexes. Ils sont particulièrement utiles dans les environnements où plusieurs processus (threads) doivent accéder à des ressources critiques de manière sécurisée et coordonnée. Cependant, leur implémentation peut être complexe et pointilleuse en raison de plusieurs subtilités.

#### 1. **Sections Critiques**
Une section critique est une partie du code où les processus ou threads accèdent à des ressources partagées. Pour éviter les conditions de course (race conditions) et garantir la cohérence des données, il est crucial de s'assurer qu'un seul processus ou thread puisse exécuter cette section à la fois.

#### 2. **Ressources Critiques**
Les ressources critiques sont des ressources partagées qui doivent être protégées contre l'accès concurrent. Cela peut inclure des variables globales, des fichiers, des périphériques matériels, ou tout autre élément qui ne peut être utilisé que par un seul processus ou thread à la fois.

#### 3. **Sémaphores**
Un sémaphore est une variable spéciale qui peut être utilisée pour contrôler l'accès à des ressources critiques. Il existe deux types principaux de sémaphores :

- **Sémaphores binaires (ou verou mutex)** : Ils peuvent prendre deux valeurs, 0 ou 1. Ils sont utilisés pour garantir l'exclusivité mutuelle, c'est-à-dire qu'un seul processus ou thread peut accéder à la ressource à la fois.
- **Sémaphores comptants** : Ils peuvent prendre des valeurs entières non négatives. Ils sont utilisés pour contrôler l'accès à des ressources qui peuvent être utilisées par un nombre limité, choisi par l'utilisateur, de processus ou threads simultanément.

### Subtilités de l'Implémentation des Sémaphores

#### a. **Initialisation**
L'initialisation correcte des sémaphores est cruciale. Un sémaphore binaire doit être initialisé à 1, tandis qu'un sémaphore comptant doit être initialisé à la valeur maximale du nombre de processus ou threads qui peuvent accéder simultanément à la ressource.

#### b. **Opérations `wait` et `signal`**
Les opérations `wait` et `signal` sont utilisées pour contrôler l'accès aux ressources critiques.

- **`wait`** : Cette opération décrémente la valeur du sémaphore. Si la valeur devient négative, le processus ou thread est bloqué jusqu'à ce qu'un autre processus ou thread exécute une opération `signal`.
- **`signal`** : Cette opération incrémente la valeur du sémaphore. Si la valeur était négative, un processus ou thread bloqué est réveillé.

#### c. **Gestion des Blocages**
La gestion des blocages peut être complexe. Si un processus ou thread est bloqué en attendant un sémaphore, il doit être réveillé correctement lorsque le sémaphore devient disponible. Cela nécessite une gestion précise des files d'attente et des états des processus ou threads.

#### d. **Interblocages (Deadlocks)**
Les interblocages peuvent se produire si plusieurs processus ou threads attendent indéfiniment des ressources détenues par d'autres processus ou threads. Les sémaphores doivent être utilisés avec précaution pour éviter les situations d'interblocage. Cela peut nécessiter des stratégies de prévention, telles que l'ordre de verrouillage des ressources ou l'utilisation de temporisateurs.

#### e. **Performance**
L'utilisation de sémaphores peut introduire des surcharges de performance en raison des opérations de blocage et de réveil. Il est important de minimiser ces surcharges en optimisant l'utilisation des sémaphores et en réduisant les sections critiques au strict nécessaire.

### Exemple : Analogie avec un Phare
Imaginons un phare qui contrôle l'entrée des bateaux dans un port. Le phare (sémaphore) peut être en mode "vert" (1) ou "rouge" (0). Quand le phare est vert, un bateau (processus) peut entrer dans le port (ressource critique). Quand le phare est rouge, les bateaux doivent attendre.

- **Initialisation** : Le phare est initialisé en mode vert (1), permettant à un bateau d'entrer.
- **Opérations `wait` et `signal`** : Quand un bateau entre dans le port, le phare passe au rouge (`wait`). Quand le bateau quitte le port, le phare repasse au vert (`signal`).
- **Gestion des Blocages** : Si plusieurs bateaux attendent, ils doivent être réveillés correctement quand le phare repasse au vert.
- **Interblocages** : Si deux bateaux attendent indéfiniment que l'autre quitte le port, c'est un interblocage.
- **Conditions de Course** : Si plusieurs bateaux essaient d'entrer en même temps sans coordination, cela peut causer des accidents.
- **Performance** : Le phare doit être efficace pour éviter les retards inutiles.

### Conclusion
L'implémentation des sémaphores nécessite une compréhension approfondie des sections critiques, des ressources critiques, et des opérations de synchronisation. Les subtilités telles que l'initialisation correcte, la gestion des blocages, la prévention des interblocages, et la gestion des conditions de course rendent cette tâche complexe et pointilleuse. Une attention particulière doit être portée à chaque étape pour garantir la cohérence et la sécurité des données dans un environnement concurrent.

### Procédé

On instancie **un seul** sémaphore en `final` dans la classe `Mobile`, car il est le même pour tous les mobiles instanciés. Ensuite, on divise le trajet du mobile en 6 boucles :

1. Premier tiers vers la droite
2. Deuxième tiers vers la droite
3. Troisième tiers vers la droite
4. Troisième tiers vers la gauche
5. Deuxième tiers vers la gauche
6. Premier tiers vers la gauche

En divisant le trajet d'un mobile de cette façon, on peut facilement contrôler l'accès au deuxième tiers avec les deux boucles qui gèrent cette section de la fenêtre. Avec le sémaphore général à 1, un seul mobile peut passer dans la section contrôlée par le sémaphore. On utilise la méthode `sem.syncWait()` avant d'entrer dans le deuxième tiers (vers la droite et la gauche) et `syncSignal()` quand on en sort pour permettre au suivant d'entrer. De cette manière, on contrôle l'accès au deuxième tiers de façon à ce qu'on ne puisse pas y voir passer plus d'un mobile à la fois.
