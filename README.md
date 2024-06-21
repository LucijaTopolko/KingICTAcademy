## 1. Postavljanje baze
Aplikacija za rad koristi PostgreSQL. Kako bi se aplikacija mogla koristiti, potrebno je kreirati bazu. Podaci o bazi navedeni su u application.properties.
```
spring.datasource.url=jdbc:postgresql://localhost:5432/kingdb
spring.datasource.username=postgres
spring.datasource.password=admin
```
U aplikaciji pgAdmin 4, u željenom serveru (izbornik s lijeve strane), potrebno je napraviti desni klik, Create > Database te bazu nazvati ```kingdb```. 

## 2. Pokretanje aplikacije
Pozicionirajte se u korijenski direktorij projekta. Iz naredbenog retka, program se pokreće:
```
mvn clean install
java -jar target/KingICTAcademy-0.0.1-SNAPSHOT.jar 
```

Program se pokreće na ```localhost:8080```.

# Dokumentacija i upute
Dokumentacija svih endpointa, zajedno s primjerima korištenja, nalazi se na linku
https://documenter.getpostman.com/view/28498378/2sA3XV9zZq

Autorizacija je ostvarena tako da samo korisnici s ulogom "ADMIN" mogu pristupiti endpointovima za prikaz proizvoda. 

## Podaci (može se prijaviti kao bilo koji korisnik s popisa https://dummyjson.com/users, lozinke su u bazi zaštićene:
### ADMIN -> može pristupiti sva 4 endpointa
username: emilys
password: emilyspass

### SVI OSTALI -> ne mogu pristupiti endpointima
username: avat
password: avatpass

# Dodatne informacije o aplikaciji
1. Koristila sam Cache kako bih omogućila brži pristup podacima ako su oni već pretraženi. CacheEvict omogućuje da se ta priručna memorija isprazni kad se nešto u bazi promijeni kako ne bi došlo do ispisa zastarjelih podataka.
2. Napisano je nekoliko testova za pojedine endpointove, a nalaze se na lokaciji: src/test/java/com/academy/kingictacademy/KingIctAcademyApplicationTests.java
3. Za brisanje svih tablica iz baze:
```
DROP TABLE users;
DROP TABLE bank;
DROP TABLE company;
DROP TABLE crypto;
DROP TABLE hair;
DROP TABLE category;
DROP TABLE product_images;
DROP TABLE product_reviews;
DROP TABLE product_tags;
DROP TABLE product;
DROP TABLE dimensions;
DROP TABLE meta_data;
DROP TABLE review;
DROP TABLE address;
DROP TABLE coordinates;
DROP TABLE login_data;

```
   
