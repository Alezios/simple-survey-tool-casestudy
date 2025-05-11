package nicolas.feith.simple_survey_tool_backend.core.model.surveys;

import java.util.Objects;
import java.util.UUID;

import nicolas.feith.simple_survey_tool_backend.core.ports.SurveyQuestionVisitor;

public abstract class SurveyQuestion {
    private UUID id;
    private String questionText;
    private boolean isRequired;

    protected SurveyQuestion(UUID id, String questionText, boolean isRequired) {
        if (id == null) {
            this.id = UUID.randomUUID(); // Generate a new UUID for the question if id is null
        } else {
            this.id = id;
        }
        this.questionText = Objects.requireNonNull(questionText, "Question text cannot be null");
        this.isRequired = isRequired;
    }

    public UUID getId() {
        return id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public abstract <R> R accept(SurveyQuestionVisitor<R> visitor);

}
