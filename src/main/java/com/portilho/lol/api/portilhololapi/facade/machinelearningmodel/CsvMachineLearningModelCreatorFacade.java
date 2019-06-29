package com.portilho.lol.api.portilhololapi.facade.machinelearningmodel;

import com.google.common.util.concurrent.RateLimiter;
import com.portilho.lol.api.portilhololapi.constant.Constant;
import com.portilho.lol.api.portilhololapi.converter.MatchConverter;
import com.portilho.lol.api.portilhololapi.database.InMemoryDataBase;
import com.portilho.lol.api.portilhololapi.exception.GetRequestException;
import com.portilho.lol.api.portilhololapi.exception.MatchException;
import com.portilho.lol.api.portilhololapi.model.ChampionModel;
import com.portilho.lol.api.portilhololapi.model.ParticipantModel;
import com.portilho.lol.api.portilhololapi.model.match.MatchModel;
import com.portilho.lol.api.portilhololapi.model.match.TeamModel;
import com.portilho.lol.api.portilhololapi.service.champion.ChampionService;
import com.portilho.lol.api.portilhololapi.service.machinelearning.CsvMachineLearningModelCreatorService;
import com.portilho.lol.api.portilhololapi.service.match.MatchService;
import org.json.JSONException;
import org.json.JSONObject;

import javax.annotation.Resource;
import java.util.*;

public class CsvMachineLearningModelCreatorFacade implements MachineLearningModelCreatorFacade
{
    @Resource
    private MatchService matchService;
    @Resource
    private MatchConverter matchConverter;
    @Resource
    private InMemoryDataBase inMemoryDataBase;
    @Resource
    private CsvMachineLearningModelCreatorService csvMachineLearningModelCreatorService;
    @Resource
    private ChampionService championService;

    @Override
    public void createModelFromBaseAccount(String accountId)
    {
        try
        {
            loadChampions();
            addUserMatchesIntoDatabase(accountId);
//            addMatchesForOtherUsers();
            csvMachineLearningModelCreatorService.createModel(inMemoryDataBase.getMatches());

        } catch (JSONException e)
        {
            throw new GetRequestException("Too many requests.");
        }
    }

    private void addUserMatchesIntoDatabase(String accountId) throws JSONException
    {
        RateLimiter throttle = RateLimiter.create(0.8);
        ArrayList<String> matchesIds = (ArrayList<String>) matchService.getMatchesIdsForAccount(accountId);
        for (String matchId : matchesIds)
        {
            throttle.acquire();
            MatchModel match = (MatchModel) matchConverter.convert(new JSONObject(matchService.getMatchById(matchId)));
            if (match.getGameMode().equals(Constant.CLASSIC))
            {
                inMemoryDataBase.addMatch(match);
            }

        }
    }

    private void addMatchesForOtherUsers()
    {
        Collection<MatchModel> matches = inMemoryDataBase.getMatches();
        for (MatchModel match : matches)
        {
            if (inMemoryDataBase.getMatchesSize() <= 200)
            {
                List<TeamModel> teams = match.getTeams();
                teams.forEach(team -> addUserMatchesForParticipants(team.getParticipants()));
            }
        }
    }

    private void addUserMatchesForParticipants(List<ParticipantModel> participants)
    {
        participants.forEach(participant ->
        {
            try
            {
                addUserMatchesIntoDatabase(participant.getAccountId());
            } catch (JSONException e)
            {
                throw new MatchException("Not able to add user matches for participants.");
            }
        });
    }

    private void loadChampions()
    {
        ArrayList<ChampionModel> champions = championService.getAllChampions();
        inMemoryDataBase.setChampions(champions);
    }
}
