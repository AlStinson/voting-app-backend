package com.alstinson.votingapp.service;

import com.alstinson.votingapp.model.Code;
import com.alstinson.votingapp.repository.CodesRepository;
import com.alstinson.votingapp.repository.SurveyResponsesRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;
import java.util.stream.IntStream;

@ApplicationScoped
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CodeService {

    CodesRepository codesRepository;
    SurveyResponsesRepository surveyResponsesRepository;

    public List<String> getAll() {
        return codesRepository.listAll().stream().map(Code::getValue).toList();
    }

    @Transactional
    public List<String> generate(int quantity) {
        List<String> existingCodes = codesRepository.listAll()
                .stream()
                .map(Code::getValue)
                .toList();
        List<String> codes = IntStream.range(0, quantity)
                .mapToObj(__ -> generateCode())
                .distinct()
                .filter(code -> !existingCodes.contains(code))
                .toList();
        if (codes.size() < quantity / 2) throw new ArithmeticException();
        codesRepository.persist(codes.stream().map(this::toModel));
        return codes;
    }

    @Transactional
    public void deleteAll() {
        surveyResponsesRepository.deleteAll();
        codesRepository.deleteAll();
    }

    private String generateCode() {
        return RandomStringUtils.randomNumeric(6).toUpperCase();
    }

    private Code toModel(String value) {
        return Code.builder().value(value).build();
    }
}
