package com.portilho.lol.api.portilhololapi.controller.match;

import com.portilho.lol.api.portilhololapi.facade.MatchesFacade;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MatchController
{
    @Resource
    MatchesFacade matchesFacade;

    @RequestMapping("/matches")
    public String getMatchesForUser(@RequestParam String username)
    {
        return matchesFacade.getPlayedMatchesForUser(username);
    }
}
