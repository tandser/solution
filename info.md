﻿### Тестовые персонажи

<table>
    <tr>
        <th>Права</th>
        <th>Логин</th>
        <th>Пароль</th>
    </tr>
    <tr>
        <td>admin</td>
        <td>l.douglas@gmail.com</td>
        <td>Mr01LRc</td>
    </tr>
    <tr>
        <td>user</td>
        <td>s.welch@gmail.com</td>
        <td>Izhyw29</td>
    </tr>
</table>

### REST-интерфейс

<table>
    <tr>
        <th>Метод</th>
        <th>Права</th>
        <th>URL</th>
        <th>Описание</th>
    </tr>
    <tr>
        <td>GET</td>
        <td>admin</td>
        <td>/rest/users</td>
        <td>Извлечь всех пользователей</td>
    </tr>
    <tr>
        <td>POST</td>
        <td>admin</td>
        <td>/rest/users</td>
        <td>Создать нового пользователя</td>
    </tr>
    <tr>
        <td>GET</td>
        <td>admin</td>
        <td>/rest/users/{<em>id</em>}</td>
        <td>Извлечь пользователя с указанным <em>id</em></td>
    </tr>
    <tr>
        <td>PUT</td>
        <td>admin</td>
        <td>/rest/users/{<em>id</em>}</td>
        <td>Обновить пользователя с указанным <em>id</em></td>
    </tr>
    <tr>
        <td>DELETE</td>
        <td>admin</td>
        <td>/rest/users/{<em>id</em>}</td>
        <td>Удалить пользователя с указанным <em>id</em></td>
    </tr>
    <tr>
        <td>GET</td>
        <td>amidn</td>
        <td>/rest/users/by?email={<em>email</em>}</td>
        <td>Извлечь пользователя с указанным <em>email</em></td>
    </tr>
    <tr>
        <td>PUT</td>
        <td>admin</td>
        <td>/rest/users/toggle/{<em>id</em>}?state={<em>true</em> | <em>false</em>}</td>
        <td>Включить или выключить пользователя с указанным <em>id</em></td>
    </tr>
    <tr>
        <td>GET</td>
        <td>admin, user</td>
        <td>/rest/meals</td>
        <td>Извлечь все записи авторизованного пользователя о приёмах пищи</td>
    </tr>
    <tr>
        <td>POST</td>
        <td>admin, user</td>
        <td>/rest/meals</td>
        <td>Создать новую запись авторизованного пользователя о приёме пищи</td>
    </tr>
    <tr>
        <td>GET</td>
        <td>admin, user</td>
        <td>/rest/meals/{<em>id</em>}</td>
        <td>Извлечь запись авторизованного пользователя о приёме пищи с указанным <em>id</em></td>
    </tr>
    <tr>
        <td>PUT</td>
        <td>admin, user</td>
        <td>/rest/meals/{<em>id</em>}</td>
        <td>Обновить запись авторизованного пользователя о приёме пищи с указанным <em>id</em></td>
    </tr>
    <tr>
        <td>DELETE</td>
        <td>admin, user</td>
        <td>/rest/meals/{<em>id</em>}</td>
        <td>Удалить запись авторизованного пользователя о приёме пищи с указанным <em>id</em></td>
    </tr>
    <tr>
        <td>GET</td>
        <td>admin, user</td>
        <td>/rest/meals/between?from={<em>from</em>}&to={<em>to</em>}</td>
        <td>Извлечь записи авторизованного пользователя о приёме пищи в промежутке между моментами времени <em>from</em> и <em>to</em>, где <em>from</em> и <em>to</em> в формате ISO-8601 yyyy-MM-ddTHH:mm</td>
    </tr>
</table>