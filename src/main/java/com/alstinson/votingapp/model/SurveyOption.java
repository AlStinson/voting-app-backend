package com.alstinson.votingapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldNameConstants
@Data
@Table(name = "survey_options")
public class SurveyOption {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;
    UUID survey;
    int number;
    String value;
}
