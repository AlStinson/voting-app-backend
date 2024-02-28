package com.alstinson.votingapp.repository;

import com.alstinson.votingapp.model.Code;
import com.alstinson.votingapp.model.Survey;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class CodesRepository implements PanacheRepositoryBase<Code, UUID> {
    public Optional<Code> getByValue(String value) {
        return find(Code.Fields.value, value).firstResultOptional();
    }
}
