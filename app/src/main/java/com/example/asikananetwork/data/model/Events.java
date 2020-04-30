package com.example.asikananetwork.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;

public class Events implements Parcelable {
    private String eventID;
    private String eventTitle;
    private String eventDetails;
    private Long eventDate;

    public Events() {
    }


    public Events(String eventID, String eventTitle, String eventDetails, String eventDate) {
        this.eventID = eventID;
        this.eventTitle = eventTitle;
        this.eventDetails = eventDetails;
        this.eventDate = Calendar.getInstance().getTimeInMillis();
        ;
    }

    protected Events(Parcel in) {
    }

    public static final Creator<Events> CREATOR = new Creator<Events>() {
        @Override
        public Events createFromParcel(Parcel in) {
            return new Events(in);
        }

        @Override
        public Events[] newArray(int size) {
            return new Events[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(String eventDetails) {
        this.eventDetails = eventDetails;
    }

    public Long getEventDate() {
        return eventDate;
    }

    public void setEventDate(Long eventDate) {
        this.eventDate = eventDate;
    }
}
