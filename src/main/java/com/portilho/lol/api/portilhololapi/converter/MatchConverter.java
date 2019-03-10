package com.portilho.lol.api.portilhololapi.converter;

import com.portilho.lol.api.portilhololapi.exception.MatchException;
import com.portilho.lol.api.portilhololapi.model.match.MatchModel;
import org.json.JSONException;
import org.json.JSONObject;

public class MatchConverter implements ModelConverter
{
    @Override
    public Object convert(JSONObject source)
    {
        MatchModel match = new MatchModel();
        try
        {
            match.setMatchId(source.getString("gameId"));
            match.setChampion(source.getString("champion"));
        } catch (JSONException e)
        {
            throw new MatchException("Not able to convert into match.");
        }
        return match;
    }
}
