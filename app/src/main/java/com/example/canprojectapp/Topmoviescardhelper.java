package com.example.canprojectapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Topmoviescardhelper implements Parcelable {
    String poster, title,rating, year ;



    public Topmoviescardhelper(String poster, String title, String rating, String year) {
        this.poster = poster;
        this.title = title;
        this.rating = rating;
        this.year = year;
    }
    public Topmoviescardhelper(){

    }

    protected Topmoviescardhelper(Parcel in) {
        poster = in.readString();
        title = in.readString();
        rating = in.readString();
        year = in.readString();
    }

    public static final Creator<Topmoviescardhelper> CREATOR = new Creator<Topmoviescardhelper>() {
        @Override
        public Topmoviescardhelper createFromParcel(Parcel in) {
            return new Topmoviescardhelper(in);
        }

        @Override
        public Topmoviescardhelper[] newArray(int size) {
            return new Topmoviescardhelper[size];
        }
    };

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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(poster);
        dest.writeString(title);
        dest.writeString(rating);
        dest.writeString(year);
    }
}
