package com.portilho.lol.api.portilhololapi.database;

import com.portilho.lol.api.portilhololapi.model.ChampionModel;
import com.portilho.lol.api.portilhololapi.model.match.MatchModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class InMemoryDataBase
{
    private ArrayList<MatchModel> matches = new ArrayList<>();
    private ArrayList<ChampionModel> champions = new ArrayList<>();

    public void addMatch(MatchModel match)
    {
        if (!matches.contains(match))
            matches.add(match);
    }

    public void setChampions(ArrayList<ChampionModel> champs)
    {
        champions = champs;
    }

    public int getMatchesSize()
    {
        return matches.size();
    }

    public ArrayList<MatchModel> getMatches()
    {
        return matches;
    }

    public ArrayList<MatchModel> getDatabase()
    {
        return matches;
    }

    public ChampionModel getChampionById(String championId)
    {
        return champions.stream().filter(champion -> champion.getId().equals(championId)).findFirst().get();
    }
}
