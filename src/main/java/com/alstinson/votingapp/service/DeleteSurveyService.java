package com.alstinson.votingapp.service;

import com.alstinson.votingapp.model.Survey;
import com.alstinson.votingapp.repository.SurveyOptionsRepository;
import com.alstinson.votingapp.repository.SurveysRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@ApplicationScoped
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class DeleteSurveyService {

    SurveysRepository surveysRepository;
    SurveyOptionsRepository surveyOptionsRepository;

    @Transactional
    public void delete(String code) throws NotFoundException {
        Survey survey = surveysRepository.getByCode(code).orElseThrow(NotFoundException::new);
        surveyOptionsRepository.deleteBySurveyId(survey.getId());
        surveysRepository.delete(survey);
    }
}
