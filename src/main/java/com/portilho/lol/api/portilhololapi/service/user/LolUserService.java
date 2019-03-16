package com.portilho.lol.api.portilhololapi.service.user;

import com.portilho.lol.api.portilhololapi.constant.RequestConstants;
import com.portilho.lol.api.portilhololapi.converter.ModelConverter;
import com.portilho.lol.api.portilhololapi.exception.UserException;
import com.portilho.lol.api.portilhololapi.model.user.UserModel;
import com.portilho.lol.api.portilhololapi.service.connection.ConnectionService;
import org.json.JSONException;
import org.json.JSONObject;

import javax.annotation.Resource;
import java.io.IOException;

public class LolUserService implements UserService
{
    @Resource
    private ConnectionService connectionService;

    @Resource
    private ModelConverter userConverter;

    @Override
    public String getUserByName(String username) throws IOException
    {
        String url = RequestConstants.SUMMONER_INFO_URL + username;
        return connectionService.sendGetRequest(url);
    }

    @Override
    public UserModel getUserModelByName(String username)
    {
        try
        {
            String url = RequestConstants.SUMMONER_INFO_URL + username;
            return (UserModel) userConverter.convert(new JSONObject(connectionService.sendGetRequest(url)));
        } catch (JSONException e)
        {
            throw new UserException("Not able to convert user into model.");
        }
    }
}
