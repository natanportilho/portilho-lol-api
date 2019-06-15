package com.portilho.lol.api.portilhololapi.service.machinelearning;

import com.portilho.lol.api.portilhololapi.model.match.MatchModel;

import java.util.Map;

public interface MachineLearningModelCreatorService
{
    void createModel(Map<String, MatchModel> matches);
}
