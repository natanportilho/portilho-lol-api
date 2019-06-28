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
    private static final String PARTICIPANTS = "participants";
    private static final String GAME_ID = "gameId";
    private static final String GAME_MODE = "gameMode";
    private static final String TEAM_ID = "teamId";
    private static final String TIMELINE = "timeline";
    private static final String LANE = "lane";
    private static final String ROLE = "role";
    private static final String CHAMPION_ID = "championId";
    private static final String TEAMS = "teams";
    private static final String WIN = "win";
    private static final String PARTICIPANT_ID = "participantId";
    private static final String PARTICIPANT_IDENTITIES = "participantIdentities";
    private static final String ACCOUNT_ID = "accountId";
    private static final String PLAYER = "player";

    @Override
    public Object convert(Object obj)
    {
        JSONObject source = (JSONObject) obj;
        MatchModel match = new MatchModel();
        try
        {
            match.setMatchId(source.getString(GAME_ID));
            match.setGameMode(source.getString(GAME_MODE));
            ArrayList<ParticipantModel> participants = createRolesForTeams(source);
            match.setTeams(createTeamsForParticipants(participants, source));
        } catch (JSONException e)
        {
            throw new MatchException("Not able to convert into match.");
        }
        return match;
    }

    private ArrayList<ParticipantModel> createRolesForTeams(JSONObject source) throws JSONException
    {
        ArrayList<ParticipantModel> participants = new ArrayList<>();
        for (int i = 0; i < source.getJSONArray(PARTICIPANTS).length(); i++)
        {
            ParticipantModel participant = new ParticipantModel();
            participant.setParticipantId(source.getJSONArray(PARTICIPANTS).getJSONObject(i).get(PARTICIPANT_ID).toString());
            participant.setTeamId(source.getJSONArray(PARTICIPANTS).getJSONObject(i).get(TEAM_ID).toString());
            participant.setLane(source.getJSONArray(PARTICIPANTS).getJSONObject(i).getJSONObject(TIMELINE).get(LANE).toString());
            participant.setRole(source.getJSONArray(PARTICIPANTS).getJSONObject(i).getJSONObject(TIMELINE).get(ROLE).toString());
            participant.setChampion(source.getJSONArray(PARTICIPANTS).getJSONObject(i).get(CHAMPION_ID).toString());
            participant.setAccountId(source.getJSONArray(PARTICIPANT_IDENTITIES).getJSONObject(i).getJSONObject(PLAYER).get(ACCOUNT_ID).toString());
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
        String teamId = source.getJSONArray(TEAMS).getJSONObject(i).get(TEAM_ID).toString();
        String win = source.getJSONArray(TEAMS).getJSONObject(i).has(WIN) ? source.getJSONArray(TEAMS).getJSONObject(i).get(WIN).toString() : "loser";
        return createTeam(teamId, isWinnerTeam(win));
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
        return win.equalsIgnoreCase(WIN);
    }

}
