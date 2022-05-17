Írj Java amőba játékot, amit két emberi játékos játszhat konzolos felületen!
Amőba játék Wikipedia oldala
A program indítása után köszöntse a felhasználót egy üzenettel a standard kimeneten, majd jelenítsen
meg egy menüt, ahol a következő menüpontok közül lehessen választani:
1. Új játék indítása
3. Játék betöltése
4. Kilépés


Az 1. menüpontot választva kezdődjön egy új játék!

A játékot mindig az X játékos kezdi.

A játékosok felváltva léphessenek a sor és oszlop koordináták megadásával!

Az nyer, akinek előbb keletkezik 5 egymás melletti szimbóluma.

Erről - és a döntetlenről - értesítse a játékosokat egy üzenettel a standard kimeneten!

Nyereség és döntetlen meccs után is a program térjen vissza a főmenübe!

Az, hogy hány egymás melletti szimbólum jelenti a győzelmet, azt egy tictactoe.properties

szöveges fájlból töltse be az alkalmazás induláskor, ami egy "resources" nevű mappában helyezkedjen
el a projekt alatt!

Ugyanebben a konfigurációs fájlban lehessen megadni azt is, hogy mekkora a játéktábla!

A játéktábla két dimenziója különböző is lehet, de mindkét dimenzió kisebb kell, hogy legyen, mint 100
és nagyobb vagy egyenlő, mint 5!

A játékot menet közben a "ment abc" utasítás megadásával lehessen elmenteni, amit a koordináták
bekérése során adhat meg alternatívaként a felhasználó.

Ennek az utasításnak a hatására a "saves" nevű mappában hozzon létre egy "abc.sav" nevű fájlt,
amiben eltárolja az aktuális meccs teljes állapotát!

Ha létezik már ilyen néven fájl ebben a mappában, akkor kérdezzen rá, hogy felülírhatja-e!

Ha nemmel válaszol erre a kérdésre a felhasználó, akkor kérjen be egy másik fájlnevet!

Szebb kirajzoláshoz használhatod ezeket a UNICODE karaktereket is:
https://en.wikipedia.org/wiki/Box-drawing_character

A 2. menüpontot választva a felhasználó adhasson meg egy fájlnevet (kiterjesztés nélkül), amit a
program betölt!

Ez egy általad választott formátumban tartalmazza egy korábbi játékmenet állását!

Ha a megadott fájlnév a kiterjesztést is tartalmazza, akkor jelenítsen meg egy figyelmeztető üzenetet,
hogy csak a játékállás nevét szükséges megadni, és kérje be újból a fájlnevet!

A 3. menüpont hatására a program álljon le és jelenítsen meg egy búcsúüzenetet a standard kimeneten!
