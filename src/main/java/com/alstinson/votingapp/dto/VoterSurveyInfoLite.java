package com.alstinson.votingapp.dto;

import com.alstinson.votingapp.model.Survey;
import com.alstinson.votingapp.model.SurveyResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class VoterSurveyInfoLite {
    String code;
    String name;
    boolean voted;

    public static VoterSurveyInfoLite fromModel(Survey survey, List<SurveyResponse> votes) {
        return VoterSurveyInfoLite.builder()
                .code(survey.getCode())
                .name(survey.getName())
                .voted(votes.stream().filter(vote -> vote.getSurvey().equals(survey.getId())).findFirst().isPresent())
                .build();
    }
}
