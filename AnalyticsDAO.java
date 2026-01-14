package com.mycompany.clubsphere.dao;

import com.mycompany.clubsphere.config.DatabaseConfig;

import java.sql.*;
import java.util.*;

public class AnalyticsDAO {

    public static Map<String, Integer> getSummaryStats() {
        Map<String, Integer> stats = new HashMap<>();
        try (Connection conn = DatabaseConfig.getDataSource().getConnection()) {
            ResultSet rs = conn.createStatement().executeQuery("SELECT COUNT(*) AS cnt FROM clubs");
            if (rs.next()) stats.put("totalClubs", rs.getInt("cnt"));

            rs = conn.createStatement().executeQuery("SELECT COUNT(*) AS cnt FROM users WHERE role='student'");
            if (rs.next()) stats.put("totalMembers", rs.getInt("cnt"));

            rs = conn.createStatement().executeQuery("SELECT COUNT(*) AS cnt FROM events");
            if (rs.next()) stats.put("totalEvents", rs.getInt("cnt"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stats;
    }

    public static Map<String, Object> getOverallRating() {
        Map<String, Object> stats = new HashMap<>();
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT AVG(rating) AS avgRating, COUNT(*) AS totalFeedback FROM feedback");
            if (rs.next()) {
                stats.put("averageRating", rs.getDouble("avgRating"));
                stats.put("totalFeedbackCount", rs.getInt("totalFeedback"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stats;
    }

    public static Map<String, Integer> getMonthlyEvents() {
        Map<String, Integer> monthly = new LinkedHashMap<>();
        String query = "SELECT MONTHNAME(date) AS month, COUNT(*) AS cnt FROM events GROUP BY MONTH(date) ORDER BY MONTH(date)";
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                monthly.put(rs.getString("month"), rs.getInt("cnt"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return monthly;
    }

    public static Map<String, Integer> getPopularEvents() {
        Map<String, Integer> popular = new LinkedHashMap<>();
        String query = "SELECT e.event_name, COUNT(a.user_id) AS attendance " +
                       "FROM attendance a JOIN events e ON a.event_id = e.event_id " +
                       "GROUP BY e.event_name ORDER BY attendance DESC LIMIT 5";
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                popular.put(rs.getString("event_name"), rs.getInt("attendance"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return popular;
    }

    public static Map<String, Double> getTopRatedEvents() {
        Map<String, Double> topRated = new LinkedHashMap<>();
        String query = "SELECT e.event_name, AVG(f.rating) AS avgRating " +
                       "FROM feedback f JOIN events e ON f.event_id = e.event_id " +
                       "GROUP BY e.event_name ORDER BY avgRating DESC LIMIT 5";
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                topRated.put(rs.getString("event_name"), rs.getDouble("avgRating"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topRated;
    }

    public static Map<Integer, Integer> getRatingDistribution() {
        Map<Integer, Integer> distribution = new LinkedHashMap<>();
        String query = "SELECT rating, COUNT(*) AS cnt FROM feedback GROUP BY rating ORDER BY rating";
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                distribution.put(rs.getInt("rating"), rs.getInt("cnt"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return distribution;
    }
}
