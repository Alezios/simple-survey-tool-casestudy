package nicolas.feith.simple_survey_tool_backend.core.ports;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import nicolas.feith.simple_survey_tool_backend.core.model.surveyresponses.SurveyResponse;
import nicolas.feith.simple_survey_tool_backend.core.model.surveys.Survey;

public interface SurveyRepository {
    
    List<Survey> getAllSurveys();
    
    Optional<Survey> getSurveyById(UUID id);

    List<SurveyResponse> getSurveyResponses(UUID surveyId);
    
    void saveSurveyResponse(SurveyResponse surveyResponse); // No return type, failure is handled by exceptions
    
}