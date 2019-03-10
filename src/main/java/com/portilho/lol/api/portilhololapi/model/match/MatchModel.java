package com.portilho.lol.api.portilhololapi.model.match;

import java.util.List;

public class MatchModel
{
    private String matchId;
    private String champion;
    private List<TeamModel> teams;

    public String getMatchId()
    {
        return matchId;
    }

    public void setMatchId(String matchId)
    {
        this.matchId = matchId;
    }

    public String getChampion()
    {
        return champion;
    }

    public void setChampion(String champion)
    {
        this.champion = champion;
    }

    public List<TeamModel> getTeams()
    {
        return teams;
    }

    public void setTeams(List<TeamModel> teams)
    {
        this.teams = teams;
    }
}
