package com.alstinson.votingapp.dto;

import com.alstinson.votingapp.model.Survey;
import com.alstinson.votingapp.model.SurveyOption;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class CreateSurvey {
    String name;
    String description;
    List<String> options;

    public Survey toSurveyModel(String code) {
        return Survey.builder()
                .code(code)
                .name(getName())
                .description(getDescription())
                .build();
    }

    public List<SurveyOption> toSurveyOptionModels(UUID surveyId) {
        List<SurveyOption> result = new ArrayList<>(getOptions().size());
        for (int i = 0; i < options.size(); i++) {
            result.add(SurveyOption.builder()
                    .survey(surveyId)
                    .value(getOptions().get(i))
                    .number(i)
                    .build());
        }
        return result;
    }
}
