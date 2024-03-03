package com.alstinson.votingapp.repository;

import com.alstinson.votingapp.model.SurveyOption;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class SurveyOptionsRepository implements PanacheRepositoryBase<SurveyOption, UUID> {
    public List<SurveyOption> getOptionsBySurveyId(UUID uuid) {
        return find(SurveyOption.Fields.survey, uuid).list();
    }

    public long deleteBySurveyId(UUID uuid) {
        return delete(SurveyOption.Fields.survey, uuid);
    }
}
