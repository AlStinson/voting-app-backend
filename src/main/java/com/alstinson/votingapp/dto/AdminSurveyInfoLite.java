package com.alstinson.votingapp.dto;

import com.alstinson.votingapp.model.Survey;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminSurveyInfoLite {
    String code;
    String name;
    boolean active;

    public static AdminSurveyInfoLite fromModel(Survey survey) {
        return AdminSurveyInfoLite.builder()
                .code(survey.getCode())
                .name(survey.getName())
                .active(survey.isActive())
                .build();
    }
}
