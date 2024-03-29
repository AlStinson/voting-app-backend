package com.alstinson.votingapp.repository;

import com.alstinson.votingapp.model.SurveyOption;
import com.alstinson.votingapp.model.SurveyResponse;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class SurveyResponsesRepository implements PanacheRepositoryBase<SurveyResponse, UUID> {
    public List<SurveyResponse> getResponsesBySurveyId(UUID survey) {
        return find(SurveyResponse.Fields.survey, survey).list();
    }

    public List<SurveyResponse> getResponsesByCodeID(UUID code) {
        return find(SurveyResponse.Fields.code, code).list();
    }

    public Optional<SurveyResponse> getResponseBySurveyIdAndCodeId(UUID survey, UUID code) {
        return find(SurveyResponse.Fields.survey + " = ?1 AND " + SurveyResponse.Fields.code + " = ?2", survey, code).stream().findFirst();
    }

    public long deleteBySurveyId(UUID uuid) {
        return delete(SurveyResponse.Fields.survey, uuid);
    }
}
