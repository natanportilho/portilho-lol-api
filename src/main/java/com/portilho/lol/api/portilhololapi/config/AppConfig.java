package com.portilho.lol.api.portilhololapi.config;

import com.portilho.lol.api.portilhololapi.converter.ChampionConverter;
import com.portilho.lol.api.portilhololapi.converter.ModelConverter;
import com.portilho.lol.api.portilhololapi.converter.UserConverter;
import com.portilho.lol.api.portilhololapi.facade.LolMatchesFacade;
import com.portilho.lol.api.portilhololapi.facade.MatchesFacade;
import com.portilho.lol.api.portilhololapi.service.champion.ChampionService;
import com.portilho.lol.api.portilhololapi.service.champion.LolChampionService;
import com.portilho.lol.api.portilhololapi.service.connection.ConnectionService;
import com.portilho.lol.api.portilhololapi.service.connection.LolConnectionService;
import com.portilho.lol.api.portilhololapi.service.match.LolMatchService;
import com.portilho.lol.api.portilhololapi.service.match.MatchService;
import com.portilho.lol.api.portilhololapi.service.user.LolUserService;
import com.portilho.lol.api.portilhololapi.service.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig
{
    @Bean
    public UserService userService() {
        return new LolUserService();
    }

    @Bean
    public ConnectionService connectionService() {
        return new LolConnectionService();
    }

    @Bean
    public MatchService matchService() {
        return new LolMatchService();
    }

    @Bean
    public MatchesFacade matchesFacade() {
        return new LolMatchesFacade();
    }

    @Bean
    public ModelConverter userConverter() {
        return new UserConverter();
    }

    @Bean
    public ModelConverter championConverter() {
        return new ChampionConverter();
    }

    @Bean
    public ChampionService championService() {
        return new LolChampionService();
    }
}