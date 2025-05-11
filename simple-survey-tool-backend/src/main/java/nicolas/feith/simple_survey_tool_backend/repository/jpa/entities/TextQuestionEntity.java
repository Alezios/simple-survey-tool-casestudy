package nicolas.feith.simple_survey_tool_backend.repository.jpa.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import nicolas.feith.simple_survey_tool_backend.core.model.surveys.SurveyQuestion;
import nicolas.feith.simple_survey_tool_backend.core.model.surveys.SurveyTextQuestion;

@Entity
@DiscriminatorValue("TEXT")
public class TextQuestionEntity extends SurveyQuestionEntity {

    @Column(name = "default_text")
    private String defaultText;
    
    @Column(name = "is_long_text")
    private boolean isLongText;

    public TextQuestionEntity() {
        // Required by JPA
    }
    
    public TextQuestionEntity(UUID id, String questionText, boolean isRequired, String defaultText, boolean isLongText) {
        super(id, questionText, isRequired);
        this.defaultText = defaultText;
        this.isLongText = isLongText;
    }

    public String getDefaultText() {
        return defaultText;
    }

    public void setDefaultText(String defaultText) {
        this.defaultText = defaultText;
    }

    public boolean isLongText() {
        return isLongText;
    }

    public void setLongText(boolean isLongText) {
        this.isLongText = isLongText;
    }

    @Override
    public SurveyQuestion toSurveyQuestion() {
        return new SurveyTextQuestion(this.getId(), this.getQuestionText(), this.isRequired(), this.getDefaultText());
    }
}