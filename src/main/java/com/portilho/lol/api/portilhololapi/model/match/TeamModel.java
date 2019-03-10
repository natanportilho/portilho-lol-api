package com.portilho.lol.api.portilhololapi.model.match;

import com.portilho.lol.api.portilhololapi.model.ChampionModel;

import java.util.List;

public class TeamModel
{
    private List<ChampionModel> champions;
    private boolean isWinner;

    public List<ChampionModel> getChampions()
    {
        return champions;
    }

    public void setChampions(List<ChampionModel> champions)
    {
        this.champions = champions;
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
