package nicolas.feith.simple_survey_tool_backend.core.model.surveys;

import java.util.UUID;

import nicolas.feith.simple_survey_tool_backend.core.ports.SurveyQuestionVisitor;

public  class SurveyTextQuestion extends SurveyQuestion {
    private String defaultText;
    private boolean isLongText; // Optional field to indicate if it's a long text question

    public SurveyTextQuestion(UUID id, String questionText, boolean isRequired, String defaultText) {
        super(id, questionText, isRequired);
        this.defaultText = defaultText;
    }

    public String getDefaultText() {
        return defaultText;
    }

    public boolean isLongText() {
        return isLongText;
    }

    public String validateAnswer(String answer) {
        if (this.isRequired() && (answer == null || answer.trim().isEmpty())) {
            throw new IllegalArgumentException("Answer is required.");
        }
        return answer;
    }

    @Override
    public <T> T accept(SurveyQuestionVisitor<T> visitor) {
        return visitor.visitTextQuestion(this);
    }    

}
