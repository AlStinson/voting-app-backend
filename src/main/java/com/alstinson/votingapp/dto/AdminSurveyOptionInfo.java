package com.alstinson.votingapp.dto;

import com.alstinson.votingapp.model.SurveyOption;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
public class AdminSurveyOptionInfo {
    UUID id;
    int number;
    String value;
    long votes;

    public static List<AdminSurveyOptionInfo> fromModels(List<SurveyOption> options, Map<UUID, Integer> votesByOption) {
        return options.stream().map(option -> fromModel(option, votesByOption)).toList();
    }

    private static AdminSurveyOptionInfo fromModel(SurveyOption surveyOption, Map<UUID, Integer> votesByOption) {
        return AdminSurveyOptionInfo.builder()
                .id(surveyOption.getId())
                .number(surveyOption.getNumber())
                .value(surveyOption.getValue())
                .votes(votesByOption.getOrDefault(surveyOption.getId(), 0))
                .build();
    }
}
