package com.portilho.lol.api.portilhololapi.facade.machinelearningmodel;

import com.google.common.util.concurrent.RateLimiter;
import com.portilho.lol.api.portilhololapi.converter.MatchConverter;
import com.portilho.lol.api.portilhololapi.database.InMemoryDataBase;
import com.portilho.lol.api.portilhololapi.exception.GetRequestException;
import com.portilho.lol.api.portilhololapi.exception.MachineLearningModelException;
import com.portilho.lol.api.portilhololapi.model.match.MatchModel;
import com.portilho.lol.api.portilhololapi.service.match.MatchService;
import org.json.JSONException;
import org.json.JSONObject;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LolMachineLearningModelCreatorFacade implements MachineLearningModelCreatorFacade
{
    @Resource
    private MatchService matchService;
    @Resource
    private MatchConverter matchConverter;
    @Resource
    private InMemoryDataBase inMemoryDataBase;

    @Override
    public void createModelFromUserZero(String accountId)
    {
        try
        {
            addUserMatchesIntoDatabase(accountId);

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
            inMemoryDataBase.addMatch(match);
        }
        createCsvModel(inMemoryDataBase.getDatabase());
    }

    private void createCsvModel(Map<String, MatchModel> database)
    {
        String targetFileName = "lol-model.csv";
        ArrayList<String> lines = createLines(database);
        writeCsvModel(targetFileName, lines);
    }

    private ArrayList<String> createLines(Map<String, MatchModel> database)
    {
        ArrayList<String> lines = new ArrayList<>();

        for (Map.Entry<String, MatchModel> entry : database.entrySet())
        {
            MatchModel match = entry.getValue();
            String line = match.getMatchId();
            lines.add(line);
        }
        return lines;
    }

    private void writeCsvModel(String targetFileName, List<String> lines)
    {
        try
        {
            Path path = Paths.get(targetFileName);
            Files.write(path, lines, Charset.forName("UTF-8"));
        } catch (IOException e)
        {
            throw new MachineLearningModelException("Not able to create machine learning model.");
        }
    }
}
