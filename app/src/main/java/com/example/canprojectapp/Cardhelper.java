package com.example.canprojectapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Cardhelper {
     private String poster, title,year,rating;

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}

//implements Parcelable {
//
//        String poster, title,year,rating;
//
//public Cardhelper(String poster, String title, String year, String rating) {
//        this.poster = poster;
//        this.title = title;
//        this.year = year;
//        this.rating = rating;
//        }
//
//protected Cardhelper(Parcel in) {
//        poster = in.readString();
//        title = in.readString();
//        year = in.readString();
//        rating = in.readString();
//        }
//
//public static final Creator<Cardhelper> CREATOR = new Creator<Cardhelper>() {
//@Override
//public Cardhelper createFromParcel(Parcel in) {
//        return new Cardhelper(in);
//        }
//
//@Override
//public Cardhelper[] newArray(int size) {
//        return new Cardhelper[size];
//        }
//        };
//
//public String getPoster() {
//        return poster;
//        }
//
//public void setPoster(String poster) {
//        this.poster = poster;
//        }
//
//public String getTitle() {
//        return title;
//        }
//
//public void setTitle(String title) {
//        this.title = title;
//        }
//
//public String getYear() {
//        return year;
//        }
//
//public void setYear(String year) {
//        this.year = year;
//        }
//
//public String getRating() {
//        return rating;
//        }
//
//public void setRating(String rating) {
//        this.rating = rating;
//        }
//
//public Cardhelper() {
//
//        }
//
//@Override
//public int describeContents() {
//        return 0;
//        }
//
//@Override
//public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(poster);
//        dest.writeString(title);
//        dest.writeString(year);
//        dest.writeString(rating);
//        }
//        }
