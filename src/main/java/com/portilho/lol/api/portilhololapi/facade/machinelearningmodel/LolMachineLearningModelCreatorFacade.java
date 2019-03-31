package com.portilho.lol.api.portilhololapi.facade.machinelearningmodel;

import com.portilho.lol.api.portilhololapi.service.match.MatchService;

import javax.annotation.Resource;

public class LolMachineLearningModelCreatorFacade implements MachineLearningModelCreatorFacade
{
    @Resource
    private MatchService matchService;

    @Override
    public void createModelFromUserZero(String accountId)
    {
//        matchService.getMatchesIdsForAccount(accountId);
    }
}
