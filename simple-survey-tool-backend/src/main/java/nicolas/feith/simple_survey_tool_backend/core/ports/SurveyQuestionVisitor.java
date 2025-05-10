package nicolas.feith.simple_survey_tool_backend.core.ports;

import nicolas.feith.simple_survey_tool_backend.core.model.surveys.SurveyBooleanQuestion;
import nicolas.feith.simple_survey_tool_backend.core.model.surveys.SurveyMultiSelectQuestion;
import nicolas.feith.simple_survey_tool_backend.core.model.surveys.SurveyRatingQuestion;
import nicolas.feith.simple_survey_tool_backend.core.model.surveys.SurveySingleSelectQuestion;
import nicolas.feith.simple_survey_tool_backend.core.model.surveys.SurveyTextQuestion;

public interface SurveyQuestionVisitor<T> {
    T visitTextQuestion(SurveyTextQuestion question);
    T visitSingleSelectQuestion(SurveySingleSelectQuestion question);
    T visitMultipleSelectQuestion(SurveyMultiSelectQuestion question);
    T visitRatingQuestion(SurveyRatingQuestion question);
    T visitBooleanQuestion(SurveyBooleanQuestion question);
}
