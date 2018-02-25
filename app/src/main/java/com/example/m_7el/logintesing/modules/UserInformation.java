package com.example.m_7el.logintesing.modules;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class UserInformation implements Parcelable{

    @SerializedName("error")
    private String error;

    @SerializedName("field")
    private String field;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;


    protected UserInformation(Parcel in) {
        error = in.readString();
        field = in.readString();
        firstName = in.readString();
        lastName = in.readString();
    }

    public static final Creator<UserInformation> CREATOR = new Creator<UserInformation>() {
        @Override
        public UserInformation createFromParcel(Parcel in) {
            return new UserInformation(in);
        }

        @Override
        public UserInformation[] newArray(int size) {
            return new UserInformation[size];
        }
    };

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getError() {
        return error;
    }

    public String getField() {
        return field;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(error);
        dest.writeString(field);
        dest.writeString(firstName);
        dest.writeString(lastName);
    }
}
