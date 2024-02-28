package com.alstinson.votingapp.service;

import com.alstinson.votingapp.model.Code;
import com.alstinson.votingapp.model.Survey;
import com.alstinson.votingapp.model.SurveyOption;
import com.alstinson.votingapp.model.SurveyResponse;
import com.alstinson.votingapp.repository.CodesRepository;
import com.alstinson.votingapp.repository.SurveyOptionsRepository;
import com.alstinson.votingapp.repository.SurveyResponsesRepository;
import com.alstinson.votingapp.repository.SurveysRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@ApplicationScoped
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class VoteService {

    SurveysRepository surveysRepository;
    SurveyOptionsRepository surveyOptionsRepository;
    CodesRepository codesRepository;
    SurveyResponsesRepository surveyResponsesRepository;

    @Transactional
    public void vote(String surveyCode, int optionNumber, String codeValue) {
        Survey survey = surveysRepository.getByCode(surveyCode).orElseThrow(NotFoundException::new);
        Code code = codesRepository.getByValue(codeValue).orElseThrow(NotFoundException::new);

        if (!survey.isActive()) throw new ClientErrorException(Response.Status.FORBIDDEN);
        if (surveyResponsesRepository.getResponseBySurveyIdAndCodeId(survey.getId(), code.getId()).isPresent())
            throw new ClientErrorException(Response.Status.CONFLICT);

        SurveyOption option = surveyOptionsRepository.getOptionsBySurveyId(survey.getId()).stream().filter(opt -> optionNumber == opt.getNumber()).findFirst().orElseThrow(NotFoundException::new);
        surveyResponsesRepository.persist(SurveyResponse.builder().survey(survey.getId()).code(code.getId()).option(option.getId()).build());
    }

}
