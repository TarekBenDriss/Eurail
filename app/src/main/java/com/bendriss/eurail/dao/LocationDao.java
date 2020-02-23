package com.bendriss.eurail.dao;

import com.bendriss.eurail.model.LocationModel;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.OnConflictStrategy;

/**
 * The DOA of location model will help us to add and retrieve locations.
 */
@Dao
public interface LocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addLocation(LocationModel locationModel);

    @Query("select * from LocationModel")
    public List<LocationModel> getAllLocations();

}
