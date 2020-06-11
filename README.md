# Die-Bucht-von-Javarrr

## Übersicht
|Gliedrung|
|---------|
|[1. Allgemein](./README.md#1-allgemein)|
|[2. Zum Repository](./README.md#2-zum-repository)|
|[3. Voraussetzungen](./README.md#3-voraussetzungen)|
|[4. Installation](./README.md#4-installation)|
|[5. Dokumentation](./README.md#5-dokumentation)|

## 1. Allgemein
Dies ist das Repository für das Javaprojekt von **Moritz Offermann**, **Niklas Vogt** und **Fabian Reitz**. Die Entwicklung startete am 23. April 2020. 

## 2. Zum Repository
Der Remote **origin** befindet sich auf [GitHub](https://github.com/FabianReitz/Die-Bucht-von-Javarrr). Da die Hochschule GitLab bevorzugt, besitzt dieses Reporitory einen weiteren Remote namens **Gitlab**. Mindestens einmal pro Tag erfolgt ein Push des **master** auf den Remote [GitLab](https://gitlab.com/fabianreitz/Die-Bucht-von-Javarrr). So wird der Stand auf GitLab jederzeit lauffähig sein, während sich auf GitHub diverse Entwicklungs-Branches befinden. 

## 3. Voraussetzungen
Der Code für dieses Projekt wurde in Eclipse 4.14.0 geschrieben und ist definitiv lauffähig unter der JDK 1.8.0. Es wird empfohlen, diese Versionen zu nutzen. Andere IDEs oder JDKs wurden nicht getestet. 

## 4. Installation
Navigieren zum gewünschten Installationsort:

```
cd [directory]
```
  
Clonen des Repositorys:

```
git clone https://github.com/FabianReitz/Die-Bucht-von-Javarrr.git
```

Mittels Eclipse kann nun das Projekt geöffnet werden und der Code eingesehen, bearbeitet und ausgeführt werden.

## 5. Dokumentation
Im Hauptmenü kann zwischen den Punkten `Neues Spiel`und `Spiel verlassen` gewählt werden. Diese Punkte sind ebenfalls jederzeit über die Menu-Bar unter `Spiel` erreichbar.
![Main Menu](/images/MainMenu.png?raw=true)

Sobald das Spiel gestartet wurde, kann das Schiff des Spielers mit den Tasten `A` und `D` nach links bzw. rechts gesteuert werden. Schüsse werden mit `LEERTASTE` abgegeben. Die Musik kann mit den Tasten `1`, `2`, `3` und `4` an, aus bzw. leiser und lauter gestellt werden.

Wird ein Level abgeschlossen, kann zwischen einem von drei Boostern gewählt werden. Während das `Feuer` den Schaden des eigenen Projektils erhöht, lässt einen die `Kanone` mehr Schüsse pro Sekunde abgeben. Das `Herz` hingegen füllt die eigenen Leben etwas auf.
![Level Done](/images/PowerUps.png?raw=true)

Fallen die eigenen Leben auf Null oder sind alle Level geschafft, ist das Spiel vorbei. Nun bietet sich die Möglichkeit, den eigenen Punktestand persistent zu speichern. Dazu muss ein Name gewählt und mit `Ai!` bestätigt werden. 
![Scores](/images/Scores.png?raw=true)

Beim nächsten Starten des Spiels wird der errichte Score angezeigt. Das Spiel wird mit fünf Scores ausgeliefert, die es zu knacken gilt. Wird ein Score erreicht, der geringer ist, als der des fünften Platzes, wird dieser persestiert und kann in der `scores.csv` eingesehen werden. Jedoch werden im Spiel nur die besten fünf Piraten geehrt.
![Scores2](/images/Scores2.png?raw=true)
