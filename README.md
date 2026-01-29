🦁 Zoo Web App

Web aplikacija za osnovno upravljanje podacima zoološkog vrta, razvijena kao Spring Boot + REST + HTML/JavaScript projekt.
Aplikacija omogućuje evidenciju životinja, skupina, nastambi, zaposlenika, troškova, incidenata i grupa posjetitelja.

🧩 Korištene tehnologije

Java 21

Spring Boot

Spring Web

Spring Data JPA

Hibernate / JPA

SQL Server / MySQL (ovisno o konfiguraciji)

HTML, CSS, Vanilla JavaScript

Maven

📁 Struktura projekta
zoo-web-app/
├── src/main/java/zoo_web_app
│   ├── Controller
│   ├── Service
│   ├── Repository
│   ├── Entity
│   ├── DTO
│   └── config
│
├── src/main/resources
│   ├── static
│   ├── application.properties
│   └── data.sql

✅ Implementirane funkcionalnosti
🐾 Životinje i skupine

Evidencija pojedinačnih jedinki

Evidencija skupina životinja

Aktivne i neaktivne jedinke (bez fizičkog brisanja)

Povezanost sa:

nastambama

načinom nabave

troškovima

incidentima

🏠 Nastambe

Evidencija nastambi

Geometrija i opis nastambe

Jedna skupina pripada jednoj nastambi

Jedna nastamba može imati više skupina

👷 Radnici i vodiči

Evidencija radnika

Posebna uloga vodiča

Dodjela vodiča grupama posjetitelja

Povezanost s obavezama i incidentima

👥 Grupe posjetitelja

Dodavanje i pregled grupa

Dodjela jednog vodiča po grupi

Datum dolaska

Vrijeme početka i završetka posjete

Status grupe (npr. NAJAVLJENO)

🚨 Incidenti

Evidencija incidenata

Vrste incidenata

Povezivanje incidenata sa:

skupinama

jedinkama

Pregled i unos putem web sučelja

💸 Troškovi

Troškovi su u potpunosti implementirani u backendu i frontendu.

Podržano:

Troškovi vezani uz:

pojedinačnu jedinku

skupinu životinja

Tip troška:

novčani trošak

trošak u satima rada

Automatski izračun ukupnog troška (broj sati × satnica)

DTO sloj za siguran i kontroliran ispis podataka

Backend:

TrosakController

TrosakService

TrosakServiceImpl

TrosakDto

Frontend:

troskovi.html – pregled troškova

troskovi-dodavanje.html – unos troškova

📊 Izvještaji

Postoji osnovni IzvjestajController

Trenutno dostupni REST endpointi za dohvat podataka

Izvoz u PDF / Excel nije implementiran

🚧 Ograničenja trenutne verzije

Nema autentikacije i korisničkih uloga

Nisu implementirani:

smjene radnika

automatsko ponavljanje obaveza

PDF / Excel export izvještaja

Frontend je izrađen bez JS frameworka (čisti HTML + JS)

▶️ Pokretanje aplikacije

Konfigurirati bazu u application.properties

Pokrenuti aplikaciju:

mvn spring-boot:run


Otvoriti u pregledniku:

http://localhost:8080
