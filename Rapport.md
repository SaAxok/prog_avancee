## TP2 : Affichage synchronisé

### Sémaphore  

#### Outils de Synchronisation  
Les sémaphores sont utilisés pour gérer l'accès sécurisé aux ressources partagées. Ils empêchent plusieurs threads d'accéder simultanément à une **section critique**, ce qui pourrait entraîner des conditions de course.

---

#### Implémentation  

1. **Initialisation** : Choisir la valeur de départ en fonction des besoins (1 pour exclusivité, >1 pour ressources multiples).  
2. **Interblocages** : Prévenir les deadlocks en respectant l'ordre des verrous.  
3. **Performance** : Minimiser les sections critiques pour limiter les délais d'accès.

---

#### Exemple : Analogie avec un Phare  
Le phare régule l'entrée des bateaux dans un port.  
- **Vert (1)** : Accès autorisé à un bateau.  
- **Rouge (0)** : Attente obligatoire pour les suivants.  
Cela illustre les concepts de `wait` (rouge) et `signal` (vert).

---

#### Procédé  
Un sémaphore unique contrôle l'accès au deuxième tiers d'une fenêtre, divisée en six sections. Un thread appelle `sem.syncWait()` pour entrer dans la section critique et `sem.syncSignal()` en sortant, assurant qu'un seul mobile y passe à la fois.

---

### Conclusion  
Les sémaphores simplifient la synchronisation des threads mais nécessitent une implémentation soignée pour éviter les interblocages et préserver les performances.