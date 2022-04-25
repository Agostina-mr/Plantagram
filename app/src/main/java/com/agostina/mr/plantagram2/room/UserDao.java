package com.agostina.mr.plantagram2.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.agostina.mr.plantagram2.model.User;

@Dao
public interface UserDao {
    @Insert
    public void insertUser(User... users);
    @Query("SELECT * FROM  user WHERE  id = :userId LIMIT 1")
    LiveData<User> getUserById(int userId);
    @Delete
    public void deleteUser(User... users);
    @Update
    public void updateUser(User... Users);
}
