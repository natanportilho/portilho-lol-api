package com.portilho.lol.api.portilhololapi.facade;

import com.portilho.lol.api.portilhololapi.facade.matches.LolMatchesFacade;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LolMatchesFacadeTest
{
    @Resource
    private LolMatchesFacade matchesFacade;

}
