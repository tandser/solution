[![Codacy Badge](https://api.codacy.com/project/badge/Grade/6566ae5228b340efb8aa5e728c1ada13)](https://www.codacy.com/app/tandser/solution?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=tandser/solution&amp;utm_campaign=Badge_Grade)
[![Dependency Status](https://dependencyci.com/github/tandser/solution/badge)](https://dependencyci.com/github/tandser/solution)
[![Build Status](https://travis-ci.org/tandser/solution.svg?branch=master)](https://travis-ci.org/tandser/solution)

# Дневник калорий

Пример веб-приложения на популярном стеке технологий и инструментов Java Enterprise с авторизацией и правами доступа на основе ролей. Работа с приложением реализована через веб-интерфейс с применением Ajax, а также по REST в формате JSON. Весь функционал приложения покрывается JUnit-тестами.

Проект разработан на Java 8 с использованием следующих фреймворков и инструментов:

[Maven](https://maven.apache.org/), [Spring Framework](http://projects.spring.io/spring-framework/), [Spring Data JPA](http://projects.spring.io/spring-data-jpa/), [Spring Security](http://projects.spring.io/spring-security/), [Hibernate ORM](http://hibernate.org/orm/), [Hibernate Validator](http://hibernate.org/validator/), [Apache Tomcat](http://tomcat.apache.org/), [PostgreSQL](https://www.postgresql.org/), [SLF4J](https://www.slf4j.org/), [Logback](https://logback.qos.ch/), [JSP](https://jcp.org/aboutJava/communityprocess/final/jsr245/index.html), [Jackson](https://github.com/FasterXML/jackson), [JUnit](http://junit.org/junit4/), JavaScript, [JQuery](https://jquery.com/) + plugins, [Bootstrap](http://getbootstrap.com/)

Демонстрация приложения доступна в облаке по адресу [tandser.herokuapp.com](http://tandser.herokuapp.com/). Как правило, старт приложения занимает не больше минуты, &#151; это время необходимо [Heroku](https://www.heroku.com/), чтобы развернуть приложение на сервере.

Для входа в приложение воспользуйтесь аккаунтами тестовых персонажей или зарегистрируйтесь.

<table>
    <tr>
        <th>Права</th>
        <th>Логин</th>
        <th>Пароль</th>
    </tr>
    <tr>
        <td>ADMIN</td>
        <td>l.douglas@gmail.com</td>
        <td>Mr01LRc</td>
    </tr>
    <tr>
        <td>USER</td>
        <td>s.welch@gmail.com</td>
        <td>Izhyw29</td>
    </tr>
</table>

## Конфигурация базы данных

В приложении используется база данных PostgreSQL. Локальная конфигурация JDBC-драйвера располагается в `src\main\resources\properties\localhost.properties`. Инициализация базы данных осуществляется с помощью сценариев, которые можно обнаружить в `src\main\resources\ddl`. Будьте внимательны при развёртывании приложения на вашем компьютере и в случае необходимости отредактируйте конфигурацию драйвера и сценарии под ваши настройки.

## Работа с REST-интерфейсом

Доступ к веб-службе REST осуществляется через базовую авторизацию зарегистрированных в приложении пользователей. В следующей таблице отражена структура служб, которая включает поддерживаемые HTTP-методы и целевые URL для различных операций.

<table>
    <tr>
        <th width="40%">URL</th>
        <th>HTTP-метод</th>
        <th>Права</th>
        <th>Описание</th>
    </tr>
    <tr>
        <td>/rest/users</td>
        <td>GET</td>
        <td>ADMIN</td>
        <td>Извлечение всех пользователей</td>
    </tr>
    <tr>
        <td>/rest/users</td>
        <td>POST</td>
        <td>ADMIN</td>
        <td>Создание нового пользователя</td>
    </tr>
    <tr>
        <td>/rest/users/{<i>id</i>}</td>
        <td>GET</td>
        <td>ADMIN</td>
        <td>Извлечение пользователя с указанным идентификатором</td>
    </tr>
    <tr>
        <td>/rest/users/{<i>id</i>}</td>
        <td>PUT</td>
        <td>ADMIN</td>
        <td>Обновление существующего пользователя с указанным идентификатором</td>
    </tr>
    <tr>
        <td>/rest/users/by?email={<i>email</i>}</td>
        <td>GET</td>
        <td>ADMIN</td>
        <td>Извлечение пользователя с указанным адресом электронной почты</td>
    </tr>
    <tr>
        <td>/rest/users/toggle/{<i>id</i>}?state={<i>true</i> | <i>false</i>}</td>
        <td>PUT</td>
        <td>ADMIN</td>
        <td></td>
    </tr>
</table>