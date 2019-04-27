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
import java.util.List;
import java.util.Optional;

public class LolChampionService implements ChampionService
{
    @Resource
    private ConnectionService connectionService;

    @Resource
    private ModelConverter championConverter;

    @Override
    public List getAllChampions()
    {
        try
        {
            JSONObject championsInfo = getChampionsInfo();
            Iterator keys = championsInfo.keys();
            return createChampions(championsInfo, keys);

        } catch (JSONException e)
        {
            throw new ChampionException("Not able create champions.");
        }
    }

    @Override
    public String getChampionNameById(String championId)
    {

        List<ChampionModel> champions = getAllChampions();
        Optional<ChampionModel> champion = champions.stream().
                filter(championModel -> championModel.getId().equals(championId)).findFirst();
        if (champion.isPresent())
            return champion.get().getName();
        throw new ChampionException("Not able get champion by id " + championId);
    }

    private JSONObject getChampionsInfo() throws JSONException
    {
        return new JSONObject(
                connectionService.sendGetRequest(RequestConstants.CHAMPIONS_URL)).getJSONObject("data");
    }

    private ArrayList<ChampionModel> createChampions(JSONObject championsInfo, Iterator<String> keys) throws JSONException
    {
        ArrayList<ChampionModel> champions = new ArrayList<>();
        while (keys.hasNext())
            createChampionFromForKey(championsInfo, keys, champions);
        return champions;
    }

    private void createChampionFromForKey(JSONObject championsInfo, Iterator<String> keys, ArrayList<ChampionModel> champions) throws JSONException
    {
        String key = keys.next();
        if (championsInfo.get(key) instanceof JSONObject)
        {
            champions.add(createChampion((JSONObject) championsInfo.get(key)));
        }
    }

    private ChampionModel createChampion(JSONObject championAsJSON)
    {
        return (ChampionModel) championConverter.convert(championAsJSON);
    }
}
