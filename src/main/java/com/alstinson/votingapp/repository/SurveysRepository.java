package com.alstinson.votingapp.repository;

import com.alstinson.votingapp.model.Survey;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class SurveysRepository implements PanacheRepositoryBase<Survey, UUID> {

    public Optional<Survey> getByCode(String code) {
        return find(Survey.Fields.code, code).firstResultOptional();
    }

}
