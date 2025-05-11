package nicolas.feith.simple_survey_tool_backend.repository.jpa.utils;

import nicolas.feith.simple_survey_tool_backend.core.model.surveyresponses.SurveyResponse;
import nicolas.feith.simple_survey_tool_backend.core.model.surveys.Survey;
import nicolas.feith.simple_survey_tool_backend.core.model.surveys.SurveyQuestion;
import nicolas.feith.simple_survey_tool_backend.repository.jpa.entities.SurveyEntity;
import nicolas.feith.simple_survey_tool_backend.repository.jpa.entities.SurveyQuestionEntity;
import nicolas.feith.simple_survey_tool_backend.repository.jpa.entities.SurveyResponseEntity;

// Maps domain models to repository entities 
public class SurveyEntityMapper {
    
    /* private static final ObjectMapper objectMapper = new ObjectMapper(); */
    private static final RepositoryEntityExportQuestionVisitor repositoryEntityExportQuestionVisitor = new RepositoryEntityExportQuestionVisitor();
    
    public static SurveyEntity toSurveyEntity(Survey survey) {
        if (survey == null) return null;
        
        SurveyEntity entity = new SurveyEntity(
            survey.getId(),
            survey.getTitle(),
            survey.getDescription(),
            survey.getQuestions().stream()
                .map(SurveyEntityMapper::toSurveyQuestionEntity)
                .toList()
        );
        
        return entity;
    }
    
    public static SurveyQuestionEntity toSurveyQuestionEntity(SurveyQuestion question) {
        if (question == null) return null;
        return question.accept(repositoryEntityExportQuestionVisitor);
    }
    
    public static SurveyResponseEntity toSurveyResponseEntity(SurveyResponse response) {
        if (response == null) return null;

        SurveyEntity surveyEntity = toSurveyEntity(response.getFromSurvey());
        SurveyResponseEntity entity = new SurveyResponseEntity(
            surveyEntity,
            response.getAnsweredAt(),
            response.getQuestionAnswers()
        );
        
        return entity;
    }
}