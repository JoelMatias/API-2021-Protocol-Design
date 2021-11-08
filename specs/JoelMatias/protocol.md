## Objectifs du protocole

Le protocole va servir à mettre en place la communication entre un client qui **voudra avoir le résultat d'un calcul** et le serveur qui **lui donnera la réponse au calcul**. Les deux vont d'abord confirmer la connexion et ensuite le client enverra le calcul suite à quoi le serveur lui enverra la réponse.



## Comportement global

- **Protocole utilisé :** TCP 
- **Serveur**
  - **Adresses :** 
    - 10.192.104.133
    - 10.192.107.128
  - **Port :** 4269
- **Connexion**
  - **Parle d'abord :** Le serveur
  - **Ferme la connexion :** Le client quand il a plus de calculs à demander au serveur



## Messages

#### Bienvenue

Message de bienvenue que le serveur enverra afin de confirmer la connexion et qui va prévient le client qu'il est prêt à recevoir un calcul. Il va également lui présenter les opérations possibles et la syntaxe à utiliser.

Le message sera le suivant : 

"Bonjour et bienvenue. Quel est le calcul dont vous souhaitez connaitre le résultat?

Opérations possibles : Addition (+), Soustration (-), Multiplication (*), Division (/)".



#### Calcul

Le client va envoyer son calcul au serveur. Le message sera envoyé sous la forme "nbr1 nbr2 op"; nbr1 sera le premier nombre, nbr2 sera le deuxième nombre et op représentera l'opération voulue par le client et ne fera qu'un seul caractère de long. Les seuls opérations autorisées seront les additions (+), les soustractions (-), les multiplications (*) ainsi que les divisions (/).



#### Résultat

Le serveur enverra que l'opération s'est bien passée suivi du résultat du calcul. Cela aura la forme suivante "OK res" où res vaudra le résultat du calcul demandé.



#### Erreur

Le serveur enverra un message d'erreur si une erreur survient.

Le format utilisé sera le suivant : "Erreur : descErreur" où descErreur représente la description de l'erreur.



#### Bye bye

Le client enverra un message afin de prévenir le client qu'il n'a plus de calculs à demander et qu'il va fermer la connexion de son côté. 

Le message sera le suivant : "Bye Bye".



## Eléments spécifiques

#### Opérations supportées

Les opérations supportées sont les suivantes :

- Addition
- Soustraction
- Multiplication
- Division



#### Erreurs

Le serveur enverra un message d'erreur si l'un des cas suivants arrive :

- Division par 0
- Format de calcul pas respecté
- Opération inexistante



## Exemples

###### Discussion entre le serveur (S) et la client (C)

S : Bonjour et bienvenue. Quel est le calcul dont vous souhaitez connaitre le résultat?

C : 3 2 +

S : OK 5

C : 5 1 (

S : Erreur : Opération inexistante

C : Bye Bye
