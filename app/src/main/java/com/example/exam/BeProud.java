package com.example.exam;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "beProud_table")
public class BeProud {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private int imageId;
    private String description;
    private String code;
    private Double rate;

    public BeProud(String code, String title, Double rate, int imageId, String description) {
        this.title = title;
        this.imageId = imageId;
        this.description = description;
        this.code = code;
        this.rate = rate;
    }

    public BeProud() {
        this.title = null;
        this.imageId = R.drawable.heart;
        this.description = null;
        this.code = null;
        this.rate = null;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getRate() {
        return rate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
