package com.portilho.lol.api.portilhololapi.converter;

import com.portilho.lol.api.portilhololapi.exception.ChampionException;
import com.portilho.lol.api.portilhololapi.model.ChampionModel;
import org.json.JSONException;
import org.json.JSONObject;

public class ChampionConverter implements ModelConverter
{
    @Override
    public Object convert(Object obj)
    {
        JSONObject source = (JSONObject) obj;
        ChampionModel champion = new ChampionModel();
        try
        {
            champion.setId(source.getString("key"));
            champion.setName(source.getString("id"));
        } catch (JSONException e)
        {
            throw new ChampionException("not able to convert into champion.");
        }
        return champion;
    }
}
