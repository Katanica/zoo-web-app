# 🐾 Zoo Web App  
Spring Boot aplikacija za upravljanje zoološkim vrtom  
(backend + dokumentacija u /docs)

---

## 📌 Opis projekta

**Zoo Web App** je backend aplikacija napravljena u **Spring Bootu** sa MySQL bazom podataka.  
Projekt omogućava upravljanje različitim dijelovima zoološkog vrta, uključujući:

- životinje  
- jedinke  
- radnike  
- grupe  
- troškove  
- hranjenje  
- edukacije i posjete  

Aplikacija koristi REST API pristup i podržava standardne CRUD operacije za sve entitete.

---

## 🧱 Tehnologije

- Java 21+
- Spring Boot
- Spring Web
- Spring Data JPA / Hibernate
- MySQL
- Maven
- Lombok

---

## 📂 Struktura projekta

```
zoo-web-app/
│
├── src/main/java/zoo_web_app
│   ├── Entity/        # JPA entiteti
│   ├── Repository/    # Repository sloj
│   ├── Service/       # Poslovna logika
│   ├── Controller/    # REST API kontroleri
│   └── ZooWebAppApplication.java
│
├── src/main/resources
│   ├── application.properties  # MySQL konfiguracija
│   └── static/                 # HTML forme za testiranje
│
├── docs/              # ER dijagrami, slike, specifikacije
├── pom.xml
└── README.md
```

## 🚀 Pokretanje aplikacije

### 1. Kloniraj projekt
```bash
git clone https://github.com/USERNAME/zoo-web-app.git
```

### 2. Kreiraj bazu
```sql
CREATE DATABASE zoo_db;
```

### 3. Pokreni aplikaciju
Terminal:
```bash
mvn spring-boot:run
```
ili IntelliJ → Run.

---

## 🐾 Funkcionalnosti

- Upravljanje životinjama  
- Upravljanje jedinkama  
- Evidencija radnika  
- Grupe i vodiči  
- Troškovi i hranjenje  
- REST API za sve entitete  
- Automatsko kreiranje tablica putem Hibernate-a  

---

## 📌 Plan razvoja

- [ ] Dodati DTO modele  
- [ ] Dodati mapiranje (ModelMapper)  
- [ ] Validacija ulaznih podataka  
- [ ] Autentifikacija (Spring Security)  
- [ ] Moderni frontend (React/Angular)  
- [ ] Docker podrška  

---

## 👥 Autori

Ivan Nikic, Gabrijel Katana, Jurica Stjepanovic, Mario Ljusanin i Marko Samardzic 
Fakultet strojarstva, računarstva i elektrotehnike (FSRE)

---
