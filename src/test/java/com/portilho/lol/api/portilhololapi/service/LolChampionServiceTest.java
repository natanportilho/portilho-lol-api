package com.portilho.lol.api.portilhololapi.service;

import com.portilho.lol.api.portilhololapi.service.champion.LolChampionService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LolChampionServiceTest
{
    @Resource
    private LolChampionService championService;

    @Test
    public void getAllChampionsTest()
    {
        Assert.assertEquals(143, championService.getAllChampions().size());
    }
}
