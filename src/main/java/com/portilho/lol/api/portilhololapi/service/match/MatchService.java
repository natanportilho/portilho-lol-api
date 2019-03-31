package com.portilho.lol.api.portilhololapi.service.match;

import org.json.JSONException;

import java.util.List;

public interface MatchService
{
    String getMatchesForUser(String accountId);

    String getMatchById(String matchId);

    List<String> getMatchesIdsForAccount(String accountId) throws JSONException;
}
