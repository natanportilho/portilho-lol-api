package com.portilho.lol.api.portilhololapi.service.user;

import com.portilho.lol.api.portilhololapi.constant.RequestConstants;
import com.portilho.lol.api.portilhololapi.service.connection.ConnectionService;

import javax.annotation.Resource;
import java.io.IOException;

public class LolUserService implements UserService
{
    @Resource
    ConnectionService connectionService;

    @Override
    public String getUserByName(String username) throws IOException
    {
        String url = RequestConstants.SUMMONER_INFO_URL + username;
        return connectionService.sendGetRequest(url);
    }
}
