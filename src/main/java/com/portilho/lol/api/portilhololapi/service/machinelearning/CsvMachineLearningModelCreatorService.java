package com.portilho.lol.api.portilhololapi.service.machinelearning;

import com.portilho.lol.api.portilhololapi.constant.Constant;
import com.portilho.lol.api.portilhololapi.converter.MachineLearningModelLineConverter;
import com.portilho.lol.api.portilhololapi.database.InMemoryDataBase;
import com.portilho.lol.api.portilhololapi.exception.MachineLearningModelException;
import com.portilho.lol.api.portilhololapi.model.MachineLearningLineModel;
import com.portilho.lol.api.portilhololapi.model.match.MatchModel;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CsvMachineLearningModelCreatorService implements MachineLearningModelCreatorService
{
    @Resource
    private MachineLearningModelLineConverter machineLearningModelLineConverter;
    @Resource
    private InMemoryDataBase inMemoryDataBase;

    @Override
    public void createModel(ArrayList<MatchModel> matches)
    {
        createCsvModelFromMatches(matches);
    }

    private void createCsvModelFromMatches(ArrayList<MatchModel> matches)
    {
        String targetFileName = Constant.LOL_MODEL + Constant.CSV;
        ArrayList<String> lines = createLines(matches);
        writeCsvModel(targetFileName, lines);
    }

    private ArrayList<String> createLines(ArrayList<MatchModel> matches)
    {
        ArrayList<String> lines = new ArrayList<>();
        matches.forEach(match -> createNewLine(lines, match));
        return lines;
    }

    private void createNewLine(ArrayList<String> lines, MatchModel match)
    {
        MachineLearningLineModel lineInfo = (MachineLearningLineModel) machineLearningModelLineConverter.convert(match);

        String line = lineInfo.getMatchId() + "," +
                getChampionNameById(lineInfo.getTeamAPlayer1()) + "," + getChampionNameById(lineInfo.getTeamAPlayer2()) + "," +
                getChampionNameById(lineInfo.getTeamAPlayer3()) + "," + getChampionNameById(lineInfo.getTeamAPlayer4()) + "," +
                getChampionNameById(lineInfo.getTeamAPlayer5()) + "," +
                getChampionNameById(lineInfo.getTeamBPlayer1()) + "," + getChampionNameById(lineInfo.getTeamBPlayer2()) + "," +
                getChampionNameById(lineInfo.getTeamBPlayer3()) + "," + getChampionNameById(lineInfo.getTeamBPlayer4()) + "," +
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
        return inMemoryDataBase.getChampionById(championId).getName();
    }
}
