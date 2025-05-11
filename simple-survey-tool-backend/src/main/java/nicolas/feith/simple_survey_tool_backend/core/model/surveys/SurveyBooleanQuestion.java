package nicolas.feith.simple_survey_tool_backend.core.model.surveys;

import java.util.UUID;

import nicolas.feith.simple_survey_tool_backend.core.ports.SurveyQuestionVisitor;

public class SurveyBooleanQuestion extends SurveyQuestion {
    private Boolean defaultValue; // Boolean instead of boolean to allow null values

    public SurveyBooleanQuestion(UUID id, String questionText, boolean isRequired, Boolean defaultValue) {
        super(id, questionText, isRequired);
        this.defaultValue = defaultValue;
    }

    public Boolean getDefaultValue() {
        return defaultValue;
    }

    public Boolean validateAnswer(Boolean answer) {
        if (this.isRequired() && answer == null) {
            throw new IllegalArgumentException("Answer is required.");
        }
        return answer;
    }
    
    @Override
    public <T> T accept(SurveyQuestionVisitor<T> visitor) {
        return visitor.visitBooleanQuestion(this);
    }
}
