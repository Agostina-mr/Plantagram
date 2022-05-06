package com.agostina.mr.plantagram2.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.agostina.mr.plantagram2.model.User;
import com.agostina.mr.plantagram2.model.plants.Plants;

@Database(entities = {User.class, Plants.class}, version =1 )
@TypeConverters({Converters.class})

public abstract class PlantDatabase extends RoomDatabase {

    private static PlantDatabase instance;
    public abstract PlantDao plantDao();
    public abstract UserDao userDao();

    public static synchronized PlantDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context,
                    PlantDatabase.class, "plant_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
