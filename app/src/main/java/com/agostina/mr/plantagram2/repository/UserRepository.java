package com.agostina.mr.plantagram2.repository;

import com.agostina.mr.plantagram2.R;
import com.agostina.mr.plantagram2.model.User;

import java.util.ArrayList;

public class UserRepository {

    private static UserRepository instance;
    private ArrayList<User> users;

    private UserRepository() {
        this.users = new ArrayList<>();
        users.add(new User("flotus", "123456", R.drawable.p2));
        users.add(new User("markus", "123456", R.drawable.p1));
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
