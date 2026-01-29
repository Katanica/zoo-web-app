🦁 Zoo Web App

Web aplikacija za osnovno upravljanje podacima zoološkog vrta, razvijena kao Spring Boot + REST + HTML/JS projekt.
Aplikacija omogućuje evidenciju životinja, skupina, nastambi, zaposlenika, troškova, incidenata i posjetiteljskih grupa.

🧩 Korištene tehnologije

Java 21

Spring Boot

Spring Web

Spring Data JPA

Hibernate / JPA

SQL Server / MySQL (ovisno o konfiguraciji)

HTML + CSS + Vanilla JavaScript

Maven

📁 Struktura projekta
zoo-web-app/
├── src/main/java/zoo_web_app
│   ├── Controller        # REST kontroleri
│   ├── Service           # Servisni sloj (interface + impl)
│   ├── Repository        # JPA repozitoriji
│   ├── Entity            # Entiteti baze podataka
│   ├── DTO               # DTO objekti (npr. Trošak)
│   └── config            # Seederi i konfiguracija
│
├── src/main/resources
│   ├── static            # HTML stranice (frontend)
│   ├── application.properties
│   └── data.sql          # Inicijalni podaci

✅ Trenutno implementirane funkcionalnosti
🐾 Životinje i skupine

Evidencija pojedinačnih jedinki

Evidencija skupina životinja

Aktivne / neaktivne životinje (bez brisanja)

Povezanost sa:

nastambama

načinom nabave

troškovima

incidentima

🏠 Nastambe

Evidencija nastambi

Geometrija i opis

Povezivanje skupina s nastambom

Nastamba može imati više skupina

👷 Radnici i vodiči

Evidencija radnika

Posebna uloga vodiča

Dodjela vodiča grupama posjetitelja

Povezanost s obavezama i incidentima

👥 Grupe posjetitelja

Dodavanje i pregled grupa

Dodjela jednog vodiča po grupi

Datum dolaska + vrijeme početka i kraja

Status grupe (npr. NAJAVLJENO)

🚨 Incidenti

Evidencija incidenata

Vrste incidenata

Povezivanje:

sa skupinama

s jedinkama

Pregled i dodavanje kroz UI

💸 Troškovi (najnovije nadograđeno)

Troškovi su potpuno funkcionalni i obrađeni u backendu.

Podržano:

Troškovi vezani uz:

jedinku

skupinu

Tip troška:

NOVČANI

SATI RADA

Automatski izračun ukupnog troška (SATI × SATNICA)

DTO sloj (TrosakDto) za siguran ispis

Backend:

TrosakService

TrosakServiceImpl

TrosakController

TrosakDto

Frontend:

troskovi.html – pregled troškova

troskovi-dodavanje.html – unos troškova

📊 Izvještaji (djelomično)

Postoji IzvjestajController

Trenutno dostupni osnovni REST endpointi

PDF / Excel export nije implementiran

🚧 Ograničenja trenutne verzije

Nema autentikacije i korisničkih uloga

Nema:

smjena radnika

automatskih ponavljajućih obaveza

exporta u PDF / Excel

Frontend je čisti HTML/JS (bez frameworka)

▶️ Pokretanje aplikacije

Konfigurirati bazu u application.properties

Pokrenuti aplikaciju:

mvn spring-boot:run


Otvoriti u pregledniku:

http://localhost:8080
