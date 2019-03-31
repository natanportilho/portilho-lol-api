package com.portilho.lol.api.portilhololapi.converter;

import com.portilho.lol.api.portilhololapi.exception.MachineLearningModelException;
import com.portilho.lol.api.portilhololapi.model.MachineLearningLineModel;
import com.portilho.lol.api.portilhololapi.model.ParticipantModel;
import com.portilho.lol.api.portilhololapi.model.match.MatchModel;
import com.portilho.lol.api.portilhololapi.model.match.TeamModel;

import java.util.ArrayList;
import java.util.Optional;

public class MachineLearningModelLineConverter implements ModelConverter
{
    @Override
    public Object convert(Object source)
    {
        MatchModel match = (MatchModel) source;
        MachineLearningLineModel machineLearningLineModel = new MachineLearningLineModel();
        machineLearningLineModel.setMatchId(match.getMatchId());

        ArrayList<ParticipantModel> participantsTeamA = getParticipantsForTeam(match, "100");
        ArrayList<ParticipantModel> participantsTeamB = getParticipantsForTeam(match, "200");
        setTeamRoles(participantsTeamA, machineLearningLineModel, "100");
        setTeamRoles(participantsTeamB, machineLearningLineModel, "200");

        setWinnerTeam(match, machineLearningLineModel);
        return machineLearningLineModel;
    }

    private ArrayList<ParticipantModel> getParticipantsForTeam(MatchModel source, String s)
    {
        Optional<TeamModel> team = source.getTeams().stream().filter(teamModel -> teamModel.getTeamId().equals(s)).findFirst();
        if (team.isPresent())
            return (ArrayList<ParticipantModel>) team.get().getParticipants();
        else
            throw new MachineLearningModelException("Not able to get team participants.");
    }

    private void setWinnerTeam(MatchModel source, MachineLearningLineModel machineLearningLineModel)
    {
        if (isWinner(source, 0))
            machineLearningLineModel.setWinnerTeam(source.getTeams().get(0).getTeamId());
        else if (isWinner(source, 1))
            machineLearningLineModel.setWinnerTeam(source.getTeams().get(1).getTeamId());
        else
            machineLearningLineModel.setWinnerTeam("no winner");
    }

    private boolean isWinner(MatchModel source, int index)
    {
        return source.getTeams().get(index).isWinner();
    }

    private void setTeamRoles(ArrayList<ParticipantModel> participantsTeam, MachineLearningLineModel machineLearningLineModel, String teamId)
    {
        if (teamId.equals("100"))
        {
            machineLearningLineModel.setTeamAPlayer1(participantsTeam.get(0).getChampion());
            machineLearningLineModel.setTeamAPlayer2(participantsTeam.get(1).getChampion());
            machineLearningLineModel.setTeamAPlayer3(participantsTeam.get(2).getChampion());
            machineLearningLineModel.setTeamAPlayer4(participantsTeam.get(3).getChampion());
            machineLearningLineModel.setTeamAPlayer5(participantsTeam.get(4).getChampion());
        } else
        {
            machineLearningLineModel.setTeamBPlayer1(participantsTeam.get(0).getChampion());
            machineLearningLineModel.setTeamBPlayer2(participantsTeam.get(1).getChampion());
            machineLearningLineModel.setTeamBPlayer3(participantsTeam.get(2).getChampion());
            machineLearningLineModel.setTeamBPlayer4(participantsTeam.get(3).getChampion());
            machineLearningLineModel.setTeamBPlayer5(participantsTeam.get(4).getChampion());
        }
    }
}
