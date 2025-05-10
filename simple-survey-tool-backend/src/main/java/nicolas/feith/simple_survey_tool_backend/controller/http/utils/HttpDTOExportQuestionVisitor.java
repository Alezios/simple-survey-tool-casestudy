package nicolas.feith.simple_survey_tool_backend.controller.http.utils;

import nicolas.feith.simple_survey_tool_backend.core.model.surveys.SurveyBooleanQuestion;
import nicolas.feith.simple_survey_tool_backend.core.model.surveys.SurveyMultiSelectQuestion;
import nicolas.feith.simple_survey_tool_backend.core.model.surveys.SurveyRatingQuestion;
import nicolas.feith.simple_survey_tool_backend.core.model.surveys.SurveySingleSelectQuestion;
import nicolas.feith.simple_survey_tool_backend.core.model.surveys.SurveyTextQuestion;
import nicolas.feith.simple_survey_tool_backend.core.ports.SurveyQuestionVisitor;
import nicolas.feith.simple_survey_tool_backend.controller.http.dtos.constants.SurveyQuestionDTOType;
import nicolas.feith.simple_survey_tool_backend.controller.http.dtos.output.surveys.SurveyBooleanQuestionOutputDTO;
import nicolas.feith.simple_survey_tool_backend.controller.http.dtos.output.surveys.SurveyMultiSelectQuestionOutputDTO;
import nicolas.feith.simple_survey_tool_backend.controller.http.dtos.output.surveys.SurveyQuestionOutputDTO;
import nicolas.feith.simple_survey_tool_backend.controller.http.dtos.output.surveys.SurveyRatingQuestionOutputDTO;
import nicolas.feith.simple_survey_tool_backend.controller.http.dtos.output.surveys.SurveySingleSelectQuestionOutputDTO;
import nicolas.feith.simple_survey_tool_backend.controller.http.dtos.output.surveys.SurveyTextQuestionOutputDTO;

public class HttpDTOExportQuestionVisitor implements SurveyQuestionVisitor<SurveyQuestionOutputDTO> {
    @Override
    public SurveyQuestionOutputDTO visitTextQuestion(SurveyTextQuestion question) {
        return new SurveyTextQuestionOutputDTO(
                question.getId(), question.getQuestionText(), question.isRequired(),
                question.isLongText() ? SurveyQuestionDTOType.COMMENT : SurveyQuestionDTOType.TEXT,
                question.getDefaultText());
    }

    @Override
    public SurveyQuestionOutputDTO visitSingleSelectQuestion(SurveySingleSelectQuestion question) {
        return new SurveySingleSelectQuestionOutputDTO(
                question.getId(),
                question.getQuestionText(),
                question.isRequired(),
                SurveyQuestionDTOType.SINGLE_CHOICE,
                question.getOptions(),
                question.getDefaultSelectedOption());
    }

    @Override
    public SurveyQuestionOutputDTO visitMultipleSelectQuestion(SurveyMultiSelectQuestion question) {
        return new SurveyMultiSelectQuestionOutputDTO(
                question.getId(),
                question.getQuestionText(),
                question.isRequired(),
                SurveyQuestionDTOType.MULTIPLE_CHOICE,
                question.getOptions(),
                question.getDefaultSelectedOptions());
    }

    @Override
    public SurveyQuestionOutputDTO visitRatingQuestion(SurveyRatingQuestion question) {
        return new SurveyRatingQuestionOutputDTO(
                question.getId(),
                question.getQuestionText(),
                question.isRequired(),
                SurveyQuestionDTOType.RATING,
                question.getMinRating(),
                question.getMaxRating(),
                question.getMinRatingLabel(),
                question.getMaxRatingLabel(),
                question.getDefaultSelectedRating());
    }

    @Override
    public SurveyQuestionOutputDTO visitBooleanQuestion(SurveyBooleanQuestion question) {
        return new SurveyBooleanQuestionOutputDTO(
                question.getId(),
                question.getQuestionText(),
                question.isRequired(),
                SurveyQuestionDTOType.BOOLEAN,
                question.getDefaultValue());
    }

}
