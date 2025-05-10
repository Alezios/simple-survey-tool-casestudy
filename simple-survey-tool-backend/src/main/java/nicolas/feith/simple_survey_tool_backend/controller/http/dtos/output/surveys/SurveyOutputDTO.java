package nicolas.feith.simple_survey_tool_backend.controller.http.dtos.output.surveys;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SurveyOutputDTO {
    @JsonProperty("id")
    private final UUID id;
    @JsonProperty("title")
    private final String title;
    @JsonProperty("description")
    private final String description;
    @JsonProperty("questions")
    private final List<SurveyQuestionOutputDTO> questions;

    public SurveyOutputDTO(UUID id, String title, String description, List<SurveyQuestionOutputDTO> questions) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.questions = questions != null ? List.copyOf(questions) : questions; //Immutable copy of the list
    }
}
