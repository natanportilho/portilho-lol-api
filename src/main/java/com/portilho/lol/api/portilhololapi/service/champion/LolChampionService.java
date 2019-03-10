package com.portilho.lol.api.portilhololapi.service.champion;

import com.portilho.lol.api.portilhololapi.constant.RequestConstants;
import com.portilho.lol.api.portilhololapi.converter.ModelConverter;
import com.portilho.lol.api.portilhololapi.exception.ChampionException;
import com.portilho.lol.api.portilhololapi.model.ChampionModel;
import com.portilho.lol.api.portilhololapi.service.connection.ConnectionService;
import org.json.JSONException;
import org.json.JSONObject;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;

public class LolChampionService implements ChampionService
{
    @Resource
    private ConnectionService connectionService;

    @Resource
    private ModelConverter championConverter;

    @Override
    public ArrayList<ChampionModel> getAllChampions()
    {
        try
        {
            JSONObject championsInfo = getChampionsInfo();
            Iterator<String> keys = championsInfo.keys();
            return createChampions(championsInfo, keys);

        } catch (JSONException e)
        {
            throw new ChampionException("Not ableto create champions.");
        }
    }

    private JSONObject getChampionsInfo() throws JSONException
    {
        return new JSONObject(
                connectionService.sendGetRequest(RequestConstants.CHAMPIONS_URL)).getJSONObject("data");
    }

    private ArrayList<ChampionModel> createChampions(JSONObject championsInfo, Iterator<String> keys) throws JSONException
    {
        ArrayList<ChampionModel> champions = new ArrayList<>();
        while(keys.hasNext()) {
            String key = keys.next();
            if (championsInfo.get(key) instanceof JSONObject) {
                champions.add(createChampion((JSONObject) championsInfo.get(key)));
            }
        }
        return champions;
    }

    private ChampionModel createChampion(JSONObject championAsJSON)
    {
        ChampionModel champion = (ChampionModel) championConverter.convert(championAsJSON);
        return champion;
    }
}
