package com.portilho.lol.api.portilhololapi.model;

public class RoleModel
{
    private String rollType;
    private ChampionModel champion;

    public String getRollType()
    {
        return rollType;
    }

    public void setRollType(String rollType)
    {
        this.rollType = rollType;
    }

    public ChampionModel getChampion()
    {
        return champion;
    }

    public void setChampion(ChampionModel champion)
    {
        this.champion = champion;
    }
}
