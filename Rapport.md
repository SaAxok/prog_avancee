## TP3 : Boîte aux lettres

### Analyse et Résolution

La **BAL (Boîte aux Lettres)** est une file circulaire utilisée pour un modèle producteur-consommateur. Elle s'appuie sur la classe **`BlockingQueue`** pour synchroniser automatiquement l'accès.  

Dans ce TP, j’ai structuré la solution autour de la classe **BAL**, qui encapsule l’interface **`BlockingQueue`** et simplifie son utilisation. La **BAL** agit comme un point central de communication entre les threads producteurs et consommateurs. Elle masque les détails complexes de synchronisation, en utilisant une instance concrète de **`ArrayBlockingQueue`** comme tampon circulaire.  

Cette organisation permet une séparation claire des responsabilités : les producteurs et consommateurs interagissent uniquement avec la **BAL** via des méthodes dédiées (deposer et retirer), tandis que la gestion des accès concurrents est entièrement confiée à l’implémentation sous-jacente de la **`BlockingQueue`**. Cette approche favorise la modularité, la lisibilité, la portabilité et la maintenance du code.

---

#### Ce que j'ai appris :  
1. **Gestion simplifiée des threads** :  
   Les méthodes comme `offer` (ajout) et `poll` (retrait) évitent les blocages indéfinis.  

2. **Arrêt propre** :  
   Un marqueur spécial signale la fin de la production, permettant aux threads de se terminer correctement.  

3. **Parallèle avec la boulangerie** :  
   - **Dépôt** : Un boulanger place un pain dans un panier (lettre dans la BAL).  
   - **Retrait** : Un client retire un pain, ou attend si le panier est vide.  
   - **Marqueur de fermeture** : Indique la fin de l'activité.

---

### Conclusion  

Ce TP illustre les avantages des outils de synchronisation modernes, comme **`BlockingQueue`**, pour gérer efficacement la concurrence, prévenir les blocages et simplifier le code.