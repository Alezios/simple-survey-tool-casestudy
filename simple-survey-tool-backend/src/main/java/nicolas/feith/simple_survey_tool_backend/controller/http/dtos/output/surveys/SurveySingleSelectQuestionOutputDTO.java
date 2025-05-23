package nicolas.feith.simple_survey_tool_backend.controller.http.dtos.output.surveys;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SurveySingleSelectQuestionOutputDTO extends SurveyQuestionOutputDTO {
    @JsonProperty("choices")
    private final List<String> options;
    @JsonProperty("defaultValue")
    private final String selectedOption;

    public SurveySingleSelectQuestionOutputDTO(UUID id, String questionText, boolean isRequired, String type, 
                                              List<String> options, String selectedOption) {
        super(id, questionText, type, isRequired);
        this.options = options != null ? List.copyOf(options) : options; // Immutable copy of the list
        this.selectedOption = selectedOption;
    }

}