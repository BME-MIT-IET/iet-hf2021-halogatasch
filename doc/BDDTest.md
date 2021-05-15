# BDD Test

## A Feladat leírása és megoldása:

Az feladathoz tartozó issue: [BDD tesztek készítése](https://github.com/BME-MIT-IET/iet-hf2021-halogatasch/issues/1)

A feladathoz tartozó pull requestek:
 - [Bdd test nagygellert](https://github.com/BME-MIT-IET/iet-hf2021-halogatasch/pull/18)
 - [BDD Tests added](https://github.com/BME-MIT-IET/iet-hf2021-halogatasch/pull/17)
 - [Bdd test markovicsgergely](https://github.com/BME-MIT-IET/iet-hf2021-halogatasch/pull/14)
 - [BDD tests created](https://github.com/BME-MIT-IET/iet-hf2021-halogatasch/pull/13)

A feladat célja az volt, hogy a játékban szereplő fő funkciók megfelelő működését teszteljük le. Megfelelően működnek-e a 
tárygak, a különböző típusú mezők, a hóvihar és a medve.

---
A tesztekhez Cucumber Plugin-t használtunk. A feladatokat igyekeztük úgy elosztani, hogy a hasonló szenáriókat egy ember kapja, így ki tudtuk használni, hogy például amikor három tesztnél is a hóesés volt az esemény, azt a függvényt elég volt egyszer megírni és többször felhasználni a feature fájlban.

## Tanulságok:

A tanulság talán az lehet, hogy habár elsőre kicsit bonyolultabbank tűnik a BDD teszt, mint a Unit teszt és talán munkával is jár, viszont hosszú távon átláthatóbb és ahogy egyre több teszt elkészül, egyre gyorsabban lehet a különböző komponensekből egy új tesztet összerakni.