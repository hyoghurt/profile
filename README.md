# web application
Java 8  
Java Servlet API  
JdbcTemplate  
Registration and authentication  
WAR archive -> Tomcat 9  

---
`docker-compose.yml` содержит: собранное приложение и базу данных (PostgreSQL)

Start: `docker compose up --build -d`  
Stop and delete volumes: `docker compose down -v`  

---

- http://localhost:8080/cinema/
- http://localhost:8080/cinema/signin
- http://localhost:8080/cinema/signup
- http://localhost:8080/cinema/profile
