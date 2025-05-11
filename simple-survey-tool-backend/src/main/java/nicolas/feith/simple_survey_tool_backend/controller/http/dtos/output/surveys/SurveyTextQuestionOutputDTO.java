package nicolas.feith.simple_survey_tool_backend.controller.http.dtos.output.surveys;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SurveyTextQuestionOutputDTO extends SurveyQuestionOutputDTO {
    @JsonProperty("value")
    private final String value;

    public SurveyTextQuestionOutputDTO(UUID id, String questionText, boolean isRequired, String type, String value) {
        super(id, questionText, type, isRequired);
        this.value = value;
    }
}