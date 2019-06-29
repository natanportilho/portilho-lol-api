package com.portilho.lol.api.portilhololapi.service.champion;

import com.portilho.lol.api.portilhololapi.model.ChampionModel;

import java.util.ArrayList;

public interface ChampionService
{
    ArrayList<ChampionModel> getAllChampions();

    String getChampionNameById(String championId);
}
