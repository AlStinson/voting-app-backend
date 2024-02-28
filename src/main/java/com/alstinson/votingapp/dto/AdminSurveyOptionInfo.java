package com.alstinson.votingapp.dto;

import com.alstinson.votingapp.model.SurveyOption;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class AdminSurveyOptionInfo {
    UUID id;
    int number;
    String value;
    long votes;

    public static List<AdminSurveyOptionInfo> fromModels(List<SurveyOption> options, List<UUID> responseOptionsIds) {
        return options.stream().map(option -> fromModel(option, responseOptionsIds)).toList();
    }

    private static AdminSurveyOptionInfo fromModel(SurveyOption surveyOption, List<UUID> responseOptionsIds) {
        return AdminSurveyOptionInfo.builder()
                .id(surveyOption.getId())
                .number(surveyOption.getNumber())
                .value(surveyOption.getValue())
                .votes(responseOptionsIds.stream().filter(surveyOption.getId()::equals).count())
                .build();
    }
}
