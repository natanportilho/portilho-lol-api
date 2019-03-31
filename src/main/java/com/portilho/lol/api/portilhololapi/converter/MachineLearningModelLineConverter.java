package com.portilho.lol.api.portilhololapi.converter;

import com.portilho.lol.api.portilhololapi.model.MachineLearningLineModel;
import com.portilho.lol.api.portilhololapi.model.ParticipantModel;
import com.portilho.lol.api.portilhololapi.model.match.MatchModel;

import java.util.ArrayList;
import java.util.Optional;

public class MachineLearningModelLineConverter implements ModelConverter
{
    @Override
    public Object convert(Object obj)
    {
        MatchModel source = (MatchModel) obj;
        MachineLearningLineModel machineLearningLineModel = new MachineLearningLineModel();

        machineLearningLineModel.setMatchId(source.getMatchId());

        ArrayList<ParticipantModel> participantsTeamA = (ArrayList<ParticipantModel>) source.getTeams().get(0).getParticipants();
        ArrayList<ParticipantModel> participantsTeamB = (ArrayList<ParticipantModel>) source.getTeams().get(1).getParticipants();

        setTeamRoles(participantsTeamA, machineLearningLineModel, "A");
        setTeamRoles(participantsTeamB, machineLearningLineModel, "B");


//        match_id,team_a_adc,team_a_support,team_a_mid,team_a_jungle,team_a_top,team_b_adc,team_b_support,team_b_mid,team_b_jungle,team_b_top,winner_team
//        MID, MIDDLE, TOP, JUNGLE, BOT, BOTTOM)
        return machineLearningLineModel;

    }

    private void setTeamRoles(ArrayList<ParticipantModel> participantsTeam, MachineLearningLineModel machineLearningLineModel, String teamName)
    {
        ParticipantModel teamAdc = getTeamParticipant(participantsTeam, "DUO_CARRY", "BOTTOM");
        ParticipantModel teamSupport = getTeamParticipant(participantsTeam, "DUO_SUPPORT", "BOTTOM");
        ParticipantModel teamMid = getTeamParticipant(participantsTeam, "MID", "NONE");
        ParticipantModel teamJungle = getTeamParticipant(participantsTeam, "JUNGLE", "NONE");
        ParticipantModel teamTop = getTeamParticipant(participantsTeam, "TOP", "NONE");

        if (teamName.equals("A")){
            machineLearningLineModel.setTeamAAdc(teamAdc.getChampion());
            machineLearningLineModel.setTeamASupport(teamSupport.getChampion());
            machineLearningLineModel.setTeamAMid(teamMid.getChampion());
            machineLearningLineModel.setTeamAJungle(teamJungle.getChampion());
            machineLearningLineModel.setTeamATop(teamTop.getChampion());
        }else {
            machineLearningLineModel.setTeamBAdc(teamAdc.getChampion());
            machineLearningLineModel.setTeamBSupport(teamSupport.getChampion());
            machineLearningLineModel.setTeamBMid(teamMid.getChampion());
            machineLearningLineModel.setTeamBJungle(teamJungle.getChampion());
            machineLearningLineModel.setTeamBTop(teamTop.getChampion());
        }
    }

    private ParticipantModel getTeamParticipant(ArrayList<ParticipantModel> participantsTeam, String duo_carry, String bottom)
    {
        ParticipantModel participant = getTeamParticipantByLane(participantsTeam, duo_carry, bottom).isPresent() ? getTeamParticipantByLane(participantsTeam, duo_carry, bottom).get() : createFakeParticipant();
        return participant;
    }

    private Optional<ParticipantModel> getTeamParticipantByLane(ArrayList<ParticipantModel> teamParticipants, String lane, String role){
        return teamParticipants.stream().filter(participant ->
                participant.getRole().equals(role)
                        && participant.getLane().equals(lane)).findFirst();
    }

    private ParticipantModel createFakeParticipant(){
        ParticipantModel participantModel = new ParticipantModel();
        participantModel.setChampion("xxx");
        participantModel.setRole("xxx");
        participantModel.setTeamId("xxx");
        participantModel.setLane("xxx");
        return participantModel;
    }
}
