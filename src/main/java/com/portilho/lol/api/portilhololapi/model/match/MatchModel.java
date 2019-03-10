package com.portilho.lol.api.portilhololapi.model.match;

public class MatchModel
{
    private String matchId;
    private String champion;

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
}
