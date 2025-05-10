package nicolas.feith.simple_survey_tool_backend.controller.http.dtos.output.surveys;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import nicolas.feith.simple_survey_tool_backend.controller.http.dtos.constants.SurveyQuestionDTOType;

public class SurveySingleSelectQuestionOutputDTO extends SurveyQuestionOutputDTO {
    @JsonProperty("options")
    private final List<String> options;
    @JsonProperty("selectedOption")
    private final String selectedOption;

    public SurveySingleSelectQuestionOutputDTO(UUID id, String questionText, boolean isRequired, SurveyQuestionDTOType type, 
                                              List<String> options, String selectedOption) {
        super(id, questionText, type, isRequired);
        this.options = options != null ? List.copyOf(options) : options; // Immutable copy of the list
        this.selectedOption = selectedOption;
    }

}