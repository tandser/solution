package ru.tandser.solution.util;

import ru.tandser.solution.domain.User;

import java.io.Serializable;
import java.util.List;

public class Users implements Serializable {

    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}