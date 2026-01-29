Zoo Web App

Ova web aplikacija služi za osnovno upravljanje podacima zoološkog vrta. Projekt je izrađen kao backend–frontend aplikacija koristeći Spring Boot (REST API) i jednostavan HTML/CSS/JavaScript frontend. Aplikacija je razvijana postupno i pokriva funkcionalnosti koje su stvarno implementirane u projektu.

Aplikacija omogućuje evidenciju životinja, skupina životinja, nastambi, radnika, vodiča, grupa posjetitelja, incidenata i troškova. Naglasak je stavljen na ispravne relacije u bazi podataka, rad REST servisa i funkcionalan web prikaz podataka.

Korištene tehnologije:
Java 21, Spring Boot (Spring Web, Spring Data JPA), Hibernate/JPA, SQL Server ili MySQL (ovisno o konfiguraciji), Maven, HTML, CSS i čisti JavaScript.

Struktura projekta sastoji se od Controller, Service, Repository, Entity i DTO slojeva. Backend je organiziran prema REST arhitekturi, a frontend koristi fetch API za komunikaciju s backendom.

Životinje i skupine:
Implementirana je evidencija pojedinačnih jedinki i skupina životinja. Svaka jedinka i skupina može biti aktivna ili neaktivna, pri čemu se zapisi ne brišu iz baze nego se deaktiviraju uz razlog. Skupine i jedinke povezane su s nastambama, načinima nabave, troškovima i incidentima.

Nastambe:
Implementirana je evidencija nastambi s osnovnim podacima kao što su oznaka, opis i geometrija. Jedna skupina pripada jednoj nastambi, dok jedna nastamba može imati više skupina.

Radnici i vodiči:
Radnici su evidentirani kao entiteti u sustavu. Dio radnika može imati ulogu vodiča koji se dodjeljuju grupama posjetitelja. Radnici su povezani s obavezama, grupama posjetitelja i incidentima.

Grupe posjetitelja:
Omogućen je unos i pregled grupa posjetitelja. Svaka grupa ima naziv, kontakt podatke, broj osoba, datum dolaska, vrijeme početka i završetka posjete te dodijeljenog vodiča. Status grupe (npr. najavljeno) također se evidentira.

Incidenti:
Implementirana je evidencija incidenata u zoološkom vrtu. Incidenti imaju datum, opis i vrstu, te mogu biti povezani s jedinkama i skupinama životinja. Incidenti se unose i pregledavaju putem web sučelja.

Troškovi:
Troškovi su implementirani za pojedinačne jedinke i skupine životinja. Troškovi mogu biti novčani ili iskazani u satima rada. U backendu je implementiran izračun ukupnog troška (npr. broj sati puta satnica). Za prikaz troškova koristi se DTO sloj kako bi se izbjegli problemi s cikličkim referencama i kako bi se prikazali samo potrebni podaci. Omogućen je unos i pregled troškova putem web stranica.

Izvještaji:
Postoji osnovni controller za izvještaje i dohvat podataka putem REST API-ja. Napredni izvještaji (PDF ili Excel) nisu implementirani.

Ograničenja trenutne verzije:
Aplikacija nema autentikaciju ni korisničke uloge. Nisu implementirane smjene radnika, automatsko ponavljanje obaveza niti izvoz izvještaja u PDF ili Excel format. Frontend je izrađen bez korištenja JavaScript frameworka.

Pokretanje aplikacije:
Potrebno je konfigurirati bazu podataka u application.properties datoteci. Aplikacija se pokreće pomoću Maven naredbe mvn spring-boot:run, nakon čega je dostupna na adresi [http://localhost:8080](http://localhost:8080).
