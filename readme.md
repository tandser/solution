[![Codacy Badge](https://api.codacy.com/project/badge/Grade/6566ae5228b340efb8aa5e728c1ada13)](https://www.codacy.com/app/tandser/solution?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=tandser/solution&amp;utm_campaign=Badge_Grade)
[![Dependency Status](https://dependencyci.com/github/tandser/solution/badge)](https://dependencyci.com/github/tandser/solution)
[![Build Status](https://travis-ci.org/tandser/solution.svg?branch=master)](https://travis-ci.org/tandser/solution)

# Дневник калорий

Пример веб-приложения на популярном стеке технологий и инструментов Java Enterprise с авторизацией и правами доступа на основе ролей. Работа с приложением реализована через веб-интерфейс с применением Ajax, а также по REST в формате JSON. Весь функционал приложения покрывается JUnit-тестами.

Приложение разработано на Java 8 с использованием следующих фреймворков и инструментов:

[*Maven*](https://maven.apache.org/), [*Spring Framework*](http://projects.spring.io/spring-framework/), [*Spring Data JPA*](http://projects.spring.io/spring-data-jpa/), [*Spring Security*](http://projects.spring.io/spring-security/), [*Hibernate ORM*](http://hibernate.org/orm/), [*Hibernate Validator*](http://hibernate.org/validator/), [*Apache Tomcat*](http://tomcat.apache.org/), [*PostgreSQL*](https://www.postgresql.org/), [*SLF4J*](https://www.slf4j.org/), [*Logback*](https://logback.qos.ch/), [*JSP*](https://jcp.org/aboutJava/communityprocess/final/jsr245/index.html), [*Jackson*](https://github.com/FasterXML/jackson), [*JUnit*](http://junit.org/junit4/), *JavaScript*, [*JQuery*](https://jquery.com/) + plugins, [*Bootstrap*](http://getbootstrap.com/)

Демонстрация приложения доступна в облаке по адресу <http://tandser.herokuapp.com>. Как правило, старт приложения занимает не больше минуты, &#151; это время необходимо [Heroku](https://www.heroku.com/), чтобы развернуть приложение на сервере. Если при первом обращении приложение оказалось недоступным, повторите попытку.

Для входа в приложение воспользуйтесь аккаунтами тестовых персонажей (см. [info.txt](https://github.com/tandser/solution/info.txt)) или зарегистрируйтесь.

## Конфигурация базы данных

В приложении используется база данных PostgreSQL. Локальная конфигурация JDBC-драйвера располагается в *src* / *main* / *resources* / *properties* / *localhost.properties*. Инициализация базы данных осуществляется с помощью сценариев, которые можно обнаружить в *src* / *main* / *resources* / *ddl*. Будьте внимательны при развёртывании приложения на вашем компьютере и в случае необходимости отредактируйте конфигурацию драйвера и сценарии под ваши настройки.

## Работа с REST-интерфейсом

Доступ к веб-службе REST осуществляется через базовую авторизацию зарегистрированных в приложении пользователей. Смотрите таблицу в [info.txt](https://github.com/tandser/solution/info.txt), в которой отражена структура служб, которая включает поддерживаемые HTTP-методы и целевые URL для различных операций.