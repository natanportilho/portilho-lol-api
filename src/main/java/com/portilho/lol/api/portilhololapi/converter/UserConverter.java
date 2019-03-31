package com.portilho.lol.api.portilhololapi.converter;

import com.portilho.lol.api.portilhololapi.exception.ConverterException;
import com.portilho.lol.api.portilhololapi.model.user.UserModel;
import org.json.JSONException;
import org.json.JSONObject;

public class UserConverter implements ModelConverter
{
    @Override
    public Object convert(Object obj)
    {
        JSONObject source = (JSONObject) obj;
        UserModel userModel = new UserModel();
        try
        {
            userModel.setAccountId(source.getString("accountId"));
            userModel.setId(source.getString("id"));
            userModel.setName(source.getString("name"));
            userModel.setProfileIconId(source.getLong("profileIconId"));
            userModel.setPuuid(source.getString("puuid"));
            userModel.setRevisionDate(source.getLong("revisionDate"));
            userModel.setSummonerLevel(source.getInt("summonerLevel"));
        } catch (JSONException e)
        {
            throw new ConverterException("Not able to convert User");
        }
        return userModel;
    }
}
