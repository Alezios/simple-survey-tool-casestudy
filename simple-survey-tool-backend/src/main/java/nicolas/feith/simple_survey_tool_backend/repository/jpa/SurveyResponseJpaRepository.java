package nicolas.feith.simple_survey_tool_backend.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nicolas.feith.simple_survey_tool_backend.repository.jpa.entities.SurveyResponseEntity;

@Repository
public interface SurveyResponseJpaRepository extends JpaRepository<SurveyResponseEntity, Long> {
    
    List<SurveyResponseEntity> findBySurveyId(String surveyId);
}