package nicolas.feith.simple_survey_tool_backend.core.services.survey;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import nicolas.feith.simple_survey_tool_backend.core.exceptions.CouldNotSaveSurveyResponseException;
import nicolas.feith.simple_survey_tool_backend.core.exceptions.SurveyNotFoundException;
import nicolas.feith.simple_survey_tool_backend.core.model.surveyresponses.SurveyResponse;
import nicolas.feith.simple_survey_tool_backend.core.model.surveys.Survey;
import nicolas.feith.simple_survey_tool_backend.core.ports.SurveyRepository;

@Service
public class SurveyService {

    private final SurveyRepository surveyRepository;
    
    public SurveyService(
        final SurveyRepository surveyRepository
    ) {
        this.surveyRepository = surveyRepository;
    }

    public List<Survey> getAllSurveys() {
        return surveyRepository.getAllSurveys();
    }
    
    @NonNull // Just help IDE to understand that this method should never return null (thanks to exception handling)
    public Survey getSurveyById(UUID surveyId) {
        final Optional<Survey> survey = surveyRepository.getSurveyById(surveyId);
        if (survey.isPresent()) {
            return survey.get();
        } else {
            throw new SurveyNotFoundException("Survey not found with ID: " + surveyId);
        }
    }

    @NonNull
    public List<SurveyResponse> getSurveyResponses(UUID surveyId) {
        final List<SurveyResponse> surveyResponses = surveyRepository.getSurveyResponses(surveyId);
        if (surveyResponses == null) {
            throw new SurveyNotFoundException("Survey not found with ID: " + surveyId);
        } else {
            return surveyResponses;
        }
    }

    public void addSurveyResponse(UUID surveyId, Map<UUID, Object> questionAnswers) { 
        // memo: service function like this take 'raw' parameters for 2 reasons: 1. taking DTOs would break dependency inversion
        // 2. Taking a model object would break the separation of concerns (external caller would become responsible for creating the model object 
        // to pass, which is the responsibility of the domain)
        // Self improvement memo: this code could use a surveyresponse builder and more precise typing of questionAnswers
        final Survey survey = getSurveyById(surveyId);
        final SurveyResponse surveyResponse = new SurveyResponse(survey, null, questionAnswers);
        try {
            surveyRepository.saveSurveyResponse(surveyResponse);
        } catch (Exception e) {
            throw new CouldNotSaveSurveyResponseException("Could not save survey response: " + e.getMessage(), e);
        }
        
    }

    /* public void createSurvey(String title, String description) {

    }
    
    public void deleteSurvey(UUID surveyId) {

    } */
    
}
