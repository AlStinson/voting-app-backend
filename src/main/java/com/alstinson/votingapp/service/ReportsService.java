package com.alstinson.votingapp.service;

import com.alstinson.votingapp.dto.AdminSurveyInfo;
import com.alstinson.votingapp.model.Survey;
import com.alstinson.votingapp.model.SurveyOption;
import com.alstinson.votingapp.model.SurveyResponse;
import com.alstinson.votingapp.repository.SurveyOptionsRepository;
import com.alstinson.votingapp.repository.SurveyResponsesRepository;
import com.alstinson.votingapp.repository.SurveysRepository;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.alstinson.votingapp.utils.Group.group;
import static com.alstinson.votingapp.utils.Count.count;

@ApplicationScoped
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class ReportsService {

    SurveysRepository surveysRepository;
    SurveyOptionsRepository surveyOptionsRepository;
    SurveyResponsesRepository surveyResponsesRepository;

    public List<AdminSurveyInfo> getReports() {
        List<Survey> surveys = surveysRepository.listAll();
        Map<UUID, List<SurveyOption>> optionsBySurvey = group(surveyOptionsRepository.listAll()).by(SurveyOption::getSurvey);
        Map<UUID, Integer> votesByOption = count(surveyResponsesRepository.listAll()).by(SurveyResponse::getOption);
        return surveys.stream()
                .map(survey ->  AdminSurveyInfo.fromModels(survey, optionsBySurvey.get(survey.getId()), votesByOption))
                .toList();
    }

}
