package com.portilho.lol.api.portilhololapi.model.match;

import java.util.List;

public class MatchModel
{
    private String matchId;
    private String gameMode;
    private List<TeamModel> teams;

    public String getMatchId()
    {
        return matchId;
    }

    public void setMatchId(String matchId)
    {
        this.matchId = matchId;
    }

    public String getGameMode()
    {
        return gameMode;
    }

    public void setGameMode(String gameMode)
    {
        this.gameMode = gameMode;
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
