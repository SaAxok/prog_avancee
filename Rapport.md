## TP1 : Dossier de conception du TP Thread

### Exercice 1

**Implémentation :**  
La classe `UnMobile` représente un mobile qui se déplace dans une interface graphique en utilisant un thread. Sa méthode `run()` gère le mouvement par incréments successifs, suivis de redessins avec `repaint()`.

**Ce que j'ai compris :**  
Le fonctionnement d'un thread inclut :  
- La gestion des boucles d'exécution (dans `run`).  
- L'utilisation de `sleep` pour réguler la vitesse.  
- L'importance de redessiner régulièrement les objets graphiques pour simuler le mouvement.

---

### Exercice 2

**Implémentation :**  
Un mobile est ajouté à une fenêtre avec `add()` dans la classe `UneFenetre`, et un thread est lancé pour animer le mobile.

**Ce que j'ai appris :**  
La manipulation de fenêtres avec `JFrame`, implique :  
- La gestion de la visibilité (`setVisible`).  
- L'ajout de composants dynamiques (comme `UnMobile`).  
- L'utilisation de threads pour animer les composants.