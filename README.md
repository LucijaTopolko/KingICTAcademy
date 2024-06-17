## 1. Postavljanje baze
Aplikacija za rad koristi PostgreSQL. Kako bi se aplikacija mogla koristiti, potrebno je kreirati bazu. Podaci o bazi navedeni su u application.properties.
```
spring.datasource.url=jdbc:postgresql://localhost:5432/kingdb
spring.datasource.username=postgres
spring.datasource.password=admin
```
U aplikaciji pgAdmin 4, u željenom serveru (izbornik s lijeve strane), potrebno je napraviti desni klik, Create > Database te bazu nazvati ```kingdb```. 
