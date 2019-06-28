package com.portilho.lol.api.portilhololapi.constant;

public class Constant
{
    private Constant()
    {
    }

    public static final String LOL_MODEL = "lol-model";
    public static final String CSV = ".csv";
    public static final String CLASSIC = "CLASSIC";

    public class Request
    {
        public static final String SUMMONER_INFO_URL = "https://br1.api.riotgames.com/lol/summoner/v4/summoners/by-name/";
        public static final String GET = "GET";
        public static final String RIOT_TOKEN = "RGAPI-f5243e13-ed3d-469e-a172-eccdee0aa9a2";
        public static final String MATCHES_FOR_USER_URL = "https://br1.api.riotgames.com/lol/match/v4/matchlists/by-account/";
        public static final String CHAMPIONS_URL = "http://ddragon.leagueoflegends.com/cdn/9.5.1/data/en_US/champion.json";
        public static final String MATCH_BY_ID_URL = "https://br1.api.riotgames.com/lol/match/v4/matches/";
    }

}
