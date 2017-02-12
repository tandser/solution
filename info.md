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
        <td>Извлечь пользователя с указанным id</td>
    </tr>
    <tr>
        <td>PUT</td>
        <td>admin</td>
        <td>/rest/users/{<em>id</em>}</td>
        <td>Обновить пользователя с указанным id</td>
    </tr>
    <tr>
        <td>DELETE</td>
        <td>admin</td>
        <td>/rest/users/{<em>id</em>}</td>
        <td>Удалить пользователя с указанным id</td>
    </tr>
    <tr>
        <td>GET</td>
        <td>amidn</td>
        <td>/rest/users/by?email={<em>email</em>}</td>
        <td>Извлечь пользователя с указанным email</td>
    </tr>
    <tr>
        <td>PUT</td>
        <td>admin</td>
        <td>/rest/users/toggle/{id}?state={<em>true</em> | <em>false</em>}</td>
        <td>Включить или выключить пользователя с указанным id</td>
    </tr>
    <tr>
        <td>GET</td>
        <td>admin, user</td>
        <td>/rest/meals</td>
        <td>Извлечь все записи авторизированного пользователя о приёмах пищи</td>
    </tr>
    <tr>
        <td>POST</td>
        <td>admin, user</td>
        <td>/rest/meals</td>
        <td>Создать новую запись авторизированного пользователя о приёме пищи</td>
    </tr>
    <tr>
        <td>GET</td>
        <td>admin, user</td>
        <td>/rest/meals/{<em>id</em>}</td>
        <td>Извлечь запись авторизированного пользователя о приёме пищи с указанным id</td>
    </tr>
    <tr>
        <td>PUT</td>
        <td>admin, user</td>
        <td>/rest/meals/{<em>id</em>}</td>
        <td>Обновить запись авторизированного пользователя о приёме пищи с указанным id</td>
    </tr>
    <tr>
        <td>DELETE</td>
        <td>admin, user</td>
        <td>/rest/meals/{<em>id</em>}</td>
        <td>Удалить запись авторизированного пользователя о приёме пищи с указанным id</td>
    </tr>
    <tr>
        <td>GET</td>
        <td>admin, user</td>
        <td>/rest/meals/between?from={<em>from</em>}&to={<em>to</em>}</td>
        <td>Извлечь записи авторизированного пользователя о приёме пищи между from и to, где from и to в формате ISO-8601 yyyy-MM-ddTHH:mm</td>
    </tr>
</table>