package com.agostina.mr.plantagram2.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.agostina.mr.plantagram2.model.plants.Plant;

import java.util.List;

@Dao
public interface PlantDao {
    @Insert
    public void insertPlant(Plant... plants);
    @Query("SELECT * FROM plant ")
    LiveData<List<Plant>> getAllPlants();
    @Query("SELECT * FROM plant WHERE dbId = :databaseId")
    LiveData<Plant> getPlant(int databaseId);
    @Delete
    public void deletePlant(Plant... plant);
    @Update
    public void updatePlant(Plant... plants);


}
