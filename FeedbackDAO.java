package com.mycompany.clubsphere.dao;

import com.mycompany.clubsphere.config.DatabaseConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FeedbackDAO {

    public static boolean insertFeedback(int userId, int eventId, int rating, String comment) {
        String query = "INSERT INTO feedback (user_id, event_id, rating, comment) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            stmt.setInt(2, eventId);
            stmt.setInt(3, rating);
            stmt.setString(4, comment);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
