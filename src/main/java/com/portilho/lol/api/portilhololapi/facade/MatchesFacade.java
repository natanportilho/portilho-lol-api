package com.portilho.lol.api.portilhololapi.facade;

import com.portilho.lol.api.portilhololapi.model.match.MatchModel;

import java.util.ArrayList;

public interface MatchesFacade
{
    String getPlayedMatchesForUser(String username);

    ArrayList<MatchModel> getUserMatchHistory(String username);

    MatchModel getMatchById(String matchId);

    String getAllMatchUpsForUser(String username);
}
