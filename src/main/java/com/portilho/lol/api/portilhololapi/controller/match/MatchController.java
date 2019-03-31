package com.portilho.lol.api.portilhololapi.controller.match;

import com.portilho.lol.api.portilhololapi.database.DatabaseConnectorService;
import com.portilho.lol.api.portilhololapi.facade.matches.MatchesFacade;
import com.portilho.lol.api.portilhololapi.model.match.MatchModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;

@RestController
public class MatchController
{
    @Resource
    private MatchesFacade matchesFacade;

    @Resource
    private DatabaseConnectorService databaseConnectorService;

    @RequestMapping("/matches")
    public String getMatchesForUser(@RequestParam String username)
    {
        return matchesFacade.getPlayedMatchesForUser(username);
    }

    @RequestMapping("/matchHistory")
    public ArrayList<MatchModel> getUserMatchHistory(@RequestParam String username)
    {
        return matchesFacade.getUserMatchHistory(username);
    }

    @RequestMapping("/match")
    public MatchModel getMatchById(@RequestParam String matchId)
    {
        return matchesFacade.getMatchById(matchId);
    }

    @RequestMapping("/matchDB")
    public void getDB()
    {
        databaseConnectorService.connect();
    }
}
