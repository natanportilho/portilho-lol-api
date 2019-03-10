package com.portilho.lol.api.portilhololapi.converter;

import org.json.JSONObject;

public interface ModelConverter
{
    Object convert(JSONObject source);
}
