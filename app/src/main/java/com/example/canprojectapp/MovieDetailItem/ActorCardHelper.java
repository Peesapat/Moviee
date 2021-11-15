package com.example.canprojectapp.MovieDetailItem;

import android.os.Parcel;
import android.os.Parcelable;

public class ActorCardHelper implements Parcelable {

    String picture, name;

    protected ActorCardHelper(Parcel in) {
        picture = in.readString();
        name = in.readString();
    }

    public ActorCardHelper(String picture, String name) {
        this.picture = picture;
        this.name = name;
    }

    public ActorCardHelper() {

    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static final Creator<ActorCardHelper> CREATOR = new Creator<ActorCardHelper>() {
        @Override
        public ActorCardHelper createFromParcel(Parcel in) {
            return new ActorCardHelper(in);
        }

        @Override
        public ActorCardHelper[] newArray(int size) {
            return new ActorCardHelper[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(picture);
        dest.writeString(name);
    }
}