package com.alstinson.votingapp.dto;

import com.alstinson.votingapp.model.Survey;
import com.alstinson.votingapp.model.SurveyOption;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class VoterSurveyInfo {
    String code;
    String name;
    String description;
    boolean active;
    List<VoterSurveyOptionInfo> options;

    public static VoterSurveyInfo fromModels(Survey survey, List<SurveyOption> options, UUID vote) {
        return VoterSurveyInfo.builder()
                .code(survey.getCode())
                .name(survey.getName())
                .description(survey.getDescription())
                .active(survey.isActive())
                .options(VoterSurveyOptionInfo.fromModels(options, vote))
                .build();
    }
}
