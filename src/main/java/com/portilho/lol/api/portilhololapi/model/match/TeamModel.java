package com.portilho.lol.api.portilhololapi.model.match;

import com.portilho.lol.api.portilhololapi.model.ParticipantModel;

import java.util.List;

public class TeamModel
{
    private String teamId;
    private List<ParticipantModel> participants;
    private boolean isWinner;

    public String getTeamId()
    {
        return teamId;
    }

    public void setTeamId(String teamId)
    {
        this.teamId = teamId;
    }

    public List<ParticipantModel> getParticipants()
    {
        return participants;
    }

    public void setParticipants(List<ParticipantModel> participants)
    {
        this.participants = participants;
    }

    public boolean isWinner()
    {
        return isWinner;
    }

    public void setWinner(boolean winner)
    {
        isWinner = winner;
    }
}
