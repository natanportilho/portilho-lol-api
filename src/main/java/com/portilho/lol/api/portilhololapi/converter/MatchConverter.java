package com.portilho.lol.api.portilhololapi.converter;

import com.portilho.lol.api.portilhololapi.exception.MatchException;
import com.portilho.lol.api.portilhololapi.model.ParticipantModel;
import com.portilho.lol.api.portilhololapi.model.match.MatchModel;
import com.portilho.lol.api.portilhololapi.model.match.TeamModel;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MatchConverter implements ModelConverter
{
    @Override
    public Object convert(JSONObject source)
    {
        MatchModel match = new MatchModel();
        try
        {
            match.setMatchId(source.getString("gameId"));
            match.setGameMode(source.getString("gameMode"));
            ArrayList<ParticipantModel> participants = createRolesForTeams(source);
            match.setTeams(createTeamsForParticipants(participants, source));
            return match;
        } catch (JSONException e)
        {
            throw new MatchException("Not able to convert into match.");
        }
    }

    private ArrayList<ParticipantModel> createRolesForTeams(JSONObject source) throws JSONException
    {
        ArrayList<ParticipantModel> participants = new ArrayList<>();
        for (int i = 0; i < source.getJSONArray("participants").length(); i++)
        {
            ParticipantModel participant = new ParticipantModel();
            participant.setTeamId(source.getJSONArray("participants").getJSONObject(i).get("teamId").toString());
            participant.setRole(source.getJSONArray("participants").getJSONObject(i).getJSONObject("timeline").get("role").toString());
            participant.setChampion(source.getJSONArray("participants").getJSONObject(i).get("championId").toString());
            participants.add(participant);
        }
        return participants;
    }

    private ArrayList<TeamModel> createTeamsForParticipants(ArrayList<ParticipantModel> participants, JSONObject source) throws JSONException
    {
        ArrayList<TeamModel> teams = new ArrayList<>();
        TeamModel teamA = createTeamFromJson(source, 0);
        TeamModel teamB = createTeamFromJson(source, 1);
        setTeamParticipants(participants, teams, teamA, teamB);
        return teams;
    }

    private void setTeamParticipants(ArrayList<ParticipantModel> participants, ArrayList<TeamModel> teams, TeamModel teamA, TeamModel teamB)
    {
        List<ParticipantModel> participantsForTeamA = getParticipantsForTeam(participants, teamA);
        List<ParticipantModel> participantsForTeamB = getParticipantsForTeam(participants, teamB);
        teamA.setParticipants(participantsForTeamA);
        teamB.setParticipants(participantsForTeamB);
        teams.add(teamA);
        teams.add(teamB);
    }

    private TeamModel createTeamFromJson(JSONObject source, int i) throws JSONException
    {
        return createTeam(source.getJSONArray("teams").getJSONObject(i).get("teamId").toString(),
                isWinnerTeam(source.getJSONArray("teams").getJSONObject(i).get("win").toString()));
    }

    private List<ParticipantModel> getParticipantsForTeam(ArrayList<ParticipantModel> participants, TeamModel team)
    {
        return participants.stream().filter(participantModel ->
                participantModel.getTeamId().equals(team.getTeamId())).collect(Collectors.toList());
    }

    private TeamModel createTeam(String teamId, boolean isWinner)
    {
        TeamModel team = new TeamModel();
        team.setTeamId(teamId);
        team.setWinner(isWinner);
        return team;
    }

    private boolean isWinnerTeam(String win)
    {
        return win.equals("Win");
    }
}
