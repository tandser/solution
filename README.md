[![Codacy Badge](https://api.codacy.com/project/badge/Grade/6566ae5228b340efb8aa5e728c1ada13)](https://www.codacy.com/app/tandser/solution?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=tandser/solution&amp;utm_campaign=Badge_Grade)
[![Dependency Status](https://dependencyci.com/github/tandser/solution/badge)](https://dependencyci.com/github/tandser/solution)
[![Build Status](https://travis-ci.org/tandser/solution.svg?branch=master)](https://travis-ci.org/tandser/solution)

# Diary Calories

Пример веб-приложения на популярном стеке технологий и инструментов Java Enterprise с авторизацией и правами доступа на основе ролей.

Проект разработан на Java SE 8 с использованием следующих фреймворков и инструментов:

* [Maven](https://maven.apache.org/)
* [Spring Framework](http://projects.spring.io/spring-framework/)
* [Spring Data JPA](http://projects.spring.io/spring-data-jpa/)
* [Spring Security](http://projects.spring.io/spring-security/)
* [Hibernate ORM](http://hibernate.org/orm/)
* [Hibernate Validator](http://hibernate.org/validator/)
* [Apache Tomcat](http://tomcat.apache.org/)
* [PostgreSQL](https://www.postgresql.org/)
* [SLF4J](https://www.slf4j.org/)
* [Logback](https://logback.qos.ch/)
* [JSP](https://jcp.org/aboutJava/communityprocess/final/jsr245/index.html)
* [Jackson](https://github.com/FasterXML/jackson)
* [JUnit](http://junit.org/junit4/)

## Конфигурация базы данных

В приложении используется база данных PostgreSQL. Конфигурация JDBC-драйвера располагается в `src\main\resources\properties\database.properties` и выглядит следующим образом:

```
postgresql.driverClassName = org.postgresql.Driver
postgresql.url             = jdbc:postgresql://localhost:5432/postgres
postgresql.username        = postgres
postgresql.password        = postgres
```

Инициализация базы данных осуществляется с помощью сценариев, которые могут быть обнаружены в `src\main\resources\ddl`.

