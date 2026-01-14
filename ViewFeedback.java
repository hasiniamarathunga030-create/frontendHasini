package com.mycompany.clubsphere.model;

import java.sql.Timestamp;

public class ViewFeedback {
    private int feedbackId;
    private int userId;
    private int rating;
    private String comment;
    private Timestamp submittedAt;
    private String username;

    public int getFeedbackId() { return feedbackId; }
    public void setFeedbackId(int feedbackId) { this.feedbackId = feedbackId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public Timestamp getSubmittedAt() { return submittedAt; }
    public void setSubmittedAt(Timestamp submittedAt) { this.submittedAt = submittedAt; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}
