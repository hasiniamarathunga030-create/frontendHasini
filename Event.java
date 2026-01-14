package com.mycompany.clubsphere.model;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class Event {
    private int eventId;
    private int clubId;
    private String eventName;
    private Date date;
    private Time time;
    private String location;
    private String description;
    private List<ViewFeedback> feedbacks;

    public Event(int eventId, int clubId, String eventName, Date date, Time time, String location, String description) {
        this.eventId = eventId;
        this.clubId = clubId;
        this.eventName = eventName;
        this.date = date;
        this.time = time;
        this.location = location;
        this.description = description;
    }

    public Event() {}

    public int getEventId() { return eventId; }
    public int getClubId() { return clubId; }
    public String getEventName() { return eventName; }
    public Date getDate() { return date; }
    public Time getTime() { return time; }
    public String getLocation() { return location; }
    public String getDescription() { return description; }
    public List<ViewFeedback> getFeedbacks() { return feedbacks; }

    public void setEventId(int eventId) { this.eventId = eventId; }
    public void setClubId(int clubId) { this.clubId = clubId; }
    public void setEventName(String eventName) { this.eventName = eventName; }
    public void setDate(Date date) { this.date = date; }
    public void setTime(Time time) { this.time = time; }
    public void setLocation(String location) { this.location = location; }
    public void setDescription(String description) { this.description = description; }
    public void setFeedbacks(List<ViewFeedback> feedbacks) { this.feedbacks = feedbacks; }
}
