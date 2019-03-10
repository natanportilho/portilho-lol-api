package com.portilho.lol.api.portilhololapi.facade;

import com.portilho.lol.api.portilhololapi.converter.ModelConverter;
import com.portilho.lol.api.portilhololapi.exception.MatchException;
import com.portilho.lol.api.portilhololapi.model.user.UserModel;
import com.portilho.lol.api.portilhololapi.service.match.MatchService;
import com.portilho.lol.api.portilhololapi.service.user.UserService;
import org.json.JSONException;
import org.json.JSONObject;

import javax.annotation.Resource;
import java.io.IOException;

public class LolMatchesFacade implements MatchesFacade
{
    @Resource
    UserService userService;
    @Resource
    MatchService matchService;
    @Resource
    ModelConverter userConverter;

    @Override
    public String getPlayedMatchesForUser(String username)
    {
        try
        {
            UserModel userModel = (UserModel) userConverter.convert(new JSONObject(userService.getUserByName(username)));
            return matchService.getMatchesForUser(userModel.getAccountId());
        } catch (JSONException | IOException e)
        {
            throw new MatchException("Matched not found");
        }
    }
}
