package com.portilho.lol.api.portilhololapi.model;

public class ParticipantModel
{
    private String teamId;
    private String lane;
    private String role;
    private String champion;

    public String getTeamId()
    {
        return teamId;
    }

    public void setTeamId(String teamId)
    {
        this.teamId = teamId;
    }

    public String getLane()
    {
        return lane;
    }

    public void setLane(String lane)
    {
        this.lane = lane;
    }

    public String getChampion()
    {
        return champion;
    }

    public void setChampion(String champion)
    {
        this.champion = champion;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }
}
