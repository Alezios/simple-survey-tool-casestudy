package nicolas.feith.simple_survey_tool_backend.repository.jpa;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import nicolas.feith.simple_survey_tool_backend.core.model.surveyresponses.SurveyResponse;
import nicolas.feith.simple_survey_tool_backend.core.model.surveys.Survey;
import nicolas.feith.simple_survey_tool_backend.core.ports.SurveyRepository;
import nicolas.feith.simple_survey_tool_backend.repository.jpa.entities.SurveyEntity;
import nicolas.feith.simple_survey_tool_backend.repository.jpa.entities.SurveyResponseEntity;
import nicolas.feith.simple_survey_tool_backend.repository.jpa.utils.SurveyEntityMapper;;

@Repository
@Transactional
public class SurveyRepositoryImpl implements SurveyRepository {

    private final SurveyJpaRepository surveyJpaRepository;
    private final SurveyResponseJpaRepository responseJpaRepository;
      public SurveyRepositoryImpl(SurveyJpaRepository surveyJpaRepository, SurveyResponseJpaRepository responseJpaRepository) {
        this.surveyJpaRepository = surveyJpaRepository;
        this.responseJpaRepository = responseJpaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Survey> getAllSurveys() {
        return surveyJpaRepository.findAll().stream()
                .map(SurveyEntity::toSurvey)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Survey> getSurveyById(UUID id) {
        return surveyJpaRepository.findById(id)
                .map(SurveyEntity::toSurvey);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SurveyResponse> getSurveyResponses(UUID surveyId) {
        return responseJpaRepository.findBySurveyId(surveyId).stream()
                .map(SurveyResponseEntity::toSurveyResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void saveSurveyResponse(SurveyResponse surveyResponse) {
        // First check if survey exists
        Optional<SurveyEntity> surveyEntityOpt = surveyJpaRepository.findById(surveyResponse.getFromSurveyId());
        if (surveyEntityOpt.isPresent()) {
            // Survey exists, proceed to save the response
            SurveyResponseEntity responseEntity = SurveyEntityMapper.toSurveyResponseEntity(surveyResponse);
            responseJpaRepository.save(responseEntity);
        } else {
            // Survey doesn't exist, don't save the response
            throw new EntityNotFoundException("Survey entity not found with ID " + surveyResponse.getFromSurveyId());
        }
    }
}