package com.portilho.lol.api.portilhololapi.database;

import com.portilho.lol.api.portilhololapi.model.match.MatchModel;

import java.util.HashMap;
import java.util.Map;


public class InMemoryDataBase
{
    private static Map<String, MatchModel> matches = new HashMap<>();

    public void addMatch(MatchModel match)
    {
        matches.putIfAbsent(match.getMatchId(), match);
    }

    public int getDatabaseSize(){
        return matches.size();
    }

    public static Map<String, MatchModel> getDatabase()
    {
        return matches;
    }
}
