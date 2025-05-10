package nicolas.feith.simple_survey_tool_backend.controller.http.dtos.output.surveys;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import nicolas.feith.simple_survey_tool_backend.controller.http.dtos.constants.SurveyQuestionDTOType;

public class SurveyBooleanQuestionOutputDTO extends SurveyQuestionOutputDTO {
    @JsonProperty("value")
    private final Boolean value;

    public SurveyBooleanQuestionOutputDTO(UUID id, String questionText, boolean isRequired, SurveyQuestionDTOType type, Boolean value) {
        super(id, questionText, type, isRequired);
        this.value = value;
    }
}
