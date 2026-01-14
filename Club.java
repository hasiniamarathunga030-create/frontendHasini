package com.mycompany.clubsphere.model;

public class Club {
    private int clubId;
    private String clubName;
    private String description;

    public Club(int clubId, String clubName, String description) {
        this.clubId = clubId;
        this.clubName = clubName;
        this.description = description;
    }

    public int getClubId() {
        return clubId;
    }

    public String getClubName() {
        return clubName;
    }

    public String getDescription() {
        return description;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
