package com.alstinson.votingapp.dto;

import com.alstinson.votingapp.model.SurveyOption;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class VoterSurveyOptionInfo {
    int number;
    String value;
    boolean voted;

    public static List<VoterSurveyOptionInfo> fromModels(List<SurveyOption> options, UUID vote) {
        return options.stream().map(option -> fromModel(option, vote)).toList();
    }

    private static VoterSurveyOptionInfo fromModel(SurveyOption surveyOption, UUID vote) {
        return VoterSurveyOptionInfo.builder()
                .number(surveyOption.getNumber())
                .value(surveyOption.getValue())
                .voted(surveyOption.getId().equals(vote))
                .build();
    }
}
