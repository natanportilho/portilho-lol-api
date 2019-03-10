package com.portilho.lol.api.portilhololapi.service.match;

import com.portilho.lol.api.portilhololapi.constant.RequestConstants;
import com.portilho.lol.api.portilhololapi.service.connection.ConnectionService;

import javax.annotation.Resource;

public class LolMatchService implements MatchService
{
    @Resource
    ConnectionService connectionService;

    @Override
    public String getMatchesForUser(String accountId)
    {
        return connectionService.sendGetRequest(RequestConstants.MATCHES_FOR_USER_URL + accountId);
    }

    @Override
    public String getMatchById(String matchId)
    {
        return connectionService.sendGetRequest(RequestConstants.MATCH_BY_ID_URL + matchId);
    }
}
