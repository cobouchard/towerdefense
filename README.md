# JavaDefense

## Git commands
```git
git status
```
donne des informations sur ta situation dans le r�po


```git
git add -A
```
(ajoute tout les fichiers modifi�s pour le prochain commit)


```git
git commit -m "un beau message"
```
commit les changements et fait une "sauvegarde" de ce point


```git
git push
```
envoi les modifications sur le serveur


```git
git pull
```
r�cup�re la derni�re version du projet

## Comment �crire du MD ici :
https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet#code

## Useful links

#Choix de l'algorithme du plus court chemin (déplacement du monstre entre spawn et chateau)

https://fr.wikipedia.org/wiki/Algorithme_de_Dijkstra 
https://cs.stackexchange.com/questions/42681/best-pathfinding-algorithm-for-undirected-unweighted-graph

https://en.wikipedia.org/wiki/Breadth-first_search
On va en fait utiliser un BFS, simple, déjà codé, assez efficace vu la taille de nos graphes.


https://www.youtube.com/watch?v=-L-WgKMFuhE
https://en.wikipedia.org/wiki/A*_search_algorithm
Voilà ce qu'on va vraiment utilisé.

## Sons
https://freesound.org/

## Images
https://opengameart.org/
Commentaire de mon frère : Et pour les "images" soit je savais ce que je voulais et je faisais une recherche google précise (genre "Cacodemon spritesheet" - pour la blague c'est vraiment une recherche que j'ai fait à l'époque)

## Code utile ?
J'ai retrouvé ça par hasard, ça sera peut être utile pour les calculs de trajectoires de projectile ou déplacement du monstre, ou pas.
```java
package HumanComputerInterface;

import javafx.scene.shape.Circle;

import java.util.function.Function;

public abstract class Bisection
{
    public static Function getFunction(double x, double y, double x_, double y_)
    {
        //given two points or 4 coordinates
        //using the general equation of the bisection to have a function describing the bisection

        //if the ordinates are equals it means the bisection is in the form x = d
        //can't use the general equation in this case because there is a zero-division

        if(y==y_)
        {
            return (Function<Double, Double>) aDouble -> (y+y_)/2;
        }

        double a = (x-x_)/(y_-y);

        double b = ((x_*x_-x*x)/(y_-y)+y+y_)/2;

        return (Function<Double, Double>) aDouble -> a*aDouble+b;

    }

    public static Function getFunction(Circle start, Circle end)
    {
        return getFunction(start.getCenterX(),start.getCenterY(),end.getCenterX(),end.getCenterY());
    }

    public static void main(String[] args)
    {
        Function<Double,Double> test = getFunction(2,5,6,3);
        System.out.println(test.apply(10.));
    }
}
```

## Propositions images case
https://opengameart.org/content/tower-defense-300-tilessprites

https://www.pinterest.com/pin/446349013041737645/

https://in.pinterest.com/pin/837317755694460457/
