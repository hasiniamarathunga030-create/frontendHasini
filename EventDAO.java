package com.mycompany.clubsphere.dao;

import com.mycompany.clubsphere.config.DatabaseConfig;
import com.mycompany.clubsphere.model.Event;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDAO {

    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT event_id, club_id, event_name, date, time, location, description FROM events";
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                events.add(new Event(
                    rs.getInt("event_id"),
                    rs.getInt("club_id"),
                    rs.getString("event_name"),
                    rs.getDate("date"),
                    rs.getTime("time"),
                    rs.getString("location"),
                    rs.getString("description")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    public void addEvent(int clubId, String name, Date date, Time time, String location, String description) {
        String sql = "INSERT INTO events (club_id, event_name, date, time, location, description) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, clubId);
            stmt.setString(2, name);
            stmt.setDate(3, date);
            stmt.setTime(4, time);
            stmt.setString(5, location);
            stmt.setString(6, description);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEvent(int id, int clubId, String name, Date date, Time time, String location, String description) {
        String sql = "UPDATE events SET club_id=?, event_name=?, date=?, time=?, location=?, description=? WHERE event_id=?";
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, clubId);
            stmt.setString(2, name);
            stmt.setDate(3, date);
            stmt.setTime(4, time);
            stmt.setString(5, location);
            stmt.setString(6, description);
            stmt.setInt(7, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEvent(int id) {
        String sql = "DELETE FROM events WHERE event_id=?";
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
