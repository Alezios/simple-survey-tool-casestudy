package nicolas.feith.simple_survey_tool_backend.repository.jpa.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import nicolas.feith.simple_survey_tool_backend.core.constants.SurveyQuestionType;
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

        /* for (Map.Entry<UUID, Object> entry : response.getQuestionAnswers().entrySet()) {
            UUID questionId = entry.getKey();
            Object value = entry.getValue();
            
            if (value != null) {
                SurveyQuestion question = findQuestionById(response.getFromSurvey(), questionId);
                if (question != null) {
                    String serializedAnswer = serializeAnswer(value, question.getType());
                    entity.addAnswer(questionId, serializedAnswer);
                }
            }
        } */
        
        return entity;
    }

    // Entity to Domain model conversions
    
    /* public static SurveyResponse toSurveyResponse(SurveyResponseEntity entity) {
        if (entity == null) return null;
        
        // Convert the string answers to the appropriate types based on question type
        Map<UUID, Object> answers = new HashMap<>();
        Survey survey = entity.getSurvey().toSurvey();
        
        for (SurveyQuestion question : survey.getQuestions()) {
            String answerStr = entity.getAnswers().get(question.getId().toString());
            if (answerStr != null) {
                answers.put(question.getId(), deserializeAnswer(answerStr, question.getType()));
            }
        }
        
        return new SurveyResponse(survey, entity.getAnsweredAt(), answers);
    } */
    
    // Helper methods
    
    /* private static SurveyQuestion findQuestionById(Survey survey, UUID questionId) {
        for (SurveyQuestion question : survey.getQuestions()) {
            if (question.getId().equals(questionId)) {
                return question;
            }
        }
        return null;
    }
    
    private static String serializeAnswer(Object answer, SurveyQuestionType questionType) {
        // Convert the answer to a string representation based on the question type
        if (answer == null) return null;
        
        try {
            switch (questionType) {
                case BOOLEAN:
                    return answer.toString();
                case SHORT_TEXT:
                case LONG_TEXT:
                    return (String) answer;
                case RATING:
                    return answer.toString();
                case SINGLE_CHOICE:
                    return (String) answer;
                case MULTIPLE_CHOICE:
                    @SuppressWarnings("unchecked")
                    List<String> multipleChoices = (List<String>) answer;
                    return JsonHelper.toJson(multipleChoices);
                default:
                    return JsonHelper.toJson(answer);
            }
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Invalid answer type for question type: " + questionType, e);
        }
    }
    
    private static Object deserializeAnswer(String answerStr, SurveyQuestionType questionType) {
        if (answerStr == null) return null;
        
        try {
            switch (questionType) {
                case BOOLEAN:
                    return Boolean.parseBoolean(answerStr);
                case SHORT_TEXT:
                case LONG_TEXT:
                    return answerStr;
                case RATING:
                    return Integer.parseInt(answerStr);
                case SINGLE_CHOICE:
                    return answerStr;
                case MULTIPLE_CHOICE:
                    return objectMapper.readValue(answerStr, new TypeReference<List<String>>() {});
                default:
                    return JsonHelper.jsonToMap(answerStr);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Error deserializing answer for question type: " + questionType, e);
        }
    } */
}