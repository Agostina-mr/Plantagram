package com.agostina.mr.plantagram2.repository;

import com.agostina.mr.plantagram2.R;
import com.agostina.mr.plantagram2.model.User;

import java.util.ArrayList;

public class UserRepository {

    private static UserRepository instance;
    private ArrayList<User> users;

    private UserRepository() {
        this.users = new ArrayList<>();

    }

    public static UserRepository getInstance()
    {
        if(instance == null)
        {
            return new UserRepository();
        }
        return instance;
    }

    public User getUsersById(int id) {
        return users.get(id);
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
}
