package nicolas.feith.simple_survey_tool_backend.controller.http.dtos.output.surveys;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class SurveyQuestionOutputDTO { 
    @JsonProperty("name")
    private final UUID id;
    @JsonProperty("title")
    private final String questionText;
    @JsonProperty("type")
    private final String type;
    @JsonProperty("isRequired")
    private final boolean isRequired;

    protected SurveyQuestionOutputDTO(UUID id, String questionText, String type, boolean isRequired) {
        this.id = id;
        this.questionText = questionText;
        this.type = type;
        this.isRequired = isRequired;
    }
}