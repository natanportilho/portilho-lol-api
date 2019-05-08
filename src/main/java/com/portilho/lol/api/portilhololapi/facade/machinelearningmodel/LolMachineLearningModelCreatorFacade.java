package com.portilho.lol.api.portilhololapi.facade.machinelearningmodel;

import com.google.common.util.concurrent.RateLimiter;
import com.portilho.lol.api.portilhololapi.converter.MachineLearningModelLineConverter;
import com.portilho.lol.api.portilhololapi.converter.MatchConverter;
import com.portilho.lol.api.portilhololapi.database.InMemoryDataBase;
import com.portilho.lol.api.portilhololapi.exception.GetRequestException;
import com.portilho.lol.api.portilhololapi.exception.MachineLearningModelException;
import com.portilho.lol.api.portilhololapi.model.MachineLearningLineModel;
import com.portilho.lol.api.portilhololapi.model.ParticipantModel;
import com.portilho.lol.api.portilhololapi.model.match.MatchModel;
import com.portilho.lol.api.portilhololapi.model.match.TeamModel;
import com.portilho.lol.api.portilhololapi.service.champion.ChampionService;
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
import java.util.Iterator;
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
    @Resource
    private MachineLearningModelLineConverter machineLearningModelLineConverter;
    @Resource
    private ChampionService championService;

    @Override
    public void createModelFromBaseAccount(String accountId)
    {
        try
        {
            addUserMatchesIntoDatabase(accountId);
            addMatchesForOtherUsers();
            createCsvModelFromDatabase(InMemoryDataBase.getDatabase());
        } catch (JSONException e)
        {
            throw new GetRequestException("Too many requests.");
        }
    }

    private void addMatchesForOtherUsers() throws JSONException
    {
        Map<String, MatchModel> matches = inMemoryDataBase.getMatches();
        Iterator it = matches.entrySet().iterator();
        while (it.hasNext() && inMemoryDataBase.getDatabaseSize() <= 200) {
            Map.Entry pair = (Map.Entry)it.next();
            MatchModel match = (MatchModel) pair.getValue();
            it.remove();

            List<TeamModel> teams = match.getTeams();

            for (TeamModel team : teams){
                List<ParticipantModel> participants = team.getParticipants();
                for (ParticipantModel participant : participants){
                    addUserMatchesIntoDatabase(participant.getAccountId());
                }
            }
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
    }

    private void createCsvModelFromDatabase(Map<String, MatchModel> database)
    {
        String targetFileName = "lol-model.csv";
        ArrayList<String> lines = createLines(database);
        writeCsvModel(targetFileName, lines);
    }

    private ArrayList<String> createLines(Map<String, MatchModel> database)
    {
        ArrayList<String> lines = new ArrayList<>();
        for (Map.Entry<String, MatchModel> entry : database.entrySet())
            createNewLine(lines, entry);
        return lines;
    }

    private void createNewLine(ArrayList<String> lines, Map.Entry<String, MatchModel> entry)
    {
        MatchModel match = entry.getValue();
        MachineLearningLineModel lineInfo = (MachineLearningLineModel) machineLearningModelLineConverter.convert(match);

        String line = lineInfo.getMatchId() + "," + getChampionNameById(lineInfo.getTeamAPlayer1()) + "," +
                getChampionNameById(lineInfo.getTeamAPlayer2()) + "," +
                getChampionNameById(lineInfo.getTeamAPlayer3()) + "," +
                getChampionNameById(lineInfo.getTeamAPlayer4()) + "," +
                getChampionNameById(lineInfo.getTeamAPlayer5()) + "," +
                getChampionNameById(lineInfo.getTeamBPlayer1()) + "," +
                getChampionNameById(lineInfo.getTeamBPlayer2()) + "," +
                getChampionNameById(lineInfo.getTeamBPlayer3()) + "," +
                getChampionNameById(lineInfo.getTeamBPlayer4()) + "," +
                getChampionNameById(lineInfo.getTeamBPlayer5()) + "," + lineInfo.getWinnerTeam();
        lines.add(line);
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

    private String getChampionNameById(String championId)
    {
        return championService.getChampionNameById(championId);
    }
}
