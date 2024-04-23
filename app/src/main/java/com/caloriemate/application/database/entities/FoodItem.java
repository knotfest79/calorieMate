package com.caloriemate.application.database.entities;

import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.caloriemate.application.R;

@Entity
public class FoodItem implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public Integer UID;
    public String Name;
    public String ImageName;

    public double Calorie;

    public double Carbs;
    public double Protein;

    public double Fat;

    public double sugar;

    public double kilojoule;

    public double Calcium;

    public double Potassium;

    public double Zinc;

    public double Selenium;

    public double Alcohol;
    public double Fiber;
    public double Phosphorous;
    public double Sodium;

    public double Copper;
    // Usable public no-arg constructor for Room
    public FoodItem() {
    }
    protected FoodItem(Parcel in) {
        UID = in.readInt();
        Name = in.readString();
        ImageName = in.readString();
        Calorie = in.readDouble();
        Carbs = in.readDouble();
        Protein = in.readDouble();
        Fat = in.readDouble();
        sugar = in.readDouble();
        kilojoule = in.readDouble();
        Calcium = in.readDouble();
        Potassium = in.readDouble();
        Zinc = in.readDouble();
        Selenium = in.readDouble();
        Alcohol = in.readDouble();
        Fiber = in.readDouble();
        Phosphorous = in.readDouble();
        Sodium = in.readDouble();
        Copper = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(UID);
        dest.writeString(Name);
        dest.writeString(ImageName);
        dest.writeDouble(Calorie);
        dest.writeDouble(Carbs);
        dest.writeDouble(Protein);
        dest.writeDouble(Fat);
        dest.writeDouble(sugar);
        dest.writeDouble(kilojoule);
        dest.writeDouble(Calcium);
        dest.writeDouble(Potassium);
        dest.writeDouble(Zinc);
        dest.writeDouble(Selenium);
        dest.writeDouble(Alcohol);
        dest.writeDouble(Fiber);
        dest.writeDouble(Phosphorous);
        dest.writeDouble(Sodium);
        dest.writeDouble(Copper);
    }

    public static final Creator<FoodItem> CREATOR = new Creator<FoodItem>() {
        @Override
        public FoodItem createFromParcel(Parcel in) {
            return new FoodItem(in);
        }

        @Override
        public FoodItem[] newArray(int size) {
            return new FoodItem[size];
        }
    };

    public int getImageResource(String imageName) {
        if (imageName == null) return R.drawable.eggs; // handle null case
        System.out.println(imageName);
        switch (imageName.toLowerCase()) { // Use toLowerCase to avoid case sensitivity issues
            case "eggs.png":
                return R.drawable.eggs;
            case "bread.png":
                return R.drawable.bread;
            case "honey.png":
                return R.drawable.honey;
            default:
                return R.drawable.eggs; // It's good to have a default case that handles unexpected inputs
        }
    }
}
