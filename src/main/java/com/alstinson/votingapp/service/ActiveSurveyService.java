package com.alstinson.votingapp.service;

import com.alstinson.votingapp.model.Survey;
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
public class ActiveSurveyService {
    SurveysRepository surveysRepository;

    @Transactional
    public void enable(String code) {
        setActive(code, true);
    }

    @Transactional
    public void disable(String code) {
        setActive(code, false);
    }

    private void setActive(String code, boolean active) {
        Survey survey = surveysRepository.getByCode(code).orElseThrow(NotFoundException::new);
        survey.setActive(active);
        surveysRepository.persist(survey);
    }
}
