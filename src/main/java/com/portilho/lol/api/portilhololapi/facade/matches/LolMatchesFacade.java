package com.portilho.lol.api.portilhololapi.facade.matches;

import com.portilho.lol.api.portilhololapi.converter.ModelConverter;
import com.portilho.lol.api.portilhololapi.exception.MatchException;
import com.portilho.lol.api.portilhololapi.model.match.MatchModel;
import com.portilho.lol.api.portilhololapi.model.user.UserModel;
import com.portilho.lol.api.portilhololapi.service.match.MatchService;
import com.portilho.lol.api.portilhololapi.service.user.UserService;
import org.json.JSONException;
import org.json.JSONObject;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

public class LolMatchesFacade implements MatchesFacade
{
    @Resource
    private UserService userService;
    @Resource
    private MatchService matchService;
    @Resource
    private ModelConverter userConverter;
    @Resource
    private ModelConverter matchConverter;

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

    @Override
    public MatchModel getMatchById(String matchId)
    {
        try
        {
            return (MatchModel) matchConverter.convert(new JSONObject(matchService.getMatchById(matchId)));
        } catch (JSONException e)
        {
            throw new MatchException("Not able to get match.");
        }
    }

    @Override
    public List<String> getMatchesIdsForAccount(String accountId)
    {
        try
        {
            return matchService.getMatchesIdsForAccount(accountId);
        } catch (JSONException e)
        {
            throw new MatchException("Not able to get matches ids for account " + accountId);
        }
    }
}
