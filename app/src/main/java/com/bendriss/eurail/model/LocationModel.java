package com.bendriss.eurail.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * This is the entity of locations which will be saved
 */
@Entity
public class LocationModel {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String time;
    private String longitude;
    private String latitude;

    @Override
    public String toString() {
        return "LocationModel{" +
                "id=" + id +
                ", time='" + time + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public LocationModel() {
    }

    public LocationModel(int id, String time, String longitude, String latitude) {
        this.id = id;
        this.time = time;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}