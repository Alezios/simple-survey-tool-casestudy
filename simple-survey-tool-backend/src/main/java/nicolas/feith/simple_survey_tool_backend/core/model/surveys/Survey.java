package nicolas.feith.simple_survey_tool_backend.core.model.surveys;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Survey {
    private UUID id;
    private String title;
    private String description;
    private List<SurveyQuestion> questions;

    public Survey(UUID id, String title, String description, List<SurveyQuestion> questions) {
        if (id == null) {
            this.id = UUID.randomUUID(); // Generate a new UUID for the survey if id is null
        } else {
            this.id = id;
        }
        this.id = Objects.requireNonNull(id, "Id cannot be null");
        this.title = Objects.requireNonNull(title, "Title cannot be null");
        this.description = description;
        this.questions = Objects.requireNonNull(questions, "Questions cannot be null");
        if (questions.size() == 0) {
            throw new IllegalArgumentException("Questions array cannot be empty, at least one question is required");
        }
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<SurveyQuestion> getQuestions() {
        return questions;
    }
    
}
