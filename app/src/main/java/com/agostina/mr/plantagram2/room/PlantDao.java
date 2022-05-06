package com.agostina.mr.plantagram2.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.agostina.mr.plantagram2.model.plants.Plants;

import java.util.List;

@Dao
public interface PlantDao {
    @Insert
    public void insertPlant(Plants... plants);
    @Query("SELECT * FROM Plant ")
    LiveData<List<Plants>> getAllPlants();
    @Query("SELECT * FROM Plant WHERE dbId = :databaseId")
    LiveData<Plants> getPlant(int databaseId);
    @Delete
    public void deletePlant(Plants... plants);
    @Update
    public void updatePlant(Plants... plants);


}
