package com.portilho.lol.api.portilhololapi.controller.machinelearningmodel;

import com.portilho.lol.api.portilhololapi.facade.machinelearningmodel.MachineLearningModelCreatorFacade;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MachineLearningModelController
{
    @Resource
    private MachineLearningModelCreatorFacade machineLearningModelCreatorFacade;

    @RequestMapping("/createModel")
    public String getMatchesForUser(@RequestParam String accountId)
    {
        machineLearningModelCreatorFacade.createModelFromBaseAccount(accountId);
        return "ok";
    }
}
