# UI Test

## Feladat leírása és megoldása:

A JavaFX programunkat a TestFX-el teszteltük le. Ez egy külön a JavaFX számára készített tesztelő kód.  

Miután sikeresen sikerült beállítanom elkezdtem vizsgálni, hogy mit tudok pontosan leellenőrizni. A JavaFX-es elemek állapotára tudtam ellenőrzéseket írni. Például gombok láthatóságára, labelek szövegére. A tesztek többsége az adott gombok megnyomásától fog állapotot váltani.

Pár példa a testekre: Menü gombjainak működése, cellákban megjelenő entitások működése (karakterek, sátor, iglu), az irányító gombok helyes működése stb... 

A Github Actions csak windows környezet alatt tudja futtatni a UI teszteket, ubuntu alatt elakadt a Build with Maven résznél.

[Pull Request link](https://github.com/BME-MIT-IET/iet-hf2021-halogatasch/pull/16)

[Issue link](https://github.com/BME-MIT-IET/iet-hf2021-halogatasch/issues/2)
