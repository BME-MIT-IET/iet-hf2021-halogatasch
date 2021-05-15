# Maven, Github Action

## Feladat leírása és megoldása:

Leírás:

Mivel a program JavaFX-et használ a grafika kirajzolásához, ezért célszerű, hogy ezeket a függőségeket ne kelljen külön a felhasználónak letölteni és hozzáadni egyesével a projekthez, hanem a maven build automatizáló segítségével ezek automatikusan települjenek. A javafx-hez kellő függőségek hozzáadása után egy másik problémával szembesültünk: a program fxml fájlokat használ a kinézet leírásához, illetve a kirajzoláshoz különböző képeket. A program az fxml fájlokat ugyanabban a mappában keresi, ahol a forrásfájlok vannak, viszont a képeket egy eggyel felsőbb szintű könyvtárban keresi. Ezekből következik, hogy a buildelés során is meg kell tartanunk ezt a mintát. Végül ezt a maven resources plugin segítségével oldottuk meg, aminek két külön execution-t adtunk meg, mivel a fent említett forrásokat két külön célmappába kell elhelyezni. Parancssorból a játék a mvn javafx:run paranccsal indítható.

A pom.xml fájlban ezeken kívül definiáltuk a tesztekhez használt összes többi függőséget, így pl. a mockito, jacoco, junit, testfx. Ezek miatt a mvn test parancs az összes, a programhoz írt tesztet (BDD, Unit, UI) lefuttatja egyben, és ennek az eredményét írja ki. Mvn verify parancs hatására pedig a jacoco kódlefedettség plugin is lefut, ennek eredménye a target/sites mappában található. 

A github actions beüzemelésénél a java with maven sablont használtuk, majd a használt java verziót 15-ösre cseréltük. Emiatt a main ágon lefuttatott pull requestek és commitok automatikusan build-elődnek.

---

Eredmény és tanulság:

A maven segítségével sokkal egyszerűbbé és követhetőbbé vált a függőségek kezelése és nyilvántartása, ezzel megkönnyítve a projekten dolgozó fejlesztők életét.