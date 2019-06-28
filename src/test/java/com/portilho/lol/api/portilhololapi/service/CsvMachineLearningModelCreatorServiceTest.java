package com.portilho.lol.api.portilhololapi.service;

import com.portilho.lol.api.portilhololapi.constant.Constant;
import com.portilho.lol.api.portilhololapi.model.match.MatchModel;
import com.portilho.lol.api.portilhololapi.model.match.TeamModel;
import com.portilho.lol.api.portilhololapi.service.machinelearning.CsvMachineLearningModelCreatorService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

@SpringBootTest
public class CsvMachineLearningModelCreatorServiceTest
{
    @Mock
    CsvMachineLearningModelCreatorService csvMachineLearningModelCreatorService;

    @Mock
    private Map<String, MatchModel> matches;
    @Mock
    private MatchModel match1;
    @Mock
    private MatchModel match2;
    @Mock
    private List<TeamModel> teamsMatch1;
    @Mock
    private List<TeamModel> teamsMatch2;

    @Before
    public void setup()
    {
        when(match1.getGameMode()).thenReturn(Constant.CLASSIC);
        when(match1.getMatchId()).thenReturn("1");
        when(match1.getTeams()).thenReturn(teamsMatch1);

        when(match2.getGameMode()).thenReturn(Constant.CLASSIC);
        when(match2.getMatchId()).thenReturn("2");
        when(match2.getTeams()).thenReturn(teamsMatch2);
    }

    @Test
    public void createModel()
    {
        csvMachineLearningModelCreatorService.createModel(matches);
    }
}
