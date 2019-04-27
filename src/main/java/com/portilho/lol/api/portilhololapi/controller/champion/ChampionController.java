package com.portilho.lol.api.portilhololapi.controller.champion;

import com.portilho.lol.api.portilhololapi.model.ChampionModel;
import com.portilho.lol.api.portilhololapi.service.champion.ChampionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class ChampionController
{
    @Resource
    private ChampionService championService;

    @RequestMapping("/champions")
    public List<ChampionModel> getAllChampions(){
        return championService.getAllChampions();
    }

    @RequestMapping("/championName")
    public String getChampionNameByIdc(@RequestParam String championId){
        return championService.getChampionNameById(championId);
    }
}
