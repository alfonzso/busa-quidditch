# Záróvizsga - Kviddics menedzser app (110 pont)

Hozz létre egy alkalmazást, amivel kviddics klubokat, játékosokat és edzőket lehet menedzselni!

## Általános elvárások (23 pont)

Három rétegű Spring Boot webalkalmazást készíts!

Az alkalmazáshoz elegendő H2 adatbázis használata.

Az alkalmazásba beérkező HTTP kéréseket és azok tartalmát (akár az url részeként, akár a kérés törzsében érkeznek) logold ki INFO szinten! A log üzenetre formai követelmény nincs, az elvárás annyi, hogy légy következetes!

Third party eszközöket (modelmapper, lombok) használni ajánlott, de nem pontlevonás, ha anélkül oldjátok meg a feladatot.

A feladatleírást olvasd el részletesen, és nézd meg az egyes részfeladatokhoz tartozó teszteseteket is, hogy milyen inputra mi az elvárt viselkedése az alkalmazásnak! A tesztosztályokban találhatsz JSON Stringeket, amikkel manuálisan is tudod tesztelni az alkalmazást, ha szeretnéd.

Minden hibakezelést egy GlobalExceptionHandler nevű osztályban végezz el a tanultak alapján!

##### Részpontszámok
- alkalmazás szerkezeti felépítése, következetes mappa és package szerkezet - 2 pont
- működő és futtatható Spring Boot webalkalmazás - 2 pont
- logolás - 4 pont
- clean code - 15 pont

## Domain osztályok létrehozása (4 pont)
#### Club
- Szükséges fieldek: id (Integer, szerver adja ki sorban), name (String), wins (int), players (List\<Players>), coach (Coach)
- Állítsd be megfelelően a kapcsolatokat! Egy klubhoz egy edző tartozik, az idegenkulcs az edzőnél legyen lementve adatbázisban!

#### Coach
- Szükséges fieldek: id (Integer, szerver adja ki sorban), name (String), deleted (boolean), club (Club)
- Állítsd be megfelelően a kapcsolatot!

#### Player
- Szükséges fieldek: id (Integer, szerver adja ki sorban), name (String), joined (LocalDate), playerType (PlayerType), wins (int), club (Club)
- Állítsd be megfelelően a kapcsolatot!

#### PlayerType (enum)
- Az enumok rendelkeznek egy maxPlayerFromType (int) értékkel, amely megadja, hogy adott típusú játékosból mennyi lehet a csapatban.
- CHASER(3)
- BEATER(2)
- KEEPER(1)
- SEEKER(1)

##### Részpontszámok
- Club osztály megfelelően lett létrehozve - 1 pont
- Coach osztály megfelelően lett létrehozve - 1 pont
- Player osztály megfelelően lett létrehozve - 1 pont
- PlayerType enum megfelelően lett létrehozve - 1 pont

## Klub mentése (8 pont)
#### POST /api/clubs
DTO-ban a következő információk érkeznek:
- név (String, nem lehet null, üres String vagy csak whitespace karakter)
- győzelmek száma (int)

A kérésben beérkező adatokat a fenti feltételek alapján validáld le, és hiba esetén küldj vissza hibaüzenetet (a pontos hibaüzeneteket megtalálhatod a vonatkozó teszteseteknél), valamint 400-as hibakódot!

Sikeres mentés esetén küldd vissza az elmentett klub id-ját, nevét és a győzelmeinek számát, valamint 201-es státuszkódot!

##### Részpontok
- a beérkező HTTP kérést az alkalmazás kezeli - 2 pont
- az adatok elmentésre kerülnek - 2 pont
- egyszerű mezők validálása és hibakezelése - 2 pont
- a válasz tartalmazza a megfelelő adatokat - 2 pont

## Játékos mentése (13 pont)
#### POST /api/players
DTO-ban a következő információk érkeznek:
- név (String, nem lehet null, üres String vagy csak whitespace karakter)
- csatlakozott (LocalDate, nem lehet null, csak múltbeli időpont lehet)
- típus (enum, nem lehet null)
- győzelmek száma (int)
- klub id-ja (Integer, nem lehet null)

A kérésben beérkező adatokat a fenti feltételek alapján validáld le, és hiba esetén küldj vissza hibaüzenetet (a pontos hibaüzeneteket megtalálhatod a vonatkozó teszteseteknél), valamint 400-as hibakódot!

Ha a megadott id-nak megfelelő klub nem található a rendszerben, akkor küldj vissza ugyanúgy 400-as hibakódot és hibaüzenetet! Ehhez használj saját kivételt! A pontos hibaüzenetet megtalálhatod a megfelelő tesztesetnél.

Ha a megadott klub létezik, ellenőrizd le, hogy a menteni kívánt játékos típusából van-e még hely a csapatban. Amennyiben nincs, küldj vissza 400-as hibakódot és hibaüzenetet! Ehhez használj saját kivételt! A pontos hibaüzenetet megtalálhatod a megfelelő tesztesetnél.

Sikeres mentés esetén küldd vissza az elmentett játékos id-ját, nevét, csatlakozási dátumát, típusát, győzelmeinek számát, és a kulbjának a nevét, valamint 201-es státuszkódot!

##### Részpontok
- a beérkező HTTP kérést az alkalmazás kezeli - 2 pont
- az adatok elmentésre kerülnek (megfelelő klubbal együtt) - 3 pont
- egyszerű mezők validálása és hibakezelése - 2 pont
- nem létező klub hiba kezelése - 2 pont
- rossz játékos típus hibájának kezelése - 2 pont
- a válasz tartalmazza a megfelelő adatokat - 2 pont

## Klub megnyer egy meccset (11 pont)
#### PUT /api/clubs/{clubId}

A kérés hatására az alkalmazás megkeresi a megadott id-hoz tartozó klubot, és növeli a győzelmeinek számát eggyel. Emellett a klubhoz tartozó összes játékos győzelmi értékét is növelnie kell.

Sikeres módosítás esetén az alkalmazás küldje vissza a klub id-ját, nevét, győzelmeinek számát, az edző nevét, és egy listát a játékosok adatairól (id, név, csatlakozás dátuma, típus, győzelmeinek száma, klub neve), valamint egy 200-es státuszkódot!

Ha a megadott id-nak megfelelő klub nem található a rendszerben, akkor küldj vissza 400-as hibakódot és hibaüzenetet! Ehhez használj saját kivételt! A pontos hibaüzenetet megtalálhatod a megfelelő tesztesetnél.

##### Részpontok
- a beérkező HTTP kérést az alkalmazás kezeli - 2 pont
- a klub adatai módosításra kerülnek - 2 pont
- az összes játékos adatai módosításra kerülnek - 2 pont
- nem létező klub hibájának kezelése - 2 pont
- a válasz tartalmazza a megfelelő adatokat - 3 pont

## Játékos átigazol egy másik klubhoz (14 pont)
#### PUT /api/players/{playerId}/club/{clubId}

A kérés hatására az alkalmazás áthelyezi az adott playerId-hoz tartozó játékost az adott clubId-val rendelkező klubhoz. Emellett a játékos 'csatlakozott' értékét beállítja a mai napra.

Sikeres módosítás esetén az alkalmazás küldje vissza a játékos adatait (id, név, csatlakozás dátuma, típus, győzelmeinek száma, klub neve) valamint egy 202-es státuszkódot!

Ha a megadott playerId-nak megfelelő játékos nem található a rendszerben, akkor küldj vissza 400-as hibakódot és hibaüzenetet! Ehhez használj saját kivételt! A pontos hibaüzenetet megtalálhatod a megfelelő tesztesetnél.

Ha a megadott clubId-nak megfelelő klub nem található a rendszerben, akkor küldj vissza 400-as hibakódot és hibaüzenetet! Ehhez használj saját kivételt! A pontos hibaüzenetet megtalálhatod a megfelelő tesztesetnél.

Ha a megadott klub létezik, ellenőrizd le, hogy a menteni kívánt játékos típusából van-e még hely a csapatban. Amennyiben nincs, küldj vissza 400-as hibakódot és hibaüzenetet! Ehhez használj saját kivételt! A pontos hibaüzenetet megtalálhatod a megfelelő tesztesetnél.

Ha a megadott clubId megegyezik a játékos jelenlegi klubjának id-jával, akkor küldj vissza 400-as hibakódot és hibaüzenetet! Ehhez használj saját kivételt! A pontos hibaüzenetet megtalálhatod a megfelelő tesztesetnél.

##### Részpontok
- a beérkező HTTP kérést az alkalmazás kezeli - 2 pont
- a megfelelő módosítások megtörténnek - 2 pont
- megfelelő válasz érkezik a kérésre - 2 pont
- nem létező játékos hibájának kezelése - 2 pont
- nem létező klub hibájának kezelése - 2 pont
- rossz játékos típus hibájának kezelése - 2 pont
- játékos már csatlakozott a klubhoz hibájának kezelése - 2 pont


## Játékosok listázása (7 pont)
#### GET /api/players

A kérés hatására az alkalmazás kilistázza az összes játékos adatait (id, név, csatlakozás dátuma, típus, győzelmeinek száma, klub neve) valamint egy 200-es státuszkódot!

Lekérdezés feltételek:
- a lekérdezésnek rögtön egy dto listát kell visszaadnia
- a rekordokat sorrendezve kapjuk vissza a klubhoz csatlakozás dátuma szerint csökkenő, valamint a győzelmek száma szerint növekvő sorrendben

##### Részpontok
- a beérkező HTTP kérést az alkalmazás kezeli - 2 pont
- a lekérdezés JPQL-t használ, dto-val tér vissza, megfelelően sorrendezett - 3 pont
- a válasz tartalmazza a megfelelő adatokat - 2 pont

## Szupersztár játékosokkal rendelkező klub ellenőrzés (11 pont)
#### GET /api/clubs/superstar/{clubId}

A kérés hatására az alkalmazás megvizsgálja, hogy az adott id-val rendelkező klub játékosai között található-e szupersztár játékos. Egy játékos akkor tekinthető szupersztárnak, ha több győzelme van, mint a jelenlegi klubjának.

A logikát adatbázis szinten kell megoldani egy JPQL segítségével. A lekérdezés egy darab stringet kell, hogy visszaadjon attól függően, hogy van, vagy nincs szupersztár játékos a klubnál. A két lehetséges visszatérési értéket a megfelelő teszteknél találod. Sikeres válasz esetén 200-as státuszkódot használj!

Ha a lekérdezés nem ad vissza eredményt (a megadott clubId-nak megfelelő klub nem található a rendszerben), akkor küldj vissza 400-as hibakódot és hibaüzenetet! Ehhez használj saját kivételt! A pontos hibaüzenetet megtalálhatod a megfelelő tesztesetnél.

##### Részpontok
- a beérkező HTTP kérést az alkalmazás kezeli - 2 pont
- a logika megfelelően van felépítve a JPQL-ben - 5 pont
- a megfelelő értékek kapjuk vissza - 2 pont
- nem létező klub hibájának kezelése - 2 pont

## Edző törlése (8 pont)
#### DELETE /api/coaches/{coachId}

A kérés hatására az alkalmazás megkeresi az id-hoz tartozó edzőt, és logikai törlést hajt végre. Ez annyit jelent, hogy átállítja a deleted értékét, valamint megszűnteti a kapcsolat a klubbal.

Ha a megadott coachId-nak megfelelő edző nem található a rendszerben, akkor küldj vissza 400-as hibakódot és hibaüzenetet! Ehhez használj saját kivételt! A pontos hibaüzenetet megtalálhatod a megfelelő tesztesetnél.

Sikeres módosítás esetén az alkalmazás küldjön vissza egy 200-as státuszkódot!

##### Részpontok
- a beérkező HTTP kérést az alkalmazás kezeli - 2 pont
- a logikatörlés megfelelően megtörténik - 2 pont
- a megfelelő választ adjuk vissza - 2 pont
- nem létező edző hibájának kezelése - 2 pont

## Edző összeállítja a klub statisztikáit (11 pont)
#### GET /api/coaches/statistics/{coachId}

A kérés hatására az alkalmazás megkeresi az id-hoz tartozó edzőt, és egy JPQL segítségével visszaadja a klub és a játékosok győzelmi statisztikáit.

A JPQL-nek egy dto-val kell visszatérnie, amely a következőket tartalmazza: a klub győzelmeinek száma, a játékosok átlagos győzelmeinek száma, a legnagyobb játékoshoz tartozó győzelmi érték, a legkisebb játékoshoz tartozó győzelmi érték. Sikeres válasz esetén használj 200-as státuszkódot. 

Ha a megadott coachId-nak megfelelő edző nem található a rendszerben, akkor küldj vissza 400-as hibakódot és hibaüzenetet! Ehhez használj saját kivételt! A pontos hibaüzenetet megtalálhatod a megfelelő tesztesetnél.

Ha a lekérdezés nem ad vissza eredményt (mert nincsenek játékosok a klubban), akkor küldj vissza 400-as hibakódot és hibaüzenetet! Ehhez használj saját kivételt! A pontos hibaüzenetet megtalálhatod a megfelelő tesztesetnél.

##### Részpontok
- a beérkező HTTP kérést az alkalmazás kezeli - 2 pont
- a lekérdezés JPQL-t használ, dto-val tér vissza - 3 pont
- a megfelelő választ adjuk vissza - 2 pont
- nem létező edző hibájának kezelése - 2 pont
- nem létező játékosok hibájának kezelése - 2 pont