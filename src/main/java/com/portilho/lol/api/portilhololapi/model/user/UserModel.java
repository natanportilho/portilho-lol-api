package com.portilho.lol.api.portilhololapi.model.user;

public class UserModel
{
    private String id;
    private String accountId;
    private String puuid;
    private String name;
    private float profileIconId;
    private float revisionDate;
    private float summonerLevel;

    public String getId()
    {
        return id;
    }

    public String getAccountId()
    {
        return accountId;
    }

    public String getPuuid()
    {
        return puuid;
    }

    public String getName()
    {
        return name;
    }

    public float getProfileIconId()
    {
        return profileIconId;
    }

    public float getRevisionDate()
    {
        return revisionDate;
    }

    public float getSummonerLevel()
    {
        return summonerLevel;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public void setAccountId(String accountId)
    {
        this.accountId = accountId;
    }

    public void setPuuid(String puuid)
    {
        this.puuid = puuid;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setProfileIconId(float profileIconId)
    {
        this.profileIconId = profileIconId;
    }

    public void setRevisionDate(float revisionDate)
    {
        this.revisionDate = revisionDate;
    }

    public void setSummonerLevel(float summonerLevel)
    {
        this.summonerLevel = summonerLevel;
    }
}
