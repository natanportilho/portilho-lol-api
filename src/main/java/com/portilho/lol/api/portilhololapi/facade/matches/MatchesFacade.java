package com.portilho.lol.api.portilhololapi.facade.matches;

import com.portilho.lol.api.portilhololapi.model.match.MatchModel;

public interface MatchesFacade
{
    String getPlayedMatchesForUser(String username);

    MatchModel getMatchById(String matchId);
}
