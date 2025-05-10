package nicolas.feith.simple_survey_tool_backend.controller.http.utils;

import java.util.List;

import nicolas.feith.simple_survey_tool_backend.core.model.surveyresponses.SurveyResponse;
import nicolas.feith.simple_survey_tool_backend.core.model.surveys.Survey;
import nicolas.feith.simple_survey_tool_backend.controller.http.dtos.output.surveyresponses.SurveyResponseOutputDTO;
import nicolas.feith.simple_survey_tool_backend.controller.http.dtos.output.surveyresponses.SurveyResponsesOutputDTO;
import nicolas.feith.simple_survey_tool_backend.controller.http.dtos.output.surveys.SurveyOutputDTO;

// Maps domain models to HTTP DTOs
public class SurveyDTOMapper {

    private static final HttpDTOExportQuestionVisitor httpDTOExportQuestionVisitor = new HttpDTOExportQuestionVisitor();

    public static List<SurveyOutputDTO> toSurveysOutput(List<Survey> surveys) {
        if (surveys == null) {
            return List.of();
        }
        return surveys.stream().map(SurveyDTOMapper::toSurveyOutput).toList();
    }

    public static SurveyOutputDTO toSurveyOutput(Survey survey) {
        if (survey == null) {
            return null;
        }
        return new SurveyOutputDTO(
            survey.getId(), survey.getTitle(), survey.getDescription(), survey.getQuestions().stream()
            .map(question -> question.accept(httpDTOExportQuestionVisitor))
            .toList()
        );
    }

    public static SurveyResponsesOutputDTO toSurveyResponsesOutput(Survey survey, List<SurveyResponse> surveyResponses) {
        if (survey == null || surveyResponses == null) { // empty survey responses should always be empty list, not null
            return null;
        }
        return new SurveyResponsesOutputDTO(survey.getId(), survey.getQuestions().stream()
            .map(question -> question.accept(httpDTOExportQuestionVisitor))
            .toList(), surveyResponses.stream()
            .map(SurveyDTOMapper::toSurveyResponseOutput).toList());
    }

    public static SurveyResponseOutputDTO toSurveyResponseOutput(SurveyResponse surveyResponse) {
        if (surveyResponse == null) {
            return null;
        }
        return new SurveyResponseOutputDTO(surveyResponse.getAnsweredAt(), surveyResponse.getQuestionAnswers());
    }
}
