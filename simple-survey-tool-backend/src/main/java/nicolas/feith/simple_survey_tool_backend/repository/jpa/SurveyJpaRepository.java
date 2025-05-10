package nicolas.feith.simple_survey_tool_backend.repository.jpa;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nicolas.feith.simple_survey_tool_backend.repository.jpa.entities.SurveyEntity;

@Repository
public interface SurveyJpaRepository extends JpaRepository<SurveyEntity, UUID> {
    // Spring Data JPA generates basic CRUD operations
}