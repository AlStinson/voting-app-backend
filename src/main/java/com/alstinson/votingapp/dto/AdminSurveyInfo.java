package com.alstinson.votingapp.dto;

import com.alstinson.votingapp.model.Survey;
import com.alstinson.votingapp.model.SurveyOption;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
public class AdminSurveyInfo {
    UUID id;
    String code;
    String name;
    String description;
    boolean active;
    List<AdminSurveyOptionInfo> options;

    public static AdminSurveyInfo fromModels(Survey survey, List<SurveyOption> options, Map<UUID, Integer> votesByOption) {
        return AdminSurveyInfo.builder()
                .id(survey.getId())
                .code(survey.getCode())
                .name(survey.getName())
                .description(survey.getDescription())
                .active(survey.isActive())
                .options(AdminSurveyOptionInfo.fromModels(options, votesByOption))
                .build();
    }
}
