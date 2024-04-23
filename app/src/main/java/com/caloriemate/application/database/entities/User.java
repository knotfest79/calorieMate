package com.caloriemate.application.database.entities;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public Integer UID;
    public String Email;
    public String Password;
    @Nullable
    public String Gender;
    @Nullable
    public String DOB;
    @Nullable
    public Integer Height;
    @Nullable
    public Integer Weight;
}
