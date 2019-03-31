package com.portilho.lol.api.portilhololapi.facade;

import com.portilho.lol.api.portilhololapi.service.match.MatchService;

import javax.annotation.Resource;

public class LolMachineLearningModelCreator implements MachineLearningModelCreator
{
    @Resource
    private MatchService matchService;

    @Override
    public void createModelFromUserZero(String accountId)
    {
//        matchService.getMatchesForUser()

    }
}
