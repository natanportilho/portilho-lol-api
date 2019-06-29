package com.portilho.lol.api.portilhololapi.service;

import com.portilho.lol.api.portilhololapi.constant.Constant;
import com.portilho.lol.api.portilhololapi.model.match.MatchModel;
import com.portilho.lol.api.portilhololapi.model.match.TeamModel;
import com.portilho.lol.api.portilhololapi.service.machinelearning.CsvMachineLearningModelCreatorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CsvMachineLearningModelCreatorServiceTest
{
    private CsvMachineLearningModelCreatorService csvMachineLearningModelCreatorService = new CsvMachineLearningModelCreatorService();
    @Mock
    private ArrayList<MatchModel> matches;
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


        matches.add(match1);
        matches.add(match2);
    }

    @Test
    public void createModel() throws FileNotFoundException
    {
        String targetFileName = Constant.LOL_MODEL + Constant.CSV;
        File file = new File(targetFileName);
        csvMachineLearningModelCreatorService.createModel(matches);
        assertTrue(file.exists());

        Scanner scanner = new Scanner(file);
        int count = getNumberOfLines(scanner);

        assertEquals(2, count);
    }

    private int getNumberOfLines(Scanner scanner)
    {
        int count = 0;
        while (scanner.hasNextLine())
        {
            count++;
            scanner.nextLine();
        }
        return count;
    }
}
