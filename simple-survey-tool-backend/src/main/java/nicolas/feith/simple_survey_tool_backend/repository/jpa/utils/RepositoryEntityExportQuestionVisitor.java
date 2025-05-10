package nicolas.feith.simple_survey_tool_backend.repository.jpa.utils;

import nicolas.feith.simple_survey_tool_backend.core.ports.SurveyQuestionVisitor;
import nicolas.feith.simple_survey_tool_backend.repository.jpa.entities.BooleanQuestionEntity;
import nicolas.feith.simple_survey_tool_backend.repository.jpa.entities.MultiSelectQuestionEntity;
import nicolas.feith.simple_survey_tool_backend.repository.jpa.entities.RatingQuestionEntity;
import nicolas.feith.simple_survey_tool_backend.repository.jpa.entities.SingleSelectQuestionEntity;
import nicolas.feith.simple_survey_tool_backend.repository.jpa.entities.SurveyQuestionEntity;
import nicolas.feith.simple_survey_tool_backend.repository.jpa.entities.TextQuestionEntity;
import nicolas.feith.simple_survey_tool_backend.core.model.surveys.SurveyTextQuestion;
import nicolas.feith.simple_survey_tool_backend.core.model.surveys.SurveySingleSelectQuestion;
import nicolas.feith.simple_survey_tool_backend.core.model.surveys.SurveyMultiSelectQuestion;
import nicolas.feith.simple_survey_tool_backend.core.model.surveys.SurveyRatingQuestion;
import nicolas.feith.simple_survey_tool_backend.core.model.surveys.SurveyBooleanQuestion;

public class RepositoryEntityExportQuestionVisitor implements SurveyQuestionVisitor<SurveyQuestionEntity> {
    @Override
    public SurveyQuestionEntity visitTextQuestion(SurveyTextQuestion question) {
        return new TextQuestionEntity(question.getId(), question.getQuestionText(), question.isRequired(),
                question.getDefaultText(), question.isLongText());
    }

    @Override
    public SurveyQuestionEntity visitSingleSelectQuestion(SurveySingleSelectQuestion question) {
        return new SingleSelectQuestionEntity(question.getId(), question.getQuestionText(), question.isRequired(),
                question.getOptions(), question.getDefaultSelectedOption());
    }

    @Override
    public SurveyQuestionEntity visitMultipleSelectQuestion(SurveyMultiSelectQuestion question) {
        return new MultiSelectQuestionEntity(question.getId(), question.getQuestionText(), question.isRequired(), question.getOptions(), question.getDefaultSelectedOptions());
    }

    @Override
    public SurveyQuestionEntity visitRatingQuestion(SurveyRatingQuestion question) {
        return new RatingQuestionEntity(question.getId(), question.getQuestionText(), question.isRequired(),
                question.getMinRating(), question.getMaxRating(), question.getMinRatingLabel(),
                question.getMaxRatingLabel(), question.getDefaultSelectedRating());
    }

    @Override
    public SurveyQuestionEntity visitBooleanQuestion(SurveyBooleanQuestion question) {
        return new BooleanQuestionEntity(question.getId(), question.getQuestionText(), question.isRequired(),
                question.getDefaultValue());
    }

}
