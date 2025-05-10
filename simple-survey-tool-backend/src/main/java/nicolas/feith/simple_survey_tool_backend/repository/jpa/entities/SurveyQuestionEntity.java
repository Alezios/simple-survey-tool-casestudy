package nicolas.feith.simple_survey_tool_backend.repository.jpa.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import nicolas.feith.simple_survey_tool_backend.core.constants.SurveyQuestionType;
import nicolas.feith.simple_survey_tool_backend.core.model.surveys.SurveyQuestion;

@Entity
@Table(name = "survey_questions")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "question_type")
public abstract class SurveyQuestionEntity {

    @Id
    @Column(name = "id")
    private UUID id;
    
    @Column(name = "question_text", nullable = false)
    private String questionText;
    
    @Column(name = "is_required")
    private boolean isRequired;

    public SurveyQuestionEntity() {
        // Required by JPA
    }
    
    public SurveyQuestionEntity(UUID id, String questionText, boolean isRequired) {
        this.id = id;
        this.questionText = questionText;
        this.isRequired = isRequired;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public void setRequired(boolean isRequired) {
        this.isRequired = isRequired;
    }
    
    public abstract SurveyQuestionType getQuestionType();

    public abstract SurveyQuestion toSurveyQuestion();
}