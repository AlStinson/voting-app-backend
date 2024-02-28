package com.alstinson.votingapp.service;

import com.alstinson.votingapp.dto.CreateSurvey;
import com.alstinson.votingapp.dto.AdminSurveyInfo;
import com.alstinson.votingapp.model.Survey;
import com.alstinson.votingapp.model.SurveyOption;
import com.alstinson.votingapp.repository.SurveyOptionsRepository;
import com.alstinson.votingapp.repository.SurveysRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CreateSurveyService {
    SurveysRepository surveysRepository;
    SurveyOptionsRepository surveyOptionsRepository;

    @Transactional
    public AdminSurveyInfo apply(CreateSurvey createSurvey) {
        Survey survey = createSurvey.toSurveyModel(generateCode());
        surveysRepository.persist(survey);
        List<SurveyOption> options = createSurvey.toSurveyOptionModels(survey.getId());
        surveyOptionsRepository.persist(options);
        return AdminSurveyInfo.fromModels(survey, options, new ArrayList<>());
    }

    private String generateCode() {
        return RandomStringUtils.randomAlphanumeric(6).toUpperCase();
    }
}
