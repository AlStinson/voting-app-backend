package com.alstinson.votingapp.service;

import com.alstinson.votingapp.dto.AdminSurveyInfo;
import com.alstinson.votingapp.dto.AdminSurveyInfoLite;
import com.alstinson.votingapp.dto.VoterSurveyInfo;
import com.alstinson.votingapp.dto.VoterSurveyInfoLite;
import com.alstinson.votingapp.model.Code;
import com.alstinson.votingapp.model.Survey;
import com.alstinson.votingapp.model.SurveyOption;
import com.alstinson.votingapp.model.SurveyResponse;
import com.alstinson.votingapp.repository.CodesRepository;
import com.alstinson.votingapp.repository.SurveyOptionsRepository;
import com.alstinson.votingapp.repository.SurveyResponsesRepository;
import com.alstinson.votingapp.repository.SurveysRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.alstinson.votingapp.utils.Count.count;

@ApplicationScoped
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class GetSurveyService {

    SurveysRepository surveysRepository;
    SurveyOptionsRepository surveyOptionsRepository;
    SurveyResponsesRepository surveyResponsesRepository;
    CodesRepository codesRepository;

    public List<AdminSurveyInfoLite> adminGetAll() {
        return surveysRepository.listAll().stream().map(AdminSurveyInfoLite::fromModel).toList();
    }

    public List<VoterSurveyInfoLite> getAll(String codeValue) {
        Code code = codesRepository.getByValue(codeValue).orElseThrow(NotFoundException::new);
        List<SurveyResponse> votes = surveyResponsesRepository.getResponsesByCodeID(code.getId());
        return surveysRepository.listAll().stream().filter(Survey::isActive).map(survey -> VoterSurveyInfoLite.fromModel(survey, votes)).toList();
    }

    public AdminSurveyInfo adminGet(String code) {
        Survey survey = surveysRepository.getByCode(code).orElseThrow(NotFoundException::new);
        List<SurveyOption> options = surveyOptionsRepository.getOptionsBySurveyId(survey.getId());
        Map<UUID, Integer> votesByOption = count(surveyResponsesRepository.getResponsesBySurveyId(survey.getId())).by(SurveyResponse::getOption);
        return AdminSurveyInfo.fromModels(survey, options, votesByOption);
    }

    public VoterSurveyInfo get(String surveyCode, String codeValue) throws NotFoundException {
        Code code = codesRepository.getByValue(codeValue).orElseThrow(NotFoundException::new);
        Survey survey = surveysRepository.getByCode(surveyCode).orElseThrow(NotFoundException::new);
        List<SurveyOption> options = surveyOptionsRepository.getOptionsBySurveyId(survey.getId());
        UUID votedOption = surveyResponsesRepository.getResponseBySurveyIdAndCodeId(survey.getId(), code.getId()).map(SurveyResponse::getOption).orElse(null);
        return VoterSurveyInfo.fromModels(survey, options, votedOption);
    }
}
