package com.mycompany.clubsphere.model;

import java.time.LocalDateTime;

public class Feedback {
    private int feedbackId;
    private int eventId;
    private int userId;
    private int rating;
    private String comment;
    private LocalDateTime submittedAt;

    public Feedback() {}

    public Feedback(int feedbackId, int eventId, int userId, int rating, String comment, LocalDateTime submittedAt) {
        this.feedbackId = feedbackId;
        this.eventId = eventId;
        this.userId = userId;
        this.rating = rating;
        this.comment = comment;
        this.submittedAt = submittedAt;
    }

    public Feedback(int eventId, int userId, int rating, String comment) {
        this.eventId = eventId;
        this.userId = userId;
        this.rating = rating;
        this.comment = comment;
    }

    public int getFeedbackId() { return feedbackId; }
    public void setFeedbackId(int feedbackId) { this.feedbackId = feedbackId; }

    public int getEventId() { return eventId; }
    public void setEventId(int eventId) { this.eventId = eventId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public LocalDateTime getSubmittedAt() { return submittedAt; }
    public void setSubmittedAt(LocalDateTime submittedAt) { this.submittedAt = submittedAt; }
}
