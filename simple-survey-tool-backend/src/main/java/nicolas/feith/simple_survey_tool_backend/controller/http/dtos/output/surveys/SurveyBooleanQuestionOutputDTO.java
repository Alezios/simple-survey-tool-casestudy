package nicolas.feith.simple_survey_tool_backend.controller.http.dtos.output.surveys;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SurveyBooleanQuestionOutputDTO extends SurveyQuestionOutputDTO {
    @JsonProperty("defaultValue")
    private final Boolean value;

    public SurveyBooleanQuestionOutputDTO(UUID id, String questionText, boolean isRequired, String type, Boolean value) {
        super(id, questionText, type, isRequired);
        this.value = value;
    }
}
