<%@page contentType="text/html" pageEncoding="UTF-8"%>

                    <ul class="nav navbar-nav">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">${pageContext.response.locale}<span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a onclick="show('en')">English</a></li>
                                <li><a onclick="show('ru')">Русский</a></li>
                            </ul>
                        </li>
                    </ul>