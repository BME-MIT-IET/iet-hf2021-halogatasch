# Unit Test

## Feladat Leírása:

A programunk logikájának tesztelésére rábíztunk mindenkire bizonyos osztályokat vagy osztálycsaládokat, amiken Unit Testeket végeztünk el.
Elvégeztünk egységteszteket a programban például a karakterekre, a karakterek tárgyainak csoportjára vagy hátitáskájára. Ezenkívül a pályán található cellákra vagy a medvére is.

A tesztek készítéséhez segítségül használtuk Mockito mock elemeit, amikkel a logikában a vizsgált osztályhoz köthető objektumokat is imitálhattuk anélkül, hogy a teszthez nem köthető elemeket be kéne konfigurálnunk. Emellett a lefedettség vizsgálásához beállítottunk jacocot is a projekthez, így láthatjuk mekkora a lefedettsége az eddigi tesztjeinknek, mi az ami fontos és még kihagytuk.

![jacoco](unitimg.png)

Az egységtesztek készítése közben mélyebben megismerkedhettünk a program logikájával, megérthettünk olyan logikai elemeket, amiket ma már máshogy csinálnánk. Kisebb-nagyobb hibákat is felfedeztünk, amik az eredeti kiadáskor nem kerültek fényre.
