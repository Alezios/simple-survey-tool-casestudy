package nicolas.feith.simple_survey_tool_backend.repository.jpa.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import nicolas.feith.simple_survey_tool_backend.core.model.surveys.SurveyBooleanQuestion;
import nicolas.feith.simple_survey_tool_backend.core.model.surveys.SurveyQuestion;

@Entity
@DiscriminatorValue("BOOLEAN")
public class BooleanQuestionEntity extends SurveyQuestionEntity {

    @Column(name = "default_value")
    private Boolean defaultValue;

    public BooleanQuestionEntity() {
        // Required by JPA
    }
    
    public BooleanQuestionEntity(UUID id, String questionText, boolean isRequired, Boolean defaultValue) {
        super(id, questionText, isRequired);
        this.defaultValue = defaultValue;
    }

    public Boolean getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Boolean defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public SurveyQuestion toSurveyQuestion() {
        return new SurveyBooleanQuestion(this.getId(), this.getQuestionText(), this.isRequired(), this.getDefaultValue());
    }
}