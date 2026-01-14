package com.mycompany.clubsphere.dao;

import com.mycompany.clubsphere.config.DatabaseConfig;
import com.mycompany.clubsphere.model.ViewFeedback;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ViewFeedbackDAO {

    public static boolean insertFeedback(int userId, int eventId, int rating, String comment) {
        String query = "INSERT INTO feedback (user_id, event_id, rating, comment) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            stmt.setInt(2, eventId);
            stmt.setInt(3, rating);
            stmt.setString(4, comment);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<ViewFeedback> getFeedbackByEventId(int eventId) {
        List<ViewFeedback> feedbackList = new ArrayList<>();
        String query = "SELECT f.feedback_id, f.user_id, f.rating, f.comment, f.submitted_at, u.username " +
                       "FROM feedback f JOIN users u ON f.user_id = u.user_id " +
                       "WHERE f.event_id = ? ORDER BY f.submitted_at DESC";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, eventId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ViewFeedback fb = new ViewFeedback();
                    fb.setFeedbackId(rs.getInt("feedback_id"));
                    fb.setUserId(rs.getInt("user_id"));
                    fb.setRating(rs.getInt("rating"));
                    fb.setComment(rs.getString("comment"));
                    fb.setSubmittedAt(rs.getTimestamp("submitted_at"));
                    fb.setUsername(rs.getString("username"));
                    feedbackList.add(fb);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return feedbackList;
    }

    public static double getAverageRatingForEvent(int eventId) {
        String query = "SELECT AVG(rating) AS avg_rating FROM feedback WHERE event_id = ?";
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, eventId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("avg_rating");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
}
