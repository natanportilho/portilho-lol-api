package com.portilho.lol.api.portilhololapi.service.match;

import com.portilho.lol.api.portilhololapi.constant.Constant;
import com.portilho.lol.api.portilhololapi.service.connection.ConnectionService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class LolMatchService implements MatchService
{
    @Resource
    private ConnectionService connectionService;

    @Override
    public String getMatchesForUser(String accountId)
    {
        return connectionService.sendGetRequest(Constant.Request.MATCHES_FOR_USER_URL + accountId);
    }

    @Override
    public String getMatchById(String matchId)
    {
        return connectionService.sendGetRequest(Constant.Request.MATCH_BY_ID_URL + matchId);
    }

    @Override
    public List<String> getMatchesIdsForAccount(String accountId) throws JSONException
    {
        ArrayList<String> matchesIds = new ArrayList<>();
        JSONArray matches = new JSONObject(connectionService.sendGetRequest(Constant.Request.MATCHES_FOR_USER_URL + accountId)).getJSONArray("matches");

        for (int i = 0; i < matches.length(); i++)
        {
            matchesIds.add(matches.getJSONObject(i).getString("gameId"));
        }
        return matchesIds;
    }
}
