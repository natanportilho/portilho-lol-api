package com.portilho.lol.api.portilhololapi.facade;

import com.portilho.lol.api.portilhololapi.converter.ModelConverter;
import com.portilho.lol.api.portilhololapi.exception.MatchException;
import com.portilho.lol.api.portilhololapi.model.match.MatchModel;
import com.portilho.lol.api.portilhololapi.model.user.UserModel;
import com.portilho.lol.api.portilhololapi.service.match.MatchService;
import com.portilho.lol.api.portilhololapi.service.user.UserService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;

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
    public ArrayList<MatchModel> getUserMatchHistory(@RequestParam String username)
    {
        try
        {
            JSONArray matches = new JSONObject(getPlayedMatchesForUser(username)).getJSONArray("matches");
            return createMatchesHistory(matches);
        } catch (JSONException e)
        {
            throw new MatchException("Not able to get matches history.");
        }
    }

    private ArrayList<MatchModel> createMatchesHistory(JSONArray matches) throws JSONException
    {
        ArrayList<MatchModel> matchesHistory = new ArrayList<>();
        for (int i = 0; i < matches.length(); i++)
        {
            matchesHistory.add((MatchModel) matchConverter.convert(matches.getJSONObject(i)));
        }
        return matchesHistory;
    }

    @Override
    public String getMatchById(String matchId)
    {
        return matchService.getMatchById(matchId);
    }

    @Override
    public String getAllMatchUpsForUser(String username)
    {
        UserModel player = userService.getUserModelByName(username);
        ArrayList<MatchModel> matches = getUserMatchHistory(username);
        String champions = "";

        for (MatchModel match : matches){
            champions += match.getChampion() + " ";
        }
        return champions;
    }
}
